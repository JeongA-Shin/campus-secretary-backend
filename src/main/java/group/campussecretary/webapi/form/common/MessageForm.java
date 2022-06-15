package group.campussecretary.webapi.form.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MessageForm {

  public static class Output{

    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleMessage{
      @ApiModelProperty(value = "응답메시지")
      private String msg;
    }

  }

}
