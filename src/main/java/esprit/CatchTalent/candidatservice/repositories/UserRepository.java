package esprit.CatchTalent.candidatservice.repositories;

import esprit.CatchTalent.candidatservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;





@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
  //  void addRoleToUser(Long userId, String roleName);
}