package esprit.CatchTalent.candidatservice.repositories;

import esprit.CatchTalent.candidatservice.entities.Offre;
import esprit.CatchTalent.candidatservice.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import esprit.CatchTalent.candidatservice.entities.Candidat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat , Integer> {
     
	//Candidat getById (Integer candidatId );
    List<Candidat> findByOffreId(Integer OffreId);



	@Query(value = "SELECT * FROM test.candidat WHERE MATCH (email,formation,situation_familiale ,titre_candidature ,niveau_etude,formation ,diplome ,langues ,comptance_extra_pro,resume_decv,mobilite, name, adresse) AGAINST (?1)", nativeQuery = true)
	public Page<Candidat> search(String keyword, Pageable pageable);

    List<Candidat> findByOffre(Offre offre);

    long countByStatus(Status status);
	@Query("SELECT c.status FROM candidat c WHERE c.id = :candidatId")
	Status findStatusById(int candidatId);

	List<Candidat> findCandidatsByStatus_Id(Integer statusId);
}