package esprit.CatchTalent.candidatservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.net.URL;

@Entity(name="entreprise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    private String adresse ;
    @Column(nullable = true)
    private int telephone;
    @Column(nullable = true)
    private int Telecopie;

    @Column(columnDefinition = "char(45)")
    private String codePostal;

    @Column(columnDefinition = "varchar(100)")
    private String DescriptionActivite;

    @Column(columnDefinition = "varchar(100)")
    private String TailleEntreprise;

    @Column(name = "website_url", nullable = true )
    private URL siteWeb ;
}
