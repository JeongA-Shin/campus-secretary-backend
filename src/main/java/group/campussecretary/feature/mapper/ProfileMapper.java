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

  public Profile modify(Profile in, Profile out){

    if(in == null){
      return null;
    }

    out.setBriefingTime(in.getBriefingTime());
    out.setCalendar(in.getCalendar());
    out.setNewsSearch(in.getNewsSearch());
    out.setWeather(in.getWeather());
    out.setCampusDay(in.getCampusDay());
    out.setNewsKeyWordList(in.getNewsKeyWordList());
    out.setNewsCount(in.getNewsCount());
    out.setScheduleCount(in.getScheduleCount());

    return out;

  }

}
