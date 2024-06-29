package esprit.CatchTalent.candidatservice.entities;



import java.net.URI;
import java.util.Date;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name="SuiviCandidat")
@Data
@NoArgsConstructor
public class SuiviCandidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateSuivi")
    private Date dateSuivi;
    @PrePersist
    protected void onCreate() {
        dateSuivi = new Date();
    }

    private Avancement avancement  ;

    @Column(name = "description" ,columnDefinition = "TEXT")
    private String DescriptionAction;


    public SuiviCandidat(Avancement avancement , String descriptionAction){
        this.avancement =avancement;
        this.DescriptionAction= descriptionAction;
    }

    @ManyToOne( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = true)
    @JoinColumn(
            name = "candidat_id" ,
            referencedColumnName = "id")
    private Candidat candidat ;



    @Override
    public String toString() {

        if (candidat == null) {
            return "SuiviCandidat [IdSuivi=" + Id + ", dateSuivi=" + dateSuivi + ", avancement=" + avancement +",  DescriptionAction=" + DescriptionAction +", candidat=" + candidat +"]";
        } else {
            return "SuiviCandidat [IdSuivi=" + Id + ", dateSuivi=" + dateSuivi + ", avancement=" + avancement +",  DescriptionAction=" + DescriptionAction +" ]";
        }

    } }
