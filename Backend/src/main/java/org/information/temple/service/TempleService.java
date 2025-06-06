package org.information.temple.service;

import org.information.temple.model.Temple;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface TempleService {
    List<Temple> getAllTemples();
    Temple getTempleById(Long id);
    Temple saveTemple(Temple temple);

    Temple updateTemple(Long id, Temple templeDetails);

    // New method for paginated results
    Page<Temple> getPaginatedTemples(int page, int size);

    Page<Temple> searchTemples(String keyword, int page, int size);
}
