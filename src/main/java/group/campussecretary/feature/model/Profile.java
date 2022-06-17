package group.campussecretary.feature.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

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
  @Column(name = "profile_id", nullable = false)
  private UUID profileId;

  /**
   *  브리핑 설정 시간
   */
  @Column(name = "briefing_time")
  private String briefingTime;

  /**
   *  contentList
   */
  @Type(type="list-array")
  @Column(name = "content_list",columnDefinition = "text[]")
  private List<String> contentList;

  /**
   *  campusDay
   */
  @Column(name = "campus_day")
  private String campusDay;

  /**
   *  뉴스 키워드 리스트
   */
  @Type(type="list-array")
  @Column(name = "news_keyword_list",columnDefinition = "text[]")
  private List<String> newsKeyWordList;

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


}
