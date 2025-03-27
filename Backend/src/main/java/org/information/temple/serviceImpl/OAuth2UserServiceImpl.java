package org.information.temple.serviceImpl;

import org.information.temple.enums.AuthProvider;
import org.information.temple.model.User;
import org.information.temple.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;

@Service
@Transactional
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public OAuth2UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
        System.out.println("OAuth2UserServiceImpl Constructor Called!");
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2UserServiceImpl is running!");
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        System.out.println("OAuth User Info: " + attributes);

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");
        String googleId = (String) attributes.get("sub");
        System.out.println("OAuth User Info: " + attributes);

        // Additional user details (if available)
        /*
        String birthdate = (String) attributes.get("birthdate");
        String phoneNumber = (String) attributes.get("phoneNumber");
        Map<String, Object> address = (Map<String, Object>) attributes.get("address");
        */

        // Check if the user already exists
        User user = userRepository.findByEmail(email).orElse(new User());
        boolean isNewUser = user.getId() == null;

        user.setEmail(email);
        user.setName(name);
        user.setPicture(picture);
        user.setProvider(AuthProvider.GOOGLE);
        user.setProviderId(googleId);


        // Set additional fields
        /*
        user.setBirthdate(birthdate);
        user.setPhoneNumber(phoneNumber);
        if (address != null) {
            user.setAddress(address.toString()); // Convert to JSON string if necessary
        }
        */

        userRepository.save(user);
        if (isNewUser) {
            System.out.println("New user created: " + user.getEmail());
        } else {
            System.out.println("Existing user updated: " + user.getEmail());
        }
        return oAuth2User;
    }
}