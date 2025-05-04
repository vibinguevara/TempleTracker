package org.information.temple.repository;

import org.information.temple.model.TempleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempleEventRepository extends JpaRepository<TempleEvent, Long> {
    List<TempleEvent> findByTempleId(Long templeId);
    List<TempleEvent> findByUserId(Long userId);
}
