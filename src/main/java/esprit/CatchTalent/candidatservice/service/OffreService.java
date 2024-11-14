package esprit.CatchTalent.candidatservice.service;



import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.dto.OffreCountDTO;
import esprit.CatchTalent.candidatservice.dto.OffreRequestDTO;
import esprit.CatchTalent.candidatservice.dto.OffreResponseDTO;
import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Offre;
import org.springframework.data.domain.Page;

import java.util.List;


public interface OffreService {

	Offre save(Offre offre) ;
	void deleteOffre(Integer id);
	OffreResponseDTO updateOffre(Integer id , OffreRequestDTO offreRequestDTO) ;
	Offre getOfferById(Integer id) ;
	
	//List<OffreResponseDTO>  getOffreByCandidatId (Integer CandidatId);
	 List<Offre>  getAllOffres() ;
	Page<Offre> multiCriteriaSearch (String keyword, int pageNum) ;

    List<OffreCountDTO> countCandidatsByOffre();
	Integer candidatPerOffer(Integer offreId);
	List<Candidat> getCandidatsByOffre(Integer offreId);
}
