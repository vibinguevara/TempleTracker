package org.information.temple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.information.temple.model.Temple;
import org.information.temple.service.TempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/temples")
@CrossOrigin("*")
@Tag(name = "Temple API", description = "APIs for managing temples and special pooja information")
public class TempleController {

    @Autowired
    private TempleService templeService;

    @GetMapping
    @Operation(summary = "Get all temples", description = "Fetches a list of all temples")
    public List<Temple> getAllTemples() {
        return templeService.getAllTemples();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a temple by ID", description = "Fetches a specific temple by its ID")
    public Temple getTempleById(@PathVariable Long id) {
        return templeService.getTempleById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new temple", description = "Adds a new temple to the database")
    public Temple saveTemple(@RequestBody Temple temple) {
        return templeService.saveTemple(temple);
    }

    @GetMapping("/today")
    @Operation(summary = "Get temples with special pooja", description = "Fetches temples with special pooja on a given date")
    public List<Temple> getTemplesBySpecialPooja() {
        return templeService.getTemplesBySpecialPoojaDate(LocalDate.now());
    }
}
