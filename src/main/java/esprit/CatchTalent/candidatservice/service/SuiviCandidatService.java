package esprit.CatchTalent.candidatservice.service;


import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Offre;
import esprit.CatchTalent.candidatservice.entities.SuiviCandidat;
import esprit.CatchTalent.candidatservice.repositories.CandidatRepository;
import esprit.CatchTalent.candidatservice.repositories.SuiviCandidatRepository;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SuiviCandidatService {
    @Autowired
    private SuiviCandidatRepository suiviCandidatRepository;

    @Autowired
    private CandidatRepository candidatRepository ;



    public List<SuiviCandidat> getSuivisByOffre(Offre offre) {

        return suiviCandidatRepository.findByCandidatOffre(offre);
    }


    public Map<String, List<?>> getCandidatsAndSuivisByOffre(Offre offre) {
        Map<String, List<?>> result = new HashMap<>();

        List<Candidat> candidats = candidatRepository.findByOffre(offre);
        List<SuiviCandidat> suivis = suiviCandidatRepository.findByCandidatOffre(offre);
        result.put("candidats", candidats);
        result.put("suivis", suivis);
        return result;
    }

    public void deleteSuiviById(Integer idSuivi) {

        suiviCandidatRepository.deleteById(idSuivi);

    }

    public SuiviCandidat UpdateSuivi ( Integer suiviId, SuiviCandidat suiviCandidat ){

      SuiviCandidat suiviCandidat1=  suiviCandidatRepository.findById(suiviId).get() ;
      suiviCandidat1.setDateSuivi(suiviCandidat.getDateSuivi());
      suiviCandidat1.setAvancement(suiviCandidat.getAvancement());
      suiviCandidat1.setDescriptionAction(suiviCandidat.getDescriptionAction());
      
      suiviCandidatRepository.save(suiviCandidat1);
      return suiviCandidat ;
    }
}
