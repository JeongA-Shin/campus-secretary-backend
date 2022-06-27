package group.campussecretary.webapi.api.security;

import group.campussecretary.webapi.form.security.MemberForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Admin", tags = {"Admin"})
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("AdminApi")
@RequiredArgsConstructor
public class AdminApi {

  //to do :admin api 테스트 위해 추가 필요

  @SneakyThrows
  @ApiOperation("관리자 관련 API")
  @PostMapping("")
  public JSONObject login(String adminId){

    JSONObject res = new JSONObject();
    res.put("admin id: ", adminId);

    return res;
  }
}
