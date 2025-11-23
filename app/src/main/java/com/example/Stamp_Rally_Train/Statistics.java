package com.example.Stamp_Rally_Train;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class Statistics extends Fragment implements SrtListAdapter.OnTripDeleteListener {


    private Button btnAddSrtTrip, btnShowSrtDistance, btnShowSrtTime, btnViewSrtData, btnShowSrtFare;
    private BarChart barChart;
    private Database dbHelper;
    private SrtListAdapter srtListAdapter;


    private ArrayAdapter<String> trainAdapter;
    private ArrayAdapter<SrtData.StationInfo> startStationAdapter;
    private ArrayAdapter<SrtData.StationInfo> endStationAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.design_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper = new Database(getContext());

        // 버튼 연결
        btnAddSrtTrip = view.findViewById(R.id.btn_add_srt_trip);
        btnShowSrtDistance = view.findViewById(R.id.btn_show_srt_distance);
        btnShowSrtTime = view.findViewById(R.id.btn_show_srt_time);
        btnShowSrtFare = view.findViewById(R.id.btn_show_srt_fare);
        btnViewSrtData = view.findViewById(R.id.btn_view_srt_data);

        // 차트 연결
        barChart = view.findViewById(R.id.bar_chart_stats);
        setupBarChart();

        // 버튼 리스너 연결
        btnAddSrtTrip.setOnClickListener(v -> showAddSrtTripDialog());
        btnViewSrtData.setOnClickListener(v -> showTripListDialog());

        // 통계 버튼 리스너
        View.OnClickListener statsClickListener = this::loadBarChartData;
        btnShowSrtDistance.setOnClickListener(statsClickListener);
        btnShowSrtTime.setOnClickListener(statsClickListener);
        btnShowSrtFare.setOnClickListener(statsClickListener);
    }

    //팝업창 메소드
    private void showAddSrtTripDialog() {
        final Dialog dialog = new Dialog(getContext(),com.google.android.material.R.style.Theme_Material3_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_add_srt_info);

        DatePicker datePicker = dialog.findViewById(R.id.date_picker_srt_trip);
        Spinner spinnerTrainNum = dialog.findViewById(R.id.spinner_train_number);
        Spinner spinnerStartStation = dialog.findViewById(R.id.spinner_start_station);
        Spinner spinnerEndStation = dialog.findViewById(R.id.spinner_end_station);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel_trip);
        Button btnSave = dialog.findViewById(R.id.btn_save_trip);

        final TextView tvStartTime = dialog.findViewById(R.id.tv_start_station_time);
        final TextView tvEndTime = dialog.findViewById(R.id.tv_end_station_time);
        final CheckBox checkboxFirstClass = dialog.findViewById(R.id.checkbox_first_class);

        trainAdapter = new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_item);
        trainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrainNum.setAdapter(trainAdapter);

        startStationAdapter = new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_item);
        startStationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartStation.setAdapter(startStationAdapter);

        endStationAdapter = new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_item);
        endStationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEndStation.setAdapter(endStationAdapter);

        //날짜 부분
        Calendar calendar = Calendar.getInstance();
        datePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                (view, year, monthOfYear, dayOfMonth) -> {
                    updateTrainSpinner(year, monthOfYear, dayOfMonth, tvStartTime, tvEndTime);
                }
        );

        // 오늘 날짜로 업데이트
        updateTrainSpinner(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                tvStartTime, tvEndTime
        );

        // 열차 번호 스피너 부분
        spinnerTrainNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTrain = (String) parent.getItemAtPosition(position);
                List<SrtData.StationInfo> stations = SrtData.getStationsForTrain(selectedTrain);

                // 출발역 리스트
                startStationAdapter.clear();
                startStationAdapter.addAll(stations);
                startStationAdapter.notifyDataSetChanged();

                // 도착역 리스트
                if (!stations.isEmpty()) {
                    SrtData.StationInfo defaultStartStation = stations.get(0);
                    tvStartTime.setText(defaultStartStation.arrivalTime);

                    // 도착역 리스트는 출발역 이전의 역은 나오지 않도록 함
                    int startPosition = 0;
                    int totalStations = stations.size();
                    List<SrtData.StationInfo> filteredEndStations = new ArrayList<>();
                    for (int i = startPosition + 1; i < totalStations; i++) {
                        filteredEndStations.add(stations.get(i));
                    }

                    endStationAdapter.clear();
                    endStationAdapter.addAll(filteredEndStations);
                    endStationAdapter.notifyDataSetChanged();

                    // 도착 시간 출력
                    if (!filteredEndStations.isEmpty()) {
                        tvEndTime.setText(filteredEndStations.get(0).arrivalTime);
                    } else {
                        tvEndTime.setText("--:--");
                    }
                } else {
                    tvStartTime.setText("--:--");
                    tvEndTime.setText("--:--");
                    endStationAdapter.clear();
                    endStationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                startStationAdapter.clear();
                startStationAdapter.notifyDataSetChanged();
                endStationAdapter.clear();
                endStationAdapter.notifyDataSetChanged();
                tvStartTime.setText("--:--");
                tvEndTime.setText("--:--");
            }
        });

        // 출발역 스피너
        spinnerStartStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SrtData.StationInfo selectedStart = startStationAdapter.getItem(position);
                if (selectedStart != null) {
                    tvStartTime.setText(selectedStart.arrivalTime);
                }

                // 도착역 스피너 필터링
                int totalStations = startStationAdapter.getCount();
                List<SrtData.StationInfo> filteredEndStations = new ArrayList<>();
                for (int i = position + 1; i < totalStations; i++) {
                    filteredEndStations.add(startStationAdapter.getItem(i));
                }

                endStationAdapter.clear();
                endStationAdapter.addAll(filteredEndStations);
                endStationAdapter.notifyDataSetChanged();

                // 도착시간 업데이트
                if (!filteredEndStations.isEmpty()) {
                    tvEndTime.setText(filteredEndStations.get(0).arrivalTime);
                } else {
                    tvEndTime.setText("--:--");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                endStationAdapter.clear();
                endStationAdapter.notifyDataSetChanged();
                tvStartTime.setText("--:--");
                tvEndTime.setText("--:--");
            }
        });

        // 도착역 스피너 부분
        spinnerEndStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 도착시간 업데이트
                SrtData.StationInfo selectedEnd = endStationAdapter.getItem(position);
                if (selectedEnd != null) {
                    tvEndTime.setText(selectedEnd.arrivalTime);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvEndTime.setText("--:--");
            }
        });


        // 저장 버튼
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            String dateStr = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, day);

            String trainNum = (String) spinnerTrainNum.getSelectedItem();
            SrtData.StationInfo startStation = (SrtData.StationInfo) spinnerStartStation.getSelectedItem();
            SrtData.StationInfo endStation = (SrtData.StationInfo) spinnerEndStation.getSelectedItem();

            //열차 번호, 역 정보가 없을 시
            if (trainNum == null || startStation == null || endStation == null) {
                Toast.makeText(getContext(), "선택된 열차나 역 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 시간, 거리 계산
            int[] stats = SrtData.calculateTravelStats(startStation, endStation);
            int travelTime = stats[0];
            int travelDist = stats[1];

            //역 순서가 바뀔 시
            if (travelTime <= 0 || travelDist <= 0) {
                Toast.makeText(getContext(), "선택한 열차 번호와 역 정보가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                return;
            }

            // 요금
            boolean isFirstClass = checkboxFirstClass.isChecked();

            // 요금이 없거나 오류 발생시
            int travelFare = SrtFareData.getFare(startStation.stationName, endStation.stationName, isFirstClass);
            if (travelFare == -1) {
                Toast.makeText(getContext(), "요금 정보를 찾을 수 없습니다.", Toast.LENGTH_LONG).show();
                return;
            }

            // 성공
            saveTripToDb(dateStr, trainNum, startStation.stationName, endStation.stationName, travelTime, travelDist, travelFare);
            Toast.makeText(getContext(), "탑승 기록이 저장되었습니다.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
        });

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
    }

    //금토일만 운행하는 열차가 있으므로 이를 위한 메소드
    private void updateTrainSpinner(int year, int month, int day, TextView tvStartTime, TextView tvEndTime) {
        // 선택된 날짜로 요일 계산
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 요일에 따라 DayType 결정
        SrtData.DayType filterType;
        if (dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            filterType = SrtData.DayType.WEEKEND_ONLY;
        } else {
            filterType = SrtData.DayType.DAILY;
        }

        // 필터링된 열차 번호 목록 가져오기
        List<String> filteredTrainNumbers = SrtData.getFilteredTrainNumbers(filterType);

        // 열차 번호 스피너 업데이트
        trainAdapter.clear();
        trainAdapter.addAll(filteredTrainNumbers);
        trainAdapter.notifyDataSetChanged();

        // 역 스피너 업데이트
        if (!filteredTrainNumbers.isEmpty()) {
            String firstTrain = filteredTrainNumbers.get(0);
            List<SrtData.StationInfo> stations = SrtData.getStationsForTrain(firstTrain);

            startStationAdapter.clear();
            startStationAdapter.addAll(stations);
            startStationAdapter.notifyDataSetChanged();
            tvStartTime.setText(stations.get(0).arrivalTime);

            int startPosition = 0;
            int totalStations = stations.size();
            List<SrtData.StationInfo> filteredEndStations = new ArrayList<>();
            for (int i = startPosition + 1; i < totalStations; i++) {
                filteredEndStations.add(stations.get(i));
            }
            endStationAdapter.clear();
            endStationAdapter.addAll(filteredEndStations);
            endStationAdapter.notifyDataSetChanged();
            if (!filteredEndStations.isEmpty()) {
                tvEndTime.setText(filteredEndStations.get(0).arrivalTime);
            } else {
                tvEndTime.setText("--:--");
            }
        } else {
            startStationAdapter.clear();
            startStationAdapter.notifyDataSetChanged();
            endStationAdapter.clear();
            endStationAdapter.notifyDataSetChanged();
            tvStartTime.setText("--:--");
            tvEndTime.setText("--:--");
        }
    }


   //'입력한 데이터 보기' 팝업창
    private void showTripListDialog() {
        final Dialog dialog = new Dialog(getContext(),com.google.android.material.R.style.Theme_Material3_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.saved_info_list);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler_view_trips);
        Button btnClose = dialog.findViewById(R.id.btn_close_dialog);

        List<Srt> tripList = dbHelper.getAllSrtTrips();
        srtListAdapter = new SrtListAdapter(tripList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(srtListAdapter);

        btnClose.setOnClickListener(v -> dialog.dismiss());

        if (dialog.getWindow() != null) {
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.95);
            int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.80);
            dialog.getWindow().setLayout(width, height);
        }
        dialog.show();
    }
    @Override
    public void onTripDelete(Srt trip) {
        dbHelper.deleteTripById(trip.getId());
        srtListAdapter.removeItem(trip);
        Toast.makeText(getContext(), "기록이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }


    //db 저장 메소드
    private void saveTripToDb(String date, String trainNum, String startStation, String endStation,
                              long travelTimeMin, long travelDistanceKm, int travelFare) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.SrtUserTripsEntry.COLUMN_NAME_DATE, date);
        values.put(Database.SrtUserTripsEntry.COLUMN_NAME_TRAIN_NUM, trainNum);
        values.put(Database.SrtUserTripsEntry.COLUMN_NAME_START_STATION, startStation);
        values.put(Database.SrtUserTripsEntry.COLUMN_NAME_END_STATION, endStation);
        values.put(Database.SrtUserTripsEntry.COLUMN_NAME_TRAVEL_TIME_MIN, travelTimeMin);
        values.put(Database.SrtUserTripsEntry.COLUMN_NAME_TRAVEL_DISTANCE_KM, travelDistanceKm);
        values.put(Database.SrtUserTripsEntry.COLUMN_NAME_TRAVEL_FARE, travelFare);
        db.insert(Database.SrtUserTripsEntry.TABLE_NAME, null, values);
        db.close();
    }


    //----차트 메소드---------
    private void setupBarChart() {
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);
        barChart.setFitBars(true);
        barChart.setExtraBottomOffset(10f);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(12f);

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setTextSize(12f);
        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getLegend().setEnabled(false);

        barChart.setNoDataText("버튼을 눌러 통계를 확인하세요.");
        barChart.setNoDataTextColor(Color.BLACK);
    }
    private void setCustomBarImage(int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        if(bitmap==null)
            return;
        ImageChart customRenderer = new ImageChart(
                barChart,
                barChart.getAnimator(),
                barChart.getViewPortHandler(),
                bitmap
        );
        barChart.setRenderer(customRenderer);
        customRenderer.initBuffers();
    }
    private void loadBarChartData(View v) {
        Map<String, long[]> statsMap = dbHelper.getMonthlyStatsGrouped();

        if (statsMap.isEmpty()) {
            barChart.clear();
            barChart.setNoDataText("기록된 데이터가 없습니다.");
            barChart.invalidate();
            return;
        }

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        int viewId = v.getId();
        int imageResId = 0;
        String label = "";
        int color = Color.BLACK;


        if (viewId == R.id.btn_show_srt_distance) {
            imageResId = R.drawable.rail;
            label = "월별 이동 거리 (km)";
            color = Color.rgb(0, 150, 136);
        } else if (viewId == R.id.btn_show_srt_time) {
            imageResId = R.drawable.train;
            label = "월별 이동 시간 (분)";
            color = Color.rgb(255, 152, 0);
        } else if (viewId == R.id.btn_show_srt_fare) {
            imageResId = R.drawable.money;
            label = "월별 내가 쓴 돈 (원)";
            color = Color.rgb(211, 47, 47);
        }

        int i = 0;
        for (Map.Entry<String, long[]> entry : statsMap.entrySet()) {
            String monthLabel = entry.getKey();
            long totalTime = entry.getValue()[0];
            long totalDist = entry.getValue()[1];
            long totalFare = entry.getValue()[2];

            if (viewId == R.id.btn_show_srt_distance) {
                entries.add(new BarEntry(i, totalDist));
            } else if (viewId == R.id.btn_show_srt_time) {
                entries.add(new BarEntry(i, totalTime));
            } else if (viewId == R.id.btn_show_srt_fare) {
                entries.add(new BarEntry(i, totalFare));
            }

            labels.add(monthLabel.substring(2));
            i++;
        }

        BarDataSet dataSet = new BarDataSet(entries, label);
        dataSet.setColor(color);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.7f);

        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < labels.size()) {
                    String originalLabel = labels.get(index);
                    try {
                        String[] parts = originalLabel.split("-");
                        if (parts.length == 2) {
                            return parts[0] + "년 " + Integer.parseInt(parts[1]) + "월";
                        } else {
                            return originalLabel;
                        }
                    } catch (Exception e) {
                        return originalLabel;
                    }
                }
                return "";
            }
        });

        barChart.setData(barData);
        if(imageResId!=0)
            setCustomBarImage(imageResId);
        barChart.animateY(1000);
        barChart.invalidate();
    }
}