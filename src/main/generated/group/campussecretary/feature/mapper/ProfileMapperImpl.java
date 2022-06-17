package group.campussecretary.feature.mapper;

import group.campussecretary.feature.model.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-17T21:23:08+0900",
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
        List<String> list = modifiedProfile.getContentList();
        if ( list != null ) {
            profile.setContentList( new ArrayList<String>( list ) );
        }
        profile.setCampusDay( modifiedProfile.getCampusDay() );
        List<String> list1 = modifiedProfile.getNewsKeyWordList();
        if ( list1 != null ) {
            profile.setNewsKeyWordList( new ArrayList<String>( list1 ) );
        }
        profile.setNewsCount( modifiedProfile.getNewsCount() );
        profile.setScheduleCount( modifiedProfile.getScheduleCount() );

        return profile;
    }
}
