package esprit.CatchTalent.candidatservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity (name="tache")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String description;
    @Column
    private String type ;
    @Column
    private Date date ;

    @Column
    private LocalTime heure;

    @ManyToOne( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = true)
    @JoinColumn(
            name = "candidat_id" ,
            referencedColumnName = "id")
    @JsonIgnore
    private Candidat candidat ;
}