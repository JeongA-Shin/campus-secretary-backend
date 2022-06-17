package group.campussecretary.feature.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import group.campussecretary.feature.Repository.ProfileRepository;
import group.campussecretary.feature.mapper.ProfileMapper;
import group.campussecretary.feature.model.Profile;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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

//  /**
//   * 조회
//   *
//   * @param id 식별번호
//   * @return
//   */
//  public Profile get(UUID id){
//    return repository.findOne(new BooleanBuilder())
//  }

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

    return mapper.toProfile(entity);
  }

  /**
   * 삭제
   * @param id
   */
  public void remove(UUID id){
    repository.deleteById(id);
  }

}
