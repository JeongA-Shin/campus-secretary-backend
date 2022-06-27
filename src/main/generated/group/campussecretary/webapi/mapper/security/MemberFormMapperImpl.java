package group.campussecretary.webapi.mapper.security;

import group.campussecretary.feature.model.Member;
import group.campussecretary.webapi.form.security.MemberForm.Input.SignUp;
import group.campussecretary.webapi.form.security.MemberForm.Output.MemberInfo;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-27T10:09:25+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_322 (Temurin)"
)
@Component
public class MemberFormMapperImpl extends MemberFormMapper {

    @Override
    public Member toMember(SignUp in) {
        if ( in == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( in.getEmail() );
        member.setUsername( in.getUsername() );
        member.setPassword( in.getPassword() );

        return member;
    }

    @Override
    public MemberInfo toMemberInfo(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberInfo memberInfo = new MemberInfo();

        memberInfo.setEmail( member.getEmail() );
        memberInfo.setUsername( member.getUsername() );
        memberInfo.setPassword( member.getPassword() );

        return memberInfo;
    }
}
