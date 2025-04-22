package org.information.temple.repository;

import org.information.temple.model.Temple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TempleRepository extends JpaRepository<Temple, Long> {
    List<Temple> findBySpecialPoojaDateTimeBetween(LocalDate start, LocalDate end);

    // Paging and sorting support
    Page<Temple> findAll(Pageable pageable);

    @Query("SELECT t FROM Temple t WHERE " +
            "LOWER(t.primaryTempleName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.cityAddress) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.stateAddress) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.postalCodeAddress) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Temple> searchTemples(@Param("keyword") String keyword, Pageable pageable);

}
