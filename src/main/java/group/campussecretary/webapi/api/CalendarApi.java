package group.campussecretary.webapi.api;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Events;
import group.campussecretary.feature.service.CalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.google.api.services.calendar.Calendar;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Calendar", tags = {"Calendar"})
@RequestMapping(value = "/briefing/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("NewsSearchApi")
@RequiredArgsConstructor
public class CalendarApi {

  //어플리케이션 이름
  private final String APPLICATION_NAME = "Campus Secretary Project";
  //instance of the JSON factory
  private final GsonFactory JSON_FACTORY= GsonFactory.getDefaultInstance();

  private final CalendarService service;


  @SneakyThrows
  @ApiOperation("일정 조회")
  @GetMapping("/")
  public void getList(){

    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    // Build a new authorized API client service.
    Calendar calendar=new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, service.getCredentials(APPLICATION_NAME,JSON_FACTORY,HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();

    DateTime now = new DateTime(System.currentTimeMillis());
    // List the next 10 events from the primary calendar.
    Events events =  calendar.events().list("primary")
        .setMaxResults(10)
        .setTimeMin(now)
        .setOrderBy("startTime")
        .setSingleEvents(true)
        .execute();

  }


}
