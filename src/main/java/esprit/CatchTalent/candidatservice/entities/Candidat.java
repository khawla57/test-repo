package esprit.CatchTalent.candidatservice.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity (name="candidat")
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Data
@AllArgsConstructor
public class Candidat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nom;
	@Column
	private String prenom;
	@Column
	private String email;

     private String adresse ;

	@Column(nullable = true)
	private int TElmobile;

	@Column(columnDefinition = "char(45)")
	private String codePostal;
	@Column(columnDefinition = "char(45)")
	private String nationalite;
	@Column(name = "situationFamiliale", columnDefinition = "varchar(100)")
	private String situationFamiliale;

	private Date dateDeNaissance;

	@Column(columnDefinition = "varchar(100)")
	private String titreCandidature;

	@Column
	private String niveauEtude;
	@Column
	private String niveauPro;
	@Lob
	@Column(name = "formation", length = 512, columnDefinition = "LONGTEXT")
	private String formation;

	@Column
	private String diplome;

	@Column
	private String langues;
	@Column
	private String permisConduire;
	@Column(columnDefinition = "varchar(100)")
	private String disponibilite;

	@Lob
	@Column(name = "comptanceExtraPro", length = 512, columnDefinition = "LONGTEXT")
	private String competanceExtrPro;

	@Lob
	@Column(name = "resumeDeCV", columnDefinition = "LONGTEXT")
	private String resumeDeCV;

	@Column(nullable = true)
	private int salaireActuel;
	@Column(nullable = true)
	private int pretentionSalariales;

	@Column(name = "mobilite", columnDefinition = "varchar(100)")
	private String mobilite;

	@Column(nullable = true)
	private String typeEmplois;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateCreation;


	@PrePersist
	protected void onCreate() {
		dateCreation = new Date();
	}



	@OneToMany(fetch = FetchType.LAZY , mappedBy = "candidat")
	@JsonIgnore
	private List<PieceJointe> piecesJointes;

	public Candidat() {
		this.piecesJointes = new ArrayList<>();
	}

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SuiviCandidat> suiviCandidats;

	@OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Tache> taches ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offre_id")
	@JsonIgnore
	private Offre offre;

	@Override
	public String toString() {
		return "Candidat{" +
				"id=" + id +
				", nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				", email='" + email + '\'' +
				", TElmobile=" + TElmobile +
				", codePostal='" + codePostal + '\'' +
				", situationFamiliale='" + situationFamiliale + '\'' +
				", dateDeNaissance=" + dateDeNaissance +
				", titreCandidature='" + titreCandidature + '\'' +
				", niveauEtude='" + niveauEtude + '\'' +
				", formation='" + formation + '\'' +
				", diplome='" + diplome + '\'' +
				", langues='" + langues + '\'' +
				", competanceExtrPro='" + competanceExtrPro + '\'' +
				", resumeDeCV='" + resumeDeCV + '\'' +
				", salaireActuel=" + salaireActuel +
				", pretentionSalariales=" + pretentionSalariales +
				", mobilite='" + mobilite + '\'' +


				'}';
	}
}
 