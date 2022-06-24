package group.campussecretary.feature.service;

import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class NewsSearchTest {

  @Test
  public void getClientInfo() throws IOException, ParseException {
    ClassPathResource resource = new ClassPathResource("client.json");
    JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(resource.getInputStream(), "UTF-8"));

    String clientId = json.get("clientId").toString(); //애플리케이션 클라이언트 아이디값"
    String clientSecret = json.get("clientSecret").toString(); //애플리케이션 클라이언트 시크릿값"

    System.out.println("id: "+clientId);
    System.out.println("secret: "+clientSecret);

  }

}
