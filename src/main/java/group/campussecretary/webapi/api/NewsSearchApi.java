package group.campussecretary.webapi.api;

import group.campussecretary.feature.service.NewsSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Api(value = "NewsSearch", tags = {"NewsSearch"})
@RequestMapping(value = "/briefing/news", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("NewsSearchApi")
@RequiredArgsConstructor
public class NewsSearchApi {

    private final NewsSearchService service;

    @SneakyThrows
    @ApiOperation("목록 조회")
    @GetMapping("/")
    public JSONObject getList(@RequestParam String query, @RequestParam Integer display){
        //네이버 공식 문서 참고 : query는 검색어, display는 검색 결과를 몇 개 받을 것인지

        String clientId = "VGzIxWXaUExf9pIPETeU"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "pXyQxd6MBh"; //애플리케이션 클라이언트 시크릿값"

        String keyWord=null;//디폴트는 null
        try{
            //네이버 공식 문서에 나온 가이드대로 키워드는 인코딩해서 요청으로 보낸다
            keyWord= URLEncoder.encode(query,"UTF-8");
        }catch(UnsupportedEncodingException e){
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        //정렬 옵션: sim (유사도순)
        String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+keyWord+"&display="+display+"&sort=sim";    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = service.get(apiURL, requestHeaders);

        //반환값을 일단 json으로 해주기 위해
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(responseBody);


        //return responseBody;
        return jsonObject;
    }

}
