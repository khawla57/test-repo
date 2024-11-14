package esprit.CatchTalent.candidatservice.entities;

public enum Avancement {
    CONTACTER("Contacter"),
    EN_MISSION("En Mission"),
    ENVOI_MAIL("Envoi Mail"),
    QUALIFICATION_DE_LA_CANDIDATURE("Qualification de la candidature"),
    REFUS_SUITE_A_LA_QUALIFIFCATION("Refus suite a la qualification"),
    ENTRETIEN_RH("entretien RH"),
    EMBAUCHE("embauche");

    private String libelle;

    Avancement(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}