package group.campussecretary.webapi.api;

import group.campussecretary.feature.service.CampusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Campus", tags = {"Campus"})
@RequestMapping(value = "/briefing/campus", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CampusApi")
@RequiredArgsConstructor
public class CampusApi {

  private final CampusService service;

  @SneakyThrows
  @ApiOperation("할 일 목록 가져오기")
  @GetMapping("/get-list")
  public JSONObject getToDoList() throws ParseException {

    String toDoList = service.activateBot();

    JSONParser parser = new JSONParser();
    Object obj = parser.parse(toDoList);
    JSONObject list =(JSONObject) obj;

    return list;

  }
}
