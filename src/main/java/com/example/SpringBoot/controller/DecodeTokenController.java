package com.example.SpringBoot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class DecodeTokenController {

    @Value("${okta.client-id}")
    private String clientId;

    @Value("${okta.client-secret}")
    private String clientSecret;

    @Value("${okta.token-uri}")
    private String tokenUri;

    @Value("${okta.redirect-uri}")
    private String redirectUri;

    @GetMapping("/decode-id-token")
    public String decodeIdToken(@RequestParam("id_token") String idToken) {
        try {
            // Decode the JWT token
            String[] jwtParts = idToken.split("\\.");
            if (jwtParts.length != 3) {
                return "Invalid ID token format.";
            }

            // Decode the payload (second part of the JWT)
            String payload = jwtParts[1];
            String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));
            
            // Parse the JSON payload
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonPayload = objectMapper.readTree(decodedPayload);

            // Extract claims from the payload
            String issuer = jsonPayload.path("iss").asText();
            String subject = jsonPayload.path("sub").asText();
            String email =jsonPayload.path("email").asText();
            String audience = jsonPayload.path("aud").asText();
            // String[] scopes = jsonPayload.path("scp").asText().split(" ");

            // Generate the output
            return String.format(
                "Issuer: %s<br/>Subject: %s<br/>Client_id: %s<br/>Email: %s",
                issuer, subject, audience, email
                // String.join(", ", scopes)
            );
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
