package esprit.CatchTalent.candidatservice.repositories;

import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {


}
