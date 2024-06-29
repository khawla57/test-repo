package esprit.CatchTalent.candidatservice.dto;

import esprit.CatchTalent.candidatservice.entities.Avancement;
import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.SuiviCandidat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class SuiviCandidatDTO {

    private Long Id ;
    private Date dateSuivi;
    private Avancement avancement;
    private String descriptionAction;

    public SuiviCandidatDTO convertToSuiviCandidatDTO(Object item) {

        if (item instanceof SuiviCandidat) {
            SuiviCandidat source = (SuiviCandidat) item;
            SuiviCandidatDTO dto = new SuiviCandidatDTO();
            dto.setId(source.getId());
            dto.setDateSuivi(source.getDateSuivi());
            dto.setAvancement(source.getAvancement());
            dto.setDescriptionAction(source.getDescriptionAction());

            return dto;
        }
        return null;
    }
}