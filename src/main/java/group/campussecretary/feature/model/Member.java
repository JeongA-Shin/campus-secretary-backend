package group.campussecretary.feature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Member implements UserDetails {
  //UserDetails : spring security로 관리하고 싶은 (회원) 객체는 UserDetils 인터페이스르 반드시 구현해야 한다고 보면 됨!
  // UserDetails: SUserDetailspring Security에서 사용자 객체의 정보를 불러오기 위해서 필수적으로 구현해야 하는 인터페이스
  //즉 일단은 spring security로 관리할 객체면 UserDetails를 구현해야 한다고 보면 됨

  private static final String ROLE_PREFIX = "ROLE_";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "member_id", nullable = false)
  private UUID memberId;

  /**
   * 이메일
   */
  @Column(name = "email", unique = true)
  private String email;

  /**
   *그냥 로그인 아이디를 유저 네임이라고 하기
   */
  @Column(name = "username",unique = true)
  private String username;

  /**
   * 패스워드
   */
  @Column(name = "password")
  private String password;

  /*
  권한
   */
  //여러 개인 경우 그냥 쉼표(,)로 구분함
  //여기는 그냥 "내"가 관리하는 권한 (ADMIN, USER)
  //spring security는 아래의 getAuthorities을 통해 얻어지는 authorities들을 관리함 - ROLE_ADMIN , ROLE_USER
  @Column(name = "roles")
  private String roles;


  public void encodePassword(PasswordEncoder passwordEncoder){
    //패스워드 암호화해서 db에 저장하기
    this.password = passwordEncoder.encode(password);
  }

  @PrePersist
  public void setRole(){
    this.roles = "ROLE_USER";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Set<GrantedAuthority> authorities= new HashSet<>();
    authorities.add(new SimpleGrantedAuthority(this.roles));
//    for (String role : roles.split(",")) {
//      authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
//    }
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    // 만료되었는지 확인하는 로직
    return true; // true -> 만료되지 않았음
  }

  @Override
  public boolean isAccountNonLocked() {
    // 계정 잠금되었는지 확인하는 로직
    return true; // true -> 잠금되지 않았음
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // 패스워드가..? 만료되었는지 확인하는 로직
    return true; // true -> 만료되지 않았음
  }

  @Override
  public boolean isEnabled() {
    // 계정이 사용 가능한지 확인하는 로직
    return true; // true -> 사용 가능
  }

}
