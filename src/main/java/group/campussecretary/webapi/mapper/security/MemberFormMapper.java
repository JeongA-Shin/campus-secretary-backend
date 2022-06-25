package group.campussecretary.webapi.mapper.security;

import group.campussecretary.feature.model.Member;
import group.campussecretary.webapi.form.security.MemberForm;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
    implementationName = "MemberFormMapperImpl",
    builder = @Builder(disableBuilder = true),
    componentModel = "spring"
)
public abstract class MemberFormMapper {

  public abstract Member toMember(MemberForm.Input.SignUp in);
  public abstract MemberForm.Output.MemberInfo toMemberInfo(Member member);

}
