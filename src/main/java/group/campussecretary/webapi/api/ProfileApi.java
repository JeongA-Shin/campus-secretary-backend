package group.campussecretary.webapi.api;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Profile", tags = {"Profile"})
@RequestMapping(value = "/briefing/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("ProfileApi")
@RequiredArgsConstructor
public class ProfileApi {



}
