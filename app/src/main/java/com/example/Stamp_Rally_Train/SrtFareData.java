package com.example.Stamp_Rally_Train;

import java.util.HashMap;
import java.util.Map;

public class SrtFareData {
    //h 들어간 변수는 서대구 경유 역 -> 요금이 달라짐
    private static String a="수서", b="동탄",c="평택지제",d="천안아산",e="오송",f="대전", g ="김천구미", h ="서대구", i ="동대구", j ="경주",
            k ="울산", l ="부산", ha="수서 ", hb="동탄 ",hc="평택 ",hd="천안아산 ",he="오송 ",hf="대전 ", hg ="김천 ", hi="동대구 ", hj="신경주 ", hk="울산 ", hl="부산 ",
            m="공주",n="익산",o="정읍",p="광주송정",q="나주",r="목포", s="밀양", t="진영", u="창원중앙", v="창원", w="마산", x="진주",
            y="포항", z="전주",z2="남원",z3="곡성",z4="구례구",z5="순천",z6="여천",z7="여수EXPO";
    private static final Map<String, Map<String, int[]>> FARES = new HashMap<>();

    static {
        /*

        Map<String, int[]> fromSuseo = new HashMap<>();
        fromSuseo.put(a, new int[]{7500, 10900});    // 수서
        fromSuseo.put(b, new int[]{7500, 10900});    // 동탄
        fromSuseo.put(c, new int[]{7700, 11200});    // 평택
        fromSuseo.put(d, new int[]{11300, 16400});   // 천안아산
        fromSuseo.put(e, new int[]{15400, 22300});   // 오송
        fromSuseo.put(f, new int[]{20100, 29100});   // 대전
        fromSuseo.put(g, new int[]{30300, 43900});   // 김천
        fromSuseo.put(i, new int[]{37400, 54200});   // 동대구
        fromSuseo.put(j, new int[]{42700, 61900});   // 경주
        fromSuseo.put(k, new int[]{46800, 67900});   // 울산
        fromSuseo.put(l, new int[]{52600, 76300});   // 부산

        fromSuseo.put(h, new int[]{36400, 52800});  //서대전 경우 시
        fromSuseo.put(hi, new int[]{37000, 53600});  //동대구
        fromSuseo.put(hj, new int[]{42400, 61500});  //신경주
        fromSuseo.put(hk, new int[]{46400, 57300});  //울산
        fromSuseo.put(hl, new int[]{52200, 75700});  //부산

        fromSuseo.put(m, new int[]{21600, 31300});  //공주
        fromSuseo.put(n, new int[]{28000, 40600});  //익산
        fromSuseo.put(o, new int[]{33900, 49200});  //정읍
        fromSuseo.put(p, new int[]{40700, 59000});  //광주송정
        fromSuseo.put(q, new int[]{42100, 61000});  //나주
        fromSuseo.put(r, new int[]{46500, 67400});  //목포

        fromSuseo.put(s, new int[]{42300, 61300});  //밀양
        fromSuseo.put(t, new int[]{44400, 64400});  //진영
        fromSuseo.put(u, new int[]{45600, 66100});  //창원중앙
        fromSuseo.put(v, new int[]{46500, 67400});  //창원
        fromSuseo.put(w, new int[]{46900, 68000});  //마산
        fromSuseo.put(x, new int[]{51100, 74100});  //진주

        fromSuseo.put(y, new int[]{47200, 68400});  //포항

        fromSuseo.put(z, new int[]{30300, 43900});  //전주
        fromSuseo.put(z2, new int[]{35200, 51000});  //남원
        fromSuseo.put(z3, new int[]{36700, 53200});  //곡성
        fromSuseo.put(z4, new int[]{38400, 55700});  //구례구
        fromSuseo.put(z5, new int[]{41000, 59500});  //순천
        fromSuseo.put(z6, new int[]{43100, 62500});  //여천
        fromSuseo.put(z7, new int[]{43800, 63500});  //여수expo

        FARES.put(a, fromSuseo);

        */


        Map<String, int[]> fromSuseo = new HashMap<>();
        fromSuseo.put(b, new int[]{7500, 10900});    // 동탄
        fromSuseo.put(c, new int[]{7700, 11200});    // 평택
        fromSuseo.put(d, new int[]{11300, 16400});   // 천안아산
        fromSuseo.put(e, new int[]{15400, 22300});   // 오송
        fromSuseo.put(f, new int[]{20100, 29100});   // 대전
        fromSuseo.put(g, new int[]{30300, 43900});   // 김천
        fromSuseo.put(i, new int[]{37400, 54200});   // 동대구
        fromSuseo.put(j, new int[]{42700, 61900});   // 경주
        fromSuseo.put(k, new int[]{46800, 67900});   // 울산
        fromSuseo.put(l, new int[]{52600, 76300});   // 부산

        fromSuseo.put(m, new int[]{21600, 31300});  //공주
        fromSuseo.put(n, new int[]{28000, 40600});  //익산
        fromSuseo.put(o, new int[]{33900, 49200});  //정읍
        fromSuseo.put(p, new int[]{40700, 59000});  //광주송정
        fromSuseo.put(q, new int[]{42100, 61000});  //나주
        fromSuseo.put(r, new int[]{46500, 67400});  //목포

        fromSuseo.put(s, new int[]{42300, 61300});  //밀양
        fromSuseo.put(t, new int[]{44400, 64400});  //진영
        fromSuseo.put(u, new int[]{45600, 66100});  //창원중앙
        fromSuseo.put(v, new int[]{46500, 67400});  //창원
        fromSuseo.put(w, new int[]{46900, 68000});  //마산
        fromSuseo.put(x, new int[]{51100, 74100});  //진주

        fromSuseo.put(y, new int[]{47200, 68400});  //포항

        fromSuseo.put(z, new int[]{30300, 43900});  //전주
        fromSuseo.put(z2, new int[]{35200, 51000});  //남원
        fromSuseo.put(z3, new int[]{36700, 53200});  //곡성
        fromSuseo.put(z4, new int[]{38400, 55700});  //구례구
        fromSuseo.put(z5, new int[]{41000, 59500});  //순천
        fromSuseo.put(z6, new int[]{43100, 62500});  //여천
        fromSuseo.put(z7, new int[]{43800, 63500});  //여수expo

        FARES.put(a, fromSuseo);


        Map<String, int[]> fromDongtan = new HashMap<>();
        fromDongtan.put(a, new int[]{7500, 10900}); // 수서
        fromDongtan.put(c, new int[]{7500, 10900}); // 평택
        fromDongtan.put(d, new int[]{7500, 10900}); // 천안
        fromDongtan.put(e, new int[]{10700, 15500}); // 오송
        fromDongtan.put(f, new int[]{15400, 22300}); // 대전
        fromDongtan.put(g, new int[]{25700, 37300}); // 김천
        fromDongtan.put(i, new int[]{32800, 47600}); // 동대구
        fromDongtan.put(j, new int[]{38200, 55400}); // 신경주
        fromDongtan.put(k, new int[]{42100, 61000}); // 울산
        fromDongtan.put(l, new int[]{48100, 69700}); // 부산

        fromDongtan.put(m, new int[]{17000, 24700});  //공주
        fromDongtan.put(n, new int[]{23500, 34100});  //익산
        fromDongtan.put(o, new int[]{29300, 42500});  //정읍
        fromDongtan.put(p, new int[]{36200, 52500});  //광주송정
        fromDongtan.put(q, new int[]{37600, 54500});  //나주
        fromDongtan.put(r, new int[]{42100, 61000});  //목포

        fromDongtan.put(s, new int[]{37600, 54500});  //밀양
        fromDongtan.put(t, new int[]{39900, 57900});  //진영
        fromDongtan.put(u, new int[]{41200, 59700});  //창원중앙
        fromDongtan.put(v, new int[]{42000, 60900});  //창원
        fromDongtan.put(w, new int[]{42300, 61300});  //마산
        fromDongtan.put(x, new int[]{46700, 67700});  //진주

        fromDongtan.put(y, new int[]{42600, 61800});  //포항

        fromDongtan.put(z, new int[]{25700, 37300});  //전주
        fromDongtan.put(z2, new int[]{30500, 44200});  //남원
        fromDongtan.put(z3, new int[]{32200, 46700});  //곡성
        fromDongtan.put(z4, new int[]{34000, 49300});  //구례구
        fromDongtan.put(z5, new int[]{36300, 52600});  //순천
        fromDongtan.put(z6, new int[]{38600, 56000});  //여천
        fromDongtan.put(z7, new int[]{39500, 57300});  //여수expo

        FARES.put(b, fromDongtan);

        Map<String, int[]> fromPyeongtaek = new HashMap<>();
        fromPyeongtaek.put(a, new int[]{7500, 11200});   // 수서
        fromPyeongtaek.put(b, new int[]{7500, 10900});   // 동탄
        fromPyeongtaek.put(d, new int[]{7500, 10900});   // 천안아산
        fromPyeongtaek.put(e, new int[]{7700, 11200});   // 오송
        fromPyeongtaek.put(f, new int[]{12400, 18000});   // 대전
        fromPyeongtaek.put(g, new int[]{22800, 33100});   // 김천
        fromPyeongtaek.put(i, new int[]{29800, 43200});   // 동대구
        fromPyeongtaek.put(j, new int[]{35300, 51200});   // 경주
        fromPyeongtaek.put(k, new int[]{39200, 56800});   // 울산
        fromPyeongtaek.put(l, new int[]{45100, 65400});   // 부산

        fromPyeongtaek.put(m, new int[]{14000, 20300});  //공주
        fromPyeongtaek.put(n, new int[]{20400, 29600});  //익산
        fromPyeongtaek.put(o, new int[]{26400, 38300});  //정읍
        fromPyeongtaek.put(p, new int[]{33300, 48300});  //광주송정
        fromPyeongtaek.put(q, new int[]{34700, 50300});  //나주
        fromPyeongtaek.put(r, new int[]{39100, 56700});  //목포

        fromPyeongtaek.put(s, new int[]{34800, 50500});  //밀양
        fromPyeongtaek.put(t, new int[]{37100, 53800});  //진영
        fromPyeongtaek.put(u, new int[]{38200, 55400});  //창원중앙
        fromPyeongtaek.put(v, new int[]{39100, 56700});  //창원
        fromPyeongtaek.put(w, new int[]{39400, 57100});  //마산
        fromPyeongtaek.put(x, new int[]{43800, 63500});  //진주

        fromPyeongtaek.put(y, new int[]{39700, 57600});  //포항

        fromPyeongtaek.put(z, new int[]{22800, 33100});  //전주
        fromPyeongtaek.put(z2, new int[]{27600, 40000});  //남원
        fromPyeongtaek.put(z3, new int[]{29100, 42200});  //곡성
        fromPyeongtaek.put(z4, new int[]{31000, 45000});  //구례구
        fromPyeongtaek.put(z5, new int[]{33500,48600});  //순천
        fromPyeongtaek.put(z6, new int[]{35700, 51800});  //여천
        fromPyeongtaek.put(z7, new int[]{36500, 52900});  //여수expo

        FARES.put(c, fromPyeongtaek);

        Map<String, int[]> fromCheonan = new HashMap<>();
        fromCheonan.put(a, new int[]{11300, 16400});   // 수서
        fromCheonan.put(b, new int[]{7500, 10900});   // 동탄
        fromCheonan.put(c, new int[]{7500, 10900});   // 평택
        fromCheonan.put(e, new int[]{7500, 10900});   // 오송
        fromCheonan.put(f, new int[]{8800, 12800});   // 대전
        fromCheonan.put(g, new int[]{19200, 27800});   // 김천
        fromCheonan.put(i, new int[]{26300, 38100});   // 동대구
        fromCheonan.put(j, new int[]{31800, 46100});   // 경주
        fromCheonan.put(k, new int[]{35700, 51800});   // 울산
        fromCheonan.put(l, new int[]{41600, 60300});   // 부산

        fromCheonan.put(m, new int[]{10400, 15100});  //공주
        fromCheonan.put(n, new int[]{16900, 24500});  //익산
        fromCheonan.put(o, new int[]{22800, 33100});  //정읍
        fromCheonan.put(p, new int[]{29800, 43200});  //광주송정
        fromCheonan.put(q, new int[]{31100, 45100});  //나주
        fromCheonan.put(r, new int[]{35600, 51600});  //목포

        fromCheonan.put(s, new int[]{31300, 45400});  //밀양
        fromCheonan.put(t, new int[]{33500, 48600});  //진영
        fromCheonan.put(u, new int[]{34700, 50300});  //창원중앙
        fromCheonan.put(v, new int[]{35700, 51800});  //창원
        fromCheonan.put(w, new int[]{36000, 52200});  //마산
        fromCheonan.put(x, new int[]{40300, 58400});  //진주

        fromCheonan.put(y, new int[]{36200, 52500});  //포항

        fromCheonan.put(z, new int[]{19200, 27800});  //전주
        fromCheonan.put(z2, new int[]{24100, 34900});  //남원
        fromCheonan.put(z3, new int[]{25600, 37100});  //곡성
        fromCheonan.put(z4, new int[]{27400, 39700});  //구례구
        fromCheonan.put(z5, new int[]{29900, 43400});  //순천
        fromCheonan.put(z6, new int[]{32200, 46700});  //여천
        fromCheonan.put(z7, new int[]{32900, 47700});  //여수expo

        FARES.put(d, fromCheonan);

        Map<String, int[]> fromOsong = new HashMap<>();
        fromOsong.put(a, new int[]{15400, 22300});   // 수서
        fromOsong.put(b, new int[]{10700, 15500});   // 동탄
        fromOsong.put(c, new int[]{7700, 11200});   // 평택
        fromOsong.put(d, new int[]{7500, 10900});   // 천안
        fromOsong.put(f, new int[]{7500, 10900});   // 대전
        fromOsong.put(g, new int[]{15100, 21900});   // 김천
        fromOsong.put(i, new int[]{22300, 32300});   // 동대구
        fromOsong.put(j, new int[]{27700, 40200});   // 경주
        fromOsong.put(k, new int[]{31700, 46000});   // 울산
        fromOsong.put(l, new int[]{37700, 54700});   // 부산

        fromOsong.put(m, new int[]{7500, 10900});  //공주
        fromOsong.put(n, new int[]{12800, 18600});  //익산
        fromOsong.put(o, new int[]{18700, 27100});  //정읍
        fromOsong.put(p, new int[]{25600, 37100});  //광주송정
        fromOsong.put(q, new int[]{27100, 39300});  //나주
        fromOsong.put(r, new int[]{31600, 45800});  //목포

        fromOsong.put(s, new int[]{27200, 39400});  //밀양
        fromOsong.put(t, new int[]{29400, 42600});  //진영
        fromOsong.put(u, new int[]{30700, 44500});  //창원중앙
        fromOsong.put(v, new int[]{31600, 45800});  //창원
        fromOsong.put(w, new int[]{32000, 46400});  //마산
        fromOsong.put(x, new int[]{36300, 52600});  //진주

        fromOsong.put(y, new int[]{32200, 46700});  //포항

        fromOsong.put(z, new int[]{15100, 21900});  //전주
        fromOsong.put(z2, new int[]{20000, 29000});  //남원
        fromOsong.put(z3, new int[]{21600, 31300});  //곡성
        fromOsong.put(z4, new int[]{23500, 34100});  //구례구
        fromOsong.put(z5, new int[]{25800, 37400});  //순천
        fromOsong.put(z6, new int[]{28100, 40700});  //여천
        fromOsong.put(z7, new int[]{29000, 24100});  //여수expo

        FARES.put(e, fromOsong);

        Map<String, int[]> fromDaejeon = new HashMap<>();
        fromDaejeon.put(a, new int[]{20100, 29100});   // 수서
        fromDaejeon.put(b, new int[]{15400, 22300});   // 동탄
        fromDaejeon.put(c, new int[]{12400, 18000});   // 평택
        fromDaejeon.put(d, new int[]{8800, 12800});   // 천안
        fromDaejeon.put(e, new int[]{7500, 10900});   // 오송
        fromDaejeon.put(g, new int[]{10400, 15100});   // 김천
        fromDaejeon.put(i, new int[]{17600, 25500});   // 동대구
        fromDaejeon.put(j, new int[]{23100, 33500});   // 경주
        fromDaejeon.put(k, new int[]{27100, 39300});   // 울산
        fromDaejeon.put(l, new int[]{33000, 47900});   // 부산

        fromDaejeon.put(s, new int[]{22700, 32900});  //밀양
        fromDaejeon.put(t, new int[]{24900, 36100});  //진영
        fromDaejeon.put(u, new int[]{26000, 37700});  //창원중앙
        fromDaejeon.put(v, new int[]{26900, 39000});  //창원
        fromDaejeon.put(w, new int[]{27300, 39600});  //마산
        fromDaejeon.put(x, new int[]{31800, 46100});  //진주

        fromDaejeon.put(y, new int[]{27600, 40000});  //포항

        fromDaejeon.put(z, new int[]{30300, 43900});  //전주
        fromDaejeon.put(z2, new int[]{35200, 51000});  //남원
        fromDaejeon.put(z3, new int[]{36700, 53200});  //곡성
        fromDaejeon.put(z4, new int[]{38400, 55700});  //구례구
        fromDaejeon.put(z5, new int[]{41000, 59500});  //순천
        fromDaejeon.put(z6, new int[]{43100, 62500});  //여천
        fromDaejeon.put(z7, new int[]{43800, 63500});  //여수expo

        FARES.put(f, fromDaejeon);

        Map<String, int[]> fromGimcheon = new HashMap<>();
        fromGimcheon.put(a, new int[]{30300, 43900});    // 수서
        fromGimcheon.put(b, new int[]{25700, 37300});    // 동탄
        fromGimcheon.put(c, new int[]{22800, 33100});    // 평택
        fromGimcheon.put(d, new int[]{19200, 27800});   // 천안아산
        fromGimcheon.put(e, new int[]{15100, 21900});   // 오송
        fromGimcheon.put(f, new int[]{10400, 15100});   // 대전
        fromGimcheon.put(i, new int[]{7500, 10900});   // 동대구
        fromGimcheon.put(j, new int[]{12800, 18600});   // 경주
        fromGimcheon.put(k, new int[]{16700, 24200});   // 울산
        fromGimcheon.put(l, new int[]{22800, 33100});   // 부산

        fromGimcheon.put(s, new int[]{12200, 17700});  //밀양
        fromGimcheon.put(t, new int[]{14500, 21000});  //진영
        fromGimcheon.put(u, new int[]{15800, 22900});  //창원중앙
        fromGimcheon.put(v, new int[]{16700, 24200});  //창원
        fromGimcheon.put(w, new int[]{17100, 24800});  //마산
        fromGimcheon.put(x, new int[]{21400, 31000});  //진주

        fromGimcheon.put(y, new int[]{17300, 25100});  //포항

        FARES.put(g, fromGimcheon);

        Map<String, int[]> fromDongdaegu = new HashMap<>();
        fromDongdaegu.put(a, new int[]{37400, 54200});    // 수서
        fromDongdaegu.put(b, new int[]{32800, 47600});    // 동탄
        fromDongdaegu.put(c, new int[]{29800, 43200});    // 평택
        fromDongdaegu.put(d, new int[]{26300, 38100});   // 천안아산
        fromDongdaegu.put(e, new int[]{22300, 32300});   // 오송
        fromDongdaegu.put(f, new int[]{17600, 25500});   // 대전
        fromDongdaegu.put(g, new int[]{7500, 10900});   // 김천
        fromDongdaegu.put(j, new int[]{7500, 10900});   // 경주
        fromDongdaegu.put(k, new int[]{9500, 13800});   // 울산
        fromDongdaegu.put(l, new int[]{15600, 22600});   // 부산

        fromDongdaegu.put(s, new int[]{7500, 10900});  //밀양
        fromDongdaegu.put(t, new int[]{7900, 11500});  //진영
        fromDongdaegu.put(u, new int[]{9300, 13500});  //창원중앙
        fromDongdaegu.put(v, new int[]{10300, 14900});  //창원
        fromDongdaegu.put(w, new int[]{10700, 15500});  //마산
        fromDongdaegu.put(x, new int[]{15500, 22500});  //진주

        fromDongdaegu.put(y, new int[]{10100, 14600});  //포항

        FARES.put(i, fromDongdaegu);

        Map<String, int[]> fromSingyeongju = new HashMap<>();
        fromSingyeongju.put(a, new int[]{42700, 61900});    // 수서
        fromSingyeongju.put(b, new int[]{38200, 55400});    // 동탄
        fromSingyeongju.put(c, new int[]{35300, 51200});    // 평택
        fromSingyeongju.put(d, new int[]{31800, 46100});   // 천안아산
        fromSingyeongju.put(e, new int[]{27700, 40200});   // 오송
        fromSingyeongju.put(f, new int[]{23100, 33500});   // 대전
        fromSingyeongju.put(g, new int[]{12800, 18600});   // 김천
        fromSingyeongju.put(i, new int[]{7500, 10900});   // 동대구
        fromSingyeongju.put(k, new int[]{7500, 10900});   // 울산
        fromSingyeongju.put(l, new int[]{10100, 14600});   // 부산

        FARES.put(j, fromSingyeongju);

        Map<String, int[]> fromUlsan = new HashMap<>();
        fromUlsan.put(a, new int[]{46800, 67900});    // 수서
        fromUlsan.put(b, new int[]{42100, 61000});    // 동탄
        fromUlsan.put(c, new int[]{39200, 56800});    // 평택
        fromUlsan.put(d, new int[]{35700, 51800});   // 천안아산
        fromUlsan.put(e, new int[]{31700, 46000});   // 오송
        fromUlsan.put(f, new int[]{27100, 39300});   // 대전
        fromUlsan.put(g, new int[]{16700, 24200});   // 김천
        fromUlsan.put(i, new int[]{9500, 13800});   // 동대구
        fromUlsan.put(j, new int[]{7500, 13800});   // 경주
        fromUlsan.put(l, new int[]{7500, 10900});   // 부산

        FARES.put(k, fromUlsan);

        Map<String, int[]> fromBusan = new HashMap<>();
        fromBusan.put(a, new int[]{52600, 76300});    // 수서
        fromBusan.put(b, new int[]{48100, 69700});    // 동탄
        fromBusan.put(c, new int[]{45100, 65400});    // 평택
        fromBusan.put(d, new int[]{41600, 60300});   // 천안아산
        fromBusan.put(e, new int[]{37700, 54700});   // 오송
        fromBusan.put(f, new int[]{33000, 47900});   // 대전
        fromBusan.put(g, new int[]{22800, 33100});   // 김천
        fromBusan.put(i, new int[]{15600, 22600});   // 동대구
        fromBusan.put(j, new int[]{10100, 14600});   // 경주
        fromBusan.put(k, new int[]{7500, 10900});   // 울산

        FARES.put(l, fromBusan);

        Map<String, int[]> fromGongju = new HashMap<>();
        fromGongju.put(a, new int[]{21600, 31300});    // 수서
        fromGongju.put(b, new int[]{17000, 24700});    // 동탄
        fromGongju.put(c, new int[]{14000, 20300});    // 평택
        fromGongju.put(d, new int[]{10400, 15100});   // 천안아산
        fromGongju.put(e, new int[]{7500, 10900});   // 오송
        fromGongju.put(n, new int[]{7500, 10900});  //익산
        fromGongju.put(o, new int[]{12400, 18000});  //정읍
        fromGongju.put(p, new int[]{19400, 28100});  //광주송정
        fromGongju.put(q, new int[]{20900, 30300});  //나주
        fromGongju.put(r, new int[]{24000, 36800});  //목포

        fromGongju.put(z, new int[]{8800, 12800});  //전주
        fromGongju.put(z2, new int[]{13700, 19900});  //남원
        fromGongju.put(z3, new int[]{15400, 22300});  //곡성
        fromGongju.put(z4, new int[]{17200, 24900});  //구례구
        fromGongju.put(z5, new int[]{19700, 28600});  //순천
        fromGongju.put(z6, new int[]{21900, 31800});  //여천
        fromGongju.put(z7, new int[]{22800, 33100});  //여수expo

        FARES.put(m, fromGongju);

        Map<String, int[]> fromIksan = new HashMap<>();
        fromIksan.put(a, new int[]{28000, 40600});    // 수서
        fromIksan.put(b, new int[]{23500, 34100});    // 동탄
        fromIksan.put(c, new int[]{20400, 29600});    // 평택
        fromIksan.put(d, new int[]{16900, 24500});   // 천안아산
        fromIksan.put(e, new int[]{12800, 18600});   // 오송
        fromIksan.put(m, new int[]{7500, 10900});  //공주
        fromIksan.put(o, new int[]{7500, 10900});  //정읍
        fromIksan.put(p, new int[]{13000, 18900});  //광주송정
        fromIksan.put(q, new int[]{14400, 20900});  //나주
        fromIksan.put(r, new int[]{19100, 27700});  //목포

        fromIksan.put(z, new int[]{7500, 10900});  //전주
        fromIksan.put(z2, new int[]{7800, 11300});  //남원
        fromIksan.put(z3, new int[]{9600, 13900});  //곡성
        fromIksan.put(z4, new int[]{11600, 16800});  //구례구
        fromIksan.put(z5, new int[]{14300, 20700});  //순천
        fromIksan.put(z6, new int[]{16800, 24400});  //여천
        fromIksan.put(z7, new int[]{17700, 25700});  //여수expo

        FARES.put(n, fromIksan);

        Map<String, int[]> fromJeongeup = new HashMap<>();
        fromJeongeup.put(a, new int[]{33900, 49200});    // 수서
        fromJeongeup.put(b, new int[]{29300, 42500});    // 동탄
        fromJeongeup.put(c, new int[]{26400, 38300});    // 평택
        fromJeongeup.put(d, new int[]{22800, 33100});   // 천안아산
        fromJeongeup.put(e, new int[]{18700, 27100});   // 오송
        fromJeongeup.put(m, new int[]{12400, 18000});  //공주
        fromJeongeup.put(n, new int[]{7500, 10900});  //익산
        fromJeongeup.put(p, new int[]{7500, 10900});  //광주송정
        fromJeongeup.put(q, new int[]{8400, 12200});  //나주
        fromJeongeup.put(r, new int[]{13000, 18900});  //목포

        FARES.put(o, fromJeongeup);

        Map<String, int[]> fromGwangju = new HashMap<>();
        fromGwangju.put(a, new int[]{40700, 59000});    // 수서
        fromGwangju.put(b, new int[]{36200, 52500});    // 동탄
        fromGwangju.put(c, new int[]{33300, 48300});    // 평택
        fromGwangju.put(d, new int[]{29800, 43200});   // 천안아산
        fromGwangju.put(e, new int[]{25600, 37100});   // 오송
        fromGwangju.put(m, new int[]{19400, 28100});  //공주
        fromGwangju.put(n, new int[]{13000, 18900});  //익산
        fromGwangju.put(o, new int[]{7500, 10900});  //정읍
        fromGwangju.put(q, new int[]{7500, 10900});  //나주
        fromGwangju.put(r, new int[]{7500, 10900});  //목포

        FARES.put(p, fromGwangju);

        Map<String, int[]> fromNaju = new HashMap<>();
        fromNaju.put(a, new int[]{42100, 61000});    // 수서
        fromNaju.put(b, new int[]{37600, 54500});    // 동탄
        fromNaju.put(c, new int[]{34700, 50300});    // 평택
        fromNaju.put(d, new int[]{31100, 45100});   // 천안아산
        fromNaju.put(e, new int[]{27100, 39300});   // 오송
        fromNaju.put(m, new int[]{20900, 30300});  //공주
        fromNaju.put(n, new int[]{14400, 20900});  //익산
        fromNaju.put(o, new int[]{8400, 12200});  //정읍
        fromNaju.put(p, new int[]{7500, 10900});  //광주송정
        fromNaju.put(r, new int[]{7500, 10900});  //목포

        FARES.put(q, fromNaju);

        Map<String, int[]> fromMokpo = new HashMap<>();
        fromMokpo.put(a, new int[]{46500, 67400});    // 수서
        fromMokpo.put(b, new int[]{42100, 61000});    // 동탄
        fromMokpo.put(c, new int[]{39100, 56700});    // 평택
        fromMokpo.put(d, new int[]{35600, 51600});   // 천안아산
        fromMokpo.put(e, new int[]{31600, 45800});   // 오송
        fromMokpo.put(m, new int[]{25400, 36800});  //공주
        fromMokpo.put(n, new int[]{19100, 27700});  //익산
        fromMokpo.put(o, new int[]{13000, 18900});  //정읍
        fromMokpo.put(p, new int[]{7500, 10900});  //광주송정
        fromMokpo.put(q, new int[]{7500, 10900});  //나주

        FARES.put(r, fromMokpo);

        Map<String, int[]> fromMilyang = new HashMap<>();
        fromMilyang.put(a, new int[]{42300, 61300});    // 수서
        fromMilyang.put(b, new int[]{37600, 54500});    // 동탄
        fromMilyang.put(c, new int[]{34800, 50500});    // 평택
        fromMilyang.put(d, new int[]{31300, 45400});   // 천안아산
        fromMilyang.put(e, new int[]{27200, 39400});   // 오송
        fromMilyang.put(f, new int[]{22700, 32900});   // 대전
        fromMilyang.put(g, new int[]{12200, 17700});   // 김천
        fromMilyang.put(i, new int[]{7500, 10900});   // 동대구
        fromMilyang.put(t, new int[]{7500, 10900});  //진영
        fromMilyang.put(u, new int[]{7500, 10900});  //창원중앙
        fromMilyang.put(v, new int[]{7500, 10900});  //창원
        fromMilyang.put(w, new int[]{7500, 10900});  //마산
        fromMilyang.put(x, new int[]{10100, 14600});  //진주

        FARES.put(s, fromMilyang);

        Map<String, int[]> fromJingyeong = new HashMap<>();
        fromJingyeong.put(a, new int[]{44400, 64400});    // 수서
        fromJingyeong.put(b, new int[]{39900, 57900});    // 동탄
        fromJingyeong.put(c, new int[]{37100, 53800});    // 평택
        fromJingyeong.put(d, new int[]{33500, 48600});   // 천안아산
        fromJingyeong.put(e, new int[]{29400, 42600});   // 오송
        fromJingyeong.put(f, new int[]{24900, 36100});   // 대전
        fromJingyeong.put(g, new int[]{14500, 21000});   // 김천
        fromJingyeong.put(i, new int[]{7900, 11500});   // 동대구
        fromJingyeong.put(s, new int[]{7500, 10900});  //밀양
        fromJingyeong.put(u, new int[]{7500, 10900});  //창원중앙
        fromJingyeong.put(v, new int[]{7500, 10900});  //창원
        fromJingyeong.put(w, new int[]{7500, 10900});  //마산
        fromJingyeong.put(x, new int[]{7500, 10900});  //진주

        FARES.put(t, fromJingyeong);

        Map<String, int[]> fromChangwonjungang = new HashMap<>();
        fromChangwonjungang.put(a, new int[]{45600, 66100});    // 수서
        fromChangwonjungang.put(b, new int[]{41200, 59700});    // 동탄
        fromChangwonjungang.put(c, new int[]{38200, 55400});    // 평택
        fromChangwonjungang.put(d, new int[]{34700, 50300});   // 천안아산
        fromChangwonjungang.put(e, new int[]{30700, 44500});   // 오송
        fromChangwonjungang.put(f, new int[]{26000, 37700});   // 대전
        fromChangwonjungang.put(g, new int[]{15800, 22900});   // 김천
        fromChangwonjungang.put(i, new int[]{9300, 13500});   // 동대구
        fromChangwonjungang.put(s, new int[]{7500, 10900});  //밀양
        fromChangwonjungang.put(t, new int[]{7500, 10900});  //진영
        fromChangwonjungang.put(v, new int[]{7500, 10900});  //창원
        fromChangwonjungang.put(w, new int[]{7500, 10900});  //마산
        fromChangwonjungang.put(x, new int[]{7500, 10900});  //진주

        FARES.put(u, fromChangwonjungang);

        Map<String, int[]> fromChangwon = new HashMap<>();
        fromChangwon.put(a, new int[]{46500, 67400});    // 수서
        fromChangwon.put(b, new int[]{42000, 60900});    // 동탄
        fromChangwon.put(c, new int[]{39100, 56700});    // 평택
        fromChangwon.put(d, new int[]{35700, 51800});   // 천안아산
        fromChangwon.put(e, new int[]{31600, 45800});   // 오송
        fromChangwon.put(f, new int[]{26900, 39000});   // 대전
        fromChangwon.put(g, new int[]{16700, 24200});   // 김천
        fromChangwon.put(i, new int[]{10300, 14900});   // 동대구
        fromChangwon.put(s, new int[]{7500, 10900});  //밀양
        fromChangwon.put(t, new int[]{7500, 10900});  //진영
        fromChangwon.put(u, new int[]{7500, 10900});  //창원중앙
        fromChangwon.put(w, new int[]{7500, 10900});  //마산
        fromChangwon.put(x, new int[]{7500, 10900});  //진주

        FARES.put(v, fromChangwon);

        Map<String, int[]> fromMasan = new HashMap<>();
        fromMasan.put(a, new int[]{46900, 68000});    // 수서
        fromMasan.put(b, new int[]{42300, 61300});    // 동탄
        fromMasan.put(c, new int[]{39400, 57100});    // 평택
        fromMasan.put(d, new int[]{36000, 52200});   // 천안아산
        fromMasan.put(e, new int[]{32000, 46400});   // 오송
        fromMasan.put(f, new int[]{27300, 39600});   // 대전
        fromMasan.put(g, new int[]{17100, 24800});   // 김천
        fromMasan.put(i, new int[]{10700, 15500});   // 동대구
        fromMasan.put(s, new int[]{7500, 10900});  //밀양
        fromMasan.put(t, new int[]{7500, 10900});  //진영
        fromMasan.put(u, new int[]{7500, 10900});  //창원중앙
        fromMasan.put(v, new int[]{7500, 10900});  //창원
        fromMasan.put(x, new int[]{7500, 10900});  //진주

        FARES.put(w, fromMasan);

        Map<String, int[]> fromJinju = new HashMap<>();
        fromJinju.put(a, new int[]{51100, 74100});    // 수서
        fromJinju.put(b, new int[]{46700, 67700});    // 동탄
        fromJinju.put(c, new int[]{43800, 63500});    // 평택
        fromJinju.put(d, new int[]{40300, 58400});   // 천안아산
        fromJinju.put(e, new int[]{36300, 52600});   // 오송
        fromJinju.put(f, new int[]{31800, 46100});   // 대전
        fromJinju.put(g, new int[]{21400, 31000});   // 김천
        fromJinju.put(i, new int[]{15500, 22500});   // 동대구
        fromJinju.put(s, new int[]{10100, 14600});  //밀양
        fromJinju.put(t, new int[]{7500, 10900});  //진영
        fromJinju.put(u, new int[]{7500, 10900});  //창원중앙
        fromJinju.put(v, new int[]{7500, 10900});  //창원
        fromJinju.put(w, new int[]{7500, 10900});  //마산

        FARES.put(x, fromJinju);

        Map<String, int[]> fromPohang = new HashMap<>();
        fromPohang.put(a, new int[]{47200, 68400});    // 수서
        fromPohang.put(b, new int[]{42600, 61800});    // 동탄
        fromPohang.put(c, new int[]{39700, 57600});    // 평택
        fromPohang.put(d, new int[]{36200, 52500});   // 천안아산
        fromPohang.put(e, new int[]{32200, 46700});   // 오송
        fromPohang.put(f, new int[]{27600, 40000});   // 대전
        fromPohang.put(g, new int[]{17300, 25100});   // 김천
        fromPohang.put(i, new int[]{10100, 14600});   // 동대구

        FARES.put(y, fromPohang);

        Map<String, int[]> fromJeonju = new HashMap<>();
        fromJeonju.put(a, new int[]{30300, 43900});    // 수서
        fromJeonju.put(b, new int[]{25700, 37300});    // 동탄
        fromJeonju.put(c, new int[]{22800, 33100});    // 평택
        fromJeonju.put(d, new int[]{19200, 27800});   // 천안아산
        fromJeonju.put(e, new int[]{15100, 21900});   // 오송
        fromJeonju.put(m, new int[]{8800, 12800});  //공주
        fromJeonju.put(n, new int[]{7500, 10900});  //익산
        fromJeonju.put(z2, new int[]{7500, 10900});  //남원
        fromJeonju.put(z3, new int[]{7500, 10900});  //곡성
        fromJeonju.put(z4, new int[]{9100, 13200});  //구례구
        fromJeonju.put(z5, new int[]{11800, 17100});  //순천
        fromJeonju.put(z6, new int[]{14300, 20700});  //여천
        fromJeonju.put(z7, new int[]{15300, 22200});  //여수expo

        FARES.put(z, fromJeonju);

        Map<String, int[]> fromNamwon = new HashMap<>();
        fromNamwon.put(a, new int[]{35200, 51000});    // 수서
        fromNamwon.put(b, new int[]{30500, 44200});    // 동탄
        fromNamwon.put(c, new int[]{27600, 40000});    // 평택
        fromNamwon.put(d, new int[]{24100, 34900});   // 천안아산
        fromNamwon.put(e, new int[]{20000, 29000});   // 오송
        fromNamwon.put(m, new int[]{13700, 19900});  //공주
        fromNamwon.put(n, new int[]{7800, 11300});  //익산
        fromNamwon.put(z, new int[]{7500, 10900});  //전주
        fromNamwon.put(z3, new int[]{7500, 10900});  //곡성
        fromNamwon.put(z4, new int[]{7500, 10900});  //구례구
        fromNamwon.put(z5, new int[]{7500, 10900});  //순천
        fromNamwon.put(z6, new int[]{9000, 13100});  //여천
        fromNamwon.put(z7, new int[]{9900, 14400});  //여수expo

        FARES.put(z2, fromNamwon);

        Map<String, int[]> fromGokseong = new HashMap<>();
        fromGokseong.put(a, new int[]{36700, 53200});    // 수서
        fromGokseong.put(b, new int[]{32200, 46700});    // 동탄
        fromGokseong.put(c, new int[]{29100, 42200});    // 평택
        fromGokseong.put(d, new int[]{25600, 37100});   // 천안아산
        fromGokseong.put(e, new int[]{21600, 31300});   // 오송
        fromGokseong.put(m, new int[]{15400, 22300});  //공주
        fromGokseong.put(n, new int[]{9600, 13900});  //익산
        fromGokseong.put(z, new int[]{7500, 10900});  //전주
        fromGokseong.put(z2, new int[]{7500, 10900});  //남원
        fromGokseong.put(z4, new int[]{7500, 10900});  //구례구
        fromGokseong.put(z5, new int[]{7500, 10900});  //순천
        fromGokseong.put(z6, new int[]{7500, 10900});  //여천
        fromGokseong.put(z7, new int[]{8200, 11900});  //여수expo

        FARES.put(z3,fromGokseong);

        Map<String, int[]> fromGurye = new HashMap<>();
        fromGurye.put(a, new int[]{38400, 55700});    // 수서
        fromGurye.put(b, new int[]{34000, 49300});    // 동탄
        fromGurye.put(c, new int[]{31000, 45000});    // 평택
        fromGurye.put(d, new int[]{27400, 39700});   // 천안아산
        fromGurye.put(e, new int[]{23500, 34100});   // 오송
        fromGurye.put(m, new int[]{17200, 24900});  //공주
        fromGurye.put(n, new int[]{11600, 16800});  //익산
        fromGurye.put(z, new int[]{9100, 13200});  //전주
        fromGurye.put(z2, new int[]{7500, 10900});  //남원
        fromGurye.put(z3, new int[]{7500, 10900});  //곡성
        fromGurye.put(z5, new int[]{7500, 10900});  //순천
        fromGurye.put(z6, new int[]{7500, 10900});  //여천
        fromGurye.put(z7, new int[]{7500, 10900});  //여수expo

        FARES.put(z4,fromGurye);

        Map<String, int[]> fromSuncheon = new HashMap<>();
        fromSuncheon.put(a, new int[]{41000, 59500});    // 수서
        fromSuncheon.put(b, new int[]{36300, 52600});    // 동탄
        fromSuncheon.put(c, new int[]{33500, 48600});    // 평택
        fromSuncheon.put(d, new int[]{29900, 43400});   // 천안아산
        fromSuncheon.put(e, new int[]{25800, 37400});   // 오송
        fromSuncheon.put(m, new int[]{19700, 28600});  //공주
        fromSuncheon.put(n, new int[]{14300, 20700});  //익산
        fromSuncheon.put(z, new int[]{11800, 17100});  //전주
        fromSuncheon.put(z2, new int[]{7500, 10900});  //남원
        fromSuncheon.put(z3, new int[]{7500, 10900});  //곡성
        fromSuncheon.put(z4, new int[]{7500, 10900});  //구례구
        fromSuncheon.put(z6, new int[]{7500, 10900});  //여천
        fromSuncheon.put(z7, new int[]{7500, 10900});  //여수expo

        FARES.put(z5, fromSuncheon);

        Map<String, int[]> fromYeocheon = new HashMap<>();
        fromYeocheon.put(a, new int[]{43100, 62500});    // 수서
        fromYeocheon.put(b, new int[]{38600, 56000});    // 동탄
        fromYeocheon.put(c, new int[]{35700, 51800});    // 평택
        fromYeocheon.put(d, new int[]{32200, 46700});   // 천안아산
        fromYeocheon.put(e, new int[]{28100, 40700});   // 오송
        fromYeocheon.put(m, new int[]{21900, 31800});  //공주
        fromYeocheon.put(n, new int[]{16800, 24400});  //익산
        fromYeocheon.put(z, new int[]{14300, 20700});  //전주
        fromYeocheon.put(z2, new int[]{9000, 13100});  //남원
        fromYeocheon.put(z3, new int[]{7500, 10900});  //곡성
        fromYeocheon.put(z4, new int[]{7500, 10900});  //구례구
        fromYeocheon.put(z5, new int[]{7500, 10900});  //순천
        fromYeocheon.put(z7, new int[]{7500, 10900});  //여수expo

        FARES.put(z6 , fromYeocheon);


        Map<String, int[]> fromYeoSu = new HashMap<>();
        fromYeoSu.put(a, new int[]{43800, 63500});    // 수서
        fromYeoSu.put(b, new int[]{39500, 57300});    // 동탄
        fromYeoSu.put(c, new int[]{36500, 52900});    // 평택
        fromYeoSu.put(d, new int[]{32900, 47700});   // 천안아산
        fromYeoSu.put(e, new int[]{29000, 42100});   // 오송
        fromYeoSu.put(m, new int[]{22800, 33100});  //공주
        fromYeoSu.put(n, new int[]{17700, 25700});  //익산
        fromYeoSu.put(z, new int[]{15300, 22200});  //전주
        fromYeoSu.put(z2, new int[]{9900, 14400});  //남원
        fromYeoSu.put(z3, new int[]{8200, 11900});  //곡성
        fromYeoSu.put(z4, new int[]{7500, 10900});  //구례구
        fromYeoSu.put(z5, new int[]{7500, 10900});  //순천
        fromYeoSu.put(z6, new int[]{7500, 10900});  //여천

        FARES.put(z7,fromYeoSu);
    }

    public static int getFare(String startStationName, String endStationName, boolean isFirstClass) {
        if (FARES.containsKey(startStationName)) {
            Map<String, int[]> endStationFares = FARES.get(startStationName);
            if (endStationFares != null && endStationFares.containsKey(endStationName)) {

                int[] fares = endStationFares.get(endStationName);
                if (fares == null || fares.length < 2) {
                    return -1; // 요금 데이터 배열이 잘못된 경우
                }

                if (isFirstClass) {
                    return fares[1]; // 특실 요금
                } else {
                    return fares[0]; // 일반실 요금
                }
            }
        }
        // 요금 데이터가 없는 경우
        return -1;
    }

}