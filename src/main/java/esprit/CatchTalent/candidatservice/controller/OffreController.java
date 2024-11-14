package esprit.CatchTalent.candidatservice.controller;


import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.dto.OffreCountDTO;
import esprit.CatchTalent.candidatservice.dto.OffreRequestDTO;
import esprit.CatchTalent.candidatservice.dto.OffreResponseDTO;
import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Offre;
import esprit.CatchTalent.candidatservice.service.OffreService;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net", maxAge = 3600 , allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/offer")
public class OffreController {



    private OffreService offreservice ;


    public OffreController(OffreService Offreservice) {
        super();
        this.offreservice = Offreservice;
    }


    @GetMapping(path = "/Offers")
    public ResponseEntity<List<Offre>> list() {
        List<Offre> Offres = offreservice.getAllOffres();
        return ResponseEntity.ok().body(Offres); }


   @GetMapping(path="/getOffer/{id}")
   public ResponseEntity<Offre>getoffreById(@PathVariable("id") Integer id){

       Offre offre =offreservice.getOfferById(id) ;
        return ResponseEntity.ok().body(offre);
   }

    @PostMapping(path ="/addOffer")
    public Offre save(@RequestBody Offre offre) {

        return offreservice.save(offre);

    }

    @PostMapping(path = "/updateOffer/{id}")
    public OffreResponseDTO update(@RequestBody OffreRequestDTO offreRequestDTO , @PathVariable ("id") Integer id) {

        return  offreservice.updateOffre(id ,offreRequestDTO );

    }
    @DeleteMapping("/deleteOffer/{id}")
    public ResponseEntity<OffreResponseDTO> deleteOffer(@PathVariable(name = "id") Integer id) {
        offreservice.deleteOffre(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }


    @GetMapping("/search/{keyword}")
    public Page<Offre> SearchOffre(@PathVariable ("keyword") String keyword){
        Page<Offre> offre = offreservice.multiCriteriaSearch( keyword ,1) ;
        return offre ;
    }

    @GetMapping("/countByOffre")
    public List<OffreCountDTO> countCandidatsByOffre() {

        return offreservice.countCandidatsByOffre();
    }




    @GetMapping("/countByOffer/{offreId}")
    public ResponseEntity<Integer> countCandidatsByOffer(@PathVariable Integer offreId) {
        Integer count = offreservice.candidatPerOffer(offreId) ;
        return ResponseEntity.ok(count);
    }



}