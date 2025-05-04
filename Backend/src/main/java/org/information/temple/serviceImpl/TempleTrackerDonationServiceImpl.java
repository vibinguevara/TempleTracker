package org.information.temple.serviceImpl;

import org.information.temple.model.TempleTrackerDonator;
import org.information.temple.repository.TempleTrackerDonationRepository;
import org.information.temple.service.TempleTrackerDonationService;
import org.springframework.stereotype.Service;

@Service
public class TempleTrackerDonationServiceImpl implements TempleTrackerDonationService {
    private final TempleTrackerDonationRepository repository;

    public TempleTrackerDonationServiceImpl(TempleTrackerDonationRepository repository) {
        this.repository = repository;
    }

    @Override
    public TempleTrackerDonator saveDonation(TempleTrackerDonator donator) {
        return repository.save(donator);
    }
}
