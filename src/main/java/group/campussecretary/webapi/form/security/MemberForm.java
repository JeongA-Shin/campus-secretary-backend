package group.campussecretary.webapi.form.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MemberForm {

  public static class Input{

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login{

      private String username;
      private String password;

    }


    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUp{

      private String email;
      private String password;
      private String username;

    }


  }


  public static class Output{

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberInfo{

      private String email;
      private String username;
      private String password;

    }

  }

}
