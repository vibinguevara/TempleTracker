package org.information.temple.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Temple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Auto-generated Temple ID")
    private Long id;

    @NotBlank(message = "Primary Temple Name is required")
    private String primaryTempleName;

    private String secondaryTempleName;

    private String streetAddress;

    private String otherAddress;

    @NotBlank(message = "City is required")
    private String cityAddress;

    @NotBlank(message = "State is required")
    private String stateAddress;

    @NotBlank(message = "Postal Code is required")
    private String postalCodeAddress;

    @Email(message = "Invalid email format")
    private String officialEmail;

    private String officialPhone;

    private String officialWebsite;

    private String deityName;

    private boolean isStateGoverned;
    private String jurisdictionOfficer;

    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Timestamp when the temple entry was created")
    private ZonedDateTime createdDateTime;

    @UpdateTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Timestamp when the temple entry was last updated")
    private ZonedDateTime updatedDateTime;

    // Many temples can be created by one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    // Add this getter to handle the userId in JSON
    @JsonProperty("userId")
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    // Add this setter to handle the userId in JSON
    @JsonProperty("userId")
    public void setUserId(Long userId) {
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            this.user = user;
        }
    }
}
