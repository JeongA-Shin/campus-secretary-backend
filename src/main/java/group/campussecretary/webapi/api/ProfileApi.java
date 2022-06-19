package group.campussecretary.webapi.api;


import group.campussecretary.feature.service.ProfileService;
import group.campussecretary.webapi.form.ProfileForm;
import group.campussecretary.webapi.mapper.ProfileFormMapper;
import group.campussecretary.webapi.predicate.ProfileFormPredicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    private final ProfileFormMapper formMapper;

    private final ProfileService service;


    @SneakyThrows
    @ApiOperation("전체 프로필 목록 조회")
    @GetMapping("/get-list")
    public List<ProfileForm.Output.GetAll> getList(ProfileForm.Input.GetAll in){

        return formMapper.toGetAllList(service.getList(ProfileFormPredicate.search(in)));

    }

    @SneakyThrows
    @ApiOperation("전체 프로필 페이징 조회")
    @GetMapping("/get-page")
    public Page<ProfileForm.Output.GetAll> getPage(ProfileForm.Input.GetAll in, @PageableDefault(size = 20) Pageable page){
        return service.getPage(ProfileFormPredicate.search(in),page).map(formMapper::toGetAll);
    }



    // TO-DO : 
//       추가 및 수정 api 과정시에


}
