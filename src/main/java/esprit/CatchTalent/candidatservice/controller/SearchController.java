package esprit.CatchTalent.candidatservice.controller;

import esprit.CatchTalent.candidatservice.dto.SearchResultDTO;
import esprit.CatchTalent.candidatservice.service.PieceJointeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net", maxAge = 3600 , allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private PieceJointeService pieceJointeService;

    @GetMapping
    public List<SearchResultDTO> search(@RequestParam("query") String query) {
        return pieceJointeService.SearchInFile(query);
    }
}
