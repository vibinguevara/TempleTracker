package org.information.temple.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@Table(name = "temple_events")
public class TempleEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "special_event_name", nullable = false)
    private String specialEventName;

    @Lob
    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "special_event_date_time", nullable = false)
    private LocalDateTime specialEventDateTime;

    @Column(name = "is_freemeals_available")
    private Boolean isFreeMealsAvailable;

    @Column(name = "is_prasadham_available")
    private Boolean isPrasadhamAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temple_id", nullable = false)
    private Temple temple;
}
