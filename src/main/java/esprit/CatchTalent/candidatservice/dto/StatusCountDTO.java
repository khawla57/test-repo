package esprit.CatchTalent.candidatservice.dto;

import esprit.CatchTalent.candidatservice.entities.Status;

public class StatusCountDTO {

    private Status status;
    private Long nbre;

    public StatusCountDTO(Status status, Long nbre) {
        this.status = status;
        this.nbre = nbre;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getNbre() {
        return nbre;
    }

    public void setNbre(Long nbre) {
        this.nbre = nbre;
    }
}