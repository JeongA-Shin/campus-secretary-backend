package group.campussecretary.feature.service.security;

import group.campussecretary.feature.model.Member;
import group.campussecretary.feature.repository.MemberRepository;
import group.campussecretary.webapi.form.security.MemberForm.Input.SignUp;
import group.campussecretary.webapi.mapper.security.MemberFormMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final MemberFormMapper formMapper;

  public String signup(SignUp in){

    Boolean IsExist = memberRepository.existsByUsername(in.getUsername()); // 이미 해당 회원이 존재하는지

    if(IsExist){
      return null;
    }

    Member member = formMapper.toMember(in);

    //반드시 회원 정보를 db에 저장할 때는 비밀번호를 암호화하여 저장해야 한다!!
    member.encodePassword(passwordEncoder);
    memberRepository.save(member);

    return member.getUsername();

  }
}
