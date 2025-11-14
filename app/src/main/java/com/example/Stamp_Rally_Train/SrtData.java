package com.example.Stamp_Rally_Train;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SrtData {
    public enum DayType {
        DAILY, // 매일
        WEEKEND_ONLY // 금토일만
    }
    public static class StationInfo {
        String stationName; // 역 이름
        String arrivalTime; // 도착 시각
        int distanceKm;     // 수서역 기준 누적 거리

        StationInfo(String stationName, String arrivalTime, int distanceKm) {
            this.stationName = stationName;
            this.arrivalTime = arrivalTime;
            this.distanceKm = distanceKm;
        }

        @NonNull
        @Override
        public String toString() {
            return stationName;
        }

        private long parseTimeToMillis() {
            if (arrivalTime == null || arrivalTime.isEmpty()) return 0;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date date = sdf.parse(arrivalTime);
                return (date != null) ? date.getTime() : 0;
            } catch (ParseException e) {
                return 0;
            }
        }
    }

    private static class TrainSchedule {
        String trainNum; // 열차 번호
        List<StationInfo> stations; // 정차역 목록
        DayType dayType; // 운행일 유형 (매일/주말)

        TrainSchedule(String trainNum, List<StationInfo> stations, DayType dayType) {
            this.trainNum = trainNum;
            this.stations = stations;
            this.dayType = dayType;
        }
    }

    private static final List<TrainSchedule> ALL_SCHEDULES = new ArrayList<>();
    private static String a="수서", b="동탄",c="평택지제",d="천안아산",e="오송",f="대전", g ="김천구미", h ="서대구", i ="동대구", j ="경주", k ="울산", l ="부산",
            ha="수서 ", hb="동탄 ",hc="평택 ",hd="천안아산 ",he="오송 ",hf="대전 ", hg ="김천 ", hi="동대구 ", hj="경주 ", hk="울산 ", hl="부산 ",
            m="공주",n="익산",o="정읍",p="광주송정",q="나주",r="목포", s="밀양", t="진영", u="창원중앙", v="창원", w="마산", x="진주",
            y="포항", z="전주",z2="남원",z3="곡성",z4="구례구",z5="순천",z6="여천",z7="여수EXPO";
    private static int da=0,db=33,dc=61,dd=86, de=115,df=150,dg=224, dh=274, di=277,dj=326,dk=356,dl=407,
                    dm=158, dn=204, dO=246, dp=296, dq=311, dr=361,
                    ds=332, dt=358, du=373, dv=383, dw=386, dx=436, dy=365,
                    dz=229,dz2=283,dz3=301, dz4=321, dz5=349, dz6=374, dz7=384, ddh=50;

    static {

        /*

        List<StationInfo> train321 = new ArrayList<>();
        train321.add(new StationInfo(a, "08:05", da));
        train321.add(new StationInfo(b, "08:22", db));
        train321.add(new StationInfo(c, "08:32", dc));
        train321.add(new StationInfo(d, "08:45", dd));
        train321.add(new StationInfo(e, "08:57", de));
        train321.add(new StationInfo(f, "09:15", df));
        train321.add(new StationInfo(g, "09:15", dg));
        train321.add(new StationInfo(h, "10:00", dh));
        train321.add(new StationInfo(i, "10:00", di));
        train321.add(new StationInfo(j, "10:18", dj));
        train321.add(new StationInfo(k, "10:31", dk));
        train321.add(new StationInfo(l, "10:52", dl));
        ALL_SCHEDULES.add(new TrainSchedule("315", train321, DayType.DAILY));


        List<StationInfo> train321 = new ArrayList<>();
        train321.add(new StationInfo(l, "08:05", -1*dl));
        train321.add(new StationInfo(k, "08:22", -1*dk));
        train321.add(new StationInfo(j, "08:32", -1*dj));
        train321.add(new StationInfo(i, "08:45", -1*di));
        train321.add(new StationInfo(h, "08:57", -1*dh));
        train321.add(new StationInfo(g, "09:15", -1*dg));
        train321.add(new StationInfo(f, "09:15", -1*df));
        train321.add(new StationInfo(e, "10:00", -1*de));
        train321.add(new StationInfo(d, "10:00", -1*dd));
        train321.add(new StationInfo(c, "10:18", -1*dc));
        train321.add(new StationInfo(b, "10:31", -1*db));
        train321.add(new StationInfo(a, "10:52", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("315", train321, DayType.DAILY));


        */

        List<StationInfo> train301 = new ArrayList<>();
        train301.add(new StationInfo(a, "05:30", da));
        train301.add(new StationInfo(b, "05:47", db));
        train301.add(new StationInfo(e, "06:11", de));
        train301.add(new StationInfo(f, "06:30", df));
        train301.add(new StationInfo(i, "07:14", di));
        train301.add(new StationInfo(j, "07:32", dj));
        train301.add(new StationInfo(k, "07:45", dk));
        train301.add(new StationInfo(l, "08:06", dl));
        ALL_SCHEDULES.add(new TrainSchedule("301", train301, DayType.DAILY));

        List<StationInfo> train302 = new ArrayList<>();
        train302.add(new StationInfo(l, "05:00", dl*-1));
        train302.add(new StationInfo(k, "05:23", dk*-1));
        train302.add(new StationInfo(i, "05:48", di*-1));
        train302.add(new StationInfo(f, "06:30", df*-1));
        train302.add(new StationInfo(d, "06:54", dd*-1));
        train302.add(new StationInfo(c, "07:07", dc*-1));
        train302.add(new StationInfo(b, "07:18", db*-1));
        train302.add(new StationInfo(a, "07:35", da*-1));
        ALL_SCHEDULES.add(new TrainSchedule("302", train302, DayType.DAILY));

        List<StationInfo> train303 = new ArrayList<>();
        train303.add(new StationInfo(a, "06:00", da));
        train303.add(new StationInfo(c, "06:22", dc));
        train303.add(new StationInfo(d, "06:35", dd));
        train303.add(new StationInfo(f, "07:00", df));
        train303.add(new StationInfo(i, "07:44", di));
        train303.add(new StationInfo(k, "08:08", dk));
        train303.add(new StationInfo(l, "08:30", dl));
        ALL_SCHEDULES.add(new TrainSchedule("303", train303, DayType.DAILY));

        List<StationInfo> train304 = new ArrayList<>();
        train304.add(new StationInfo(l, "05:34", dl*-1));
        train304.add(new StationInfo(j, "06:02", dj*-1));
        train304.add(new StationInfo(i, "06:21", di*-1));
        train304.add(new StationInfo(f, "07:04", df*-1));
        train304.add(new StationInfo(e, "07:21", de*-1));
        train304.add(new StationInfo(c, "07:41", dc*-1));
        train304.add(new StationInfo(a, "08:03", da*-1));
        ALL_SCHEDULES.add(new TrainSchedule("304", train304, DayType.DAILY));

        List<StationInfo> train305 = new ArrayList<>();
        train305.add(new StationInfo(a, "06:30", da));
        train305.add(new StationInfo(b, "06:47", db));
        train305.add(new StationInfo(e, "07:11", de));
        train305.add(new StationInfo(f, "07:30", df));
        train305.add(new StationInfo(i, "08:14", di));
        train305.add(new StationInfo(j, "08:32", dj));
        train305.add(new StationInfo(k, "08:45", dk));
        train305.add(new StationInfo(l, "09:06", dl));
        ALL_SCHEDULES.add(new TrainSchedule("305", train305, DayType.WEEKEND_ONLY));

        List<StationInfo> train306 = new ArrayList<>();
        train306.add(new StationInfo(hl, "05:50", -1*(dl+ddh)));
        train306.add(new StationInfo(hk, "06:13", -1*(dk+ddh)));
        train306.add(new StationInfo(hi, "06:38", -1*(di+ddh)));
        train306.add(new StationInfo(h, "06:47", -1*dh));
        train306.add(new StationInfo(hg, "07:10", -1*dg));
        train306.add(new StationInfo(hf, "07:35", -1*df));
        train306.add(new StationInfo(he, "07:53", -1*de));
        train306.add(new StationInfo(hd, "08:06", -1*dd));
        train306.add(new StationInfo(hb, "08:24", -1*db));
        train306.add(new StationInfo(ha, "08:41", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("306", train306, DayType.DAILY));

        List<StationInfo> train307 = new ArrayList<>();
        train307.add(new StationInfo(a, "06:54", da));
        train307.add(new StationInfo(b, "07:11", db));
        train307.add(new StationInfo(d, "07:29", dd));
        train307.add(new StationInfo(f, "07:54", df));
        train307.add(new StationInfo(g, "08:19", dg));
        train307.add(new StationInfo(i, "08:44", di));
        train307.add(new StationInfo(j, "09:02", dj));
        train307.add(new StationInfo(l, "09:30", dl));
        ALL_SCHEDULES.add(new TrainSchedule("307", train307, DayType.DAILY));

        List<StationInfo> train308 = new ArrayList<>();
        train308.add(new StationInfo(l, "06:53", -1*dl));
        train308.add(new StationInfo(j, "07:21", -1*dj));
        train308.add(new StationInfo(i, "07:40", -1*di));
        train308.add(new StationInfo(g, "08:03", -1*dg));
        train308.add(new StationInfo(f, "08:28", -1*df));
        train308.add(new StationInfo(e, "08:46", -1*de));
        train308.add(new StationInfo(c, "09:07", -1*dc));
        train308.add(new StationInfo(a, "09:29", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("308", train308, DayType.DAILY));

        List<StationInfo> train309 = new ArrayList<>();
        train309.add(new StationInfo(a, "07:05", da));
        train309.add(new StationInfo(b, "07:23", db));
        train309.add(new StationInfo(c, "07:33", dc));
        train309.add(new StationInfo(d, "07:45", dd));
        train309.add(new StationInfo(e, "07:58", de));
        train309.add(new StationInfo(f, "08:16", df));
        train309.add(new StationInfo(i, "09:00", di));
        train309.add(new StationInfo(k, "09:24", dk));
        train309.add(new StationInfo(l, "09:46", dl));
        ALL_SCHEDULES.add(new TrainSchedule("309", train309, DayType.DAILY));

        List<StationInfo> train310 = new ArrayList<>();
        train310.add(new StationInfo(hl, "07:12", -1*(dl+ddh)));
        train310.add(new StationInfo(hk, "07:35", -1*(dk+ddh)));
        train310.add(new StationInfo(hi, "08:00", -1*(di+ddh)));
        train310.add(new StationInfo(h, "08:09", -1*dh));
        train310.add(new StationInfo(hg, "08:32", -1*dg));
        train310.add(new StationInfo(hf, "08:57", -1*df));
        train310.add(new StationInfo(he, "09:15", -1*de));
        train310.add(new StationInfo(hd, "09:28", -1*dd));
        train310.add(new StationInfo(hc, "09:41", -1*dc));
        train310.add(new StationInfo(ha, "10:03", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("310", train310, DayType.DAILY));

        List<StationInfo> train311 = new ArrayList<>();
        train311.add(new StationInfo(a, "07:25", da));
        train311.add(new StationInfo(b, "07:43", db));
        train311.add(new StationInfo(e, "08:07", de));
        train311.add(new StationInfo(f, "08:26", df));
        train311.add(new StationInfo(i, "09:10", di));
        train311.add(new StationInfo(j, "09:28", dj));
        train311.add(new StationInfo(l, "09:56", dl));
        ALL_SCHEDULES.add(new TrainSchedule("311", train311, DayType.DAILY));

        List<StationInfo> train312 = new ArrayList<>();
        train312.add(new StationInfo(l, "07:34", -1*dl));
        train312.add(new StationInfo(k, "07:57", -1*dk));
        train312.add(new StationInfo(j, "08:09", -1*dj));
        train312.add(new StationInfo(i, "08:28", -1*di));
        train312.add(new StationInfo(f, "09:12", -1*df));
        train312.add(new StationInfo(e, "09:29", -1*de));
        train312.add(new StationInfo(d, "09:42", -1*dd));
        train312.add(new StationInfo(c, "09:55", -1*dc));
        train312.add(new StationInfo(a, "10:17", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("312", train312, DayType.DAILY));

        List<StationInfo> train313 = new ArrayList<>();
        train313.add(new StationInfo(ha, "08:00", da));
        train313.add(new StationInfo(hc, "08:23", dc));
        train313.add(new StationInfo(hf, "08:54", df));
        train313.add(new StationInfo(h, "09:37", dh));
        train313.add(new StationInfo(hi, "09:46", di+ddh));
        train313.add(new StationInfo(hk, "10:12", dk+ddh));
        train313.add(new StationInfo(hl, "10:34", dl+ddh));
        ALL_SCHEDULES.add(new TrainSchedule("313", train313, DayType.DAILY));

        List<StationInfo> train314 = new ArrayList<>();
        train314.add(new StationInfo(l, "08:00", -1*dl));
        train314.add(new StationInfo(j, "08:29", -1*dj));
        train314.add(new StationInfo(i, "08:52", -1*di));
        train314.add(new StationInfo(f, "09:35", -1*df));
        train314.add(new StationInfo(e, "09:53", -1*de));
        train314.add(new StationInfo(c, "10:13", -1*dc));
        train314.add(new StationInfo(b, "10:24", -1*db));
        train314.add(new StationInfo(a, "10:41", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("314", train314, DayType.WEEKEND_ONLY));

        List<StationInfo> train315 = new ArrayList<>();
        train315.add(new StationInfo(a, "08:05", da));
        train315.add(new StationInfo(b, "08:22", db));
        train315.add(new StationInfo(c, "08:32", dc));
        train315.add(new StationInfo(d, "08:45", dd));
        train315.add(new StationInfo(e, "08:57", de));
        train315.add(new StationInfo(f, "09:15", df));
        train315.add(new StationInfo(i, "10:00", di));
        train315.add(new StationInfo(j, "10:18", dj));
        train315.add(new StationInfo(k, "10:31", dk));
        train315.add(new StationInfo(l, "10:52", dl));
        ALL_SCHEDULES.add(new TrainSchedule("315", train315, DayType.DAILY));

        List<StationInfo> train316 = new ArrayList<>();
        train316.add(new StationInfo(l, "08:28", -1*dl));
        train316.add(new StationInfo(k, "08:51", -1*dk));
        train316.add(new StationInfo(i, "09:16", -1*di));
        train316.add(new StationInfo(f, "09:59", -1*df));
        train316.add(new StationInfo(e, "10:16", -1*de));
        train316.add(new StationInfo(c, "10:37", -1*dc));
        train316.add(new StationInfo(a, "10:59", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("316", train316, DayType.DAILY));

        List<StationInfo> train317 = new ArrayList<>();
        train317.add(new StationInfo(a, "09:00", da));
        train317.add(new StationInfo(f, "09:47", df));
        train317.add(new StationInfo(i, "10:31", di));
        train317.add(new StationInfo(l, "11:11", dl));
        ALL_SCHEDULES.add(new TrainSchedule("317", train317, DayType.DAILY));

        List<StationInfo> train318 = new ArrayList<>();
        train318.add(new StationInfo(l, "08:59", -1*dl));
        train318.add(new StationInfo(j, "09:29", -1*dj));
        train318.add(new StationInfo(i, "09:48", -1*di));
        train318.add(new StationInfo(f, "10:31", -1*df));
        train318.add(new StationInfo(b, "11:07", -1*db));
        train318.add(new StationInfo(a, "11:24", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("318", train318, DayType.DAILY));

        List<StationInfo> train319 = new ArrayList<>();
        train319.add(new StationInfo(ha, "09:05", da));
        train319.add(new StationInfo(hb, "09:23", db));
        train319.add(new StationInfo(hd, "09:41", dd));
        train319.add(new StationInfo(hf, "10:06", df));
        train319.add(new StationInfo(h, "10:49", dh));
        train319.add(new StationInfo(hi, "10:58", di+ddh));
        train319.add(new StationInfo(hj, "11:18", dj+ddh));
        train319.add(new StationInfo(hk, "11:30", dk+ddh));
        train319.add(new StationInfo(hl, "11:52", dl+ddh));
        ALL_SCHEDULES.add(new TrainSchedule("319", train319, DayType.DAILY));

        List<StationInfo> train320 = new ArrayList<>();
        train320.add(new StationInfo(l, "09:13", -1*dl));
        train320.add(new StationInfo(k, "09:36", -1*dk));
        train320.add(new StationInfo(i, "10:01", -1*di));
        train320.add(new StationInfo(f, "10:45", -1*df));
        train320.add(new StationInfo(d, "11:10", -1*dd));
        train320.add(new StationInfo(c, "11:24", -1*dc));
        train320.add(new StationInfo(a, "11:46", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("320", train320, DayType.DAILY));

        List<StationInfo> train321 = new ArrayList<>();
        train321.add(new StationInfo(a, "09:20", da));
        train321.add(new StationInfo(c, "09:42", dc));
        train321.add(new StationInfo(e, "10:00", de));
        train321.add(new StationInfo(f, "10:19", df));
        train321.add(new StationInfo(g, "10:44", dg));
        train321.add(new StationInfo(i, "11:09", di));
        train321.add(new StationInfo(j, "11:27", dj));
        train321.add(new StationInfo(k, "11:40", dk));
        train321.add(new StationInfo(l, "12:01", dl));
        ALL_SCHEDULES.add(new TrainSchedule("321", train321, DayType.DAILY));

        List<StationInfo> train322 = new ArrayList<>();
        train322.add(new StationInfo(l, "09:32", -1*dl));
        train322.add(new StationInfo(i, "10:21", -1*di));
        train322.add(new StationInfo(f, "11:04", -1*df));
        train322.add(new StationInfo(c, "11:35", -1*dc));
        train322.add(new StationInfo(b, "11:46", -1*db));
        train322.add(new StationInfo(a, "12:03", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("322", train322, DayType.WEEKEND_ONLY));

        List<StationInfo> train323 = new ArrayList<>();
        train323.add(new StationInfo(a, "10:00", da));
        train323.add(new StationInfo(d, "10:29", dd));
        train323.add(new StationInfo(e, "10:41", de));
        train323.add(new StationInfo(f, "10:59", df));
        train323.add(new StationInfo(i, "11:43", di));
        train323.add(new StationInfo(k, "12:07", dk));
        train323.add(new StationInfo(l, "12:29", dl));
        ALL_SCHEDULES.add(new TrainSchedule("323", train323, DayType.DAILY));

        List<StationInfo> train324 = new ArrayList<>();
        train324.add(new StationInfo(l, "10:12", -1*dl));
        train324.add(new StationInfo(j, "10:40", -1*dj));
        train324.add(new StationInfo(i, "10:59", -1*di));
        train324.add(new StationInfo(f, "11:43", -1*df));
        train324.add(new StationInfo(e, "12:01", -1*de));
        train324.add(new StationInfo(c, "12:21", -1*dc));
        train324.add(new StationInfo(a, "12:43", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("324", train324, DayType.DAILY));

        List<StationInfo> train325 = new ArrayList<>();
        train325.add(new StationInfo(a, "10:30", da));
        train325.add(new StationInfo(b, "10:48", db));
        train325.add(new StationInfo(d, "11:06", dd));
        train325.add(new StationInfo(f, "11:31", df));
        train325.add(new StationInfo(g, "11:56", dg));
        train325.add(new StationInfo(i, "12:21", di));
        train325.add(new StationInfo(l, "13:01", dl));
        ALL_SCHEDULES.add(new TrainSchedule("325", train325, DayType.DAILY));

        List<StationInfo> train326 = new ArrayList<>();
        train326.add(new StationInfo(l, "10:33", -1*dl));
        train326.add(new StationInfo(k, "10:56", -1*dk));
        train326.add(new StationInfo(j, "11:08", -1*dj));
        train326.add(new StationInfo(i, "11:26", -1*di));
        train326.add(new StationInfo(g, "11:49", -1*dg));
        train326.add(new StationInfo(f, "12:15", -1*df));
        train326.add(new StationInfo(d, "12:39", -1*dd));
        train326.add(new StationInfo(a, "13:08", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("326", train326, DayType.DAILY));

        List<StationInfo> train327 = new ArrayList<>();
        train327.add(new StationInfo(a, "10:50", da));
        train327.add(new StationInfo(d, "11:19", dd));
        train327.add(new StationInfo(e, "11:31", de));
        train327.add(new StationInfo(f, "11:49", df));
        train327.add(new StationInfo(i, "12:33", di));
        train327.add(new StationInfo(j, "12:52", dj));
        train327.add(new StationInfo(k, "13:04", dk));
        train327.add(new StationInfo(l, "13:26", dl));
        ALL_SCHEDULES.add(new TrainSchedule("327", train327, DayType.DAILY));

        List<StationInfo> train328 = new ArrayList<>();
        train328.add(new StationInfo(l, "10:47", -1*dl));
        train328.add(new StationInfo(k, "11:10", -1*dk));
        train328.add(new StationInfo(i, "11:35", -1*di));
        train328.add(new StationInfo(f, "12:19", -1*df));
        train328.add(new StationInfo(e, "12:36", -1*de));
        train328.add(new StationInfo(d, "12:49", -1*dd));
        train328.add(new StationInfo(b, "13:08", -1*db));
        train328.add(new StationInfo(a, "13:25", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("328", train328, DayType.DAILY));

        List<StationInfo> train329 = new ArrayList<>();
        train329.add(new StationInfo(a, "12:04", da));
        train329.add(new StationInfo(b, "12:21", db));
        train329.add(new StationInfo(c, "12:31", dc));
        train329.add(new StationInfo(d, "12:44", dd));
        train329.add(new StationInfo(f, "13:09", df));
        train329.add(new StationInfo(g, "13:33", dg));
        train329.add(new StationInfo(i, "13:58", di));
        train329.add(new StationInfo(j, "14:18", dj));
        train329.add(new StationInfo(l, "14:46", dl));
        ALL_SCHEDULES.add(new TrainSchedule("329", train329, DayType.WEEKEND_ONLY));

        List<StationInfo> train330 = new ArrayList<>();
        train330.add(new StationInfo(l, "12:15", -1*dl));
        train330.add(new StationInfo(k, "12:38", -1*dk));
        train330.add(new StationInfo(j, "12:50", -1*dj));
        train330.add(new StationInfo(i, "13:09", -1*di));
        train330.add(new StationInfo(f, "13:53", -1*df));
        train330.add(new StationInfo(d, "14:17", -1*dd));
        train330.add(new StationInfo(c, "14:30", -1*dc));
        train330.add(new StationInfo(a, "14:52", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("330", train330, DayType.DAILY));

        List<StationInfo> train331 = new ArrayList<>();
        train331.add(new StationInfo(a, "12:28", da));
        train331.add(new StationInfo(c, "12:50", dc));
        train331.add(new StationInfo(d, "13:02", dd));
        train331.add(new StationInfo(f, "13:27", df));
        train331.add(new StationInfo(i, "14:11", di));
        train331.add(new StationInfo(k, "14:36", dk));
        train331.add(new StationInfo(l, "14:57", dl));
        ALL_SCHEDULES.add(new TrainSchedule("331", train331, DayType.DAILY));

        List<StationInfo> train332 = new ArrayList<>();
        train332.add(new StationInfo(l, "12:40", -1*dl));
        train332.add(new StationInfo(k, "13:03", -1*dk));
        train332.add(new StationInfo(i, "13:28", -1*di));
        train332.add(new StationInfo(g, "13:51", -1*dg));
        train332.add(new StationInfo(f, "14:16", -1*df));
        train332.add(new StationInfo(e, "14:34", -1*de));
        train332.add(new StationInfo(b, "14:59", -1*db));
        train332.add(new StationInfo(a, "15:16", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("332", train332, DayType.DAILY));

        List<StationInfo> train333 = new ArrayList<>();
        train333.add(new StationInfo(ha, "12:50", da));
        train333.add(new StationInfo(hb, "13:08", db));
        train333.add(new StationInfo(hf, "13:45", df));
        train333.add(new StationInfo(h, "14:27", dh));
        train333.add(new StationInfo(hi, "14:36", di+ddh));
        train333.add(new StationInfo(hl, "15:17", dl+ddh));
        ALL_SCHEDULES.add(new TrainSchedule("333", train333, DayType.DAILY));

        List<StationInfo> train334 = new ArrayList<>();
        train334.add(new StationInfo(hl, "12:51", -1*(dl+ddh)));
        train334.add(new StationInfo(hj, "13:19", -1*(dj+ddh)));
        train334.add(new StationInfo(hi, "13:38", -1*(di+ddh)));
        train334.add(new StationInfo(h, "13:47", -1*dh));
        train334.add(new StationInfo(hf, "14:30", -1*df));
        train334.add(new StationInfo(hd, "14:54", -1*dd));
        train334.add(new StationInfo(ha, "15:23", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("334", train334, DayType.DAILY));

        List<StationInfo> train335 = new ArrayList<>();
        train335.add(new StationInfo(a, "13:00", da));
        train335.add(new StationInfo(c, "13:22", dc));
        train335.add(new StationInfo(d, "13:34", dd));
        train335.add(new StationInfo(f, "13:59", df));
        train335.add(new StationInfo(g, "14:23", dg));
        train335.add(new StationInfo(i, "14:48", di));
        train335.add(new StationInfo(j, "15:06", dj));
        train335.add(new StationInfo(l, "15:34", dl));
        ALL_SCHEDULES.add(new TrainSchedule("335", train335, DayType.DAILY));

        List<StationInfo> train336 = new ArrayList<>();
        train336.add(new StationInfo(l, "13:33", -1*dl));
        train336.add(new StationInfo(k, "13:56", -1*dk));
        train336.add(new StationInfo(i, "14:21", -1*di));
        train336.add(new StationInfo(f, "15:04", -1*df));
        train336.add(new StationInfo(d, "15:28", -1*dd));
        train336.add(new StationInfo(b, "15:47", -1*db));
        train336.add(new StationInfo(a, "16:04", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("336", train336, DayType.DAILY));

        List<StationInfo> train337 = new ArrayList<>();
        train337.add(new StationInfo(a, "13:30", da));
        train337.add(new StationInfo(b, "13:47", db));
        train337.add(new StationInfo(d, "14:05", dd));
        train337.add(new StationInfo(e, "14:17", de));
        train337.add(new StationInfo(f, "14:35", df));
        train337.add(new StationInfo(i, "15:19", di));
        train337.add(new StationInfo(k, "15:44", dk));
        train337.add(new StationInfo(l, "16:06", dl));
        ALL_SCHEDULES.add(new TrainSchedule("337", train337, DayType.DAILY));

        List<StationInfo> train338 = new ArrayList<>();
        train338.add(new StationInfo(l, "13:43", -1*dl));
        train338.add(new StationInfo(i, "14:25", -1*di));
        train338.add(new StationInfo(f, "15:08", -1*df));
        train338.add(new StationInfo(e, "15:26", -1*de));
        train338.add(new StationInfo(c, "15:47", -1*dc));
        train338.add(new StationInfo(a, "16:09", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("338", train338, DayType.DAILY));

        List<StationInfo> train339 = new ArrayList<>();
        train339.add(new StationInfo(a, "13:55", da));
        train339.add(new StationInfo(f, "14:44", df));
        train339.add(new StationInfo(i, "15:28", di));
        train339.add(new StationInfo(j, "15:46", dj));
        train339.add(new StationInfo(l, "16:14", dl));
        ALL_SCHEDULES.add(new TrainSchedule("339", train339, DayType.DAILY));

        List<StationInfo> train340 = new ArrayList<>();
        train340.add(new StationInfo(l, "14:09", -1*dl));
        train340.add(new StationInfo(k, "14:32", -1*dk));
        train340.add(new StationInfo(i, "14:57", -1*di));
        train340.add(new StationInfo(g, "15:20", -1*dg));
        train340.add(new StationInfo(f, "15:45", -1*df));
        train340.add(new StationInfo(d, "16:10", -1*dd));
        train340.add(new StationInfo(b, "16:29", -1*db));
        train340.add(new StationInfo(a, "16:46", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("340", train340, DayType.DAILY));

        List<StationInfo> train341 = new ArrayList<>();
        train341.add(new StationInfo(a, "14:30", da));
        train341.add(new StationInfo(c, "14:52", dc));
        train341.add(new StationInfo(d, "15:04", dd));
        train341.add(new StationInfo(f, "15:29", df));
        train341.add(new StationInfo(i, "16:13", di));
        train341.add(new StationInfo(k, "16:38", dk));
        train341.add(new StationInfo(l, "16:59", dl));
        ALL_SCHEDULES.add(new TrainSchedule("341", train341, DayType.DAILY));

        List<StationInfo> train342 = new ArrayList<>();
        train342.add(new StationInfo(l, "14:24", -1*dl));
        train342.add(new StationInfo(k, "14:47", -1*dk));
        train342.add(new StationInfo(j, "14:59", -1*dj));
        train342.add(new StationInfo(i, "15:17", -1*di));
        train342.add(new StationInfo(f, "16:01", -1*df));
        train342.add(new StationInfo(e, "16:19", -1*de));
        train342.add(new StationInfo(c, "16:39", -1*dc));
        train342.add(new StationInfo(a, "17:01", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("342", train342, DayType.DAILY));

        List<StationInfo> train343 = new ArrayList<>();
        train343.add(new StationInfo(a, "15:04", da));
        train343.add(new StationInfo(d, "15:34", dd));
        train343.add(new StationInfo(f, "15:58", df));
        train343.add(new StationInfo(g, "16:22", dg));
        train343.add(new StationInfo(i, "16:47", di));
        train343.add(new StationInfo(j, "17:06", dj));
        train343.add(new StationInfo(k, "17:18", dk));
        train343.add(new StationInfo(l, "17:40", dl));
        ALL_SCHEDULES.add(new TrainSchedule("343", train343, DayType.DAILY));

        List<StationInfo> train344 = new ArrayList<>();
        train344.add(new StationInfo(hl, "14:47", -1*(dl+ddh)));
        train344.add(new StationInfo(hi, "15:28", -1*(di+ddh)));
        train344.add(new StationInfo(h, "15:38", -1*dh));
        train344.add(new StationInfo(hg, "16:00", -1*dg));
        train344.add(new StationInfo(hf, "16:26", -1*df));
        train344.add(new StationInfo(hb, "17:02", -1*db));
        train344.add(new StationInfo(ha, "17:19", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("344", train344, DayType.DAILY));

        List<StationInfo> train345 = new ArrayList<>();
        train345.add(new StationInfo(a, "15:29", da));
        train345.add(new StationInfo(b, "15:46", db));
        train345.add(new StationInfo(c, "15:56", dc));
        train345.add(new StationInfo(e, "16:15", de));
        train345.add(new StationInfo(f, "16:33", df));
        train345.add(new StationInfo(i, "17:17", di));
        train345.add(new StationInfo(l, "17:58", dl));
        ALL_SCHEDULES.add(new TrainSchedule("345", train345, DayType.DAILY));

        List<StationInfo> train346 = new ArrayList<>();
        train346.add(new StationInfo(l, "15:28", -1*dl));
        train346.add(new StationInfo(k, "15:51", -1*dk));
        train346.add(new StationInfo(i, "16:16", -1*di));
        train346.add(new StationInfo(f, "16:59", -1*df));
        train346.add(new StationInfo(e, "17:16", -1*de));
        train346.add(new StationInfo(a, "17:53", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("346", train346, DayType.DAILY));

        List<StationInfo> train347 = new ArrayList<>();
        train347.add(new StationInfo(ha, "15:56", da));
        train347.add(new StationInfo(hb, "16:14", db));
        train347.add(new StationInfo(hf, "16:51", df));
        train347.add(new StationInfo(h, "17:34", dh));
        train347.add(new StationInfo(hi, "17:43", di+ddh));
        train347.add(new StationInfo(hk, "18:09", dk+ddh));
        train347.add(new StationInfo(hl, "18:31", dl+ddh));
        ALL_SCHEDULES.add(new TrainSchedule("347", train347, DayType.DAILY));

        List<StationInfo> train348 = new ArrayList<>();
        train348.add(new StationInfo(l, "15:39", -1*dl));
        train348.add(new StationInfo(i, "16:22", -1*di));
        train348.add(new StationInfo(g, "16:45", -1*dg));
        train348.add(new StationInfo(f, "17:10", -1*df));
        train348.add(new StationInfo(d, "17:34", -1*dd));
        train348.add(new StationInfo(b, "17:54", -1*db));
        train348.add(new StationInfo(a, "18:11", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("348", train348, DayType.DAILY));

        List<StationInfo> train349 = new ArrayList<>();
        train349.add(new StationInfo(a, "16:21", da));
        train349.add(new StationInfo(d, "16:50", dd));
        train349.add(new StationInfo(f, "17:17", df));
        train349.add(new StationInfo(i, "18:01", di));
        train349.add(new StationInfo(j, "18:20", dj));
        train349.add(new StationInfo(l, "18:48", dl));
        ALL_SCHEDULES.add(new TrainSchedule("349", train349, DayType.DAILY));

        List<StationInfo> train350 = new ArrayList<>();
        train350.add(new StationInfo(l, "15:54", -1*dl));
        train350.add(new StationInfo(i, "16:36", -1*di));
        train350.add(new StationInfo(f, "17:19", -1*df));
        train350.add(new StationInfo(d, "17:43", -1*dd));
        train350.add(new StationInfo(b, "18:02", -1*db));
        train350.add(new StationInfo(a, "18:19", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("350", train350, DayType.WEEKEND_ONLY));

        List<StationInfo> train351 = new ArrayList<>();
        train351.add(new StationInfo(a, "16:34", da));
        train351.add(new StationInfo(b, "16:52", db));
        train351.add(new StationInfo(c, "17:02", dc));
        train351.add(new StationInfo(f, "17:34", df));
        train351.add(new StationInfo(g, "17:58", dg));
        train351.add(new StationInfo(i, "18:23", di));
        train351.add(new StationInfo(k, "18:48", dk));
        train351.add(new StationInfo(l, "19:09", dl));
        ALL_SCHEDULES.add(new TrainSchedule("351", train351, DayType.WEEKEND_ONLY));

        List<StationInfo> train352 = new ArrayList<>();
        train352.add(new StationInfo(l, "15:59", -1*dl));
        train352.add(new StationInfo(k, "16:22", -1*dk));
        train352.add(new StationInfo(j, "16:34", -1*dj));
        train352.add(new StationInfo(i, "16:52", -1*di));
        train352.add(new StationInfo(f, "17:35", -1*df));
        train352.add(new StationInfo(e, "17:54", -1*de));
        train352.add(new StationInfo(d, "18:07", -1*dd));
        train352.add(new StationInfo(c, "18:20", -1*dc));
        train352.add(new StationInfo(b, "18:31", -1*db));
        train352.add(new StationInfo(a, "18:48", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("352", train352, DayType.DAILY));

        List<StationInfo> train353 = new ArrayList<>();
        train353.add(new StationInfo(a, "16:52", da));
        train353.add(new StationInfo(b, "17:10", db));
        train353.add(new StationInfo(c, "17:20", dc));
        train353.add(new StationInfo(e, "17:39", de));
        train353.add(new StationInfo(f, "17:58", df));
        train353.add(new StationInfo(i, "18:43", di));
        train353.add(new StationInfo(k, "19:07", dk));
        train353.add(new StationInfo(l, "19:29", dl));
        ALL_SCHEDULES.add(new TrainSchedule("353", train353, DayType.DAILY));

        List<StationInfo> train354 = new ArrayList<>();
        train354.add(new StationInfo(l, "16:43", -1*dl));
        train354.add(new StationInfo(k, "17:06", -1*dk));
        train354.add(new StationInfo(j, "17:18", -1*dj));
        train354.add(new StationInfo(i, "17:36", -1*di));
        train354.add(new StationInfo(f, "18:20", -1*df));
        train354.add(new StationInfo(d, "18:44", -1*dd));
        train354.add(new StationInfo(c, "18:58", -1*dc));
        train354.add(new StationInfo(b, "19:09", -1*db));
        train354.add(new StationInfo(a, "19:26", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("354", train354, DayType.DAILY));

        List<StationInfo> train355 = new ArrayList<>();
        train355.add(new StationInfo(ha, "17:30", da));
        train355.add(new StationInfo(hf, "18:19", df));
        train355.add(new StationInfo(h, "19:02", dh));
        train355.add(new StationInfo(hi, "19:11", di+ddh));
        train355.add(new StationInfo(hj, "19:30", dj+ddh));
        train355.add(new StationInfo(hk, "19:43", dk+ddh));
        train355.add(new StationInfo(hl, "20:04", dl+ddh));
        ALL_SCHEDULES.add(new TrainSchedule("355", train355, DayType.DAILY));

        List<StationInfo> train356 = new ArrayList<>();
        train356.add(new StationInfo(l, "17:29", -1*dl));
        train356.add(new StationInfo(k, "17:52", -1*dk));
        train356.add(new StationInfo(i, "18:17", -1*di));
        train356.add(new StationInfo(g, "18:40", -1*dg));
        train356.add(new StationInfo(f, "19:06", -1*df));
        train356.add(new StationInfo(b, "19:41", -1*db));
        train356.add(new StationInfo(a, "19:58", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("356", train356, DayType.DAILY));

        List<StationInfo> train357 = new ArrayList<>();
        train357.add(new StationInfo(a, "18:00", da));
        train357.add(new StationInfo(d, "18:29", dd));
        train357.add(new StationInfo(f, "18:53", df));
        train357.add(new StationInfo(i, "19:37", di));
        train357.add(new StationInfo(j, "19:56", dj));
        train357.add(new StationInfo(k, "20:08", dk));
        train357.add(new StationInfo(l, "20:30", dl));
        ALL_SCHEDULES.add(new TrainSchedule("357", train357, DayType.DAILY));

        List<StationInfo> train358 = new ArrayList<>();
        train358.add(new StationInfo(l, "17:40", -1*dl));
        train358.add(new StationInfo(j, "18:08", -1*dj));
        train358.add(new StationInfo(i, "18:31", -1*di));
        train358.add(new StationInfo(f, "19:14", -1*df));
        train358.add(new StationInfo(e, "19:32", -1*de));
        train358.add(new StationInfo(d, "19:45", -1*dd));
        train358.add(new StationInfo(c, "19:58", -1*dc));
        train358.add(new StationInfo(b, "20:09", -1*db));
        train358.add(new StationInfo(a, "20:26", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("358", train358, DayType.WEEKEND_ONLY));

        List<StationInfo> train359 = new ArrayList<>();
        train359.add(new StationInfo(a, "18:24", da));
        train359.add(new StationInfo(b, "18:42", db));
        train359.add(new StationInfo(d, "18:59", dd));
        train359.add(new StationInfo(e, "19:11", de));
        train359.add(new StationInfo(f, "19:30", df));
        train359.add(new StationInfo(i, "20:14", di));
        train359.add(new StationInfo(j, "20:32", dj));
        train359.add(new StationInfo(k, "20:45", dk));
        train359.add(new StationInfo(l, "21:06", dl));
        ALL_SCHEDULES.add(new TrainSchedule("359", train359, DayType.DAILY));

        List<StationInfo> train360 = new ArrayList<>();
        train360.add(new StationInfo(l, "18:11", -1*dl));
        train360.add(new StationInfo(j, "18:39", -1*dj));
        train360.add(new StationInfo(i, "18:58", -1*di));
        train360.add(new StationInfo(g, "19:21", -1*dg));
        train360.add(new StationInfo(f, "19:46", -1*df));
        train360.add(new StationInfo(d, "20:12", -1*dd));
        train360.add(new StationInfo(b, "20:30", -1*db));
        train360.add(new StationInfo(a, "20:47", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("360", train360, DayType.DAILY));

        List<StationInfo> train361 = new ArrayList<>();
        train361.add(new StationInfo(a, "18:37", da));
        train361.add(new StationInfo(b, "18:55", db));
        train361.add(new StationInfo(c, "19:05", dc));
        train361.add(new StationInfo(d, "19:18", dd));
        train361.add(new StationInfo(f, "19:43", df));
        train361.add(new StationInfo(g, "20:07", dg));
        train361.add(new StationInfo(i, "20:32", di));
        train361.add(new StationInfo(j, "20:51", dj));
        train361.add(new StationInfo(k, "21:03", dk));
        train361.add(new StationInfo(l, "21:25", dl));
        ALL_SCHEDULES.add(new TrainSchedule("361", train361, DayType.DAILY));

        List<StationInfo> train362 = new ArrayList<>();
        train362.add(new StationInfo(l, "18:51", -1*dl));
        train362.add(new StationInfo(k, "19:14", -1*dk));
        train362.add(new StationInfo(j, "19:26", -1*dj));
        train362.add(new StationInfo(i, "19:45", -1*di));
        train362.add(new StationInfo(f, "20:28", -1*df));
        train362.add(new StationInfo(e, "20:46", -1*de));
        train362.add(new StationInfo(b, "21:12", -1*db));
        train362.add(new StationInfo(a, "21:29", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("362", train362, DayType.DAILY));

        List<StationInfo> train363 = new ArrayList<>();
        train363.add(new StationInfo(a, "19:00", da));
        train363.add(new StationInfo(b, "19:17", db));
        train363.add(new StationInfo(d, "19:35", dd));
        train363.add(new StationInfo(f, "20:00", df));
        train363.add(new StationInfo(g, "20:24", dg));
        train363.add(new StationInfo(i, "20:49", di));
        train363.add(new StationInfo(l, "21:29", dl));
        ALL_SCHEDULES.add(new TrainSchedule("363", train363, DayType.WEEKEND_ONLY));

        List<StationInfo> train364 = new ArrayList<>();
        train364.add(new StationInfo(l, "19:21", -1*dl));
        train364.add(new StationInfo(k, "19:44", -1*dk));
        train364.add(new StationInfo(j, "19:56", -1*dj));
        train364.add(new StationInfo(i, "20:22", -1*di));
        train364.add(new StationInfo(f, "21:05", -1*df));
        train364.add(new StationInfo(d, "21:29", -1*dd));
        train364.add(new StationInfo(c, "21:42", -1*dc));
        train364.add(new StationInfo(b, "21:53", -1*db));
        train364.add(new StationInfo(a, "22:10", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("364", train364, DayType.WEEKEND_ONLY));

        List<StationInfo> train365 = new ArrayList<>();
        train365.add(new StationInfo(a, "19:15", da));
        train365.add(new StationInfo(b, "19:33", db));
        train365.add(new StationInfo(c, "19:43", dc));
        train365.add(new StationInfo(f, "20:15", df));
        train365.add(new StationInfo(i, "20:59", di));
        train365.add(new StationInfo(k, "21:24", dk));
        train365.add(new StationInfo(l, "21:45", dl));
        ALL_SCHEDULES.add(new TrainSchedule("365", train365, DayType.DAILY));

        List<StationInfo> train366 = new ArrayList<>();
        train366.add(new StationInfo(l, "20:00", -1*dl));
        train366.add(new StationInfo(i, "20:41", -1*di));
        train366.add(new StationInfo(f, "21:23", -1*df));
        train366.add(new StationInfo(e, "21:40", -1*de));
        train366.add(new StationInfo(c, "22:00", -1*dc));
        train366.add(new StationInfo(b, "22:11", -1*db));
        train366.add(new StationInfo(a, "22:28", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("366", train366, DayType.DAILY));

        List<StationInfo> train367 = new ArrayList<>();
        train367.add(new StationInfo(a, "19:24", da));
        train367.add(new StationInfo(b, "19:42", db));
        train367.add(new StationInfo(c, "19:53", dc));
        train367.add(new StationInfo(e, "20:11", de));
        train367.add(new StationInfo(f, "20:29", df));
        train367.add(new StationInfo(i, "21:13", di));
        train367.add(new StationInfo(j, "21:31", dj));
        train367.add(new StationInfo(l, "21:59", dl));
        ALL_SCHEDULES.add(new TrainSchedule("367", train367, DayType.WEEKEND_ONLY));

        List<StationInfo> train368 = new ArrayList<>();
        train368.add(new StationInfo(l, "20:15", -1*dl));
        train368.add(new StationInfo(k, "20:38", -1*dk));
        train368.add(new StationInfo(i, "21:03", -1*di));
        train368.add(new StationInfo(g, "21:26", -1*dg));
        train368.add(new StationInfo(f, "21:51", -1*df));
        train368.add(new StationInfo(d, "22:16", -1*dd));
        train368.add(new StationInfo(a, "22:45", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("368", train368, DayType.DAILY));

        List<StationInfo> train369 = new ArrayList<>();
        train369.add(new StationInfo(a, "20:00", da));
        train369.add(new StationInfo(d, "20:30", dd));
        train369.add(new StationInfo(f, "20:54", df));
        train369.add(new StationInfo(i, "21:38", di));
        train369.add(new StationInfo(k, "22:02", dk));
        train369.add(new StationInfo(l, "22:24", dl));
        ALL_SCHEDULES.add(new TrainSchedule("369", train369, DayType.DAILY));

        List<StationInfo> train370 = new ArrayList<>();
        train370.add(new StationInfo(l, "20:30", -1*dl));
        train370.add(new StationInfo(k, "20:53", -1*dk));
        train370.add(new StationInfo(j, "21:05", -1*dj));
        train370.add(new StationInfo(i, "21:23", -1*di));
        train370.add(new StationInfo(f, "22:06", -1*df));
        train370.add(new StationInfo(e, "22:24", -1*de));
        train370.add(new StationInfo(c, "22:44", -1*dc));
        train370.add(new StationInfo(b, "22:55", -1*db));
        train370.add(new StationInfo(a, "23:12", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("370", train370, DayType.DAILY));

        List<StationInfo> train371 = new ArrayList<>();
        train371.add(new StationInfo(a, "20:28", da));
        train371.add(new StationInfo(b, "20:46", db));
        train371.add(new StationInfo(c, "20:56", dc));
        train371.add(new StationInfo(d, "21:08", dd));
        train371.add(new StationInfo(e, "21:20", de));
        train371.add(new StationInfo(f, "21:39", df));
        train371.add(new StationInfo(g, "22:03", dg));
        train371.add(new StationInfo(i, "22:28", di));
        train371.add(new StationInfo(j, "22:47", dj));
        train371.add(new StationInfo(l, "23:15", dl));
        ALL_SCHEDULES.add(new TrainSchedule("371", train371, DayType.DAILY));

        List<StationInfo> train372 = new ArrayList<>();
        train372.add(new StationInfo(l, "20:49", -1*dl));
        train372.add(new StationInfo(k, "21:12", -1*dk));
        train372.add(new StationInfo(i, "21:37", -1*di));
        train372.add(new StationInfo(f, "22:20", -1*df));
        train372.add(new StationInfo(d, "22:44", -1*dd));
        train372.add(new StationInfo(b, "23:03", -1*db));
        train372.add(new StationInfo(a, "23:20", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("372", train372, DayType.DAILY));

        List<StationInfo> train373 = new ArrayList<>();
        train373.add(new StationInfo(a, "21:00", da));
        train373.add(new StationInfo(b, "21:17", db));
        train373.add(new StationInfo(d, "21:35", dd));
        train373.add(new StationInfo(f, "22:00", df));
        train373.add(new StationInfo(i, "22:44", di));
        train373.add(new StationInfo(k, "23:08", dk));
        train373.add(new StationInfo(l, "23:30", dl));
        ALL_SCHEDULES.add(new TrainSchedule("373", train373, DayType.DAILY));

        List<StationInfo> train374 = new ArrayList<>();
        train374.add(new StationInfo(l, "21:19", -1*dl));
        train374.add(new StationInfo(k, "21:42", -1*dk));
        train374.add(new StationInfo(i, "22:08", -1*di));
        train374.add(new StationInfo(f, "22:52", -1*df));
        train374.add(new StationInfo(a, "23:39", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("374", train374, DayType.DAILY));

        List<StationInfo> train375 = new ArrayList<>();
        train375.add(new StationInfo(a, "21:28", da));
        train375.add(new StationInfo(c, "21:50", dc));
        train375.add(new StationInfo(e, "22:08", de));
        train375.add(new StationInfo(f, "22:27", df));
        train375.add(new StationInfo(i, "23:11", di));
        train375.add(new StationInfo(k, "23:36", dk));
        train375.add(new StationInfo(l, "23:57", dl));
        ALL_SCHEDULES.add(new TrainSchedule("375", train375, DayType.DAILY));

        List<StationInfo> train376 = new ArrayList<>();
        train376.add(new StationInfo(l, "21:49", -1*dl));
        train376.add(new StationInfo(k, "22:12", -1*dk));
        train376.add(new StationInfo(i, "22:37", -1*di));
        train376.add(new StationInfo(f, "23:20", -1*df));
        train376.add(new StationInfo(e, "23:37", -1*de));
        train376.add(new StationInfo(b, "24:03", -1*db));
        train376.add(new StationInfo(a, "24:20", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("376", train376, DayType.DAILY));

        List<StationInfo> train377 = new ArrayList<>();
        train377.add(new StationInfo(a, "22:00", da));
        train377.add(new StationInfo(b, "22:18", db));
        train377.add(new StationInfo(f, "22:54", df));
        train377.add(new StationInfo(g, "23:19", dg));
        train377.add(new StationInfo(i, "23:44", di));
        train377.add(new StationInfo(l, "24:24", dl));
        ALL_SCHEDULES.add(new TrainSchedule("377", train377, DayType.DAILY));

        List<StationInfo> train378 = new ArrayList<>();
        train378.add(new StationInfo(hl, "22:21", -1*(dl+ddh)));
        train378.add(new StationInfo(hk, "22:44", -1*(dk+ddh)));
        train378.add(new StationInfo(hj, "22:56", -1*(dj+ddh)));
        train378.add(new StationInfo(hi, "23:14", -1*(di+ddh)));
        train378.add(new StationInfo(h, "23:24", -1*dh));
        train378.add(new StationInfo(hf, "24:06", -1*df));
        train378.add(new StationInfo(he, "24:24", -1*de));
        train378.add(new StationInfo(hb, "24:49", -1*db));
        train378.add(new StationInfo(ha, "25:06", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("378", train378, DayType.DAILY));

        List<StationInfo> train379 = new ArrayList<>();
        train379.add(new StationInfo(a, "22:40", da));
        train379.add(new StationInfo(b, "22:57", db));
        train379.add(new StationInfo(d, "23:15", dd));
        train379.add(new StationInfo(e, "23:26", de));
        train379.add(new StationInfo(f, "23:44", df));
        train379.add(new StationInfo(i, "24:28", di));
        train379.add(new StationInfo(l, "25:08", dl));
        ALL_SCHEDULES.add(new TrainSchedule("379", train379, DayType.DAILY));

        List<StationInfo> train380 = new ArrayList<>();
        train380.add(new StationInfo(l, "23:00", -1*dl));
        train380.add(new StationInfo(k, "23:23", -1*dk));
        train380.add(new StationInfo(i, "23:48", -1*di));
        train380.add(new StationInfo(f, "24:30", -1*df));
        train380.add(new StationInfo(a, "25:17", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("380", train380, DayType.DAILY));

        //--------경전선

        List<StationInfo> train381 = new ArrayList<>();
        train381.add(new StationInfo(a, "12:04", da));
        train381.add(new StationInfo(b, "12:21", db));
        train381.add(new StationInfo(c, "12:31", dc));
        train381.add(new StationInfo(d, "12:44", dd));
        train381.add(new StationInfo(f, "13:09", df));
        train381.add(new StationInfo(g, "13:33", dg));
        train381.add(new StationInfo(i, "14:01", di));
        train381.add(new StationInfo(s, "14:34", ds));
        train381.add(new StationInfo(t, "14:53", dt));
        train381.add(new StationInfo(u, "15:04", du));
        train381.add(new StationInfo(v, "15:13", dv));
        train381.add(new StationInfo(w, "15:19", dw));
        train381.add(new StationInfo(x, "15:44", dx));

        ALL_SCHEDULES.add(new TrainSchedule("381", train381, DayType.DAILY));

        List<StationInfo> train382 = new ArrayList<>();
        train382.add(new StationInfo(x, "07:08", dx*-1));
        train382.add(new StationInfo(w, "07:33", dw*-1));
        train382.add(new StationInfo(v, "07:39", dv*-1));
        train382.add(new StationInfo(u, "07:48", du*-1));
        train382.add(new StationInfo(t, "07:58", du*-1));
        train382.add(new StationInfo(s, "08:15", du*-1));
        train382.add(new StationInfo(i, "08:52", di*-1));
        train382.add(new StationInfo(f, "09:35", df*-1));
        train382.add(new StationInfo(e, "09:53", de*-1));
        train382.add(new StationInfo(c, "10:13", dc*-1));
        train382.add(new StationInfo(b, "10:24", db*-1));
        train382.add(new StationInfo(a, "10:41", da*-1));
        ALL_SCHEDULES.add(new TrainSchedule("382", train382, DayType.DAILY));

        List<StationInfo> train383 = new ArrayList<>();
        train383.add(new StationInfo(a, "19:24", da));
        train383.add(new StationInfo(b, "19:42", db));
        train383.add(new StationInfo(c, "19:53", dc));
        train383.add(new StationInfo(e, "20:11", de));
        train383.add(new StationInfo(f, "20:29", df));
        train383.add(new StationInfo(i, "21:16", di));
        train383.add(new StationInfo(s, "21:49", ds));
        train383.add(new StationInfo(t, "22:07", dt));
        train383.add(new StationInfo(u, "22:19", du));
        train383.add(new StationInfo(v, "22:27", dv));
        train383.add(new StationInfo(w, "22:34", dw));
        train383.add(new StationInfo(x, "22:59", dx));

        ALL_SCHEDULES.add(new TrainSchedule("383", train383, DayType.DAILY));

        List<StationInfo> train384 = new ArrayList<>();
        train384.add(new StationInfo(x, "16:47", dx*-1));
        train384.add(new StationInfo(w, "17:12", dw*-1));
        train384.add(new StationInfo(v, "17:18", dv*-1));
        train384.add(new StationInfo(u, "17:27", du*-1));
        train384.add(new StationInfo(t, "17:38", du*-1));
        train384.add(new StationInfo(s, "17:55", du*-1));
        train384.add(new StationInfo(i, "18:31", di*-1));
        train384.add(new StationInfo(f, "19:14", df*-1));
        train384.add(new StationInfo(e, "19:32", de*-1));
        train384.add(new StationInfo(d, "19:45", dd*-1));
        train384.add(new StationInfo(c, "19:58", dc*-1));
        train384.add(new StationInfo(b, "20:09", db*-1));
        train384.add(new StationInfo(a, "20:26", da*-1));
        ALL_SCHEDULES.add(new TrainSchedule("384", train384, DayType.DAILY));

        //----동해선-----------------------

        List<StationInfo> train391 = new ArrayList<>();
        train391.add(new StationInfo(a, "06:30", da));
        train391.add(new StationInfo(b, "06:47", db));
        train391.add(new StationInfo(e, "07:11", de));
        train391.add(new StationInfo(f, "07:30", df));
        train391.add(new StationInfo(i, "08:17", di));
        train391.add(new StationInfo(y, "08:52", dy));
        ALL_SCHEDULES.add(new TrainSchedule("391", train391, DayType.DAILY));

        List<StationInfo> train392 = new ArrayList<>();
        train392.add(new StationInfo(y, "09:41", -1*dy));
        train392.add(new StationInfo(i, "10:21", -1*di));
        train392.add(new StationInfo(f, "11:04", -1*df));
        train392.add(new StationInfo(c, "11:35", -1*dc));
        train392.add(new StationInfo(b, "11:46", -1*db));
        train392.add(new StationInfo(a, "12:03", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("392", train392, DayType.DAILY));

        List<StationInfo> train393 = new ArrayList<>();
        train393.add(new StationInfo(a, "16:34", da));
        train393.add(new StationInfo(b, "16:52", db));
        train393.add(new StationInfo(c, "17:02", dc));
        train393.add(new StationInfo(f, "17:34", df));
        train393.add(new StationInfo(g, "17:58", dg));
        train393.add(new StationInfo(i, "18:26", di));
        train391.add(new StationInfo(y, "19:02", dy));
        ALL_SCHEDULES.add(new TrainSchedule("393", train393, DayType.DAILY));

        List<StationInfo> train394 = new ArrayList<>();
        train394.add(new StationInfo(y, "19:42", -1*dy));
        train394.add(new StationInfo(i, "20:22", -1*di));
        train394.add(new StationInfo(f, "21:05", -1*df));
        train394.add(new StationInfo(d, "21:29", -1*dd));
        train394.add(new StationInfo(c, "21:42", -1*dc));
        train394.add(new StationInfo(b, "21:53", -1*db));
        train394.add(new StationInfo(a, "22:10", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("394", train394, DayType.DAILY));

        //----호남선-----------------------

        List<StationInfo> train601 = new ArrayList<>();
        train601.add(new StationInfo(a, "05:38", da));
        train601.add(new StationInfo(b, "05:56", db));
        train601.add(new StationInfo(d, "06:14", dd));
        train601.add(new StationInfo(m, "06:38", dm));
        train601.add(new StationInfo(n, "06:58", dn));
        train601.add(new StationInfo(p, "07:28", dp));
        ALL_SCHEDULES.add(new TrainSchedule("601", train601, DayType.DAILY));

        List<StationInfo> train602 = new ArrayList<>();
        train602.add(new StationInfo(p, "05:12", -1*dp));
        train602.add(new StationInfo(o, "05:31", -1*dO));
        train602.add(new StationInfo(n, "05:50", -1*dn));
        train602.add(new StationInfo(e, "06:21", -1*de));
        train602.add(new StationInfo(d, "06:35", -1*dd));
        train602.add(new StationInfo(b, "06:53", -1*db));
        train602.add(new StationInfo(a, "07:10", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("602", train602, DayType.DAILY));

        List<StationInfo> train603 = new ArrayList<>();
        train603.add(new StationInfo(a, "07:40", da));
        train603.add(new StationInfo(c, "08:02", dc));
        train603.add(new StationInfo(n, "08:47", dn));
        train603.add(new StationInfo(p, "09:16", dp));
        ALL_SCHEDULES.add(new TrainSchedule("603", train603, DayType.DAILY));

        List<StationInfo> train604 = new ArrayList<>();
        train604.add(new StationInfo(p, "06:16", -1*dp));
        train604.add(new StationInfo(o, "06:35", -1*dO));
        train604.add(new StationInfo(n, "06:54", -1*dn));
        train604.add(new StationInfo(m, "07:13", -1*dm));
        train604.add(new StationInfo(e, "07:31", -1*de));
        train604.add(new StationInfo(d, "07:45", -1*dd));
        train604.add(new StationInfo(c, "07:58", -1*dc));
        train604.add(new StationInfo(b, "08:09", -1*db));
        train604.add(new StationInfo(a, "08:26", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("604", train604, DayType.DAILY));

        List<StationInfo> train605 = new ArrayList<>();
        train605.add(new StationInfo(a, "08:30", da));
        train605.add(new StationInfo(b, "08:48", db));
        train605.add(new StationInfo(c, "08:58", dc));
        train605.add(new StationInfo(e, "09:17", de));
        train605.add(new StationInfo(m, "09:34", dm));
        train605.add(new StationInfo(n, "09:54", dn));
        train605.add(new StationInfo(o, "10:11", dO));
        train605.add(new StationInfo(p, "10:29", dp));
        ALL_SCHEDULES.add(new TrainSchedule("605", train605, DayType.DAILY));

        List<StationInfo> train606 = new ArrayList<>();
        train606.add(new StationInfo(p, "07:53", -1*dp));
        train606.add(new StationInfo(n, "08:30", -1*dn));
        train606.add(new StationInfo(m, "08:49", -1*dm));
        train606.add(new StationInfo(e, "09:07", -1*de));
        train606.add(new StationInfo(c, "09:28", -1*dc));
        train606.add(new StationInfo(b, "09:39", -1*db));
        train606.add(new StationInfo(a, "09:56", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("606", train606, DayType.DAILY));

        List<StationInfo> train607 = new ArrayList<>();
        train607.add(new StationInfo(a, "10:20", da));
        train607.add(new StationInfo(b, "10:37", db));
        train607.add(new StationInfo(c, "10:47", dc));
        train607.add(new StationInfo(d, "11:00", dd));
        train607.add(new StationInfo(e, "11:12", de));
        train607.add(new StationInfo(m, "11:29", dm));
        train607.add(new StationInfo(n, "11:49", dn));
        train607.add(new StationInfo(p, "12:19", dp));
        ALL_SCHEDULES.add(new TrainSchedule("607", train607, DayType.DAILY));

        List<StationInfo> train608 = new ArrayList<>();
        train608.add(new StationInfo(p, "10:17", -1*dp));
        train608.add(new StationInfo(n, "10:48", -1*dn));
        train608.add(new StationInfo(e, "11:19", -1*de));
        train608.add(new StationInfo(c, "11:42", -1*dc));
        train608.add(new StationInfo(b, "11:54", -1*db));
        train608.add(new StationInfo(a, "12:11", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("608", train608, DayType.DAILY));

        List<StationInfo> train609 = new ArrayList<>();
        train609.add(new StationInfo(a, "12:20", da));
        train609.add(new StationInfo(d, "12:49", dd));
        train609.add(new StationInfo(m, "13:13", dm));
        train609.add(new StationInfo(n, "13:32", dn));
        train609.add(new StationInfo(p, "14:03", dp));
        ALL_SCHEDULES.add(new TrainSchedule("609", train609, DayType.DAILY));

        List<StationInfo> train610 = new ArrayList<>();
        train610.add(new StationInfo(p, "11:12", -1*dp));
        train610.add(new StationInfo(n, "11:43", -1*dn));
        train610.add(new StationInfo(m, "12:03", -1*dm));
        train610.add(new StationInfo(c, "12:36", -1*dc));
        train610.add(new StationInfo(a, "12:58", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("610", train610, DayType.DAILY));

        List<StationInfo> train611 = new ArrayList<>();
        train611.add(new StationInfo(a, "14:09", da));
        train611.add(new StationInfo(b, "14:26", db));
        train611.add(new StationInfo(e, "14:51", de));
        train611.add(new StationInfo(n, "15:21", dn));
        train611.add(new StationInfo(o, "15:39", dO));
        train611.add(new StationInfo(p, "15:57", dp));
        ALL_SCHEDULES.add(new TrainSchedule("611", train611, DayType.DAILY));

        List<StationInfo> train612 = new ArrayList<>();
        train612.add(new StationInfo(p, "12:55", -1*dp));
        train612.add(new StationInfo(n, "13:27", -1*dn));
        train612.add(new StationInfo(b, "14:20", -1*db));
        train612.add(new StationInfo(a, "14:37", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("612", train612, DayType.DAILY));

        List<StationInfo> train613 = new ArrayList<>();
        train613.add(new StationInfo(a, "15:20", da));
        train613.add(new StationInfo(c, "15:42", dc));
        train613.add(new StationInfo(d, "15:54", dd));
        train613.add(new StationInfo(e, "16:07", de));
        train613.add(new StationInfo(m, "16:23", dm));
        train613.add(new StationInfo(n, "16:43", dn));
        train613.add(new StationInfo(p, "17:13", dp));
        ALL_SCHEDULES.add(new TrainSchedule("613", train613, DayType.DAILY));

        List<StationInfo> train614 = new ArrayList<>();
        train614.add(new StationInfo(p, "15:38", -1*dp));
        train614.add(new StationInfo(n, "16:15", -1*dn));
        train614.add(new StationInfo(m, "16:34", -1*dm));
        train614.add(new StationInfo(c, "17:08", -1*dc));
        train614.add(new StationInfo(b, "17:19", -1*db));
        train614.add(new StationInfo(a, "17:36", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("614", train614, DayType.DAILY));

        List<StationInfo> train615 = new ArrayList<>();
        train615.add(new StationInfo(a, "18:05", da));
        train615.add(new StationInfo(b, "18:22", db));
        train615.add(new StationInfo(d, "18:40", dd));
        train615.add(new StationInfo(m, "19:05", dm));
        train615.add(new StationInfo(n, "19:25", dn));
        train615.add(new StationInfo(p, "19:54", dp));
        ALL_SCHEDULES.add(new TrainSchedule("615", train615, DayType.DAILY));

        List<StationInfo> train616 = new ArrayList<>();
        train616.add(new StationInfo(p, "16:22", -1*dp));
        train616.add(new StationInfo(o, "16:40", -1*dO));
        train616.add(new StationInfo(n, "16:59", -1*dn));
        train616.add(new StationInfo(m, "17:18", -1*dm));
        train616.add(new StationInfo(e, "17:36", -1*de));
        train616.add(new StationInfo(d, "17:50", -1*dd));
        train616.add(new StationInfo(c, "18:04", -1*dc));
        train616.add(new StationInfo(b, "18:15", -1*db));
        train616.add(new StationInfo(a, "18:32", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("616", train616, DayType.DAILY));

        List<StationInfo> train617 = new ArrayList<>();
        train617.add(new StationInfo(a, "19:40", da));
        train617.add(new StationInfo(b, "19:58", db));
        train617.add(new StationInfo(e, "20:23", de));
        train617.add(new StationInfo(n, "20:53", dn));
        train617.add(new StationInfo(o, "21:10", dO));
        train617.add(new StationInfo(p, "21:28", dp));
        ALL_SCHEDULES.add(new TrainSchedule("617", train617, DayType.DAILY));

        List<StationInfo> train618 = new ArrayList<>();
        train618.add(new StationInfo(p, "18:30", -1*dp));
        train618.add(new StationInfo(o, "18:49", -1*dO));
        train618.add(new StationInfo(n, "19:08", -1*dn));
        train618.add(new StationInfo(m, "19:27", -1*dm));
        train618.add(new StationInfo(d, "19:54", -1*dd));
        train618.add(new StationInfo(c, "20:07", -1*dc));
        train618.add(new StationInfo(b, "20:18", -1*db));
        train618.add(new StationInfo(a, "20:35", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("618", train618, DayType.DAILY));

        List<StationInfo> train619 = new ArrayList<>();
        train619.add(new StationInfo(a, "22:20", da));
        train619.add(new StationInfo(c, "22:41", dc));
        train619.add(new StationInfo(e, "23:01", de));
        train619.add(new StationInfo(n, "23:31", dn));
        train619.add(new StationInfo(o, "23:48", dO));
        train619.add(new StationInfo(p, "24:06", dp));
        ALL_SCHEDULES.add(new TrainSchedule("619", train619, DayType.DAILY));

        List<StationInfo> train620 = new ArrayList<>();
        train620.add(new StationInfo(p, "20:21", -1*dp));
        train620.add(new StationInfo(n, "20:52", -1*dn));
        train620.add(new StationInfo(m, "21:12", -1*dm));
        train620.add(new StationInfo(e, "21:30", -1*de));
        train620.add(new StationInfo(d, "21:44", -1*dd));
        train620.add(new StationInfo(b, "22:02", -1*db));
        train620.add(new StationInfo(a, "22:19", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("620", train620, DayType.DAILY));

        List<StationInfo> train621 = new ArrayList<>();
        train621.add(new StationInfo(a, "22:55", da));
        train621.add(new StationInfo(b, "23:13", db));
        train621.add(new StationInfo(c, "23:23", dc));
        train621.add(new StationInfo(d, "23:35", dd));
        train621.add(new StationInfo(n, "24:13", dn));
        train621.add(new StationInfo(p, "24:42", dp));
        ALL_SCHEDULES.add(new TrainSchedule("621", train621, DayType.DAILY));

        List<StationInfo> train622 = new ArrayList<>();
        train622.add(new StationInfo(p, "21:53", -1*dp));
        train622.add(new StationInfo(o, "22:12", -1*dO));
        train622.add(new StationInfo(n, "22:30", -1*dn));
        train622.add(new StationInfo(m, "22:50", -1*dm));
        train622.add(new StationInfo(e, "23:08", -1*de));
        train622.add(new StationInfo(c, "23:29", -1*dc));
        train622.add(new StationInfo(a, "23:51", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("622", train622, DayType.DAILY));

        List<StationInfo> train651 = new ArrayList<>();
        train651.add(new StationInfo(a, "05:08", da));
        train651.add(new StationInfo(d, "05:44", dd));
        train651.add(new StationInfo(e, "06:15", de));
        train651.add(new StationInfo(n, "06:32", dn));
        train651.add(new StationInfo(o, "06:32", dO));
        train651.add(new StationInfo(p, "06:52", dp));
        train651.add(new StationInfo(q, "07:02", dq));
        train651.add(new StationInfo(r, "07:28", dr));
        ALL_SCHEDULES.add(new TrainSchedule("651", train651, DayType.DAILY));

        List<StationInfo> train652 = new ArrayList<>();
        train652.add(new StationInfo(r, "06:21", -1*dr));
        train652.add(new StationInfo(q, "06:48", -1*dq));
        train652.add(new StationInfo(p, "06:59", -1*dp));
        train652.add(new StationInfo(o, "07:18", -1*dO));
        train652.add(new StationInfo(n, "07:36", -1*dn));
        train652.add(new StationInfo(d, "08:15", -1*dd));
        train652.add(new StationInfo(c, "08:28", -1*dc));
        train652.add(new StationInfo(b, "08:39", -1*db));
        train652.add(new StationInfo(a, "08:56", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("652", train652, DayType.DAILY));

        List<StationInfo> train653 = new ArrayList<>();
        train653.add(new StationInfo(a, "06:39", da));
        train653.add(new StationInfo(b, "06:56", db));
        train653.add(new StationInfo(c, "07:06", dc));
        train653.add(new StationInfo(d, "07:19", dd));
        train653.add(new StationInfo(e, "07:31", de));
        train653.add(new StationInfo(n, "08:02", dn));
        train653.add(new StationInfo(o, "08:19", dO));
        train653.add(new StationInfo(p, "08:39", dp));
        train653.add(new StationInfo(q, "08:49", dq));
        train653.add(new StationInfo(r, "09:15", dr));
        ALL_SCHEDULES.add(new TrainSchedule("653", train653, DayType.DAILY));

        List<StationInfo> train654 = new ArrayList<>();
        train654.add(new StationInfo(r, "07:56", -1*dr));
        train654.add(new StationInfo(q, "08:23", -1*dq));
        train654.add(new StationInfo(p, "08:34", -1*dp));
        train654.add(new StationInfo(o, "08:53", -1*dO));
        train654.add(new StationInfo(n, "09:11", -1*dn));
        train654.add(new StationInfo(d, "09:50", -1*dd));
        train654.add(new StationInfo(b, "10:09", -1*db));
        train654.add(new StationInfo(a, "10:26", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("654", train654, DayType.DAILY));

        List<StationInfo> train655 = new ArrayList<>();
        train655.add(new StationInfo(a, "09:39", da));
        train655.add(new StationInfo(b, "09:56", db));
        train655.add(new StationInfo(c, "10:06", dc));
        train655.add(new StationInfo(d, "10:19", dd));
        train655.add(new StationInfo(e, "10:31", de));
        train655.add(new StationInfo(n, "11:02", dn));
        train655.add(new StationInfo(p, "11:33", dp));
        train655.add(new StationInfo(q, "11:43", dq));
        train655.add(new StationInfo(r, "12:09", dr));
        ALL_SCHEDULES.add(new TrainSchedule("655", train655, DayType.DAILY));

        List<StationInfo> train656 = new ArrayList<>();
        train656.add(new StationInfo(r, "10:05", -1*dr));
        train656.add(new StationInfo(q, "10:32", -1*dq));
        train656.add(new StationInfo(p, "10:43", -1*dp));
        train656.add(new StationInfo(o, "11:02", -1*dO));
        train656.add(new StationInfo(n, "11:20", -1*dn));
        train656.add(new StationInfo(d, "11:59", -1*dd));
        train656.add(new StationInfo(c, "12:12", -1*dc));
        train656.add(new StationInfo(a, "12:34", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("656", train656, DayType.DAILY));

        List<StationInfo> train657 = new ArrayList<>();
        train657.add(new StationInfo(a, "11:00", da));
        train657.add(new StationInfo(d, "11:29", dd));
        train657.add(new StationInfo(n, "12:08", dn));
        train657.add(new StationInfo(o, "12:25", dO));
        train657.add(new StationInfo(p, "12:45", dp));
        train657.add(new StationInfo(q, "12:55", dq));
        train657.add(new StationInfo(r, "13:21", dr));
        ALL_SCHEDULES.add(new TrainSchedule("657", train657, DayType.DAILY));

        List<StationInfo> train658 = new ArrayList<>();
        train658.add(new StationInfo(r, "13:05", -1*dr));
        train658.add(new StationInfo(q, "13:32", -1*dq));
        train658.add(new StationInfo(p, "13:43", -1*dp));
        train658.add(new StationInfo(o, "14:02", -1*dO));
        train658.add(new StationInfo(n, "14:20", -1*dn));
        train658.add(new StationInfo(e, "14:51", -1*de));
        train658.add(new StationInfo(d, "15:05", -1*dd));
        train658.add(new StationInfo(c, "15:18", -1*dc));
        train658.add(new StationInfo(a, "15:40", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("658", train658, DayType.DAILY));

        List<StationInfo> train659 = new ArrayList<>();
        train659.add(new StationInfo(a, "13:09", da));
        train659.add(new StationInfo(c, "13:31", dc));
        train659.add(new StationInfo(d, "13:43", dd));
        train659.add(new StationInfo(e, "13:56", de));
        train659.add(new StationInfo(n, "14:26", dn));
        train659.add(new StationInfo(p, "14:58", dp));
        train659.add(new StationInfo(q, "15:08", dq));
        train659.add(new StationInfo(r, "15:34", dr));
        ALL_SCHEDULES.add(new TrainSchedule("659", train659, DayType.DAILY));

        List<StationInfo> train660 = new ArrayList<>();
        train660.add(new StationInfo(r, "14:00", -1*dr));
        train660.add(new StationInfo(q, "14:27", -1*dq));
        train660.add(new StationInfo(p, "14:38", -1*dp));
        train660.add(new StationInfo(o, "14:57", -1*dO));
        train660.add(new StationInfo(n, "15:15", -1*dn));
        train660.add(new StationInfo(m, "15:35", -1*dm));
        train660.add(new StationInfo(d, "16:02", -1*dd));
        train660.add(new StationInfo(b, "16:20", -1*db));
        train660.add(new StationInfo(a, "16:37", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("660", train660, DayType.DAILY));

        List<StationInfo> train661 = new ArrayList<>();
        train661.add(new StationInfo(a, "16:11", da));
        train661.add(new StationInfo(b, "16:29", db));
        train661.add(new StationInfo(m, "17:06", dm));
        train661.add(new StationInfo(n, "17:26", dn));
        train661.add(new StationInfo(p, "17:58", dp));
        train661.add(new StationInfo(q, "18:08", dq));
        train661.add(new StationInfo(r, "18:34", dr));
        ALL_SCHEDULES.add(new TrainSchedule("661", train661, DayType.DAILY));

        List<StationInfo> train662 = new ArrayList<>();
        train662.add(new StationInfo(r, "16:41", -1*dr));
        train662.add(new StationInfo(q, "17:08", -1*dq));
        train662.add(new StationInfo(p, "17:19", -1*dp));
        train662.add(new StationInfo(o, "17:38", -1*dO));
        train662.add(new StationInfo(n, "17:57", -1*dn));
        train662.add(new StationInfo(e, "18:29", -1*de));
        train662.add(new StationInfo(b, "18:55", -1*db));
        train662.add(new StationInfo(a, "19:12", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("662", train662, DayType.DAILY));

        List<StationInfo> train663 = new ArrayList<>();
        train663.add(new StationInfo(a, "17:09", da));
        train663.add(new StationInfo(b, "17:27", db));
        train663.add(new StationInfo(e, "17:52", de));
        train663.add(new StationInfo(n, "18:22", dn));
        train663.add(new StationInfo(o, "18:40", dO));
        train663.add(new StationInfo(p, "19:00", dp));
        train663.add(new StationInfo(q, "19:10", dq));
        train663.add(new StationInfo(r, "19:36", dr));
        ALL_SCHEDULES.add(new TrainSchedule("663", train663, DayType.DAILY));

        List<StationInfo> train664 = new ArrayList<>();
        train664.add(new StationInfo(r, "19:01", -1*dr));
        train664.add(new StationInfo(q, "19:29", -1*dq));
        train664.add(new StationInfo(p, "19:40", -1*dp));
        train664.add(new StationInfo(o, "19:59", -1*dO));
        train664.add(new StationInfo(n, "20:17", -1*dn));
        train664.add(new StationInfo(m, "20:37", -1*dm));
        train664.add(new StationInfo(e, "20:55", -1*de));
        train664.add(new StationInfo(c, "21:16", -1*dc));
        train664.add(new StationInfo(b, "21:27", -1*db));
        train664.add(new StationInfo(a, "21:44", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("664", train664, DayType.DAILY));

        List<StationInfo> train665 = new ArrayList<>();
        train665.add(new StationInfo(a, "19:08", da));
        train665.add(new StationInfo(b, "19:26", db));
        train665.add(new StationInfo(c, "19:36", dc));
        train665.add(new StationInfo(e, "19:56", de));
        train665.add(new StationInfo(m, "20:13", dm));
        train665.add(new StationInfo(n, "20:33", dn));
        train665.add(new StationInfo(o, "20:51", dO));
        train665.add(new StationInfo(p, "21:11", dp));
        train665.add(new StationInfo(q, "21:21", dq));
        train665.add(new StationInfo(r, "21:47", dr));
        ALL_SCHEDULES.add(new TrainSchedule("665", train665, DayType.DAILY));

        List<StationInfo> train666 = new ArrayList<>();
        train666.add(new StationInfo(r, "20:22", -1*dr));
        train666.add(new StationInfo(q, "20:49", -1*dq));
        train666.add(new StationInfo(p, "21:00", -1*dp));
        train666.add(new StationInfo(o, "21:19", -1*dO));
        train666.add(new StationInfo(n, "21:37", -1*dn));
        train666.add(new StationInfo(m, "21:57", -1*dm));
        train666.add(new StationInfo(e, "22:15", -1*de));
        train666.add(new StationInfo(c, "22:36", -1*dc));
        train666.add(new StationInfo(b, "22:47", -1*db));
        train666.add(new StationInfo(a, "23:04", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("666", train666, DayType.DAILY));

        List<StationInfo> train667 = new ArrayList<>();
        train667.add(new StationInfo(a, "21:08", da));
        train667.add(new StationInfo(b, "21:26", db));
        train667.add(new StationInfo(d, "21:44", dd));
        train667.add(new StationInfo(m, "22:09", dm));
        train667.add(new StationInfo(n, "22:29", dn));
        train667.add(new StationInfo(p, "23:00", dp));
        train667.add(new StationInfo(q, "23:10", dq));
        train667.add(new StationInfo(r, "23:36", dr));
        ALL_SCHEDULES.add(new TrainSchedule("667", train667, DayType.DAILY));

        List<StationInfo> train668 = new ArrayList<>();
        train668.add(new StationInfo(r, "22:25", -1*dr));
        train668.add(new StationInfo(q, "22:52", -1*dq));
        train668.add(new StationInfo(p, "23:03", -1*dp));
        train668.add(new StationInfo(o, "23:22", -1*dO));
        train668.add(new StationInfo(n, "23:40", -1*dn));
        train668.add(new StationInfo(d, "24:19", -1*dd));
        train668.add(new StationInfo(b, "24:37", -1*db));
        train668.add(new StationInfo(a, "24:54", -1*da));
        ALL_SCHEDULES.add(new TrainSchedule("668", train668, DayType.DAILY));


        //----전라선----------

        List<StationInfo> train681 = new ArrayList<>();
        train681.add(new StationInfo(a, "08:05", da));
        train681.add(new StationInfo(b, "08:22", db));
        train681.add(new StationInfo(c, "08:32", dc));
        train681.add(new StationInfo(d, "08:45", dd));
        train681.add(new StationInfo(e, "08:57", de));
        train681.add(new StationInfo(m, "09:15", dm));
        train681.add(new StationInfo(n, "09:15", dn));
        train681.add(new StationInfo(z, "10:00", dz));
        train681.add(new StationInfo(z2, "10:00", dz2));
        train681.add(new StationInfo(z3, "10:18", dz3));
        train681.add(new StationInfo(z4, "10:31", dz4));
        train681.add(new StationInfo(z5, "10:52", dz5));
        train681.add(new StationInfo(z6, "10:52", dz6));
        train681.add(new StationInfo(z7, "10:52", dz7));
        ALL_SCHEDULES.add(new TrainSchedule("681", train681, DayType.DAILY));

        List<StationInfo> train682 = new ArrayList<>();
        train682.add(new StationInfo(z7, "06:45", dz7*-1));
        train682.add(new StationInfo(z6, "06:53", dz6*-1));
        train682.add(new StationInfo(z5, "07:07", dz5*-1));
        train682.add(new StationInfo(z4, "07:22", dz4*-1));
        train682.add(new StationInfo(z3, "07:33", dz3*-1));
        train682.add(new StationInfo(z2, "07:44", dz2*-1));
        train682.add(new StationInfo(z, "08:10", dz*-1));
        train682.add(new StationInfo(n, "08:30", dn*-1));
        train682.add(new StationInfo(m, "08:49", dm*-1));
        train682.add(new StationInfo(e, "09:07", de*-1));
        train682.add(new StationInfo(c, "09:28", dc*-1));
        train682.add(new StationInfo(b, "09:39", db*-1));
        train682.add(new StationInfo(a, "09:56", da*-1));
        ALL_SCHEDULES.add(new TrainSchedule("682", train682, DayType.DAILY));

        List<StationInfo> train683 = new ArrayList<>();
        train683.add(new StationInfo(a, "19:08", da));
        train683.add(new StationInfo(b, "19:26", db));
        train683.add(new StationInfo(c, "19:36", dc));
        train683.add(new StationInfo(e, "19:56", de));
        train683.add(new StationInfo(m, "20:13", dm));
        train683.add(new StationInfo(n, "20:36", dn));
        train683.add(new StationInfo(z, "20:53", dz));
        train683.add(new StationInfo(z2, "21:20", dz2));
        train683.add(new StationInfo(z3, "21:30", dz3));
        train683.add(new StationInfo(z4, "21:41", dz4));
        train683.add(new StationInfo(z5, "21:57", dz5));
        train683.add(new StationInfo(z6, "22:11", dz6));
        train683.add(new StationInfo(z7, "22:20", dz7));
        ALL_SCHEDULES.add(new TrainSchedule("683", train683, DayType.DAILY));

        List<StationInfo> train684 = new ArrayList<>();
        train684.add(new StationInfo(z7, "14:30", dz7*-1));
        train684.add(new StationInfo(z6, "14:38", dz6*-1));
        train684.add(new StationInfo(z5, "14:51", dz5*-1));
        train684.add(new StationInfo(z4, "15:06", dz4*-1));
        train684.add(new StationInfo(z3, "15:18", dz3*-1));
        train684.add(new StationInfo(z2, "15:29", dz2*-1));
        train684.add(new StationInfo(z, "15:55", dz*-1));
        train684.add(new StationInfo(n, "16:15", dn*-1));
        train684.add(new StationInfo(m, "16:34", dm*-1));
        train684.add(new StationInfo(c, "17:08", dc*-1));
        train684.add(new StationInfo(b, "17:19", db*-1));
        train684.add(new StationInfo(a, "17:36", da*-1));
        ALL_SCHEDULES.add(new TrainSchedule("684", train684, DayType.DAILY));
    }

    public static List<String> getFilteredTrainNumbers(DayType filterType) {
        List<String> filteredTrainNumbers = new ArrayList<>();

        for (TrainSchedule schedule : ALL_SCHEDULES) {
            if (filterType == DayType.WEEKEND_ONLY) {
                // weekend 선택 시 모든 열차 표시
                if (schedule.dayType == DayType.DAILY || schedule.dayType == DayType.WEEKEND_ONLY) {
                    filteredTrainNumbers.add(schedule.trainNum);
                }
            } else {
                // daily 선택 시 금토일 열차 제외
                if (schedule.dayType == DayType.DAILY) {
                    filteredTrainNumbers.add(schedule.trainNum);
                }
            }
        }
        return filteredTrainNumbers;
    }


     //특정 열차 번호 정차역 목록 반환
    public static List<StationInfo> getStationsForTrain(String trainNum) {
        for (TrainSchedule schedule : ALL_SCHEDULES) {
            if (schedule.trainNum.equals(trainNum)) {
                return schedule.stations;
            }
        }
        // 만약 데이터가 없으면 빈 리스트 반환
        return new ArrayList<>();
    }

    public static int[] calculateTravelStats(StationInfo start, StationInfo end) {
        int[] stats = new int[2]; // [시간, 거리]
        try {
            long startTimeMs = start.parseTimeToMillis();
            long endTimeMs = end.parseTimeToMillis();
            if (startTimeMs == 0 || endTimeMs == 0) throw new Exception("Time is zero");

            int travelTimeMin = (int) ((endTimeMs - startTimeMs) / (1000 * 60));
            stats[0] = travelTimeMin;

        } catch (Exception e) {
            stats[0] = 0;
        }

        int travelDistKm = end.distanceKm - start.distanceKm;
        stats[1] = travelDistKm;
        return stats;
    }
}