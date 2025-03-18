package org.information.temple.controller;

import org.information.temple.model.Temple;
import org.information.temple.service.TempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/temples")
@CrossOrigin("*")
public class TempleController {

    @Autowired
    private TempleService templeService;

    @GetMapping
    public List<Temple> getAllTemples() {
        return templeService.getAllTemples();
    }

    @GetMapping("/{id}")
    public Temple getTempleById(@PathVariable Long id) {
        return templeService.getTempleById(id);
    }

    @PostMapping
    public Temple saveTemple(@RequestBody Temple temple) {
        return templeService.saveTemple(temple);
    }

    @GetMapping("/today")
    public List<Temple> getTemplesBySpecialPooja() {
        return templeService.getTemplesBySpecialPoojaDate(LocalDate.now());
    }
}
