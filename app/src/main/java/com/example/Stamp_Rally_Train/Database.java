package com.example.Stamp_Rally_Train;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Stamps.db";

    public static class StampEntry implements BaseColumns {
        public static final String TABLE_NAME = "stamps";
        public static final String COLUMN_NAME_STATION = "station_name";
        public static final String COLUMN_NAME_IMAGE_PATH = "image_path";
    }

    private static final String SQL_CREATE_STAMPS_ENTRIES =
            "CREATE TABLE " + StampEntry.TABLE_NAME + " (" +
                    StampEntry._ID + " INTEGER PRIMARY KEY," +
                    StampEntry.COLUMN_NAME_STATION + " TEXT UNIQUE," +
                    StampEntry.COLUMN_NAME_IMAGE_PATH + " TEXT)";

    private static final String SQL_DELETE_STAMPS_ENTRIES =
            "DROP TABLE IF EXISTS " + StampEntry.TABLE_NAME;

    public static class SrtUserTripsEntry implements BaseColumns {
        public static final String TABLE_NAME = "srt_user_trips";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TRAIN_NUM = "train_num";
        public static final String COLUMN_NAME_START_STATION = "start_station";
        public static final String COLUMN_NAME_END_STATION = "end_station";
        public static final String COLUMN_NAME_TRAVEL_TIME_MIN = "travel_time_min";
        public static final String COLUMN_NAME_TRAVEL_DISTANCE_KM = "travel_distance_km";
        public static final String COLUMN_NAME_TRAVEL_FARE = "travel_fare_krw";
    }

    private static final String SQL_CREATE_SRT_TRIPS_ENTRIES =
            "CREATE TABLE " + SrtUserTripsEntry.TABLE_NAME + " (" +
                    SrtUserTripsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SrtUserTripsEntry.COLUMN_NAME_DATE + " TEXT," +
                    SrtUserTripsEntry.COLUMN_NAME_TRAIN_NUM + " TEXT," +
                    SrtUserTripsEntry.COLUMN_NAME_START_STATION + " TEXT," +
                    SrtUserTripsEntry.COLUMN_NAME_END_STATION + " TEXT," +
                    SrtUserTripsEntry.COLUMN_NAME_TRAVEL_TIME_MIN + " INTEGER," +
                    SrtUserTripsEntry.COLUMN_NAME_TRAVEL_DISTANCE_KM + " INTEGER," +
                    SrtUserTripsEntry.COLUMN_NAME_TRAVEL_FARE + " INTEGER)";

    private static final String SQL_DELETE_SRT_TRIPS_ENTRIES =
            "DROP TABLE IF EXISTS " + SrtUserTripsEntry.TABLE_NAME;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STAMPS_ENTRIES);
        db.execSQL(SQL_CREATE_SRT_TRIPS_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            db.execSQL(SQL_CREATE_SRT_TRIPS_ENTRIES);
        }
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE " + SrtUserTripsEntry.TABLE_NAME +
                    " ADD COLUMN " + SrtUserTripsEntry.COLUMN_NAME_TRAVEL_FARE + " INTEGER DEFAULT 0");
        }
    }

    public Map<String, long[]> getMonthlyStatsGrouped() {
        Map<String, long[]> monthlyStats = new LinkedHashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // [수정] SUM(요금) 추가
        String query = "SELECT " +
                "strftime('%Y-%m', " + SrtUserTripsEntry.COLUMN_NAME_DATE + ") as month, " +
                "SUM(" + SrtUserTripsEntry.COLUMN_NAME_TRAVEL_TIME_MIN + "), " +
                "SUM(" + SrtUserTripsEntry.COLUMN_NAME_TRAVEL_DISTANCE_KM + "), " +
                "SUM(" + SrtUserTripsEntry.COLUMN_NAME_TRAVEL_FARE + ") " +
                "FROM " + SrtUserTripsEntry.TABLE_NAME + " " +
                "GROUP BY month " +
                "ORDER BY month ASC";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String month = cursor.getString(0);
            long totalTime = cursor.getLong(1);
            long totalDist = cursor.getLong(2);
            long totalFare = cursor.getLong(3);

            monthlyStats.put(month, new long[]{totalTime, totalDist, totalFare});
        }
        cursor.close();
        db.close();
        return monthlyStats;
    }

    public List<Srt> getAllSrtTrips() {
        List<Srt> tripList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                SrtUserTripsEntry._ID,
                SrtUserTripsEntry.COLUMN_NAME_DATE,
                SrtUserTripsEntry.COLUMN_NAME_TRAIN_NUM,
                SrtUserTripsEntry.COLUMN_NAME_START_STATION,
                SrtUserTripsEntry.COLUMN_NAME_END_STATION,
                SrtUserTripsEntry.COLUMN_NAME_TRAVEL_TIME_MIN,
                SrtUserTripsEntry.COLUMN_NAME_TRAVEL_DISTANCE_KM,
                SrtUserTripsEntry.COLUMN_NAME_TRAVEL_FARE
        };
        String sortOrder = SrtUserTripsEntry.COLUMN_NAME_DATE + " DESC";

        Cursor cursor = db.query(
                SrtUserTripsEntry.TABLE_NAME,
                columns,
                null, null, null, null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(SrtUserTripsEntry._ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(SrtUserTripsEntry.COLUMN_NAME_DATE));
            String trainNum = cursor.getString(cursor.getColumnIndexOrThrow(SrtUserTripsEntry.COLUMN_NAME_TRAIN_NUM));
            String start = cursor.getString(cursor.getColumnIndexOrThrow(SrtUserTripsEntry.COLUMN_NAME_START_STATION));
            String end = cursor.getString(cursor.getColumnIndexOrThrow(SrtUserTripsEntry.COLUMN_NAME_END_STATION));
            long time = cursor.getLong(cursor.getColumnIndexOrThrow(SrtUserTripsEntry.COLUMN_NAME_TRAVEL_TIME_MIN));
            long dist = cursor.getLong(cursor.getColumnIndexOrThrow(SrtUserTripsEntry.COLUMN_NAME_TRAVEL_DISTANCE_KM));
            int fare = cursor.getInt(cursor.getColumnIndexOrThrow(SrtUserTripsEntry.COLUMN_NAME_TRAVEL_FARE));

            tripList.add(new Srt(id, date, trainNum, start, end, time, dist, fare));
        }
        cursor.close();
        db.close();
        return tripList;
    }
    public void deleteTripById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = SrtUserTripsEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete(SrtUserTripsEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }
}