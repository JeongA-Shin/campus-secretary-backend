package group.campussecretary.webapi.api;

import group.campussecretary.webapi.form.ProfileForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
  public List<JSONObject> generateBriefing(@PathVariable UUID profileId){

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

    if(profile.getNewsSearch()=="Y"){
      //TO - DO : 뉴스 키워드 파싱해서 각각 호출하고 결과를 하나로 내기
      newsSearch = newsSearchApi.getList(profile.getNewsKeyWordList(),Integer.parseInt(profile.getNewsCount()));
    }

    if(profile.getWeather()=="Y"){
      weather = weatherApi.getWeather("today");
    }



    //TO-D0 : 리스트가 아니라 그냥 JSON 자체로만 반환하는 게 어떰?  뉴스 서치가 애초에 리스트로 반환되어야 해서 막힌 거니까
    // BriefingTest 코드와 https://ktko.tistory.com/entry/JAVA%EC%97%90%EC%84%9C-JSON-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0 참조
   List<JSONObject> briefing = new ArrayList<>();
    briefing.add(calendar);
    briefing.add(campus);
    briefing.add(newsSearch);
    briefing.add(weather);


    return briefing;

  }


}
