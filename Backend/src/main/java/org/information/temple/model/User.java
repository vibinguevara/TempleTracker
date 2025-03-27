package org.information.temple.model;

import lombok.Getter;
import lombok.Setter;
import org.information.temple.enums.AuthProvider;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private String picture;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    // One user can create multiple temples
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Temple> temples = new ArrayList<>();

    /*
        private String birthdate;  // New field
        private String phoneNumber; // New field
        private String address;  // New field (store as JSON or string)
    */
}
