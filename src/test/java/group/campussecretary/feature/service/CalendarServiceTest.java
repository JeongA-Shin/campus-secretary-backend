package group.campussecretary.feature.service;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;



public class CalendarServiceTest {

  private ResourceLoader resourceLoader;



  @Test
  public void sandbox(){

    //resource에 있는 파일 경로를 어떻게 읽어와야 하는지 테스트
    // src/main/reousrces..이런 식으로 풀로 해줘야 하는지

    InputStream in = CalendarService.class.getResourceAsStream("/credentials.json");  //이렇게만 해줘도 성공임
    System.out.println(in.toString());
  }


  @Test
  public void sandbox2() throws IOException {

  }

  @Test
  public void sandbox3() throws GeneralSecurityException, IOException {

    CalendarService service=new CalendarService();
    service.getCredentials(GoogleNetHttpTransport.newTrustedTransport());

  }

}
