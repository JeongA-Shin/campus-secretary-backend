package group.campussecretary.feature.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {

  /**
   * 회원객체
   */

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "member_id",columnDefinition = "BINARY(16)", nullable = false)
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
  @Column(name = "password",unique = true)
  private String password;

  /*
  권한
   */
  @Column(name = "role",unique = true)
  private String role;


  public void encodePassword(PasswordEncoder passwordEncoder){
    //패스워드 암호화해서 db에 저장하기
    this.password = passwordEncoder.encode(password);
  }

}
