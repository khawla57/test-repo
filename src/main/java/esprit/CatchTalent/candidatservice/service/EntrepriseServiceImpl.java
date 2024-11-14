package esprit.CatchTalent.candidatservice.service;

import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.dto.EntrepriseRequestDTO;
import esprit.CatchTalent.candidatservice.dto.EntrepriseResponseDTO;
import esprit.CatchTalent.candidatservice.entities.Entreprise;
import esprit.CatchTalent.candidatservice.repositories.EntrepriseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
@Slf4j
public class EntrepriseServiceImpl  implements  EntrepriseService{

    @Autowired
    EntrepriseRepository entrepriseRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        super();

        this.entrepriseRepository= entrepriseRepository ;
    }

    @Override
    public List<Entreprise> getAllEntreprises() {
      return   entrepriseRepository.findAll();
    }

    @Override
    public void addEntreprise(Entreprise entreprise) {
        entrepriseRepository.save(entreprise);
    }

    @Override
    public void deleteEntreprise(Integer id) {
        entrepriseRepository.deleteById(id);
    }


}
