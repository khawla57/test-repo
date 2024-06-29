package esprit.CatchTalent.candidatservice.repositories;

import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Offre;
import esprit.CatchTalent.candidatservice.entities.SuiviCandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuiviCandidatRepository extends JpaRepository<SuiviCandidat, Integer> {


    List<SuiviCandidat> getSuiviByCandidat (Candidat candidat);

    List<SuiviCandidat> findByCandidatOffre(Offre offre);

}
