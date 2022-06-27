package group.campussecretary.feature.service.security;

import group.campussecretary.feature.model.Member;
import group.campussecretary.webapi.form.security.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

  //private final MemberRepository memberRepository;
 // private final PasswordEncoder passwordEncoder;
  private final UserDetailsServiceImpl userDetailsService;
  private final AuthenticationManager authenticationManager;

  //얘를 원래 private final AuthenticationManager authenticationManager;으로 아무 생각없이 해줬었는데 이러니까 빈 순환참조인지 관련 에러가 나타났음,
  // 그래서 그냥 private으로 해주니까 됐음

  public String login(MemberForm.Input.Login req){

    /**
     * 로그인 요청 시 들어온 값들에 대해 인증 과정시 쓰일 인증 정보 생성
     */
    //1. 사용자의 로그인 요청 폼에 들어온 정보인 username 과 password 를 조합해서 토큰을 만듦
    Authentication authentication =  authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
   //2.  토큰을 스레드 내 인증정보의 저장소 역할을 하는 SecurityContextHolder에 저장
    // 세션 등록
    SecurityContextHolder.getContext().setAuthentication(authentication);

    /**
     * 위에서 생성된 인증정보에 해당되는 멤버 객체를 가져옴
     */
    //3. 해당 인증 정보에 해당되는 사용자 객체( UserDetails를 구현한 엔티티) 가져옴 : getPrincipal()의 기능
    Member member = (Member) authentication.getPrincipal();

    return userDetailsService.loadUserByUsername(member.getUsername()).toString();
  }


}
