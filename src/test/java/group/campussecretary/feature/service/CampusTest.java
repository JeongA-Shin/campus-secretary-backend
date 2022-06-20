package group.campussecretary.feature.service;

import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import org.springframework.core.io.ClassPathResource;

public class CampusTest {

    @Test
    public void sandbox(){

       CampusService service = new CampusService();

       service.activateBot();
    }

    @Test
    public void sandbox2() throws IOException, ParseException {
      ClassPathResource resource = new ClassPathResource("login.json");
      JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(resource.getInputStream(), "UTF-8"));
      System.out.println(">>>>>>>> "+json.toString());
    }

}
