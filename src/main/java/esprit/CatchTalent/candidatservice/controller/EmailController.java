package esprit.CatchTalent.candidatservice.controller;



import esprit.CatchTalent.candidatservice.entities.EmailDetails;
import esprit.CatchTalent.candidatservice.service.CandidatService;
import esprit.CatchTalent.candidatservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net", maxAge = 3600 , allowCredentials="true",allowedHeaders = "*")
@RequestMapping(path = "api/candidat")
@RestController

public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private CandidatService candidatService ;


    @RequestMapping(value = "/sendMail" , method =  RequestMethod.POST)


    public ResponseEntity<?> sendMail(@RequestBody EmailDetails details)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "true");

        emailService.sendSimpleMail(details);

     return ResponseEntity.ok().headers(headers).build();
    }


    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = emailService.sendMailWithAttachment(details);

        return status;
    }


     /*   @PostMapping("/envoyer-toutes-les-pieces-jointes")
        public String envoyerPiecesJointesAuxEntreprises() {

            List<Candidat> candidats = candidatService.getAllCandidats(); // Sélectionnez tous les candidats
            List<PieceJointe> piecesJointes = new ArrayList<>();

            for (Candidat candidat : candidats) {
                piecesJointes.addAll(candidat.getPiecesJointes());
            }

            String contenuEmail = "Voici les pièces jointes de tous les candidats :\n\n";
            // Générez le contenu de l'e-mail en ajoutant les détails nécessaires

            emailService.envoyerPiecesJointesAuxEntreprises("email-entreprise-partenaire@example.com", contenuEmail, piecesJointes);

            return "Pièces jointes envoyées avec succès.";
        }
    }*/

}
