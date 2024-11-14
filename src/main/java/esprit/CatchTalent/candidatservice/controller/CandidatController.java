package esprit.CatchTalent.candidatservice.controller;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;


import esprit.CatchTalent.candidatservice.dto.*;
import esprit.CatchTalent.candidatservice.entities.*;
import esprit.CatchTalent.candidatservice.repositories.PieceJointeRepository;
import esprit.CatchTalent.candidatservice.service.*;
import esprit.CatchTalent.candidatservice.springbootazure.AzureBlobAdapter;

import esprit.CatchTalent.candidatservice.useful.ExcelGenerator;
import jakarta.persistence.PostUpdate;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net", maxAge = 3600 , allowCredentials="true" ,allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/candidat")
public class CandidatController {
	private static final Logger logger = LoggerFactory.getLogger(CandidatController.class);

	@Autowired
	private PieceJointeService pjservice ;
	private CandidatService candidatservice ;
    @Autowired
	private PieceJointeRepository jointeRepository ;
	@Autowired
	private StatusService statusService;
	@Autowired
	private OffreService offreService ;
	@Autowired
	private SuiviCandidatService suiviCandidatService ;
	@Autowired
	private AzureBlobAdapter azureBlobAdapter;

	   public CandidatController(CandidatService candidatservice , PieceJointeService pjservice ,StatusService statusService) {
		super();
		this.candidatservice = candidatservice;
        this.pjservice =pjservice ;
		this.statusService=statusService ;
	}

	   @GetMapping(path = "/getAllcandidat")

	   public ResponseEntity<List<Candidat>> list() {
	      List<Candidat> candidats = candidatservice.getAllCandidats();
	      return ResponseEntity.ok().body(candidats);
	   }
	   
	   @GetMapping("/candidat/{id}")
	   public ResponseEntity<Candidat> GetCandidatById(@PathVariable ("id") Integer id){

		  Candidat candidat =candidatservice.getCandidatById(id) ;
		  return ResponseEntity.ok().body(candidat);
	   }
	   
	   
	   @PostMapping("/addCandidat")
	   public ResponseEntity<?> SaveCandidat(@RequestBody Candidat candidat) {
		   Integer id = candidatservice.save(candidat).getId();
		   return ResponseEntity.ok().body("New candidat has been saved with ID:" + id);
	   }

	   @CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net")
	   @PostMapping("addCandidatWithPj")
	    public ResponseEntity<Candidat> ajouterCandidatAvecPieceJointe(@RequestPart Candidat candidat,
			@RequestPart("file") MultipartFile file) {

		candidatservice.sauvegarderCandidatAvecPieceJointe(candidat, file);
		return ResponseEntity.ok().body(candidat);
	   }


	  @PostMapping(path="/Offer/{offreId}/candidat")
	    public ResponseEntity<CandidatResponseDTO> AddCandidatToOffre(@RequestBody CandidatRequestDTO candidat , @PathVariable ("offreId") Integer offreId ) {

		    CandidatResponseDTO candidatdto =candidatservice.AddCandidatToOffre(candidat , offreId);
		  return ResponseEntity.ok().body(candidatdto);
      }

     @GetMapping("/getCandidatsByOfferId/{offreId}")
      public ResponseEntity<List<Candidat>> GetCandidatsByOffre( @PathVariable("offreId") Integer offreId ) {

        List<Candidat> candidat =candidatservice.getCandidatsByOffre(offreId);
        return ResponseEntity.ok().body(candidat);
       }



	@PostMapping("/{candidatId}/suivi-candidat")
	public void ajouterSuiviCandidat(@PathVariable Integer candidatId,
			@RequestBody SuiviCandidat suiviCandidat) {

        candidatservice.addSuiviToCandidatById(suiviCandidat, candidatId );

	}

	@GetMapping("/getSuivicandidat/{candidatId}")
	public ResponseEntity<List<SuiviCandidatDTO>> getSuiviCandidat(@PathVariable(value = "candidatId") Integer candidatId) {
		List<SuiviCandidatDTO> suiviCandidatDTOs = candidatservice.getSuiviCandidatByCandidatId(candidatId);
		return new ResponseEntity<>(suiviCandidatDTOs, HttpStatus.OK);
	}

	@DeleteMapping("/deleteSuivi/{suiviId}")
	public ResponseEntity<?>  deleteSuiviCandidat(@PathVariable(value = "suiviId") Integer suiviId) {
		suiviCandidatService.deleteSuiviById(suiviId);
		return ResponseEntity.ok().body("New candidat has been saved with ID:" + suiviId);
	}

