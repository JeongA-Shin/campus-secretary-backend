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

import org.junit.jupiter.api.Test;

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

    WeatherService service=new WeatherService();

    System.out.println(service.getWeatherInfo());
  }

}
