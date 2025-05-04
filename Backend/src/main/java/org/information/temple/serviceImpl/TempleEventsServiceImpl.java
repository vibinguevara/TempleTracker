package org.information.temple.serviceImpl;

import org.information.temple.model.TempleEvent;
import org.information.temple.repository.TempleEventRepository;
import org.information.temple.service.TempleEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempleEventsServiceImpl implements TempleEventService {
    private final TempleEventRepository templeEventRepository;

    public TempleEventsServiceImpl(TempleEventRepository templeEventRepository) {
        this.templeEventRepository = templeEventRepository;
    }

    @Override
    public TempleEvent createEvent(TempleEvent event) {
        return templeEventRepository.save(event);
    }

    @Override
    public List<TempleEvent> getEventsByTemple(Long templeId) {
        return templeEventRepository.findByTempleId(templeId);
    }

    @Override
    public List<TempleEvent> getEventsByUser(Long userId) {
        return templeEventRepository.findByUserId(userId);
    }
}
