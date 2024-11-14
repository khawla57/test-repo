package esprit.CatchTalent.candidatservice.service;

import esprit.CatchTalent.candidatservice.entities.StatusName;

public class StatusWithCandidatCount {
    private Integer id;
    private StatusName nom;
    private long nombreCandidats;



    public StatusWithCandidatCount(Integer id, StatusName nom, Long nombreCandidats) {
        this.id = id;
        this.nom = nom;
        this.nombreCandidats = nombreCandidats;
    }
}