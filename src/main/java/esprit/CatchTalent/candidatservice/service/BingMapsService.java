package esprit.CatchTalent.candidatservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BingMapsService {

    private static final String BING_MAPS_API_ENDPOINT = "https://dev.virtualearth.net/REST/v1/Routes?wayPoint.1=%s&wayPoint.2=%s&key=%s";
    private static final String BING_MAPS_API_KEY = "AiDYCFRW2oqe3ZPXQfEXytTnPGvrn1gkF6cRIVKJdqSX5UvhANDrHnRw-Pk3O8AP";

    public double calculateDistance(String candidateAddress , String WORK_ADDRESS  ) throws JsonProcessingException {

        String url = String.format(BING_MAPS_API_ENDPOINT, WORK_ADDRESS, candidateAddress, BING_MAPS_API_KEY);
        RestTemplate restTemplate = new RestTemplate();

        String responseFromBingMapsAPI = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new Jdk8Module());
        JsonNode jsonResponse = objectMapper.readTree(responseFromBingMapsAPI);

        JsonNode distanceNode = jsonResponse
                .path("resourceSets")
                .path(0)
                .path("resources")
                .path(0)
                .path("travelDistance");

        double distanceInKilometers = -1.0;

        if (distanceNode.isNumber()) {
            distanceInKilometers = distanceNode.asDouble();
            return distanceInKilometers;
        }

return  distanceInKilometers ;


}}