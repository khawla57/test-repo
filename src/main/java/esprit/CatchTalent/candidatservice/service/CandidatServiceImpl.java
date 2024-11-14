package esprit.CatchTalent.candidatservice.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.microsoft.azure.storage.blob.BlobInputStream;
import esprit.CatchTalent.candidatservice.dto.SuiviCandidatDTO;
import esprit.CatchTalent.candidatservice.entities.*;
import esprit.CatchTalent.candidatservice.repositories.*;
import esprit.CatchTalent.candidatservice.springbootazure.AzureBlobAdapter;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import esprit.CatchTalent.candidatservice.dto.CandidatRequestDTO;
import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.mappers.CandidatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
@Transactional(readOnly = false)
@Slf4j
public class CandidatServiceImpl implements CandidatService {


	public static final int SEARCH_RESULT_PER_PAGE = 10;
	private CandidatRepository candidatRepository;
	private OffreRepository offreRepository;
	private CandidatMapper candidatMapper;


	@Autowired
	private SuiviCandidatRepository suiviCandidatRepository ;
	@Autowired
    private PieceJointeRepository pieceJointeRepository ;
	@Autowired
	private AzureBlobAdapter azureBlobAdapter;

	@Autowired
	StatusRepository statusRepository;
	@Autowired
	private TacheRepository tacheRepository;

	public CandidatServiceImpl(CandidatRepository candidatRepository, OffreRepository offreRepository, CandidatMapper candidatMapper ,SuiviCandidatRepository suiviCandidatRepository ,StatusRepository statusRepository ,PieceJointeRepository pieceJointeRepository,TacheRepository tacheRepository) {
		super();
		this.candidatRepository = candidatRepository;
		this.candidatMapper = candidatMapper;
		this.offreRepository = offreRepository;
		this.suiviCandidatRepository =suiviCandidatRepository;
		this.statusRepository = statusRepository;
		this.pieceJointeRepository = pieceJointeRepository;
		this.tacheRepository = tacheRepository ;
	}

	@Override
	public Candidat save(Candidat candidat) {

		//Candidat candidat = candidatMapper.candidatRequestDToToCandidat(candidatRequestDTO);
		Candidat savedCandidat = candidatRepository.save(candidat);
		//CandidatResponseDTO candidatResponseDTO = candidatMapper.candidatToCandidatResponseDTO(saveCandidat);
		return savedCandidat;
	}

	public void sauvegarderCandidatAvecPieceJointe(Candidat candidat, MultipartFile file)  {

		candidat = candidatRepository.save(candidat);
		PieceJointe pieceJointe = new PieceJointe();
		Status status = new Status();
		URI url = azureBlobAdapter.upload(file);

		System.out.println("URL du fichier téléchargé : " + url);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		pieceJointe.setFileName(fileName);
		pieceJointe.setFileType(file.getContentType());
		pieceJointe.setUrl(url);
		pieceJointe.setCandidat(candidat);
		status.setNom(StatusName.NOUVEAU_POSITIONNEMENT);
		statusRepository.save(status);
		pieceJointeRepository.save(pieceJointe);
		candidat.getPiecesJointes().add(pieceJointe);
		candidatRepository.save(candidat);

	}



	@Override
	public Candidat getCandidatById(Integer id) {

		Candidat candidat = candidatRepository.findById(id).get();
		System.out.println("this is la valeur de candidat" +candidat);
		//CandidatResponseDTO candidatdto = candidatMapper.candidatToCandidatResponseDTO(candidat);
		return candidat;
	}

	@Override
	public List<Candidat> getAllCandidats() {
		List<Candidat> candidats = candidatRepository.findAll();
		/*List<CandidatResponseDTO> candidatResponseDTOs = candidats.stream()
				.map(cand -> candidatMapper.candidatToCandidatResponseDTO(cand))
				.collect(Collectors.toList());*/
		return candidats;
	}

	@Override
	public CandidatResponseDTO AddCandidatToOffre(CandidatRequestDTO candidatRequestDTO, Integer offreId) {
		Candidat candidat =  candidatMapper.candidatRequestDToToCandidat(candidatRequestDTO);
		Optional<Offre> optionalOffre = offreRepository.findById(offreId);

		if (optionalOffre.isPresent()) {
			Offre offre = optionalOffre.get();
			candidat.setOffre(offre);
			Candidat saveCandidat = candidatRepository.save(candidat);
			CandidatResponseDTO responseDTO = candidatMapper.candidatToCandidatResponseDTO(saveCandidat);
			responseDTO.setId(candidat.getId());
			responseDTO.setOffreId(offreId);
			responseDTO.setMessage("Candidate added to offer successfully");

			return responseDTO;
		} else {

			System.out.println("Offer with ID " + offreId + " not found");
			return null ;
		}

}

