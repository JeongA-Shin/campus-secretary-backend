package group.campussecretary.feature.service;

import group.campussecretary.feature.service.common.CheckHttpConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsSearchService {

    //어차피 뉴스 검색 결과만 나오면 되니까 조회 api만 있으면 됨

    /**
     * 
     * @param apiUrl 요청할 외부(여기서는 네이버 뉴스 검색 api) url
     * @param requestHeaders 외부 api에 대해 요청시 필요한 헤더 정보가 map자료형으로 넘겨받은 것
     * @return
     * @throws ProtocolException
     */

    public String get(String apiUrl, Map<String, String> requestHeaders) {
        // 우선 api 정상적으로 연결 되었는지 테스트
        CheckHttpConnection checkHttpConnection=new CheckHttpConnection();
        //HttpURLConnection을 이용하여 외부 API에 대해 요청하는 HTTP Request entity를 만들어낸다고 생각하면 쉬움
        //con은 현재 외부 url에 대해 접속을 요청한 결과가 담긴 객체
        /*[설 명]
         * 1. HttpURLConnection은 http 통신을 수행할 객체입니다
         * 2. URL 객체로 connection을 만듭니다
         * 3. 응답받은 결과를 InputStream으로 받아서 버퍼에 순차적으로 쌓습니다
         * */
        HttpURLConnection con = checkHttpConnection.connect(apiUrl);


        try{
            //외부 url에 대한 요청을 get으로 함
            con.setRequestMethod("GET");
            //외부 api에 대해 요청시 필요한 헤더 정보(ex. 클라이언트 아이디 및 비밀번호)를 map으로 넘겨받았으므로 이 map을 돌면서 con에 헤더 정보로 넣어줌
            for(Map.Entry<String,String> header: requestHeaders.entrySet()){
                con.setRequestProperty(header.getKey(),header.getValue());
            }

            int responseStatusCode= con.getResponseCode();
            // 외부 url에 접속 성공하면 (200)
            if(responseStatusCode==HttpURLConnection.HTTP_OK){
                return readBody(con.getInputStream()); 
            }else{ //에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        }finally {
            con.disconnect();// 에러든 성공이든 응답을 모두 받았으므로 외부 url에 대한 연결 해제
        }
        
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try{
            BufferedReader lineReader = new BufferedReader(streamReader);
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();

        }catch(IOException e){
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
