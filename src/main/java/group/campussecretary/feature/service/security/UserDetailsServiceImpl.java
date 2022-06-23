package group.campussecretary.feature.service.security;

import group.campussecretary.feature.model.Member;
import group.campussecretary.feature.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//DB에서 username으로 해당 객체를 찾는 기능을 하는 서비스라고 생각하면 됨
//그냥 쉽게 서비스 하나를 구현하되, Spring Security에서는 UserDetailsService를 구현해야 한다고 생각하면 됨
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findMemberByUsername(username);
    return member;
  }
}

