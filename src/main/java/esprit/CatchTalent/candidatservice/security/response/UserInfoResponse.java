package esprit.CatchTalent.candidatservice.security.response;

import esprit.CatchTalent.candidatservice.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long id ;
    private String username;
    private String email;
    private List<String> role;
}
