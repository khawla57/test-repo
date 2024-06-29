package esprit.CatchTalent.candidatservice.entities;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity (name="ressourceHumaine")
@Data
@AllArgsConstructor
public class RessourceHumaine{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String email;



}