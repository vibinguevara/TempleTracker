package org.information.temple.controller;

import org.information.temple.model.User;
import org.information.temple.service.GoogleAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleAuthService googleAuthService;

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody TokenRequest tokenRequest) {
        try {
            System.out.println("tokenRequest.getIdToken() ---> "+tokenRequest.getIdToken());
            System.out.println("tokenRequest ---> "+tokenRequest);
            System.out.println("tokenRequest.getIdToken().isEmpty() ---> "+tokenRequest.getIdToken().isEmpty());
            if (tokenRequest == null || tokenRequest.getIdToken() == null || tokenRequest.getIdToken().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"error\": \"Invalid token request\"}");
            }

            User authenticatedUser = googleAuthService.verifyGoogleTokenAndSaveUser(tokenRequest.getIdToken());
            System.out.println("authenticatedUser ---------> "+authenticatedUser.getEmail());
            return ResponseEntity.ok(authenticatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body("{\"error\": \"" + e.getMessage().replace("\"", "'") + "\"}");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("{\"message\": \"Logged out successfully\"}");
    }
}

// Create this as a separate class in its own file
@lombok.Data
class TokenRequest {
    private String idToken;
}