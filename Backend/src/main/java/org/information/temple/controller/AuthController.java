package org.information.temple.controller;

import org.information.temple.model.User;
import org.information.temple.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public Optional<User> getAuthenticatedUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Optional.empty();
        }
        return userRepository.findByEmail(principal.getAttribute("email"));
    }

    @PostMapping("/logout")
    public String logout() {
        return "You have been logged out!";
    }
}