	@Override
	public List<Candidat> getCandidatsByOffre(Integer offreId) {


		List<Candidat> candidats = candidatRepository.findByOffreId(offreId) ;

		List<CandidatResponseDTO> candidatDTOs = new ArrayList<>();
		for(Candidat candidat : candidats) {
			CandidatResponseDTO candidatdto = candidatMapper.candidatToCandidatResponseDTO(candidat);
			candidatDTOs.add(candidatdto);
		}
		return candidats ;
	}




	public Page<Candidat> multiCriteriaSearch(String keyword, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE);
		return candidatRepository.search(keyword, pageable);
	}

	public void addSuiviToCandidatById(SuiviCandidat suiviCandidat, Integer candidatId) {

		Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);

		if (optionalCandidat.isPresent()) {
			Candidat candidat = optionalCandidat.get();
			suiviCandidat.setCandidat(candidat);
			candidat.getSuiviCandidats().add(suiviCandidat);
			candidatRepository.save(candidat);
		} else {
			throw new EntityNotFoundException("Candidat not found with ID: " + candidatId);
		}
	}


	public List<SuiviCandidatDTO> getSuiviCandidatByCandidatId(Integer candidatId) {
		Candidat candidat = candidatRepository.getById(candidatId);
		List<SuiviCandidat> suiviCandidats = suiviCandidatRepository.getSuiviByCandidat(candidat);

		List<SuiviCandidatDTO> suiviCandidatDTOs = new ArrayList<>();
		for (SuiviCandidat suiviCandidat : suiviCandidats) {
			SuiviCandidatDTO dto = new SuiviCandidatDTO();
			dto.setDateSuivi(suiviCandidat.getDateSuivi());
			dto.setAvancement(suiviCandidat.getAvancement());
			dto.setDescriptionAction(suiviCandidat.getDescriptionAction());
			suiviCandidatDTOs.add(dto);
		}

		return suiviCandidatDTOs;
	}
	public void AddPjtoCandidat(MultipartFile pj , Integer candidatId){

		Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);

		if (optionalCandidat.isPresent()) {
			Candidat candidat = optionalCandidat.get();
			PieceJointe pieceJointe = new PieceJointe();

			URI url = azureBlobAdapter.upload(pj);
			String fileName = StringUtils.cleanPath(pj.getOriginalFilename());
			pieceJointe.setFileName(fileName);
			pieceJointe.setFileType(pj.getContentType());
			pieceJointe.setUrl(url);
			pieceJointe.setCandidat(candidat);
			pieceJointeRepository.save(pieceJointe);
			candidat.getPiecesJointes().add(pieceJointe);
			candidatRepository.save(candidat);
		} else {
			throw new EntityNotFoundException("Candidat not found with ID: " + candidatId);
		}
	}
     public List<PieceJointe> getPieceJointeBycandidatId(Integer candidatId){

		 List<PieceJointe> pj = pieceJointeRepository.findByCandidatId(candidatId);
		 /*
		 List<ResponseEntity<byte[]>> responses = new ArrayList<>();

		 for (PieceJointe pieceJointe : pj) {
			 BlobInputStream blobInputStream = azureBlobAdapter.download(pieceJointe.getFileName());
			 if (blobInputStream != null) {
				 try {
					 // Lire le contenu du BlobInputStream en tant que tableau d'octets
					 byte[] fileContent = IOUtils.toByteArray(blobInputStream);

					 HttpHeaders headers = new HttpHeaders();
					 headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
					 headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pieceJointe.getFileName());

					 ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
					 responses.add(responseEntity);
				 } catch (IOException e) {
					 // Gérer l'exception
				 }
			 }
		 }*/
		 return pj;
	 }

	@Override
	public List<Tache> getTacheByCandidatId(Integer candidatId) {

		return tacheRepository.findByCandidatId(candidatId);

	}

	@Override
	public List<Tache> getAllTache() {
		return tacheRepository.findAll();
	}

	public void addTacheToCandidat(Tache tache, Integer candidatId) {

		Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);

		if (optionalCandidat.isPresent()) {
			Candidat candidat = optionalCandidat.get();
			tache.setCandidat(candidat);
			candidat.getTaches().add(tache);
			candidatRepository.save(candidat);
		} else {
			throw new EntityNotFoundException("Candidat not found with ID: " + candidatId);
		}
	}
	public Status getStatusByCandidatId(int candidatId) {

		return candidatRepository.findStatusById(candidatId);
	}

	public List<Candidat> getCandidatsByStatusId(Integer statusId) {
		return candidatRepository.findCandidatsByStatus_Id(statusId);
	}
}
