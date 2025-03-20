package org.information.temple.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    private String picture; // Profile picture URL from Google

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId; // Google User ID
}
