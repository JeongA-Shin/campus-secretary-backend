package group.campussecretary.feature.mapper;

import group.campussecretary.feature.model.Profile;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-18T16:51:23+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl extends ProfileMapper {

    @Override
    public Profile toProfile(Profile modifiedProfile) {
        if ( modifiedProfile == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setProfileId( modifiedProfile.getProfileId() );
        profile.setBriefingTime( modifiedProfile.getBriefingTime() );
        profile.setCalendar( modifiedProfile.getCalendar() );
        profile.setNewsSearch( modifiedProfile.getNewsSearch() );
        profile.setWeather( modifiedProfile.getWeather() );
        profile.setCampusDay( modifiedProfile.getCampusDay() );
        profile.setNewsKeyWordList( modifiedProfile.getNewsKeyWordList() );
        profile.setNewsCount( modifiedProfile.getNewsCount() );
        profile.setScheduleCount( modifiedProfile.getScheduleCount() );

        return profile;
    }
}
