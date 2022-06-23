package group.campussecretary.feature.repository;

import group.campussecretary.feature.model.Member;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
  Member findMemberByUsername(String username);
  Member findMemberByEmail(String email);
}
