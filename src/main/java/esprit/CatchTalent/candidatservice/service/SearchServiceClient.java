package esprit.CatchTalent.candidatservice.service;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.search.documents.SearchClient;
import com.azure.search.documents.SearchClientBuilder;
import com.azure.search.documents.SearchDocument;
import com.azure.search.documents.util.SearchPagedIterable;
import com.azure.search.documents.util.SearchPagedResponse;
import jakarta.json.Json;
import jakarta.json.JsonValue;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

import static esprit.CatchTalent.candidatservice.service.SearchServiceHelper.*;
@Service
public class SearchServiceClient {
    private final String _apiKey;
    private final Properties _properties;
    private final static HttpClient client = HttpClient.newHttpClient();
    private static final String ENDPOINT = "https://searchtalentservice.search.windows.net";
    private static final String API_KEY =  "GfFdKK40pG3gbPAuYovamHQLNtmmVvL0amiUErMW53AzSeDsrmcp";
    private static final String INDEX_NAME = "azureblob-index";
    private static final long SEARCH_SKIP_LIMIT = 100_000;

    public SearchServiceClient(Properties properties) {
        _apiKey = properties.getProperty("SearchServiceApiKey");
        _properties = properties;
    }




    public Map<String, Object> createSearchClientFromBuilder(String textSearch) {
        Map<String, Object> searchResults = new HashMap<>();

        // BEGIN: com.azure.search.documents.SearchClient.instantiation
        // Création du client de recherche
        SearchClient searchClient = new SearchClientBuilder()
                .credential(new AzureKeyCredential(API_KEY))
                .endpoint(ENDPOINT)
                .indexName(INDEX_NAME)
                .buildClient();
        // END: com.azure.search.documents.SearchClient.instantiation

        // BEGIN: com.azure.search.documents.SearchClient.search#String
        // Exécution de la recherche
        SearchPagedIterable searchPagedIterable = searchClient.search(textSearch);
        System.out.printf("There are around %d results.", searchPagedIterable.getTotalCount());

        //Traitement des résultats de recherche
        List<Map<String, Object>> resultList = new ArrayList<>();
        long numberOfDocumentsReturned = 0;
        for (SearchPagedResponse resultResponse : searchPagedIterable.iterableByPage()) {
            System.out.println("The status code of the response is " + resultResponse.getStatusCode());
            numberOfDocumentsReturned += resultResponse.getValue().size();
            resultResponse.getValue().forEach(searchResult -> {
                Map<String, Object> resultMap = new HashMap<>();
                for (Map.Entry<String, Object> keyValuePair : searchResult.getDocument(SearchDocument.class).entrySet()) {
                    resultMap.put(keyValuePair.getKey(), keyValuePair.getValue());
                }
                resultList.add(resultMap);
            });

            if (numberOfDocumentsReturned >= SEARCH_SKIP_LIMIT) {
                // Reached the $skip limit, stop requesting more documents.
                break;
            }
        }
        searchResults.put("results", resultList);
        // END: com.azure.search.documents.SearchClient.search#String
        return searchResults;
    }



}
