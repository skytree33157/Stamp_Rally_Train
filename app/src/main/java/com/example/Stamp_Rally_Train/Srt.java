package com.example.Stamp_Rally_Train;

import androidx.annotation.NonNull;
import java.util.Locale;
public class Srt {
    private final int id;
    private final String date; // 날짜
    private final String trainNum; // 열차번호
    private final String startStation; // 출발역
    private final String endStation; // 도착역
    private final long travelTimeMin; // 이동 시간
    private final long travelDistanceKm; // 이동 거리
    private final int travelFare; // 요금

    public Srt(int id, String date, String trainNum, String startStation, String endStation, long travelTimeMin, long travelDistanceKm, int travelFare) {
        this.id = id;
        this.date = date;
        this.trainNum = trainNum;
        this.startStation = startStation;
        this.endStation = endStation;
        this.travelTimeMin = travelTimeMin;
        this.travelDistanceKm = travelDistanceKm;
        this.travelFare = travelFare;
    }

    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getTrainNum() {
        return trainNum;
    }
    public String getStartStation() {
        return startStation;
    }
    public String getEndStation() {
        return endStation;
    }
    public long getTravelTimeMin() {
        return travelTimeMin;
    }
    public long getTravelDistanceKm() {
        return travelDistanceKm;
    }
    public int getTravelFare() {
        return travelFare;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s (#%s)", date, trainNum);
    }
    public String getRouteString() {
        return String.format(Locale.getDefault(), "%s → %s (%dkm, %d분, %,d원)",
                startStation, endStation, travelDistanceKm, travelTimeMin, travelFare);
    }
}