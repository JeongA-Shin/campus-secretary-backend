package group.campussecretary.webapi.mapper;


import group.campussecretary.feature.model.Profile;
import group.campussecretary.webapi.form.ProfileForm;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        implementationName = "ProfileFormMapperImpl",
        builder = @Builder(disableBuilder = true),
        componentModel = "spring"
)
public abstract class ProfileFormMapper {

    //컨트롤러에서 input과 output 모두 forMapping이 되도록 하는 과정을 거침 -> 그래야 사용자 입력값의 각 프로퍼티와 실제 엔티티의 프로퍼티 항목이 매핑되게끔 되므로
    //그리고 forMapping 객체는 실제 엔티티로 매핑하는 거임
    public abstract Profile toProfile (ProfileForm.Input.forMapping in);

    public abstract ProfileForm.Output.GetAll toGetAll(Profile in);

    public abstract List<ProfileForm.Output.GetAll> toGetAllList(List<Profile> in);

}
