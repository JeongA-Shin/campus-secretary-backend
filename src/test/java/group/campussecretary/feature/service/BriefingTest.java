package group.campussecretary.feature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

public class BriefingTest {

  @Test
  public void sandbox(){

    //given
    List<String> list = new ArrayList<>();
    list.add("코로나");
    list.add("학교");
    String str = list.toString(); //내가 가지고 있는 전제 조건 - 리스트가 string으로 되어 있는 것

    //when
    String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
    str = str.replaceAll(match, " "); // 코로나  학교

    List<String> splitStr = Arrays.asList(str.split("\\s+"));
    /**
     * 코로나
     * 학교
     */

    List<JSONObject> briefing = new ArrayList<>();


  }
}
