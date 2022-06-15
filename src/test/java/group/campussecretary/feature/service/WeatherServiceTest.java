package group.campussecretary.feature.service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

}
