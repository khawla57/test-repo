package esprit.CatchTalent.candidatservice.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.*;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name="offre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(100)")
    private String poste;

    @Column(columnDefinition = "varchar(100)")
    private String entreprise;

    @Column(name = "InfoEntreprise", columnDefinition = "LONGTEXT")
    private String informationEntreprise;

    @Column(columnDefinition = "varchar(100)")
    private String consultant;

    @Column(columnDefinition = "LONGTEXT")
    private String projet;

    @Column(columnDefinition = "LONGTEXT")
    private String profileRecherche;

    @Column(columnDefinition = "TEXT")
    private String niveauEtude;

    @Column(columnDefinition = "varchar(100)")
    private String niveauExperience;

    @Column(columnDefinition = "varchar(100)")
    private String intituleeAnnonce;

    @Column(columnDefinition = "varchar(100)")
    private String localisationOffre;

    @Column(columnDefinition = "varchar(100)")
    private String typeContrat;

    @Column(columnDefinition = "varchar(100)")
    private String salaire;

    @Column(columnDefinition = "varchar(100)")
    private String disponibilite;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation")
    private Date dateCreation;


    @PrePersist
    protected void onCreate() {
        dateCreation = new Date();
    }

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "offre",fetch = FetchType.LAZY )
    @JsonIgnore
    private List<Candidat> candidats;

}