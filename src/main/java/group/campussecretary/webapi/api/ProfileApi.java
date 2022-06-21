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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @SneakyThrows
    @ApiOperation("프로필 상세 조회")
    @GetMapping("/get/{id}")
    public ProfileForm.Output.GetAll get(@PathVariable UUID id){
        return formMapper.toGetAll(service.get(id));
    }


    @SneakyThrows
    @ApiOperation("새로운 프로필 등록")
    @PostMapping("/add")
    public ProfileForm.Output.GetAll add(@RequestBody ProfileForm.Input.Add in){
        //우선 add 객체를 forMapping으로 바꿔줌
        ProfileForm.Input.forMapping midEntity= service.parsingForMapping(in);

        return formMapper.toGetAll(service.add(formMapper.toProfile(midEntity)));
    }

    @SneakyThrows
    @ApiOperation("기존 프로필 수정")
    @PostMapping("/modify/{id}")
    public ProfileForm.Output.GetAll modify(@PathVariable UUID id, @RequestBody ProfileForm.Input.Modify in){
        //우선 modify 객체를 forMapping으로 바꿔줌
        ProfileForm.Input.forMapping midEntity= service.parsingForMappingByModify(in);

        return formMapper.toGetAll(service.modify(id, formMapper.toProfile(midEntity)));
    }

    @SneakyThrows
    @ApiOperation("프로필 삭제")
    @PostMapping("/remove/{id}")
    public void remove(@PathVariable UUID id){
        service.remove(id);
    }

}
