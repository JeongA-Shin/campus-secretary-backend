package group.campussecretary.feature.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

//
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Profile {

  /**
   * db에 저장될 프로필 정보
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "profile_id",columnDefinition = "BINARY(16)", nullable = false)
  private UUID profileId;

  /**
   *  브리핑 설정 시간
   */
  @Column(name = "briefing_time")
  private String briefingTime;

  //spring은 basic type에 대한 리스트를 property로 가지는 것을 매우매우 선호하지 않음
  //따라서 그냥 항목당 boolean 값으로 주는 게 더 나을 듯
//  /**
//   *  contentList
//   */
//  @Column(name="content_list")
//  private String contentList;

  /*
  브리핑 기능 중 구글 캘린더 조회를 쓸 지]
  useYn처럼 Y or N
   */
  @Column(name = "calendar")
  private String calendar;

  /*
  브리핑 기능 중 뉴스 검색 기능을 쓸지
   */
  @Column(name="newsSearch")
  private String newsSearch;


  /*
  브리핑 기능 중 날씨 예보 조회 기능을 쓸지
   */
  @Column(name="weather")
  private String weather;

  /**
   *  campusDay
   */
  @Column(name = "campus_day")
  private String campusDay;

  /**
   *  뉴스 키워드 리스트
   */
  @Column(name="news_keyword_list")
  private String newsKeyWordList; ////코로나,경제

  /**
   * newsCount
   */
  @Column(name = "news_count")
  private String newsCount;

  /**
   * scheduleCount
   */
  @Column(name = "schedule_count")
  private String scheduleCount;



  @PrePersist
  //insert 전 defaultValue 설정
  public void onPrePersist() {
    this.defaultValue();
  }

  public void defaultValue(){
    //public static <T> T defaultIfNull(final T object, final T defaultValue)
    //final T object: The object to check for null.
    //final T defaultValue: The default value to return.
    calendar= ObjectUtils.defaultIfNull(getCalendar(), "N");
    newsSearch=ObjectUtils.defaultIfNull(getNewsSearch(),"N");
    weather=ObjectUtils.defaultIfNull(getWeather(),"N");
  }

}
