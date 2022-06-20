package group.campussecretary.feature.service;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CampusService {

    WebDriver driver;

    String url;

    private void setWebDriver(){
        String WEB_DRIVER_ID = "webdriver.chrome.driver";
        String WEB_DRIVER_PATH = "C:\\Users\\intern2\\Desktop\\secretary\\campus-secretary-backend\\chromedriver.exe";

        //1. webDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        //2. WebDriver 옵션 설정

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        url = "https://e-campus.khu.ac.kr/xn-sso/login.php?auto_login=&sso_only=&cvs_lgn=&return_url=https%3A%2F%2Fe-campus.khu.ac.kr%2Fxn-sso%2Fgw-cb.php%3Ffrom%3D%26login_type%3Dstandalone%26return_url%3Dhttps%253A%252F%252Fe-campus.khu.ac.kr%252Flogin%252Fcallback";

    }

    public String activateBot(){

        String toDoList="todo";

        setWebDriver();

        try{
            driver.get(url);
            Thread.sleep(2000); // 3. 페이지 로딩 대기 시간

            ClassPathResource resource = new ClassPathResource("login.json");
            JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(resource.getInputStream(), "UTF-8"));
            System.out.println(">>>>>>>> "+json.toString());

            //id입력
            WebElement element=driver.findElement(By.name("login_user_id"));
            element.sendKeys(json.get("id").toString());

            //비밀번호 입력
            element=driver.findElement(By.name("login_user_password"));
            element.sendKeys(json.get("pw").toString());

            //전송
            element=driver.findElement(By.className("login_btn"));
            element.click();
            Thread.sleep(1000);

            //내 강의실 들어가기 버튼 클릭
            // //*[@id="visual"]/div/div[2]/div[2]/div[1]/a
            element= driver.findElement(By.xpath("//*[@id=\"visual\"]/div/div[2]/div[2]/div[1]/a"));
            element.click();
            Thread.sleep(2000);

            //할 일 목록
            element= driver.findElement(By.xpath("//*[@id=\"right-side\"]/div[2]/div/span"));
            //System.out.println(">>>>>>>>>>>>>getText: "+element.getText());
            Thread.sleep(2000);

            toDoList = element.getText();

        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            //브라우저 종료
            driver.close();

        }

        return toDoList;
    }
}
