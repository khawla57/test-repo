package esprit.CatchTalent.candidatservice.service;

import esprit.CatchTalent.candidatservice.dto.CandidatRequestDTO;
import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.dto.EntrepriseRequestDTO;
import esprit.CatchTalent.candidatservice.dto.EntrepriseResponseDTO;
import esprit.CatchTalent.candidatservice.entities.Entreprise;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EntrepriseService {


    List<Entreprise> getAllEntreprises();

    void addEntreprise (Entreprise entreprise) ;
    void deleteEntreprise ( Integer id) ;
}
