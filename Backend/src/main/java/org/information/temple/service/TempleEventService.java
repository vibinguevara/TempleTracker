package org.information.temple.service;

import org.information.temple.model.TempleEvent;

import java.util.List;

public interface TempleEventService {
    TempleEvent createEvent(TempleEvent event);

    List<TempleEvent> getEventsByTemple(Long templeId);

    List<TempleEvent> getEventsByUser(Long userId);
}
