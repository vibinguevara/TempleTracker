package org.information.temple.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "temple_tracker_donators")
public class TempleTrackerDonator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String phoneNumber;
    private Double amount;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentId;

    private LocalDateTime donatedAt = LocalDateTime.now();

}
