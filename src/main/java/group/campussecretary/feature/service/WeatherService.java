package group.campussecretary.feature.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WeatherService {

  //기상청 API URL
  private final StringBuilder apiUrl = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
  private final String apiKey="StHx15%2FPZFKIJvR5AtCu8uyowAtnEYHpAXR%2B5XYYN6OWDt%2BCz15z%2Fxn%2FDiV%2FLN9%2BY5YSPZUPUnCq982CiBupmw%3D%3D";

  public void getWeatherInfo() throws UnsupportedEncodingException {

    String todayDate=getTodayDate();
    String hour= String.valueOf(getHour());

    //T0- DO: https://dev-coco.tistory.com/31 참고해서 getHour()함수를 갈아 엎자. 6이 아니라 0600의 형태로 나와야 함. 어차피 정시 단위이므로 시간만 있으면 됨

    apiUrl.append("?"+ URLEncoder.encode("serviceKey","UTF-8") + "="+apiKey); //서비스 키
    apiUrl.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
    apiUrl.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("600", "UTF-8")); /*한 페이지 결과 수*/
    apiUrl.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
    apiUrl.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(todayDate, "UTF-8")); /*‘21년 6월 28일 발표*/
    apiUrl.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*06시 발표(정시단위) */


  }


  private String getTodayDate(){

    // 현재 날짜 구하기
    LocalDate now = LocalDate.now();
    // 포맷 정의
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    //포맷 적용
    String formatedNow=now.format(formatter);
    //결과 출력
    //System.out.println(formatedNow);

    return formatedNow;
  }

  private Integer getHour(){
    //현재 시간
    LocalTime now=LocalTime.now(); //현재 6시 32분이면
    int hour = now.getHour(); //6이 나옴

    ////"예보"이니까 가장 가까운 시를 구해야 함. 예를 들어서 6시 30분에 예보를 보면 7시것을 보는 것이지 6시 것은 이미 지났으므로 쓸모 없음
    return hour+1;
  }


}
