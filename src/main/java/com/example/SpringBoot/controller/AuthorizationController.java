package com.example.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizationController {

    @Value("${okta.client-id}")
    private String clientId;

    @Value("${okta.redirect-uri}")
    private String redirectUri;

    @Value("${okta.authorization-uri}")
    private String authorizationUri;

    @GetMapping("/login")
    public RedirectView login(HttpSession session) {
        // Generate a random state parameter
        String state = UUID.randomUUID().toString();
        
        // Store the state parameter in the session
        session.setAttribute("auth_state", state);

        String authorizationUrl = authorizationUri +
            "?response_type=code" +
            "&client_id=" + clientId +
            "&redirect_uri=" + redirectUri +
            "&scope=openid profile email" +
            "&state=" + state; // Include the state parameter

        return new RedirectView(authorizationUrl);
    }
}


// package com.example.SpringBoot.controller;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.view.RedirectView;

// import jakarta.servlet.http.HttpSession;
// import java.security.SecureRandom;
// import java.math.BigInteger;

// @RestController
// public class AuthorizationController {

//     @Value("${okta.client-id}")
//     private String clientId;

//     @Value("${okta.redirect-uri}")
//     private String redirectUri;

//     @Value("${okta.authorization-uri}")
//     private String authorizationUri;

//     @GetMapping("/login")
//     public RedirectView login(HttpSession session) {
//         // Generate a unique state parameter
//         String state = generateStateParameter();
//         session.setAttribute("oauth_state", state);

//         String authorizationUrl = authorizationUri + "?response_type=code" +
//             "&client_id=" + clientId +
//             "&redirect_uri=" + redirectUri +
//             "&scope=openid profile email" +
//             "&state=" + state;

//         return new RedirectView(authorizationUrl);
//     }

//     private String generateStateParameter() {
//         SecureRandom random = new SecureRandom();
//         return new BigInteger(130, random).toString(32);
//     }
// }






