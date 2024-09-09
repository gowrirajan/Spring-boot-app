// package com.example.SpringBoot.controller;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.RestTemplate;
// import org.json.JSONObject;

// @RestController
// public class Oauth2Controller {

//     @Value("${okta.client-id}")
//     private String clientId;

//     @Value("${okta.client-secret}")
//     private String clientSecret;

//     @Value("${okta.token-uri}")
//     private String tokenUri;

//     @PostMapping("/get-token")
//     public String getToken(@RequestBody UserCredentials credentials) {
//         RestTemplate restTemplate = new RestTemplate();

//         String requestBody = "grant_type=password" +
//             "&username=" + credentials.getUsername() +
//             "&password=" + credentials.getPassword() +
//             "&client_id=" + clientId +
//             "&client_secret=" + clientSecret +
//             "&scope=openid profile email";

//         HttpHeaders headers = new HttpHeaders();
//         headers.set("Content-Type", "application/x-www-form-urlencoded");

//         ResponseEntity<String> response = restTemplate.exchange(
//             tokenUri,
//             HttpMethod.POST,
//             new org.springframework.http.HttpEntity<>(requestBody, headers),
//             String.class
//         );

//         JSONObject jsonResponse = new JSONObject(response.getBody());
//         String idToken = jsonResponse.getString("id_token");

//         return "ID Token: " + idToken;
//     }

//     // Define a simple class to handle the user credentials
//     public static class UserCredentials {
//         private String username;
//         private String password;

//         // Getters and setters
//         public String getUsername() {
//             return username;
//         }

//         public void setUsername(String username) {
//             this.username = username;
//         }

//         public String getPassword() {
//             return password;
//         }

//         public void setPassword(String password) {
//             this.password = password;
//         }
//     }
// }

