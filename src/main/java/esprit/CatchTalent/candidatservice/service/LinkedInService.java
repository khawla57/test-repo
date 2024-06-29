package esprit.CatchTalent.candidatservice.service;

/*
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class LinkedInService {

    @Value("${linkedin.accessToken}")
    private String accessToken;

    public String createPost(String text) {
        String url = "https://api.linkedin.com/v2/ugcPosts";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        headers.add("X-Restli-Protocol-Version", "2.0.0");

        Map<String, Object> contentEntity = new HashMap<>();
        //contentEntity.put("content", text);

        Map<String, Object> shareContent = new HashMap<>();
        shareContent.put("shareCommentary", text);
        shareContent.put("shareMediaCategory", "NONE");

        Map<String, Object> specificContent = new HashMap<>();
        specificContent.put("com.linkedin.ugc.ShareContent", shareContent);

        Map<String, Object> body = new HashMap<>();
        body.put("author", "urn:li:person:zasufXxxL0");
        body.put("lifecycleState", "PUBLISHED");
        body.put("specificContent", specificContent);
        body.put("visibility", Collections.singletonMap("com.linkedin.ugc.MemberNetworkVisibility", "PUBLIC"));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return response.getBody();

        } catch (HttpClientErrorException.Forbidden forbiddenException) {
            // Handle the exception and log more details if needed
            System.out.println(forbiddenException.getResponseBodyAsString());
            throw forbiddenException;
        }
    }
}*/
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class LinkedInService {

    private final WebClient webClient;
    @Value("${linkedin.accessToken}")
    private String accessToken;

    public LinkedInService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.linkedin.com").build();
    }

    String requestBody = "{\"author\":\"urn:li:person:zasufXxxL0\",\"lifecycleState\":\"PUBLISHED\",\"specificContent\":{\"com.linkedin.ugc.ShareContent\":{\"shareCommentary\":{\"text\":\"Hello my friends !\"},\"shareMediaCategory\":\"ARTICLE\",\"media\":[{\"status\":\"READY\",\"description\":{\"text\":\"Official LinkedIn Blog - Your source for insights and information about LinkedIn.\"},\"originalUrl\":\"https://blog.linkedin.com/\",\"title\":{\"text\":\"Official LinkedIn Blog\"}}]}},\"visibility\":{\"com.linkedin.ugc.MemberNetworkVisibility\":\"PUBLIC\"}}";

    private Object buildRequestBody(String text) {
        //text = "hello my friends";
        return Map.of(
                "author", "urn:li:person:zasufXxxL0",
                "lifecycleState", "PUBLISHED",
                "specificContent", Map.of(
                        "com.linkedin.ugc.ShareContent", Map.of(
                                "shareCommentary", Map.of("text", text),
                                "shareMediaCategory", "NONE"
                        )
                ),
                "visibility", Map.of("com.linkedin.ugc.MemberNetworkVisibility", "PUBLIC")
        );
    }

    public String createPost(String text) {
        String url = "/v2/ugcPosts";
        Object requestBody = buildRequestBody(text);

        return webClient.post()
                .uri(url)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + accessToken)
                .header("X-Restli-Protocol-Version", "2.0.0")
                .body(BodyInserters.fromValue(convertObjectToJson(requestBody)))
                .exchange()
                .flatMap(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        clientResponse.body((clientHttpResponse, context) -> {
                            return clientHttpResponse.getBody();
                        });
                        return clientResponse.bodyToMono(String.class);
                    } else
                        return clientResponse.bodyToMono(String.class);
                })
                .block();
    }

    private String convertObjectToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // Handle exception or log an error
            e.printStackTrace();
            return "";
        }
    }
}
