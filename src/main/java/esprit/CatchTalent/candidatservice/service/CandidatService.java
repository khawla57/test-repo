package esprit.CatchTalent.candidatservice.service;

import java.util.List;

import esprit.CatchTalent.candidatservice.dto.CandidatRequestDTO;
import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.dto.SuiviCandidatDTO;
import esprit.CatchTalent.candidatservice.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface CandidatService {

	Candidat save(Candidat candidat) ;
	Candidat getCandidatById(Integer id) ;
	List<Candidat> getAllCandidats();
	CandidatResponseDTO AddCandidatToOffre(CandidatRequestDTO candidatRequestDTO, Integer offreId ) ;

	Page<Candidat> multiCriteriaSearch (String keyword, int pageNum) ;
	public void sauvegarderCandidatAvecPieceJointe(Candidat candidat, MultipartFile file);

	List<Candidat> getCandidatsByOffre(Integer offreId);
	void AddPjtoCandidat(MultipartFile pj , Integer candidatId);
	List<PieceJointe> getPieceJointeBycandidatId(Integer candidatId);
	List<SuiviCandidatDTO> getSuiviCandidatByCandidatId(Integer candidatId);
	public void addSuiviToCandidatById(SuiviCandidat suivicandidat , Integer id);

	List<Tache> getTacheByCandidatId(Integer candidatId) ;
    List<Tache> getAllTache();
	void addTacheToCandidat(Tache tache, Integer candidatId);
	Status getStatusByCandidatId(int candidatId);
	List<Candidat> getCandidatsByStatusId(Integer statusId);
}