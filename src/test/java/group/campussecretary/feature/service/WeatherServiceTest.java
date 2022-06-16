package group.campussecretary.feature.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

@Slf4j
public class WeatherServiceTest {

  @Test
  public void sandbox(){

    // 현재 날짜 구하기
    LocalDate now = LocalDate.now();
    // 포맷 정의
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    //포맷 적용
    String formatNow=now.format(formatter);
    //결과 출력
    System.out.println(formatNow);


  }


  @Test
  public void sandbox2(){

    //현재 시간
    Date now = new Date(); //현재 6시 32분이면
    //포맷 정의하기
    SimpleDateFormat formatter = new SimpleDateFormat("HH");
    Calendar cal= Calendar.getInstance();
    cal.setTime(now);
    //3시간 전으로 캘린더의 시간을 세팅 - 만약 날짜가 바뀐다면 그것까지 반영됨을 확인했음
    cal.add(Calendar.HOUR,-3);
    String result= formatter.format(cal.getTime());
    System.out.println(">>>>>>>>"+result);

  }


  @Test
  public void sandbox3(){
    //현재 시간
    Date now = new Date();

    //포맷 정의하기
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
    Calendar cal= Calendar.getInstance();
    cal.setTime(now);
    //3시간 전으로 캘린더의 시간을 세팅 - 만약 날짜가 바뀐다면 그것까지 반영됨을 확인했음
    //cal.add(Calendar.HOUR,-3);
    //cal.add(Calendar.DAY_OF_MONTH,1);

    String result= dateFormatter.format(cal.getTime());
    System.out.println(">>>>>>>>"+result);
  }

  @Test
  public void sandbox4() throws IOException {
    /* 포스트 맨에서 날짜 바꾸면서 조회해본 결과
    이거는 "예보"이니까 오늘 예측 정보를 보려면 어제 날짜를 baseDate로 해서 get메서드를 보내봐야 함
    오늘 거를 baseDate로 해서 오늘 정보를 받는 거는 단기실황쪽에 더 가까우므로
    * */

    WeatherService service=new WeatherService();
    System.out.println(service.getWeatherInfo());

  }

  @Test
  public void sandbox5() throws IOException {

    WeatherService service=new WeatherService();
    //{yesterday=20220615, today=20220616, tomorrow=20220617} 내가 원한는대로 결과 나옴
    System.out.println(service.getDateMap().toString());

  }

  @Test
  public void sandbox6() throws IOException, ParseException {

    WeatherService service=new WeatherService();
    //사실상 로깅이 정상적으로 되는지 테스트. 로깅을 통해 결과가 잘 나옴을 확인
    service.getWantedDayWeatherInfo("tomorrow");
  }

}
