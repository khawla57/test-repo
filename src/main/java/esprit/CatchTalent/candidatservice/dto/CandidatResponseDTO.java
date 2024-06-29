package esprit.CatchTalent.candidatservice.dto;

import esprit.CatchTalent.candidatservice.entities.Offre;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatResponseDTO {

		private int id;

	    private String nom ;
		private String prenom ;
		private String email ;
		private String diplome;
	    private int TElmobile;
	    private String codePostal;
	    private String nationalite;
		private String situationFamiliale;
		private Date dateDeNaissance;
		private String titreCandidature;
		private String niveauEtude;
	    private String niveauPro;
		private String formation;
	    private String langues;
		private String disponibilite;
		private String competanceExtrPro;
		private String resumeDeCV;
		private int salaireActuel;
		private int pretentionSalariales;
		private String mobilite;
		private Date dateCreation;

		private Integer OffreId  ;

		private Offre offre ;
		private String message ;

	
	public CandidatResponseDTO(int id,  String nom,String prenom, String email , String diplome) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.diplome=diplome;
	}


}