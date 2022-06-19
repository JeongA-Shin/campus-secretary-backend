package group.campussecretary.feature.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import group.campussecretary.feature.mapper.ProfileMapper;
import group.campussecretary.feature.model.Profile;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import group.campussecretary.feature.model.QProfile;
import group.campussecretary.feature.repository.ProfileRepository;
import group.campussecretary.webapi.form.ProfileForm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

  private final ProfileRepository repository;
  private final ProfileMapper mapper;

  /**
   * 목록조회
   * @param search
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<Profile> getList(Predicate search){
    return (List<Profile>) repository.findAll(search);
  }

  /**
   *  페이징 조회
   * @param search
   * @param page
   * @return
   */
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public Page<Profile> getPage(Predicate search, Pageable page) {
    return repository.findAll(search, page);
  }

  /**
   * 조회
   *
   * @param id 식별번호
   * @return
   */
  public Profile get(UUID id){
    return repository.findOne(new BooleanBuilder(QProfile.profile.profileId.eq(id))).orElse(null);
  }

  /**
   *  프로필 등록
   * @param entity
   * @return
   */
  public Profile add(Profile entity){
    return repository.save(entity);
  }

  /**
   * 수정
   * @param id
   * @param entity
   * @return
   */
  public Profile modify(UUID id, Profile entity){ //id는 수정될 객체의 id, entity는 이렇게 수정하고 싶은 내용의 엔티티
    if(entity==null){
      return null;
    }

    entity.setProfileId(id);

    return mapper.modify(entity,get(entity.getProfileId()));
  }

  /**
   * 삭제
   * @param id
   */
  public void remove(UUID id){
    repository.deleteById(id);
  }


  public ProfileForm.Input.forMapping parsingForMapping(ProfileForm.Input.Add in){

    ProfileForm.Input.forMapping forMapping =new ProfileForm.Input.forMapping();

    //add의 리스트로 입력받는 contentList를 파싱하여 컬럼별로 y,n을 줌
    //리스트로 입력받는  newsKeyword를 스트링으로 변환해줘야 함
    //왜냐면 입력폼과 실제 엔티티의 프로퍼티가 서로 다르기 때문임


    System.out.println(">>>>>>getContentList: "+in.getContentList().toString());

    forMapping.setBriefingTime(in.getBriefingTime());

    List<String> contentList = in.getContentList();
    if(contentList.contains("calendar")){
      forMapping.setCalendar("Y");
    }
    if(contentList.contains("newsSearch")){
      forMapping.setNewsSearch("Y");
    }
    if(contentList.contains("weather")){
      forMapping.setWeather("Y");
    }

    forMapping.setCampusDay(in.getCampusDay());

    forMapping.setNewsKeyWordList(in.getNewsKeyWordList().toString());

    forMapping.setNewsCount(in.getNewsCount());

    forMapping.setScheduleCount(in.getScheduleCount());


    System.out.println(">>>>> forMapping: "+forMapping.toString());
    return forMapping;

  }


  public ProfileForm.Input.forMapping parsingForMappingByModify(ProfileForm.Input.Modify in){

    ProfileForm.Input.forMapping forMapping =new ProfileForm.Input.forMapping();

    //add의 리스트로 입력받는 contentList를 파싱하여 컬럼별로 y,n을 줌
    //리스트로 입력받는  newsKeyword를 스트링으로 변환해줘야 함
    //왜냐면 입력폼과 실제 엔티티의 프로퍼티가 서로 다르기 때문임


    System.out.println(">>>>>>getContentList: "+in.getContentList().toString());

    forMapping.setBriefingTime(in.getBriefingTime());

    List<String> contentList = in.getContentList();
    if(contentList.contains("calendar")){
      forMapping.setCalendar("Y");
    }else{
      forMapping.setCalendar("N");
    }

    if(contentList.contains("newsSearch")){
      forMapping.setNewsSearch("Y");
    }else{
      forMapping.setNewsSearch("N");
    }

    if(contentList.contains("weather")){
      forMapping.setWeather("Y");
    }else{
      forMapping.setWeather("N");
    }


    forMapping.setCampusDay(in.getCampusDay());

    forMapping.setNewsKeyWordList(in.getNewsKeyWordList().toString());

    forMapping.setNewsCount(in.getNewsCount());

    forMapping.setScheduleCount(in.getScheduleCount());


    System.out.println(">>>>> forMapping: "+forMapping.toString());
    return forMapping;

  }

}
