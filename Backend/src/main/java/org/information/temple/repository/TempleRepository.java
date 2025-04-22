package org.information.temple.repository;

import org.information.temple.model.Temple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TempleRepository extends JpaRepository<Temple, Long> {
    List<Temple> findBySpecialPoojaDateTimeBetween(LocalDate start, LocalDate end);

    // Paging and sorting support
    Page<Temple> findAll(Pageable pageable);
}
