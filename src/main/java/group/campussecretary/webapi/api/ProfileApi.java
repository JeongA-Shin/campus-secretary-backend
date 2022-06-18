package group.campussecretary.webapi.api;


import group.campussecretary.feature.service.ProfileService;
import group.campussecretary.webapi.form.ProfileForm;
import group.campussecretary.webapi.mapper.ProfileFormMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Profile", tags = {"Profile"})
@RequestMapping(value = "/briefing/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("ProfileApi")
@RequiredArgsConstructor
public class ProfileApi {

//    private final ProfileFormMapper mapper;
//
//    private final ProfileService service;


//    @SneakyThrows
//    @ApiOperation("전체 프로필 목록 조회")
//    @GetMapping("/get-list")
//    public List<ProfileForm.Output.GetAll> getList(ProfileForm.Input.GetAll in){
//
//
//        // TO-DO : in으로 들어온 객체를 forMapping을 위한 객체로 변환하는 과정 필요 - 서비스에 추가함
//
//        return null;
//    }


}
