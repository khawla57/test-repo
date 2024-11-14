package esprit.CatchTalent.candidatservice.entities;

public enum StatusName {
    NOUVEAU_POSITIONNEMENT("Nouveau Positionnement"),
    A_TRAITER("À traiter"),
    CANDIDATURE_QUALIFIEE("Candidature qualifiée"),
    CANDIDAT_EN_PHASE_D_ENTRETIEN("Candidat en phase d'entretien"),
    RECRUTE("Recruté");

    private final String label;

    private StatusName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
