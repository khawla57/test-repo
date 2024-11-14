package esprit.CatchTalent.candidatservice.dto;

import esprit.CatchTalent.candidatservice.entities.Offre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatRequestDTO {
	
		private int id;
		
		private String nom ;
	    private String prenom ;
		private String email ;

	    private Integer OffreId  ;
	    private String diplome;

  	public CandidatRequestDTO(int id, String nom,String prenom, String email , String diplome) {
		this.id = id;
		this.nom = nom;
		this.prenom =prenom ;
		this.email = email;
		this.diplome=diplome;
	}
}
