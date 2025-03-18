package org.information.temple.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Temple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Primary Temple Name is required")
    private String primaryTempleName;

    private String secondaryTempleName;

    @NotBlank(message = "Street Address is required")
    private String streetAddress;

    @NotBlank(message = "Other Address is required")
    private String otherAddress;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Postal Code is required")
    private String postalCode;

    @Email(message = "Invalid email format")
    private String officialEmail;

    private String officialPhone;

    private LocalDateTime specialPoojaDateTime;

    private boolean isPrasadhamAvailable;

    private boolean isAnnadhanamAvailable;
}
