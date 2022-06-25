package group.campussecretary.webapi.api.security;

import group.campussecretary.feature.service.security.LoginService;
import group.campussecretary.feature.service.security.SignUpService;
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

@Api(value = "Auth", tags = {"Auth"})
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("AuthApi")
@RequiredArgsConstructor
public class AuthApi {

  private final LoginService loginService;
  private final SignUpService signUpService;

  @SneakyThrows
  @ApiOperation("로그인")
  @PostMapping("/login")
  public JSONObject login(@RequestBody MemberForm.Input.Login in){

    JSONObject res = new JSONObject();
    res.put("username", loginService.login(in));

    return res;
  }

  @SneakyThrows
  @ApiOperation("회원가입")
  @PostMapping("/signup")
  public JSONObject signup (@RequestBody MemberForm.Input.SignUp in){

    JSONObject res = new JSONObject();
    res.put("new member username", signUpService.signup(in));

    return res;
  }

}
