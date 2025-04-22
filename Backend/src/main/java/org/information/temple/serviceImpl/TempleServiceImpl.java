package org.information.temple.serviceImpl;

import org.information.temple.model.Temple;
import org.information.temple.repository.TempleRepository;
import org.information.temple.service.TempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
        LocalDate startOfDay = LocalDate.from(date.atTime(LocalTime.MIN));
        LocalDate endOfDay = LocalDate.from(date.atTime(LocalTime.MAX));
        return templeRepository.findBySpecialPoojaDateTimeBetween(startOfDay, endOfDay);
    }

    @Override
    public Page<Temple> getPaginatedTemples(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return templeRepository.findAll(pageable);
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
        if (templeDetails.getCityAddress() != null) {
            existingTemple.setCityAddress(templeDetails.getCityAddress());
        }
        if (templeDetails.getStateAddress() != null) {
            existingTemple.setStateAddress(templeDetails.getStateAddress());
        }
        if (templeDetails.getPostalCodeAddress() != null) {
            existingTemple.setPostalCodeAddress(templeDetails.getPostalCodeAddress());
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
        if (templeDetails.isStateGoverned()) {
            existingTemple.setAnnadhanamAvailable(templeDetails.isAnnadhanamAvailable());
        }
        if(templeDetails.getJurisdictionOfficer()!=null){
            existingTemple.setJurisdictionOfficer(templeDetails.getJurisdictionOfficer());
        }
        if (templeDetails.isPrasadhamAvailable()) {
            existingTemple.setPrasadhamAvailable(templeDetails.isPrasadhamAvailable());
        }
        return templeRepository.save(existingTemple);
    }

    @Override
    public Page<Temple> searchTemples(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return templeRepository.searchTemples(keyword, pageable);
    }
}
