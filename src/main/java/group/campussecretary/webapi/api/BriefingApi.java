package group.campussecretary.webapi.api;

import group.campussecretary.webapi.form.ProfileForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Briefing", tags = {"Briefing"})
@RequestMapping(value = "/briefing", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("BriefingApi")
@RequiredArgsConstructor
public class BriefingApi {

  private final CalendarApi calendarApi;
  private final CampusApi campusApi;
  private final NewsSearchApi newsSearchApi;
  private final WeatherApi weatherApi;
  private final ProfileApi profileApi;

  @SneakyThrows
  @ApiOperation("사용자 프로필에 따른 브리핑 결과 가져오기")
  @GetMapping("/get-briefing/{profileId}")
  public JSONObject generateBriefing(@PathVariable UUID profileId){

    ProfileForm.Output.GetAll profile = profileApi.get(profileId);

    JSONObject calendar=null;
    JSONObject campus = null;
    JSONObject newsSearch = null;
    JSONObject weather = null;

    if(profile.getCalendar()=="Y"){
      calendar = calendarApi.getList(Integer.parseInt(profile.getScheduleCount()));
    }

    if(profile.getCampusDay()=="Y"){
      campus = campusApi.getToDoList();
    }

    JSONArray jsonArray = new JSONArray();
    if(profile.getNewsSearch()=="Y"){
      String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
      String newsKeyWords = profile.getNewsKeyWordList().replaceAll(match, " ");
      List<String> newsKeyWordsList = Arrays.asList( newsKeyWords.trim().split("\\s+"));

      for(int i=0;i< newsKeyWordsList.size();i++){
        newsKeyWordsList.set(i,newsKeyWordsList.get(i).trim());  //각 요소마다 혹시나 여백이 남아있지 않도록
      }

      for(int i=0;i< newsKeyWordsList.size();i++){
        JSONObject res = newsSearchApi.getList(profile.getNewsKeyWordList(),Integer.parseInt(profile.getNewsCount()));
        jsonArray.add(res);
      }


      //newsSearch = newsSearchApi.getList(profile.getNewsKeyWordList(),Integer.parseInt(profile.getNewsCount()));
    }

    if(profile.getWeather()=="Y"){
      weather = weatherApi.getWeather("today");
    }



    //리스트가 아니라 그냥 JSON 자체로만 반환하는 게 어떰?  뉴스 서치가 애초에 리스트로 반환되어야 해서 막힌 거니까
    // BriefingTest 코드와 https://ktko.tistory.com/entry/JAVA%EC%97%90%EC%84%9C-JSON-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0 참조
//   List<JSONObject> briefing = new ArrayList<>();
//    briefing.add(calendar);
//    briefing.add(campus);
//    briefing.add(newsSearch);
//    briefing.add(weather);

    JSONObject briefing = new JSONObject();

    briefing.put("calendar",calendar);
    briefing.put("campus",campus);
    briefing.put("newsSearch",jsonArray);
    briefing.put("weather",weather);


    return briefing;

  }


}
