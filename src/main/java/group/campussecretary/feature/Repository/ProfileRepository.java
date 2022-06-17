package group.campussecretary.feature.Repository;

import group.campussecretary.feature.model.Profile;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID>, QuerydslPredicateExecutor<Profile> {

}
