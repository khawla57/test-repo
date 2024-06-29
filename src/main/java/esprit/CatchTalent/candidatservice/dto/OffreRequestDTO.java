package esprit.CatchTalent.candidatservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffreRequestDTO{


	private Integer id;
	private String poste;
	private String entreprise;
	private String informationEntreprise;
	private String consultant;
	private String projet;
	private String profileRecherche;
	private String niveauEtude;
	private String niveauExperience;
	private String intituleeAnnonce;
	private String localisationOffre;
	private String typeContrat;
	private String salaire;
	private String disponibilite;
	private Date dateCreation;

	private String candidatId;

	
	
	 }
	 
		  