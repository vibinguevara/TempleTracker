package org.information.temple.service;

import org.information.temple.model.Temple;

import java.time.LocalDate;
import java.util.List;

public interface TempleService {
    List<Temple> getAllTemples();
    Temple getTempleById(Long id);
    Temple saveTemple(Temple temple);
    List<Temple> getTemplesBySpecialPoojaDate(LocalDate date);

    Temple updateTemple(Long id, Temple templeDetails);
}
