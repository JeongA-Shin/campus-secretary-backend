package group.campussecretary.webapi.mapper;

import group.campussecretary.feature.model.Profile;
import group.campussecretary.webapi.form.ProfileForm.Input.forMapping;
import group.campussecretary.webapi.form.ProfileForm.Output.GetAll;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-27T10:09:25+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_322 (Temurin)"
)
@Component
public class ProfileFormMapperImpl extends ProfileFormMapper {

    @Override
    public Profile toProfile(forMapping in) {
        if ( in == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setBriefingTime( in.getBriefingTime() );
        profile.setCalendar( in.getCalendar() );
        profile.setNewsSearch( in.getNewsSearch() );
        profile.setWeather( in.getWeather() );
        profile.setCampusDay( in.getCampusDay() );
        profile.setNewsKeyWordList( in.getNewsKeyWordList() );
        profile.setNewsCount( in.getNewsCount() );
        profile.setScheduleCount( in.getScheduleCount() );

        return profile;
    }

    @Override
    public GetAll toGetAll(Profile in) {
        if ( in == null ) {
            return null;
        }

        GetAll getAll = new GetAll();

        getAll.setProfileId( in.getProfileId() );
        getAll.setBriefingTime( in.getBriefingTime() );
        getAll.setCalendar( in.getCalendar() );
        getAll.setNewsSearch( in.getNewsSearch() );
        getAll.setWeather( in.getWeather() );
        getAll.setCampusDay( in.getCampusDay() );
        getAll.setNewsKeyWordList( in.getNewsKeyWordList() );
        getAll.setNewsCount( in.getNewsCount() );
        getAll.setScheduleCount( in.getScheduleCount() );

        return getAll;
    }

    @Override
    public List<GetAll> toGetAllList(List<Profile> in) {
        if ( in == null ) {
            return null;
        }

        List<GetAll> list = new ArrayList<GetAll>( in.size() );
        for ( Profile profile : in ) {
            list.add( toGetAll( profile ) );
        }

        return list;
    }
}
