package com.example.Stamp_Rally_Train;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Stamp extends Fragment {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private static final double TARGET_RADIUS_METERS = 100.0;
    private static final long LOCATION_UPDATE_INTERVAL = 5000;
    private static final long FASTEST_LOCATION_UPDATE_INTERVAL = 2000;
    private static final int RESIZED_IMAGE_WIDTH = 1080;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private ActivityResultLauncher<Uri> takePictureLauncher;
    private Uri pendingImageUri;
    private Station pendingStationForCamera;
    private Database dbHelper;

    private static class Station {
        String name;
        double latitude;
        double longitude;
        TextView textView;
        int viewId;
        Status status;
        String savedImagePath;
        enum Status { NOT_ARRIVED, ARRIVED, COLLECTED }
        Station(String name, double latitude, double longitude, int viewId) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.viewId = viewId;
            this.status = Status.NOT_ARRIVED;
            this.savedImagePath = null;
        }
    }
    private Map<Integer, Station> stationMap = new HashMap<>();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dbHelper = new Database(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.design_stamp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                this::onFullPictureTaken
        );
        initializeStations(view);
        createLocationCallback();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPermissionAndStartUpdates();
    }
    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close(); // 프래그먼트 종료 시 DB 헬퍼 닫기
        }
    }

    //fragment_stamp.xml과 연결, 좌표 데이터 입력
    private void initializeStations(View view) {
        Map<String, String> collectedStamps = loadCollectedStampsFromDb();
        Station suseo = new Station("과기대", 37.635311, 127.075657, R.id.stamp_suseo);
        //Station suseo = new Station("수서", 37.485539, 127.104436, R.id.stamp_suseo);
        Station dongtan = new Station("동탄", 37.199317, 127.097006, R.id.stamp_dongtan);
        Station pyeongtaek = new Station("평택지제", 37.018771, 127.069925, R.id.stamp_pyeongtaek);
        Station cheonan = new Station("천안아산", 36.79391, 127.10445, R.id.stamp_cheonan);
        Station osong = new Station("오송", 36.620557, 127.327377, R.id.stamp_osong);
        Station dajeon = new Station("대전", 36.332510, 127.434200, R.id.stamp_daejeon);
        Station gimcheon = new Station("김천구미", 36.113482, 128.180991, R.id.stamp_gimcheon);
        Station seodaegu = new Station("서대구", 35.891304, 128.539901, R.id.stamp_seodaegu);
        Station dongdaegu = new Station("동대구", 35.879787, 128.628623, R.id.stamp_dongdaegu);
        Station gyeongju = new Station("경주", 35.79876574, 129.1387516, R.id.stamp_gyeongju);
        Station ulsan = new Station("울산", 35.5515794, 129.1385479, R.id.stamp_ulsan );
        Station busan = new Station("부산", 35.114495, 129.039330, R.id.stamp_busan);
        Station gongju = new Station("공주", 36.332361, 127.096778, R.id.stamp_gongju);
        Station iksan = new Station("익산", 35.939400, 126.946773, R.id.stamp_iksan);
        Station jeongeup = new Station("정읍", 35.575556, 126.8425, R.id.stamp_jeongeup);
        Station gwangju = new Station("광주송정", 35.14, 126.79, R.id.stamp_gwangju );
        Station naju = new Station("나주", 35.01, 126.18, R.id.stamp_naju);
        Station mokpo = new Station("목포", 34.79, 126.39, R.id.stamp_mokpo);
        Station milyang = new Station("밀양", 35.4744457, 128.7771466, R.id.stamp_milyang);
        Station jinyeong = new Station("진영", 35.298602, 128.7740326, R.id.stamp_jinyeong);
        Station changwonjungang = new Station("창원중앙", 35.14, 128.42, R.id.stamp_changwonjungang);
        Station changwon = new Station("창원", 35.15, 128.36, R.id.stamp_changwon );
        Station masan = new Station("마산", 35.14, 128.34, R.id.stamp_masan);
        Station jinju = new Station("진주", 35.15, 128.11, R.id.stamp_jinju);
        Station pohang = new Station("포항", 36.071865, 129.341983, R.id.stamp_pohang);
        Station jeonju = new Station("전주", 35.849772, 127.161845, R.id.stamp_jeonju);
        Station namwon = new Station("남원", 35.411284, 127.361398, R.id.stamp_namwon);
        Station gokseong = new Station("곡성", 35.28, 127.3, R.id.stamp_gokseong);
        Station gurye = new Station("구례구", 35, 127, R.id.stamp_gurye);
        Station suncheon = new Station("순천", 34.95, 127.5, R.id.stamp_suncheon);
        Station yeocheon = new Station("여천", 34.78, 127.66, R.id.stamp_yeocheon);
        Station yeosu = new Station("여수", 34.75, 127.75, R.id.stamp_yeosu);

        stationMap.put(suseo.viewId, suseo);
        stationMap.put(dongtan.viewId, dongtan);
        stationMap.put(pyeongtaek.viewId, pyeongtaek);
        stationMap.put(cheonan.viewId, cheonan);
        stationMap.put(osong.viewId, osong);
        stationMap.put(dajeon.viewId, dajeon);
        stationMap.put(gimcheon.viewId, gimcheon);
        stationMap.put(seodaegu.viewId, seodaegu);
        stationMap.put(dongdaegu.viewId, dongdaegu);
        stationMap.put(gyeongju.viewId, gyeongju);
        stationMap.put(ulsan.viewId, ulsan);
        stationMap.put(busan.viewId, busan);
        stationMap.put(gongju.viewId, gongju);
        stationMap.put(iksan.viewId, iksan );
        stationMap.put(jeongeup.viewId, jeongeup);
        stationMap.put(gwangju.viewId, gwangju);
        stationMap.put(naju.viewId, naju);
        stationMap.put(mokpo.viewId, mokpo);
        stationMap.put(milyang.viewId, milyang);
        stationMap.put(jinyeong.viewId, jinyeong );
        stationMap.put(changwonjungang.viewId, changwonjungang);
        stationMap.put(changwon.viewId, changwon);
        stationMap.put(masan.viewId, masan);
        stationMap.put(jinju.viewId, jinju);
        stationMap.put(pohang.viewId, pohang);
        stationMap.put(jeonju.viewId, jeonju);
        stationMap.put(namwon.viewId, namwon);
        stationMap.put(gokseong.viewId, gokseong);
        stationMap.put(gurye.viewId, gurye);
        stationMap.put(suncheon.viewId, suncheon);
        stationMap.put(yeocheon.viewId, yeocheon);
        stationMap.put(yeosu.viewId, yeosu);

        for (Station station : stationMap.values()) {
            station.textView = view.findViewById(station.viewId);

            if (station.textView == null) {
                continue;
            }

            // ID를 찾은 경우에만
            String imagePath = collectedStamps.get(station.name);
            if (imagePath != null) {
                station.status = Station.Status.COLLECTED;
                station.savedImagePath = imagePath;
            }
            updateStampView(station);
            station.textView.setOnClickListener(stampClickListener);
            station.textView.setOnLongClickListener(stampLongClickListener);
        }
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location lastLocation = locationResult.getLastLocation();
                if (lastLocation != null) {
                    checkAllStationDistances(lastLocation);
                }
            }
        };
    }

    // 권한 및 위치
    private void checkPermissionAndStartUpdates() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                Toast.makeText(getContext(), "위치 권한이 거부되었습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, LOCATION_UPDATE_INTERVAL)
                .setMinUpdateIntervalMillis(FASTEST_LOCATION_UPDATE_INTERVAL)
                .build();
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    private void stopLocationUpdates() {
        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

     private void checkAllStationDistances(Location currentLocation) {
        for (Station station : stationMap.values()) {
            if (station.status == Station.Status.COLLECTED) {
                continue;
            }
            Location stationLocation = new Location("Station");
            stationLocation.setLatitude(station.latitude);
            stationLocation.setLongitude(station.longitude);
            float distance = currentLocation.distanceTo(stationLocation);
            if (distance <= TARGET_RADIUS_METERS) {
                if (station.status == Station.Status.NOT_ARRIVED) {
                    station.status = Station.Status.ARRIVED;
                    updateStampView(station);
                }
            } else {
                if (station.status == Station.Status.ARRIVED) {
                    station.status = Station.Status.NOT_ARRIVED;
                    updateStampView(station);
                }
            }
        }
    }

    private final View.OnClickListener stampClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Station clickedStation = stationMap.get(v.getId());
            if (clickedStation == null) return;
            switch (clickedStation.status) {
                case NOT_ARRIVED:
                    Toast.makeText(getContext(), "아직 " + clickedStation.name + "역에 도착하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case ARRIVED:
                    pendingStationForCamera = clickedStation;
                    pendingImageUri = createTempImageUri();
                    if (pendingImageUri != null) {
                        takePictureLauncher.launch(pendingImageUri);
                    } else {
                        Toast.makeText(getContext(), "카메라 실행을 위한 임시 파일을 만들지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case COLLECTED:
                    showImageDialog(clickedStation.savedImagePath);
                    break;
            }
        }
    };

    private final View.OnLongClickListener stampLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Station clickedStation = stationMap.get(v.getId());
            if (clickedStation == null || clickedStation.status != Station.Status.COLLECTED) {
                return false;
            }
            showDeleteConfirmDialog(clickedStation);
            return true;
        }
    };

    //--------스탬프 삭제
    private void showDeleteConfirmDialog(Station station) {
        new AlertDialog.Builder(getContext())
                .setTitle(station.name + " 스탬프 삭제")
                .setMessage("정말로 이 스탬프와 사진을 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    deleteStamp(station);
                })
                .setNegativeButton("취소", (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteStamp(Station station) {
        if (station.savedImagePath != null) {
            File file = new File(requireContext().getFilesDir(), station.savedImagePath);
            if (file.exists()) {
                file.delete();
            }
        }
        deleteStampFromDb(station.name);
        station.status = Station.Status.NOT_ARRIVED;
        station.savedImagePath = null;
        updateStampView(station);
        Toast.makeText(getContext(), station.name + " 스탬프가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }

    //스탬프 사진 기능
    private void onFullPictureTaken(Boolean success) {
        if (success && pendingStationForCamera != null && pendingImageUri != null) {
            try {
                InputStream inputStream = requireContext().getContentResolver().openInputStream(pendingImageUri);
                Bitmap fullBitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                inputStream = requireContext().getContentResolver().openInputStream(pendingImageUri);
                fullBitmap = getCorrectlyOrientedBitmap(fullBitmap, inputStream);
                inputStream.close();

                Bitmap resizedBitmap = resizeBitmap(fullBitmap, RESIZED_IMAGE_WIDTH);

                String filename = "stamp_" + pendingStationForCamera.name + ".jpg";
                boolean saveSuccess = saveBitmapToInternalStorage(resizedBitmap, filename);

                if (saveSuccess) {
                    pendingStationForCamera.status = Station.Status.COLLECTED;
                    pendingStationForCamera.savedImagePath = filename;
                    updateStampView(pendingStationForCamera);
                    saveStampStateToDb(pendingStationForCamera.name, filename);
                    Toast.makeText(getContext(), pendingStationForCamera.name + " 스탬프 획득!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "사진 저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
                requireContext().getContentResolver().delete(pendingImageUri, null, null);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "사진 처리 중 오류 발생", Toast.LENGTH_SHORT).show();
            }
        } else if (pendingStationForCamera != null) {
            Toast.makeText(getContext(), "사진 촬영이 취소되었습니다.", Toast.LENGTH_SHORT).show();
        }
        pendingStationForCamera = null;
        pendingImageUri = null;
    }


    private void showImageDialog(String filename) {
        if (filename == null || filename.isEmpty()) {
            Toast.makeText(getContext(), "표시할 사진이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = loadBitmapFromInternalStorage(filename);
        if (bitmap != null) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setAdjustViewBounds(true);
            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.setContentView(imageView, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            try {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int displayWidth = displayMetrics.widthPixels;
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = (int) (displayWidth * 0.9f);
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
            imageView.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
        } else {
            Toast.makeText(getContext(), "사진을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateStampView(Station station) {
        if (station == null || station.textView == null) return;
        switch (station.status) {
            case NOT_ARRIVED:
                station.textView.setText(station.name + "\n(미도착)");
                station.textView.setBackgroundColor(Color.parseColor("#F0F0F0"));
                station.textView.setTextColor(Color.parseColor("#333333"));
                break;
            case ARRIVED:
                station.textView.setText(station.name + "\n(도착!)");
                station.textView.setBackgroundColor(Color.parseColor("#C8E6C9"));
                station.textView.setTextColor(Color.parseColor("#2E7D32"));
                break;
            case COLLECTED:
                station.textView.setText(station.name + "\n(사진 보기)");
                station.textView.setBackgroundColor(Color.parseColor("#FFCDD2"));
                station.textView.setTextColor(Color.parseColor("#D32F2F"));
                break;
        }
    }

    private Bitmap getCorrectlyOrientedBitmap(Bitmap bitmap, InputStream inputStream) throws Exception {
        ExifInterface exifInterface = new ExifInterface(inputStream);
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                break;
            default:
                return bitmap;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int targetWidth) {
        if (bitmap.getWidth() <= targetWidth) {
            return bitmap;
        }
        int targetHeight = (int) (bitmap.getHeight() * (float) targetWidth / (float) bitmap.getWidth());
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }

    private Uri createTempImageUri() {
        try {
            File storageDir = requireContext().getCacheDir();
            File tempFile = File.createTempFile("temp_stamp_", ".jpg", storageDir);
            return FileProvider.getUriForFile(requireContext(),
                    "com.example.Stamp_Rally_Train.fileprovider",
                    tempFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean saveBitmapToInternalStorage(Bitmap bitmap, String filename) {
        try (FileOutputStream fos = requireContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Bitmap loadBitmapFromInternalStorage(String filename) {
        try (FileInputStream fis = requireContext().openFileInput(filename)) {
            return BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


     private void saveStampStateToDb(String stationName, String imagePath) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.StampEntry.COLUMN_NAME_STATION, stationName);
        values.put(Database.StampEntry.COLUMN_NAME_IMAGE_PATH, imagePath);
        db.insertWithOnConflict(Database.StampEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    private void deleteStampFromDb(String stationName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = Database.StampEntry.COLUMN_NAME_STATION + " = ?";
        String[] selectionArgs = { stationName };
        db.delete(Database.StampEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    private Map<String, String> loadCollectedStampsFromDb() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                Database.StampEntry.COLUMN_NAME_STATION,
                Database.StampEntry.COLUMN_NAME_IMAGE_PATH
        };
        Cursor cursor = db.query(
                Database.StampEntry.TABLE_NAME,
                projection, null, null, null, null, null);

        Map<String, String> collectedStamps = new HashMap<>();
        while (cursor.moveToNext()) {
            String stationName = cursor.getString(cursor.getColumnIndexOrThrow(Database.StampEntry.COLUMN_NAME_STATION));
            String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(Database.StampEntry.COLUMN_NAME_IMAGE_PATH));
            collectedStamps.put(stationName, imagePath);
        }
        cursor.close();
        return collectedStamps;
    }

}