	@PutMapping("/updateSuivi/{suiviId}")
	public ResponseEntity<SuiviCandidat> updateSuiviCandidat (@PathVariable(value = "suiviId") Integer suiviId , SuiviCandidat suiviCandidat){
		   suiviCandidatService.UpdateSuivi(suiviId , suiviCandidat);
		return ResponseEntity.ok().body(suiviCandidat);
	}

//   :::::::::::::::::::::::: GESTION DE PIECEJOINTE ::::::::::::::::::::::::

    @GetMapping("/{candidatId}/piecejoints")
    public ResponseEntity<List<PieceJointe> >getAllPieceJointeByCandidatId(@PathVariable(value = "candidatId") Integer candidatId) {
	List<PieceJointe> pj = candidatservice.getPieceJointeBycandidatId(candidatId);
	return ResponseEntity.ok(pj)  ;
    }
	@PostMapping("/addPjtoCandidat/{candidatId}")
	public ResponseEntity<String> AjouterPieceJointe(@RequestPart("file") MultipartFile file ,@PathVariable ("candidatId") Integer candidatId){
		candidatservice.AddPjtoCandidat(file ,candidatId);
		return  ResponseEntity.ok("Piece Jointe ajouté avec succès.");
	}
	@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials="true")
	@GetMapping("/getAllPjs")
	public List<PieceJointe> getAllPj (){

		return jointeRepository.findAll();
	}


	//   :::::::::::::::::::::::: GESTION DE BLOB::::::::::::::::::
	@GetMapping("/blobs")
	public ResponseEntity getAllBlobs(@RequestParam String containerName){
		List<URI> uris = azureBlobAdapter.listBlobs(containerName);
		return ResponseEntity.ok(uris);
	}




//   :::::::::::::::::::::::: GESTION DE PIECEJOINTE EN LOCAL::::::::::::::::::

	@PostMapping("/LocalUploadFile")
	public Response uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName =pjservice.LocalstoreFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName)
				.toUriString();

		return new Response(fileName, fileDownloadUri,
				file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
/*
	@GetMapping("/LocaldownloadFile/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = pjservice.loadFileAsResource(fileName);
				String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}*/

	//:::::::::::::::::::::::: Recherche Par mots clé::::::::::::::::::

	@GetMapping("/search/{keyword}")
	public Page<Candidat> SearchCandidat(@PathVariable ("keyword") String keyword){
		Page<Candidat> candidat = candidatservice.multiCriteriaSearch( keyword ,1) ;
		return candidat ;
	}
	//:::::::::::::::::::::::: Gestion des Taches ::::::::::::::::::
	@GetMapping("/getTache/{candidatId}")
	public List<Tache> getAllTacheByCandidatId(@PathVariable Integer candidatId) {
		return candidatservice.getTacheByCandidatId(candidatId);
	}
	@PostMapping("/tache/{candidatId}")
	public void AddTachetoCandidat(@PathVariable Integer candidatId,
									 @RequestBody Tache tache) {

		candidatservice.addTacheToCandidat(tache, candidatId );

	}
	@GetMapping(path = "/getAlltache")

	public ResponseEntity<List<Tache>> listTaches() {
		List<Tache> taches = candidatservice.getAllTache();
		return ResponseEntity.ok().body(taches);
	}

	//:::::::::::::::::::::::: Gestion de Status Candidat ::::::::::::::::::

	@GetMapping("/getAllstatus")
	public List<Status> getAllStatus() {

		return statusService.getAllStatus();
	}

	@GetMapping("/{candidatId}/status")
	public Status getStatusByCandidatId(@PathVariable Integer candidatId) {
		return candidatservice.getStatusByCandidatId(candidatId);
	}

	@PostMapping("/{candidatId}/status")
	public Status addStatusCandidat(@PathVariable Integer candidatId, @RequestBody Status status) {
		return statusService.addStatusCandidat(candidatId, status);
	}

	@PutMapping("/updateStatus/{statusId}")
	public Status updateStatus(@PathVariable Integer statusId, @RequestBody Status updatedStatus) {
		return statusService.updateStatus(statusId, updatedStatus);
	}
	@GetMapping("/{statusId}/byStatus")
	public List<Candidat> getCandidatsByStatus(@PathVariable Integer statusId) {
		return candidatservice.getCandidatsByStatusId(statusId);
	}
	@GetMapping("/Status")
	public List<StatusWithCandidatCount> getAllStatusWithCandidatCount() {
		return statusService.getAllStatusWithCandidatCount();
	}
	@GetMapping("/countByStatus")
	public List<StatusCountDTO> countCandidatsByStatus() {

		return statusService.countCandidatsByStatus();
	}

	@GetMapping("/export-to-excel")
	public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=candidat" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List <Candidat> listOfCandidats = candidatservice.getAllCandidats();
		ExcelGenerator generator = new ExcelGenerator(listOfCandidats);
		generator.generateExcelFile(response);

	}

}

