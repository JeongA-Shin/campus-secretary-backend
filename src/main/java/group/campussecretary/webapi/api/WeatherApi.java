package group.campussecretary.webapi.api;


import com.google.gson.JsonObject;
import group.campussecretary.feature.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "Weather", tags = {"Weather"})
@RequestMapping(value = "/briefing/weather", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("WeatherApi")
@RequiredArgsConstructor
public class WeatherApi {

    private final WeatherService service;

    @SneakyThrows
    @ApiOperation("날씨 정보 조회")
    @GetMapping
    public JSONObject getWeather(@RequestParam String day){ //이 때 파라미터는 today 혹은 tomorrow 둘 중 하나여야 한다
        Map<String,String> weatherInfo = service.getWantedDayWeatherInfo(day);

        JSONObject getWeatherInfo = new JSONObject(weatherInfo);

        return getWeatherInfo;
    }

}
