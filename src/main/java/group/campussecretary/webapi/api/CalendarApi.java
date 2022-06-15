package group.campussecretary.webapi.api;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import group.campussecretary.feature.service.CalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.google.api.services.calendar.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Calendar", tags = {"Calendar"})
@RequestMapping(value = "/briefing/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CalendarApi")
@RequiredArgsConstructor
public class CalendarApi {

  //어플리케이션 이름
  private final String APPLICATION_NAME = "Campus Secretary Project";
  //instance of the JSON factory
  private final GsonFactory JSON_FACTORY= GsonFactory.getDefaultInstance();
  private final CalendarService service;

  @SneakyThrows
  @ApiOperation("구글 캘린더 일정 조회")
  @GetMapping("/")
  public JSONObject getList(@RequestParam Integer maxCount){

    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    // Build a new authorized API client service.
    Calendar calendar=new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, service.getCredentials(APPLICATION_NAME,JSON_FACTORY,HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();

    DateTime now = new DateTime(System.currentTimeMillis());
    // List the next 5 events from the primary calendar.
    Events events =  calendar.events().list("primary")
        .setMaxResults(maxCount)
        .setTimeMin(now)
        .setOrderBy("startTime")
        .setSingleEvents(true)
        .execute();

    List<Event> items = events.getItems();

    Map<String, String> calendarEventsMap=new LinkedHashMap<>();
    Map<String,String> responseMessageMap=new LinkedHashMap<>();

    if(items.isEmpty()){
      responseMessageMap.put("empty","No UpComing Events Found");
    }else{
      for(Event event: items){
        DateTime start=event.getStart().getDateTime();
        if(start==null){
          start = event.getStart().getDate();
        }
       calendarEventsMap.put(event.getSummary(), String.valueOf(start));
      }
    }

    JSONObject result ;
    if(responseMessageMap.isEmpty()){ //일정이 있다면
      result=new JSONObject( calendarEventsMap);
    }else{
      result=new JSONObject(responseMessageMap);
    }
    return result;
  }
}
