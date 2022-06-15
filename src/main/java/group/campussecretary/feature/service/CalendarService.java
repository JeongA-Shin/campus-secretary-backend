package group.campussecretary.feature.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.CalendarScopes;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {

  //어플리케이션 이름
  private static final String APPLICATION_NAME = "Campus Secretary Project";
  //instance of the JSON factory
  private static final GsonFactory JSON_FACTORY= GsonFactory.getDefaultInstance();
  // Directory to store authorization tokens
  //여기서 말하는 토큰: api 접근으 위한 액세스 토큰 등
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  //If modifying these scopes, delete your previously saved tokens/ folder.
  //사용할 구글 API 범위(SCOPE 설정)
  private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR); //"https://www.googleapis.com/auth/calendar";값임
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";





  //어차피 구글 일정 조회만 나오면 되니까 조회 api만 있으면 됨

}
