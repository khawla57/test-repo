package esprit.CatchTalent.candidatservice.service;


import esprit.CatchTalent.candidatservice.dto.*;
import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Offre;
import esprit.CatchTalent.candidatservice.entities.Status;
import esprit.CatchTalent.candidatservice.mappers.CandidatMapper;
import esprit.CatchTalent.candidatservice.mappers.OffreMapper;
import esprit.CatchTalent.candidatservice.repositories.OffreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Tayssir
 *
 */
@Service
@Transactional
public class OffreServiceImpl implements OffreService {

	
	private OffreRepository offreRepository ;
	private OffreMapper offreMapper ;

	private CandidatMapper candidatMapper;
	public static final int SEARCH_RESULT_PER_PAGE = 10;
	
	public OffreServiceImpl(OffreRepository offreRepository, OffreMapper offreMapper) {
		super();
		this.offreRepository = offreRepository;
		this.offreMapper = offreMapper;

	}
	
	@Override
	public List<Offre> getAllOffres() {
		List<Offre> offres = offreRepository.findAll() ;
		
		return offres ;
	}

	@Override
	public Offre getOfferById(Integer id) {
		Offre offre = offreRepository.getById(id) ;

		//return offreMapper.fromOffre(offre);
		return  offre ;
	}


	public void DeleteOffer(Integer id) {
		offreRepository.deleteById(id);

	}


	@Override
	public Offre save(Offre offre) {
		//Offre offre = offreMapper.OffreRequestDToToOffre(OffreRequestDTO);

		Offre savedOffre = offreRepository.save(offre);
		//OffreResponseDTO offreResponseDTO = offreMapper.fromOffre(saveOffre);
		return savedOffre;
	}

	@Override
	public void deleteOffre(Integer id) {
		 offreRepository.deleteById(id);
		
		
	}

	@Override
	public OffreResponseDTO updateOffre(Integer id  , OffreRequestDTO offreRequestDTO) {

		Offre offre = offreRepository.findById(id).get();

		offre.setPoste(offreRequestDTO.getPoste()) ;
		offre.setEntreprise(offreRequestDTO.getEntreprise());
		offreRepository.save(offre) ;
		return offreMapper.fromOffre(offre);
	}

	public Page<Offre> multiCriteriaSearch(String keyword, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE);
		return offreRepository.search(keyword, pageable);
	}

	@Override
	public List<OffreCountDTO> countCandidatsByOffre() {

			List<Object[]> result = offreRepository.countCandidatsByOffre();

			return result.stream()
					.map(row -> new OffreCountDTO((Offre) row[0], (Long) row[1]))
					.collect(Collectors.toList());

	}
	@Override
	public Integer candidatPerOffer(Integer offreId) {

		Long Nbrecandidats = offreRepository.countById(offreId);
		return Nbrecandidats.intValue();
	}

	@Override
	public List<Candidat> getCandidatsByOffre(Integer offreId) {


		List<Candidat> candidats = offreRepository.findCandidatsById(offreId) ;

	/*	List<CandidatResponseDTO> candidatDTOs = new ArrayList<>();
		for(Candidat candidat : candidats) {
			CandidatResponseDTO candidatdto = candidatMapper.candidatToCandidatResponseDTO(candidat);
			candidatDTOs.add(candidatdto);
		}*/
		return candidats ;
	}
/*
	@Override
	public OffreResponseDTO AddCandidatToOffre(Candidat candidat, Integer offreId) {
		
		Offre offre = offreRepository.findById(offreId).get();
		 candidatRestClient.SaveCandidat(candidat);
		
		 offre.setCandidat(candidat);
		 offreRepository.save(offre);
		 OffreResponseDTO offreResponseDTO = offreMapper.fromOffre(offre);
			return offreResponseDTO ;
	}
*/

	

	

}
