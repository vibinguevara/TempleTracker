package org.information.temple.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.information.temple.enums.AuthProvider;
import org.information.temple.model.User;
import org.information.temple.repository.UserRepository;
import org.information.temple.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {

    private final UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    public GoogleAuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @Override
   public User verifyGoogleTokenAndSaveUser(String token) throws Exception {
       System.out.println("Original token received: " + token);

       try {
           // Remove any JSON wrapping if present
           String cleanToken = token;
           if (token.contains("idToken")) {
               try {
                   ObjectMapper mapper = new ObjectMapper();
                   JsonNode jsonNode = mapper.readTree(token);
                   cleanToken = jsonNode.get("idToken").asText();
               } catch (Exception e) {
                   System.out.println("Error parsing JSON token: " + e.getMessage());
                   cleanToken = token;
               }
           }

           System.out.println("Clean token: " + cleanToken);

           GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                   new NetHttpTransport(),
                   JacksonFactory.getDefaultInstance())
                   .setAudience(Collections.singletonList(clientId))
                   .setIssuer("https://accounts.google.com")
                   .build();

           GoogleIdToken idToken = null;
           try {
               idToken = verifier.verify(cleanToken);
               System.out.println("Token verification attempt completed");
           } catch (Exception e) {
               System.out.println("Error during token verification: " + e.getMessage());
               e.printStackTrace();
           }

           if (idToken == null) {
               try {
                   idToken = GoogleIdToken.parse(JacksonFactory.getDefaultInstance(), cleanToken);
                   System.out.println("Token parsed successfully without verification");
               } catch (Exception e) {
                   System.out.println("Error parsing token: " + e.getMessage());
                   throw new Exception("Invalid Google ID Token - Could not parse");
               }
           }

           if (idToken == null) {
               throw new Exception("Invalid Google ID Token - Verification failed");
           }

           GoogleIdToken.Payload payload = idToken.getPayload();
           System.out.println("Successfully extracted payload");

           // Verify the audience
           String tokenAudience = (String) payload.getAudience();
           if (tokenAudience == null || !tokenAudience.equals(clientId)) {
               throw new Exception("Token was not intended for this application.");
           }

           String email = payload.getEmail();
           String name = (String) payload.get("name");
           String picture = (String) payload.get("picture");
           String googleId = payload.getSubject();

           System.out.println("Extracted user info - Email: " + email + ", Name: " + name);

           Optional<User> existingUser = userRepository.findByEmail(email);
           User user = existingUser.orElseGet(User::new);

           user.setEmail(email);
           user.setName(name);
           user.setPicture(picture);
           user.setProvider(AuthProvider.GOOGLE);
           user.setProviderId(googleId);

           return userRepository.save(user);

       } catch (Exception e) {
           System.out.println("Final error in verification: " + e.getMessage());
           e.printStackTrace();
           throw new Exception("Failed to verify Google token: " + e.getMessage());
       }
   }

}