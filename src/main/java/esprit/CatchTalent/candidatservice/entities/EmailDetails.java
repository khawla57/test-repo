package esprit.CatchTalent.candidatservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String Sender ;
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}