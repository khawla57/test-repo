package esprit.CatchTalent.candidatservice.springbootazure.controller;


import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.PieceJointe;
import esprit.CatchTalent.candidatservice.repositories.CandidatRepository;
import esprit.CatchTalent.candidatservice.service.CandidatService;
import esprit.CatchTalent.candidatservice.service.PieceJointeService;
import esprit.CatchTalent.candidatservice.springbootazure.AzureBlobAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class AzureController {

    @Autowired
    private AzureBlobAdapter azureBlobAdapter;

    @Autowired
    private PieceJointeService pjservice ;
    private CandidatService candidatservice ;


    @PostMapping("/container")
    public ResponseEntity createContainer(@RequestBody String containerName){
        boolean created = azureBlobAdapter.createContainer(containerName);
        return ResponseEntity.ok(created);
    }

  @PostMapping
    public ResponseEntity upload(@RequestParam MultipartFile multipartFile ){
        URI url = azureBlobAdapter.upload(multipartFile);
        return ResponseEntity.ok(url);
    }



    @GetMapping("/blobs")
    public ResponseEntity getAllBlobs(@RequestParam String containerName){
        List<URI> uris = azureBlobAdapter.listBlobs(containerName);
        return ResponseEntity.ok(uris);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam String containerName, @RequestParam String blobName){
        azureBlobAdapter.deleteBlob(containerName, blobName);
        return ResponseEntity.ok().build();
    }


}
