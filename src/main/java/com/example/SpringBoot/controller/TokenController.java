package com.example.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TokenController {

    @Value("${okta.client-id}")
    private String clientId;

    @Value("${okta.client-secret}")
    private String clientSecret;

    @Value("${okta.token-uri}")
    private String tokenUri;

    @Value("${okta.redirect-uri}")
    private String redirectUri;

    @GetMapping("/authorization-code/callback")
    public String callback(@RequestParam("code") String code, @RequestParam("state") String state) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Build request body for token exchange
            String requestBody = "grant_type=authorization_code" +
                "&code=" + code +
                "&redirect_uri=" + redirectUri +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            // Make the request
            ResponseEntity<String> response = restTemplate.exchange(
                tokenUri,
                HttpMethod.POST,
                new org.springframework.http.HttpEntity<>(requestBody, headers),
                String.class
            );

            // Parse the response
            // Here you could parse the response to get the tokens if needed
            return "Authorization Code: " + code;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}


// package com.example.SpringBoot.controller;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.RestTemplate;

// @RestController
// public class TokenController {

//     @Value("${okta.client-id}")
//     private String clientId;

//     @Value("${okta.client-secret}")
//     private String clientSecret;

//     @Value("${okta.token-uri}")
//     private String tokenUri;

//     @Value("${okta.redirect-uri}")
//     private String redirectUri;

//     @GetMapping("/authorization-code/callback")
//     public String callback(@RequestParam("code") String code) {
//         try {
//             RestTemplate restTemplate = new RestTemplate();

//             // Build request body for token exchange
//             String requestBody = "grant_type=authorization_code" +
//                 "&code=" + code +
//                 "&redirect_uri=" + redirectUri +
//                 "&client_id=" + clientId +
//                 "&client_secret=" + clientSecret;

//             HttpHeaders headers = new HttpHeaders();
//             headers.set("Content-Type", "application/x-www-form-urlencoded");

//             // Make the request
//             ResponseEntity<String> response = restTemplate.exchange(
//                 tokenUri,
//                 HttpMethod.POST,
//                 new org.springframework.http.HttpEntity<>(requestBody, headers),
//                 String.class
//             );

//             // Parse the response
//             ObjectMapper objectMapper = new ObjectMapper();
//             JsonNode jsonResponse = objectMapper.readTree(response.getBody());
//             String idToken = jsonResponse.path("id_token").asText();
//             String accessToken = jsonResponse.path("access_token").asText();
            
//             return "ID Token: " + idToken + "<br/>Access Token: " + accessToken;
//         } catch (Exception e) {
//             return "Error: " + e.getMessage();
//         }
//     }
// }