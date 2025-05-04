package org.information.temple.controller;

import org.information.temple.model.TempleEvent;
import org.information.temple.service.TempleEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temple-events")
public class TempleEventController {
    private final TempleEventService templeEventService;

    public TempleEventController(TempleEventService templeEventService) {
        this.templeEventService = templeEventService;
    }

    // Create a new event
    @PostMapping
    public ResponseEntity<TempleEvent> createEvent(@RequestBody TempleEvent templeEvent) {
        TempleEvent createdEvent = templeEventService.createEvent(templeEvent);
        return ResponseEntity.ok(createdEvent);
    }

    // Get all events for a specific temple
    @GetMapping("/temple/{templeId}")
    public ResponseEntity<List<TempleEvent>> getEventsByTemple(@PathVariable Long templeId) {
        List<TempleEvent> events = templeEventService.getEventsByTemple(templeId);
        return ResponseEntity.ok(events);
    }

    // Get all events created by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TempleEvent>> getEventsByUser(@PathVariable Long userId) {
        List<TempleEvent> events = templeEventService.getEventsByUser(userId);
        return ResponseEntity.ok(events);
    }
}
