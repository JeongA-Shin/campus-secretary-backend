package group.campussecretary.feature.service.security;

import group.campussecretary.feature.model.Member;
import group.campussecretary.feature.repository.MemberRepository;
import group.campussecretary.webapi.form.security.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public String login(MemberForm.Input.Login req){

    /**
     * 로그인 요청 시 들어온 값들에 대해 인증 과정시 쓰일 인증 정보 생성
     */
    //1. 사용자의 로그인 요청 폼에 들어온 정보인 username 과 password 를 조합해서 토큰을 만듦
    AuthenticationManager authentication = (AuthenticationManager) authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
   //2.  토큰을 스레드 내 인증정보의 저장소 역할을 하는 SecurityContextHolder에 저장
    SecurityContextHolder.getContext().setAuthentication((Authentication) authentication);

    /**
     * 위에서 생성된 인증정보에 해당되는 멤버 객체를 가져옴
     */
    //3. 해당 인증 정보에 해당되는 사용자 객체( UserDetails를 구현한 엔티티) 가져옴 : getPrincipal()의 기능
    Member member = (Member) ((Authentication) authentication).getPrincipal();

    return member.getUsername();
  }


}
