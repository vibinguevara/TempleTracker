package org.information.temple.repository;

import org.information.temple.model.TempleTrackerDonator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempleTrackerDonationRepository extends JpaRepository<TempleTrackerDonator, Long> {
}
