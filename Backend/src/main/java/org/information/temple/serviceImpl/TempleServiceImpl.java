package org.information.temple.serviceImpl;

import org.information.temple.model.Temple;
import org.information.temple.model.User;
import org.information.temple.repository.TempleRepository;
import org.information.temple.repository.UserRepository;
import org.information.temple.service.TempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TempleServiceImpl implements TempleService {
    @Autowired
    private TempleRepository templeRepository;

    @Autowired
    private UserRepository userRepository;

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
        // Get the user ID from the temple object
        Long userId = temple.getUser() != null ? temple.getUser().getId() : null;

        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        // Fetch the user from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Set the user on the temple
        temple.setUser(user);

        return templeRepository.save(temple);
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
        if(templeDetails.getOfficialWebsite()!=null){
            existingTemple.setOfficialWebsite(templeDetails.getOfficialWebsite());
        }
        if(templeDetails.getDeityName()!=null){
            existingTemple.setDeityName(templeDetails.getDeityName());
        }
        if(templeDetails.getJurisdictionOfficer()!=null){
            existingTemple.setJurisdictionOfficer(templeDetails.getJurisdictionOfficer());
        }

        return templeRepository.save(existingTemple);
    }

    @Override
    public Page<Temple> searchTemples(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return templeRepository.searchTemples(keyword, pageable);
    }
}
