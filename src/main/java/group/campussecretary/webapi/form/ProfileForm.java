package group.campussecretary.webapi.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProfileForm {


  public static class Input{

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAll {

      @ApiModelProperty(value = "브리핑 시간")
      private UUID profileId;

    }


    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

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

      @ApiModelProperty(value = "contentList")
      private List<String> contentList;

      @ApiModelProperty(value = "campusDay")
      private String campusDay;

      @ApiModelProperty(value = "newsKeyWordList")
      private List<String> newsKeyWordList;

      @ApiModelProperty(value = "newsCount")
      private String newsCount;

      @ApiModelProperty(value = "scheduleCount")
      private String scheduleCount;
    }

  }

}
