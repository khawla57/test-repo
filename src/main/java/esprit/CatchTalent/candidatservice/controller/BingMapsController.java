package esprit.CatchTalent.candidatservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import esprit.CatchTalent.candidatservice.dto.DistanceRequestDTO;
import esprit.CatchTalent.candidatservice.service.BingMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600 , allowCredentials="true")
@RestController
public class BingMapsController {

    @Autowired
    private BingMapsService bingMapsService;


    @PostMapping("/calculateDistance")
    public double calculateDistance(@RequestBody DistanceRequestDTO requestDTO) throws JsonProcessingException {
        return bingMapsService.calculateDistance(requestDTO.getCandidat().getAdresse(), requestDTO.getWORK_ADDRESS());
    }


}