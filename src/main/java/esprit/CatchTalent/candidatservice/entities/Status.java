package esprit.CatchTalent.candidatservice.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private StatusName nom;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL ,mappedBy = "status")
    @JsonIgnore
    private List<Candidat> candidats;

    public Integer getId() {
        return id;
    }

    public StatusName getNom() {
        return nom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(StatusName nom) {
        this.nom = nom;
    }

    public void setCandidats(List<Candidat> candidats) {
        this.candidats = candidats;
    }
}