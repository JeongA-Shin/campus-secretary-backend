package group.campussecretary.feature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WeatherService{

  //기상청 단기예보 API URL
  private final StringBuilder apiUrl = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");
  private final String apiKey="StHx15%2FPZFKIJvR5AtCu8uyowAtnEYHpAXR%2B5XYYN6OWDt%2BCz15z%2Fxn%2FDiV%2FLN9%2BY5YSPZUPUnCq982CiBupmw%3D%3D";
  Map<String, String> dateMap=new HashMap<>();

  public String getWeatherInfo() throws IOException {

    //파라미터로 오늘 날씨인지 내일 날씨인지를 구분하는 값을 받아오자
    /* 포스트 맨에서 날짜 바꾸면서 조회해본 결과
    이거는 "예보"이니까 오늘 예측 정보를 보려면 어제 날짜를 baseDate로 해서 get메서드를 보내봐야 함
    오늘 거를 baseDate로 해서 오늘 정보를 받는 거는 단기실황쪽에 더 가까우므로

    따라서 오늘 날씨 예보를 보려면 baseDate를 어제로 해서 조회
    [주의] 근데 내일 날씨 예보를 보려고 오늘 날짜를 baseDate로 해서 조회하면 아직 예보 데이ㅓ가 없어서 {"response":{"header":{"resultCode":"03","resultMsg":"NO_DATA"}}} 이렇게 No Data 에러가 뜸
    따라서 그냥 이거 조회할 때마다 그냥 baseDate를 무조건 어제 기준으로 하고 baseTime을 23시로 해서 - 이렇게하면 1시간 단위로 나오게 되므로 응답에 바로 오늘의 날씨 예측부터 나오게 됨
    그리고 numOfRows를 580으로 해주니까 딱 오늘부터 시작해서 내일까지의 예보 정보만 나옴. 개수를 늘리면 모레 등 더 먼 미래의 데이터까지 나옴
     오늘이랑 내일 날씨 예보 데이터를 받아오면 됨
    * */

    //일단 호춠해서 멤버변수인 dateMap에 값을 담음
    getDateMap();
    //위의 설명대로 baseDate를 무조건 어제 기준으로 하고 baseTime을 23시로 해서 해야 결과가 깔끔하게 떨어짐
   String date= dateMap.get("yesterday");

    apiUrl.append("?"+ URLEncoder.encode("serviceKey","UTF-8") + "="+apiKey); //서비스 키
    apiUrl.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
    apiUrl.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("580", "UTF-8")); /*한 페이지 결과 수*/
    apiUrl.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
    apiUrl.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*‘21년 6월 28일 발표*/
    apiUrl.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("2300", "UTF-8")); /*06시 발표(정시단위) */
    apiUrl.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("62", "UTF-8")); /*예보지점의 X 좌표값*/
    apiUrl.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("120", "UTF-8")); /*예보지점의 Y 좌표값*/

    URL url = new URL(apiUrl.toString());
    //log.debug(">>>>>> apiUrl: "+apiUrl.toString()); //확인 -
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");

    BufferedReader rd; //성공된 응답이든 실패한 응답이든 결과값이 담김
    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
      rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }else{ //실패 응답이 오면
      rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    }

    StringBuilder sb = new StringBuilder();
    String line;

    //rd에 담긴 응답을 한 줄씩 읽고 한 줄씩 추가
    while ((line = rd.readLine()) != null) {
      sb.append(line);
    }

    rd.close();
    conn.disconnect();

    // {"response":{"header":{"resultCode":"00","resultMsg":"NORMAL_SERVICE"},"body":{"dataType":"JSON","items":{"item":[{"baseDate":"20220616","baseTime":"0000","category":"PTY","nx":62,"ny":120,"obsrValue":"1"},{"baseDate":"20220616","baseTime":"0000","category":"REH","nx":62,"ny":120,"obsrValue":"100"},{"baseDate":"20220616","baseTime":"0000","category":"RN1","nx":62,"ny":120,"obsrValue":"0"},{"baseDate":"20220616","baseTime":"0000","category":"T1H","nx":62,"ny":120,"obsrValue":"17.6"},{"baseDate":"20220616","baseTime":"0000","category":"UUU","nx":62,"ny":120,"obsrValue":"1.4"},{"baseDate":"20220616","baseTime":"0000","category":"VEC","nx":62,"ny":120,"obsrValue":"239"},{"baseDate":"20220616","baseTime":"0000","category":"VVV","nx":62,"ny":120,"obsrValue":"0.8"},{"baseDate":"20220616","baseTime":"0000","category":"WSD","nx":62,"ny":120,"obsrValue":"1.6"}]},"pageNo":1,"numOfRows":600,"totalCount":8}}}
    return sb.toString();
  }

  public void getWantedDayWeatherInfo(String wantedDay) throws IOException, ParseException {

    //일단 분석하기 쉽게 일단 String으로 받았던 오늘 내일 예보 정보를(getWeatherInfo()를 통해 받아옴) jsonObject로 변환함
    JSONParser jsonParser= new JSONParser();
    Object obj= jsonParser.parse(getWeatherInfo());
    //JsonObject weatherInfo=(JsonObject) obj;
    JSONObject weatherInfo = (JSONObject) obj;

    //얻어야 하는 정보: 최고 기온, 최저 기온, 강수량
    JSONObject response = (JSONObject) weatherInfo.get("response");
    //log.debug(String.valueOf(weatherInfo.get("response"))); // 잘 나옴
    JSONObject respBody = (JSONObject) response.get("body");
    //log.debug(String.valueOf(respBody));
    JSONObject items = (JSONObject) respBody.get("items");
    //log.debug(String.valueOf(items));
    List<JSONObject> item = (List<JSONObject>) items.get("item");
    //log.debug(String.valueOf(item));

    //정말 반복말고는 답이 없나; >filter를 사용해보자!!
    /* 참고 코드
    List<JSONObject> list = arr.stream()
	.filter(json -> "apple".equals(((JSONObject) json).getString(key)) ///filter(json ->....) 에서 jsonObject는 for문 돌 때 객체에 각각 담기는 거처럼 jsonObject라는 변수 안에 각 리스트의 객체들이 담기는 것
        .collect(Collectors.toList());
     */
    //응답 중에 오늘 날씨만 (오늘 날씨 예보) 만 가져오기
    List<JSONObject> todayList = item.stream().filter(jsonObject -> dateMap.get("today").equals(jsonObject.get("fcstDate"))).collect(
        Collectors.toList());
    //||dateMap.get("tomorrow").equals(jsonObject.get("fcstDate"))
    //log.debug(String.valueOf(todayList));

    //이제 일 최저, 최고 기온 구하기
    List<JSONObject> highAndLowTmp = todayList.stream().filter(jsonObject -> "TMN".equals(jsonObject.get("category"))||"TMX".equals(jsonObject.get("category"))).collect(
        Collectors.toList());
   // log.debug(String.valueOf(highAndLowTmp));
    //JSONObject objectTMN= minimumTemperatureJsonObject.get(0);
//    String minimumTmp = (String) objectTMN.get("fcstValue");
//    log.debug(String.valueOf(minimumTmp));




  }


  public Map<String,String> getDateMap(){
    Date now = new Date();
    //포맷 정의하기
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

    Calendar cal= Calendar.getInstance();

    cal.setTime(now);
    String today= dateFormatter.format(cal.getTime()); //20220616
    dateMap.put("today",today);

    //어제로 캘린더의 시간을 세팅
    cal.add(Calendar.DATE,-1);
    String yesterday = dateFormatter.format(cal.getTime());
    dateMap.put("yesterday",yesterday);


    //내일로 캘린더의 시간을 세팅
    //그런데 위에서 어제 날짜를 구하느라 캘린더가 어제의 시점인 상황
    // 따라서 내일을 구하려고 하면 +2를 해줘야 함 (+1을 해주면 오늘 날짜만 나옴)
    cal.add(Calendar.DATE,2);
    String tomorrow = dateFormatter.format(cal.getTime());
    dateMap.put("tomorrow",tomorrow);

    return dateMap;
  }


}
