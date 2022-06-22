package group.campussecretary.feature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class BriefingTest {

  /*{
    “SECR_KEY”: "ktko.tistory.com”,
    “KEY”: “ktko”,
    “REQ_DATA”: [
        {
            “BANK_CD”: “088”,
            “SEARCH_ACCT_NO”: “1231231234”,
            “ACNM_NO”: “123456”,
            “ICHE_AMT”: “0”,
            “TRSC_SEQ_NO”: “0000001”
        }
    ]
}

위와 같은 형식을 만들고 싶으면

 import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("SECR_KEY", "ktko.tistory.com");
        jsonObject.put("KEY", "ktko");

        JSONObject data = new JSONObject();
        data.put("BANK_CD", "088");
        data.put("SEARCH_ACCT_NO", "1231231234");
        data.put("ACNM_NO", "123456");
        data.put("ICHE_AMT", "0");
        data.put("TRSC_SEQ_NO", "0000001");

        JSONArray req_array = new JSONArray();
        req_array.add(data);

        jsonObject.put("REQ_DATA", req_array);

이렇게 해주면 됨

*/

  @Test
  public void sandbox() throws ParseException {

    //given
    List<String> list = new ArrayList<>();
    list.add("apple");
    list.add("banana");
    String str = list.toString(); //내가 가지고 있는 전제 조건 - 리스트가 string으로 되어 있는 것

    //when
    String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
    str = str.replaceAll(match, " "); // 코로나  학교
    //System.out.println(">>> str: "+str);
    // trim 없이 그냥 해버리면 [, 코로나, 학교] 그냥 해버리면 여백까지 리스트에 추가되어버림
    List<String> splitStr = Arrays.asList( str.trim().split("\\s+"));

    for(int i=0;i< splitStr.size();i++){
      splitStr.set(i,splitStr.get(i).trim());  //각 요소마다 혹시나 여백이 남아있지 않도록
    }

    JSONArray jsonArray = new JSONArray();

    for(int i=0;i< splitStr.size();i++){
      JSONObject res = getJsonResult(splitStr.get(i));
      //System.out.println(":::: "+res.toString());
      jsonArray.add(res);
    }

    System.out.println(jsonArray.toString());

    JSONObject briefing = new JSONObject();
    briefing.put("newsSearch",jsonArray);

    System.out.println(briefing);


  }


  public JSONObject getJsonResult(String word) throws ParseException {

    JSONObject jsonObject = new JSONObject();

   jsonObject.put("title",word);
   jsonObject.put("desc","content");


    return jsonObject;

  }

}
