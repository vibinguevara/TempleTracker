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

    @Override
    public Temple updateTemple(Long id, Temple templeDetails) {
        Temple existingTemple = templeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Temple not found with id: " + id));

        // Update only the fields that are provided in the request
        if (templeDetails.getPrimaryTempleName() != null) {
            existingTemple.setPrimaryTempleName(templeDetails.getPrimaryTempleName());
        }
        if (templeDetails.getSecondaryTempleName() != null) {
            existingTemple.setSecondaryTempleName(templeDetails.getSecondaryTempleName());
        }
        if (templeDetails.getStreetAddress() != null) {
            existingTemple.setStreetAddress(templeDetails.getStreetAddress());
        }
        if (templeDetails.getOtherAddress() != null) {
            existingTemple.setOtherAddress(templeDetails.getOtherAddress());
        }
        if (templeDetails.getCity() != null) {
            existingTemple.setCity(templeDetails.getCity());
        }
        if (templeDetails.getState() != null) {
            existingTemple.setState(templeDetails.getState());
        }
        if (templeDetails.getPostalCode() != null) {
            existingTemple.setPostalCode(templeDetails.getPostalCode());
        }
        if (templeDetails.getOfficialEmail() != null) {
            existingTemple.setOfficialEmail(templeDetails.getOfficialEmail());
        }
        if (templeDetails.getOfficialPhone() != null) {
            existingTemple.setOfficialPhone(templeDetails.getOfficialPhone());
        }
        if (templeDetails.getSpecialPoojaDateTime() != null) {
            existingTemple.setSpecialPoojaDateTime(templeDetails.getSpecialPoojaDateTime());
        }
        if (templeDetails.isAnnadhanamAvailable()) {
            existingTemple.setAnnadhanamAvailable(templeDetails.isAnnadhanamAvailable());
        }
        if (templeDetails.isPrasadhamAvailable()) {
            existingTemple.setPrasadhamAvailable(templeDetails.isPrasadhamAvailable());
        }

        return templeRepository.save(existingTemple);
    }
}
