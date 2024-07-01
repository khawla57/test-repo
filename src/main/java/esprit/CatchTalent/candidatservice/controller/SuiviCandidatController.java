package esprit.CatchTalent.candidatservice.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.dto.SuiviCandidatDTO;
import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Offre;
import esprit.CatchTalent.candidatservice.entities.SuiviCandidat;
import esprit.CatchTalent.candidatservice.repositories.CandidatRepository;
import esprit.CatchTalent.candidatservice.repositories.OffreRepository;
import esprit.CatchTalent.candidatservice.service.CandidatService;
import esprit.CatchTalent.candidatservice.service.OffreService;
import esprit.CatchTalent.candidatservice.service.SuiviCandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net", maxAge = 3600 , allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/api/suivi-candidat")
public class SuiviCandidatController {
    @Autowired
    private SuiviCandidatService suiviCandidatService;
    @Autowired
    private OffreRepository offreRepository;
    private CandidatService candidatservice;

    @Autowired
    private CandidatRepository candidatRepository;

    public SuiviCandidatController(SuiviCandidatService suiviCandidatService, OffreRepository offreRepository, CandidatRepository candidatRepository, CandidatService candidatservice) {
        this.suiviCandidatService = suiviCandidatService;
        this.offreRepository = offreRepository;
        this.candidatRepository = candidatRepository;
        this.candidatservice = candidatservice;
    }
}
/*
@JsonIgnore
@GetMapping("/getSuiviByoffre/{offreId}")
public ResponseEntity<Map<String, List<?>>> getSuivisAndCandidatsByOffre(@PathVariable Integer offreId) {
    Offre offre = offreRepository.getById(offreId);
    List<Candidat> candidats = candidatRepository.findByOffreId(offreId);
    List<SuiviCandidat> suivis = suiviCandidatService.getSuivisByOffre(offre);

    List<SuiviCandidatDTO> suiviCandidatDTOs = new ArrayList<>();
    for (SuiviCandidat suiviCandidat : suivis) {
        SuiviCandidatDTO dto = new SuiviCandidatDTO();
        dto.setDateSuivi(suiviCandidat.getDateSuivi());
        dto.setAvancement(suiviCandidat.getAvancement());
        dto.setDescriptionAction(suiviCandidat.getDescriptionAction());
        suiviCandidatDTOs.add(dto);
    }

    List<CandidatResponseDTO> candidatDTOs = candidatservice.getCandidatsByOffre(offreId);

    Map<String, List<?>> responseMap = new HashMap<>();
    responseMap.put("candidat", candidatDTOs);
    responseMap.put("suivicandidat", suiviCandidatDTOs);

    return ResponseEntity.ok(responseMap);
}

}*/
