package esprit.CatchTalent.candidatservice.dto;

import esprit.CatchTalent.candidatservice.entities.Offre;
import esprit.CatchTalent.candidatservice.entities.Status;

public class OffreCountDTO {

    private Offre offre;
    private Long nbre;

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Long getNbre() {
        return nbre;
    }

    public void setNbre(Long nbre) {
        this.nbre = nbre;
    }

    public OffreCountDTO(Offre offre, Long nbre) {
        this.offre = offre;
        this.nbre = nbre;
    }
}
