package group.campussecretary.feature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WeatherService{

  //기상청 API URL
  private final StringBuilder apiUrl = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");
  private final String apiKey="StHx15%2FPZFKIJvR5AtCu8uyowAtnEYHpAXR%2B5XYYN6OWDt%2BCz15z%2Fxn%2FDiV%2FLN9%2BY5YSPZUPUnCq982CiBupmw%3D%3D";

  public String getWeatherInfo() throws IOException {
    //파라미터로 오늘 날씨인지 내일 날씨인지를 구분하는 값을 받아오자
    //그리고 오늘 날씨면 오늘 날짜를 넣고
    //내일 날씨면 내일 날짜를 넣음
    String date=getDate();

    //T0- DO: https://dev-coco.tistory.com/31 참고해서 getHour()함수를 갈아 엎자. 6이 아니라 0600의 형태로 나와야 함. 어차피 정시 단위이므로 시간만 있으면 됨

    apiUrl.append("?"+ URLEncoder.encode("serviceKey","UTF-8") + "="+apiKey); //서비스 키
    apiUrl.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
    apiUrl.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("600", "UTF-8")); /*한 페이지 결과 수*/
    apiUrl.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
    apiUrl.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*‘21년 6월 28일 발표*/
    apiUrl.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0000", "UTF-8")); /*06시 발표(정시단위) */
    apiUrl.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("62", "UTF-8")); /*예보지점의 X 좌표값*/
    apiUrl.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("120", "UTF-8")); /*예보지점의 Y 좌표값*/

    URL url = new URL(apiUrl.toString());
    //System.out.println(">>>>>> apiUrl: "+apiUrl.toString()); //확인
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

  
  private String getDate(){
    Date now = new Date();

    //포맷 정의하기
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

    Calendar cal= Calendar.getInstance();

    cal.setTime(now);
    //3시간 전으로 캘린더의 시간을 세팅 - 만약 날짜가 바뀐다면 그것까지 반영됨을 확인했음
    //cal.add(Calendar.HOUR,-3);
    //cal.add(Calendar.DAY_OF_MONTH,1);

    String result= dateFormatter.format(cal.getTime()); //20220616

    return result;
  }


}
