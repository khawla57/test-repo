package esprit.CatchTalent.candidatservice.controller;



import esprit.CatchTalent.candidatservice.entities.PieceJointe;
import esprit.CatchTalent.candidatservice.entities.Status;
import esprit.CatchTalent.candidatservice.repositories.StatusRepository;
import esprit.CatchTalent.candidatservice.service.PieceJointeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net", maxAge = 3600 , allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/rh")
public class RessourceHumaineController {

    @Autowired
    PieceJointeService pjservice ;

    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/geAlltStatus")
    public ResponseEntity<List<Status>> getAllStatus(){
        List<Status> statusList = statusRepository.findAll();
        return new ResponseEntity<>(statusList, HttpStatus.OK) ;
    }

   /* @GetMapping("/getPiecejointsByStatus/{statusId}/piecejoints")
    public ResponseEntity<List<PieceJointe>> getAllPieceJointeByStatus(@PathVariable(value = "statusId") Integer statusId) {
        List<PieceJointe> pj = pjservice.findByStatus(statusId);
        return new ResponseEntity<>(pj, HttpStatus.OK);
    }*/


}
