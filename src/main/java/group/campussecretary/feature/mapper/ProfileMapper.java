package group.campussecretary.feature.mapper;

import group.campussecretary.feature.model.Profile;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
        implementationName = "ProfileMapperImpl",
        builder=@Builder(disableBuilder = true),
        componentModel = "spring"
)
public abstract class ProfileMapper {

  //기존의 객체를 수정할 내용으로 뒤집어 씌움
  public abstract Profile toProfile(Profile modifiedProfile);

}
