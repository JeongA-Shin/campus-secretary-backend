/**
 * 서비스단에서 합치지 말고 그냥 API 단에서 서로 불러서 합치는 게 더 뭔가 의미적으로 맞을 듯
 *  - 서비스 단에서 합쳐버리면 굳이 API 단들을 각각 만들어준 의미가 퇴색됨
 *  - 게다가 컨트롤러 단에서 처리를 마저 더 하는 경우도 있기 떄문에
 */

//package group.campussecretary.feature.service;
//
//import group.campussecretary.feature.model.Profile;
//import java.io.IOException;
//import java.util.UUID;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class BriefingService {
//
//  private final CalendarService calendarService;
//  private final CampusService campusService;
//  private final NewsSearchService newsSearchService;
//  private final WeatherService weatherService;
//  private final ProfileService profileService;
//
//  //해당 프로필에 저장된 정보를 바탕으로 해당 프로필이 필요한 것만 호출해서 결과로 리턴해주는 서비스
//  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
//  public void generateBriefing(UUID profileId) throws IOException {
//
////    [
////      {
////      "briefingTime": "string",
////        "calendar": "string",
////        "campusDay": "string",
////        "newsCount": "string",
////        "newsKeyWordList": "string",
////        "newsSearch": "string",
////        "profileId": "string",
////        "scheduleCount": "string",
////        "weather": "string"
////    }
////]
//    Profile profile = profileService.get(profileId); //해당 프로필에 해당되는 상세 정보
//    String weather =null;
//    String campus=null;
//
//    if(profile.getCalendar()=="Y"){
//      weather= weatherService.getWeatherInfo().toString();
//    }
//
//    if(profile.getCampusDay()=="Y"){
//      campus=campusService.activateBot().toString();
//    }
//
//  }
//
//
//}
