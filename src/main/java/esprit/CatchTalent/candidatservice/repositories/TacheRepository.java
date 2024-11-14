package esprit.CatchTalent.candidatservice.repositories;


import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.SuiviCandidat;
import esprit.CatchTalent.candidatservice.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer > {

    List<Tache> findByCandidatId (Integer candidatId);
}
