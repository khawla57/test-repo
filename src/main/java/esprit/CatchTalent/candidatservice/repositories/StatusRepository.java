package esprit.CatchTalent.candidatservice.repositories;

import esprit.CatchTalent.candidatservice.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query("SELECT c.status, COUNT(c) FROM candidat c GROUP BY c.status")
    List<Object[]> countCandidatsByStatus();
}