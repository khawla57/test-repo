package esprit.CatchTalent.candidatservice.controller;

import esprit.CatchTalent.candidatservice.service.LinkedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600 , allowCredentials="true")
@RestController
public class LinkedInController {

    private final LinkedInService linkedInService;

    @Autowired
    public LinkedInController(LinkedInService linkedInService) {
        this.linkedInService = linkedInService;
    }



    @PostMapping("/createPost")
    public ResponseEntity<String> postToLinkedIn(@RequestBody String text) {

        String response = linkedInService.createPost(text);
        return ResponseEntity.ok(response);
    }
}
