package group.campussecretary.webapi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

public class ProfileForm {


  public static class Input{

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "브리핑 시간")
      private String briefingTime;

    }


    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

      @ApiModelProperty(value = "브리핑 시간")
      private UUID profileId;

      //사용자로부터 입력은 리스트로 받고 컨트롤러에서 파싱하여 컬럼 별로 y,n을 준다
      @ApiModelProperty(value = "contentList")
      private List<String> contentList;

      @ApiModelProperty(value = "campusDay")
      private String campusDay;

      //얘도 마찬가지로 입력은 리스트로 받고 스트링으로 컨트롤러에서 변환하자
      @ApiModelProperty(value = "newsKeyWordList")
      private List<String> newsKeyWordList;

      @ApiModelProperty(value = "newsCount")
      private String newsCount;

      @ApiModelProperty(value = "schedule_count")
      private String scheduleCount;

    }


    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {

      @ApiModelProperty(value = "브리핑 시간")
      private UUID profileId;

      @ApiModelProperty(value = "contentList")
      private List<String> contentList;

      @ApiModelProperty(value = "campusDay")
      private String campusDay;

      @ApiModelProperty(value = "newsKeyWordList")
      private List<String> newsKeyWordList;

      @ApiModelProperty(value = "newsCount")
      private String newsCount;

      @ApiModelProperty(value = "schedule_count")
      private String scheduleCount;

    }

  }

  public static class Output{

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll{

      @ApiModelProperty(value = "브리핑 시간")
      private UUID profileId;

//      @ApiModelProperty(value = "contentList")
//      private List<String> contentList;

      /*
        브리핑 기능 중 구글 캘린더 조회를 쓸 지]
         useYn처럼 Y or N
      */
      @ApiModelProperty(value = "calendar")
      private String calendar;

      /*
      브리핑 기능 중 뉴스 검색 기능을 쓸지
       */
      @ApiModelProperty(value ="newsSearch")
      private String newsSearch;


      /*
      브리핑 기능 중 날씨 예보 조회 기능을 쓸지
       */
      @ApiModelProperty(value ="weather")
      private String weather;


      @ApiModelProperty(value = "campusDay")
      private String campusDay;

      @ApiModelProperty(value = "newsKeyWordList")
      private String newsKeyWordList;

      @ApiModelProperty(value = "newsCount")
      private String newsCount;

      @ApiModelProperty(value = "scheduleCount")
      private String scheduleCount;
    }

  }

}
