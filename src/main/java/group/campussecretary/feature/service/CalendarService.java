package group.campussecretary.feature.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
  //token.json 파일을 저장할 디렉토리
  private static final String TOKENS_DIRECTORY_PATH = "tokens";
  //If modifying these scopes, delete your previously saved tokens/ folder.
  //사용할 구글 API 범위(SCOPE 설정)
  private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR); //"https://www.googleapis.com/auth/calendar";값임
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";


  //어차피 구글 일정 조회만 나오면 되니까 조회 api만 있으면 됨

  public Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {

    //LOAD CLIENT SECRET
    //credentials.json 에 담겨 있는 내용 읽어오기
    InputStream in = CalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);

    if(in == null){
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }

    //credentials.json에서 읽어온 내용 바탕으로 클라이언트 비밀번호 로드
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build GoogleAuthorizationCodeFlow and trigger user authorization request.
    //여기 빌드 과정에서 token.json 파일이 생성됨
    GoogleAuthorizationCodeFlow flow= new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();

    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");


    //returns an authorized Credential object.
    return credential;

  }

}
