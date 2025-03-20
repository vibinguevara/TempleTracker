package org.information.temple.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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

    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Timestamp when the temple entry was created")
    private ZonedDateTime createdDateTime;

    @UpdateTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Timestamp when the temple entry was last updated")
    private ZonedDateTime updatedDateTime;

    public String getPrimaryTempleName() {
        return primaryTempleName;
    }

    public void setPrimaryTempleName(String primaryTempleName) {
        this.primaryTempleName = primaryTempleName;
    }

    public String getSecondaryTempleName() {
        return secondaryTempleName;
    }

    public void setSecondaryTempleName(String secondaryTempleName) {
        this.secondaryTempleName = secondaryTempleName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getOfficialPhone() {
        return officialPhone;
    }

    public void setOfficialPhone(String officialPhone) {
        this.officialPhone = officialPhone;
    }

    public LocalDateTime getSpecialPoojaDateTime() {
        return specialPoojaDateTime;
    }

    public void setSpecialPoojaDateTime(LocalDateTime specialPoojaDateTime) {
        this.specialPoojaDateTime = specialPoojaDateTime;
    }

    public boolean isPrasadhamAvailable() {
        return isPrasadhamAvailable;
    }

    public void setPrasadhamAvailable(boolean prasadhamAvailable) {
        isPrasadhamAvailable = prasadhamAvailable;
    }

    public boolean isAnnadhanamAvailable() {
        return isAnnadhanamAvailable;
    }

    public void setAnnadhanamAvailable(boolean annadhanamAvailable) {
        isAnnadhanamAvailable = annadhanamAvailable;
    }
}
