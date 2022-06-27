package group.campussecretary.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){

        //docket : 구성을 위한 여러가지 기본값과 편리한 방법을 제공함
        //.select()로 ApiSelectBuilder를 반환 받아 사용할 수 있게 해줌
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //  docket.useDefaultResponseMessages(false); - 확인해보기
        docket.apiInfo(apiInfo())
            .ignoredParameterTypes(Pageable.class) //@Pageable 의 파라미터 요구 필드를 없애기 위함
            .select() //ApiSelectBuilder를 반환 받아 사용할 수 있게 해줌
            .apis((RequestHandlerSelectors.any()))// 이전과 달리 적용하고 싶은 특정 패키지 경로
            // (나같은 경우는 wepapi 패키지 안에 있는 컨트롤러만 swagger로 확인하고 싶음)만 표시, 이러면 해당 패키지 하위에 있는 Controller 기준으로 만들어짐
            .paths(PathSelectors.any()) // URL 경로를 지정하여 해당 URL에 해당하는 요청만 Swagger API 문서로 만듭니다.(필수), 특정 apth만 필터링하여 문서화 함
            .build();

        int tagOrd = 0;
        docket.tags(
            new Tag("Auth", "로그인 API", ++tagOrd),
            new Tag("Admin", "관리자 API", ++tagOrd),
            new Tag("Profile", "프로필 관리 API", ++tagOrd),
            new Tag("Weather", "날씨 예보 API", ++tagOrd),
            new Tag("NewsSearch", "뉴스 검색 API", ++tagOrd),
            new Tag("Calendar", "구글캘린더 일정 API", ++tagOrd),
            new Tag("Campus", "할 일 목록 API", ++tagOrd),
            new Tag("Briefing", "브리핑 API", ++tagOrd)
        );
        return docket;
    }


    //해당 swagger 문서 전체에 대한 info
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("campus-secretary-backend API")
            .version("1.0.0")
            .description("대학생용 비서 만들기")
            .build();
    }

    // 완료가 되었으면 오른쪽 URL 로 접속 => http://localhost:8080/swagger-ui.html

//    /**
//     * Swagger UI Model 버그수정
//     */
//    @Bean
//    public DefaultTypeNameProviderFixer defaultTypeNameProviderFixer() {
//        return new DefaultTypeNameProviderFixer();
//    }
}

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean //반드시 빈으로 등록이 되어야 한다!
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .ignoredParameterTypes(Pageable.class)
//                .select()
//                .apis(RequestHandlerSelectors.any())// 스웨거가 RestController를 전부 스캔을 한다. , 단 이러면 Spring Boot에서 기본적으로 제공하는 Basic -error- controller 도 api 문서로 만들어짐
//                .paths(PathSelectors.any()) // URL 경로를 지정하여 해당 URL에 해당하는 요청만 Swagger API 문서로 만듭니다.(필수)
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//
//        return new ApiInfoBuilder()
//                .title("campus-secretary-backend API")
//                .description("campus-secretary API 서비스 입니다")
//                .version("1.0.0")
//                //.termsOfServiceUrl("https://antstudy.tistory.com/")
//                .license("LICENSE")
//                .licenseUrl("")
//                .build();
//    }
//
//    // 완료가 되었으면 오른쪽 URL 로 접속 => http://localhost:8080/swagger-ui.html
//}
