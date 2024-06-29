package esprit.CatchTalent.candidatservice.dto;

import esprit.CatchTalent.candidatservice.entities.Candidat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceRequestDTO {

    private Candidat candidat;
    private String WORK_ADDRESS;


}