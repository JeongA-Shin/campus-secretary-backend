package group.campussecretary.webapi.predicate;

//폼으로 입력 받은 값을 바탕으로 predicate 만들어보기 - Q객체 검색 조건 만들기

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import group.campussecretary.feature.model.QProfile;
import group.campussecretary.webapi.form.ProfileForm;
import org.apache.commons.lang3.StringUtils;


public class ProfileFormPredicate {

    public static Predicate search (ProfileForm.Input.GetAll in){

        BooleanBuilder builder = new BooleanBuilder();
        QProfile qProfile  = QProfile.profile;


        if(StringUtils.isNotEmpty(in.getBriefingTime())){

            builder.and(qProfile.briefingTime.eq(in.getBriefingTime()));
        }

        return builder;
    }
}
