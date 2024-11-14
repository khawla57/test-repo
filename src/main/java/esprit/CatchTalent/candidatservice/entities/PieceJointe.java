package esprit.CatchTalent.candidatservice.entities;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.net.URI;
import java.util.Date;

@Entity
@Table(name = "PieceJointe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceJointe {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String fileName;

    private String fileType;

    private URI url ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation")
    private Date dateCreation;


    @PrePersist
    protected void onCreate() {
        dateCreation = new Date();
    }


    public PieceJointe(String fileName, String fileType , URI url , Date dateCreation) {
        super();
        this.fileName = fileName;
        this.fileType = fileType;
        this.url = url ;

    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "candidat_id")
    @JsonIgnore
    private Candidat candidat;




}
