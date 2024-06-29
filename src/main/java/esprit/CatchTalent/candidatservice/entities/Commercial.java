package esprit.CatchTalent.candidatservice.entities;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity (name="commercial")
@Data
@AllArgsConstructor
public class Commercial{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String email;



}