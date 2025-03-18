package org.information.temple.serviceImpl;

import org.information.temple.model.Temple;
import org.information.temple.repository.TempleRepository;
import org.information.temple.service.TempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TempleServiceImpl implements TempleService {
    @Autowired
    private TempleRepository templeRepository;

    @Override
    public List<Temple> getAllTemples() {
        return templeRepository.findAll();
    }

    @Override
    public Temple getTempleById(Long id) {
        return templeRepository.findById(id).orElse(null);
    }

    @Override
    public Temple saveTemple(Temple temple) {
        return templeRepository.save(temple);
    }

    @Override
    public List<Temple> getTemplesBySpecialPoojaDate(LocalDate date) {
        LocalDate startOfDay = LocalDate.from(date.atTime(LocalTime.MIN));  // 00:00:00
        LocalDate endOfDay = LocalDate.from(date.atTime(LocalTime.MAX));    // 23:59:59.999999999
        return templeRepository.findBySpecialPoojaDateTimeBetween(startOfDay, endOfDay);
    }
}
