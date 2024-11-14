package esprit.CatchTalent.candidatservice.controller;




import com.azure.search.documents.util.SearchPagedIterable;
import esprit.CatchTalent.candidatservice.service.SearchServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
@CrossOrigin(origins = "https://catchtalent-app.azurewebsites.net", maxAge = 3600 , allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/azureSearch")
public class SearchControllerAzure {


    private final String searchEndpoint = "https://searchtalentservice.search.windows.net";
    private final String apiKey = "GfFdKK40pG3gbPAuYovamHQLNtmmVvL0amiUErMW53AzSeDsrmcp";
    private final String indexName = "azureblob-index";

    @Autowired
    private final SearchServiceClient searchService;


    public SearchControllerAzure(SearchServiceClient searchService) {
        this.searchService = searchService;
    }



    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String textSearch) {

        Map<String, Object> searchResults = searchService.createSearchClientFromBuilder(textSearch);

        return ResponseEntity.ok(searchResults);
    }

/*
    @GetMapping("/search")
    public String performQuery(@RequestParam String searchString) {
        try {
            var configProperties = loadPropertiesFromResource("application.properties");
            var client = new SearchServiceClient(configProperties);
            client.createIndex();
            client.createDatasource();
            client.createIndexer();
            if (client.syncIndexerData()) {
                client.performQueries();
            }else {
                System.err.print("Data indexing failed.");
            }
        } catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
            e.printStackTrace();
        }
    }
    private static Properties loadPropertiesFromResource(String resourcePath) throws IOException {

        var configProperties = new Properties();
        configProperties.load(WebtvApplication.class.getClassLoader().getResourceAsStream("sender.properties"));

        return configProperties;
    }*/



}








