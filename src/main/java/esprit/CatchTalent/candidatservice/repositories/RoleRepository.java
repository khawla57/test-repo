package esprit.CatchTalent.candidatservice.repositories;

import java.util.Optional;

import esprit.CatchTalent.candidatservice.entities.ERole;
import esprit.CatchTalent.candidatservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}