package group.campussecretary.feature.service.common;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

public class CheckHttpConnectionTest {

    @Test
    public void sandbox(){
        CheckHttpConnection checkHttpConnection=new CheckHttpConnection();
        HttpURLConnection con = checkHttpConnection.connect("https://openapi.naver.com/v1/search/news.json");
        System.out.println(">>>> result: "+con);
        //con : sun.net.www.protocol.https.DelegateHttpsURLConnection:https://openapi.naver.com/v1/search/news.json
    }
}
