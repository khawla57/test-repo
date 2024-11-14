package esprit.CatchTalent.candidatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDTO {

    private Integer candidatId;
    private String fileName;
    private Long fileDate;
}
