package group.campussecretary.feature.service;

import group.campussecretary.feature.model.Profile;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ProfileTest {

  @Autowired
  private ProfileService service;


  @Test
  public void sandbox(){
    //수정 서비스 테스트

    Profile profile = Profile.builder().profileId(
            UUID.fromString("9f1bf68f-3e3a-433d-ab65-e5613cbbc0ba"))
            .briefingTime("0900")
            .campusDay("camp")
            .newsCount("3")
            .scheduleCount("2")
        .build();

    Profile profile2 = Profile.builder()
        .briefingTime("0900")
        .campusDay("modified")
        .newsCount("1")
        .scheduleCount("1")
        .build();

    System.out.println(profile.toString());

    service.modify(UUID.fromString("9f1bf68f-3e3a-433d-ab65-e5613cbbc0ba"),profile2);

    System.out.println(profile.toString());

  }
}
