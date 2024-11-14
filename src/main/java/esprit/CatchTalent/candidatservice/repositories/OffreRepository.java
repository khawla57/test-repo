package esprit.CatchTalent.candidatservice.repositories;

import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Offre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OffreRepository  extends JpaRepository<Offre, Integer> {


    @Query(value = "SELECT * FROM test.offre WHERE MATCH (consultant,intitulee_annonce,localisation_offre ,poste ,profile_recherche ,type_contrat ,salaire ) AGAINST (?1)", nativeQuery = true)

    public Page<Offre> search(String keyword, Pageable pageable);

    @Query("SELECT o, COUNT(c) FROM offre o LEFT JOIN candidat c ON o = c.offre GROUP BY o")
    List<Object[]> countCandidatsByOffre();

    Long countById(Integer offreId);
    List<Candidat> findCandidatsById(Integer OffreId);

}