package com.naviroq.orbit.controller;

import com.naviroq.orbit.domain.CreateOrbitRequest;
import com.naviroq.orbit.domain.UpdateOrbitRequest;
import com.naviroq.orbit.domain.dto.CreateOrbitRequestDto;
import com.naviroq.orbit.domain.dto.OrbitDto;
import com.naviroq.orbit.domain.dto.UpdateOrbitRequestDto;
import com.naviroq.orbit.domain.entity.Orbit;
import com.naviroq.orbit.mapper.OrbitMapper;
import com.naviroq.orbit.service.OrbitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api/v1/orbit")
public class OrbitController {

    private final OrbitService orbitService;
    private final OrbitMapper orbitMapper;

    public OrbitController(OrbitService orbitService, OrbitMapper orbitMapper) {
        this.orbitService = orbitService;
        this.orbitMapper = orbitMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<OrbitDto> createOrbit(
            @RequestBody
            @Valid
            CreateOrbitRequestDto createOrbitRequestDto
    ) {
        CreateOrbitRequest createOrbitRequest = orbitMapper.fromDto(createOrbitRequestDto);
        Orbit orbit =  orbitService.createOrbit(createOrbitRequest);
        OrbitDto createdOrbitDto = orbitMapper.toDto(orbit);
        return new ResponseEntity<>(createdOrbitDto, HttpStatus.CREATED);

    }

    // Get a single task
    @GetMapping("/{orbitId}")
    public ResponseEntity<OrbitDto> getOrbit(@PathVariable UUID orbitId) {
        Orbit orbit = orbitService.getOrbitById(orbitId);
        OrbitDto orbitDto = orbitMapper.toDto(orbit);
        return new ResponseEntity<>(orbitDto, HttpStatus.FOUND);
    }

    // Get a list of ALL orbits
    @GetMapping
    public ResponseEntity <List<OrbitDto>> listOrbits() {
        List<Orbit> orbits = orbitService.listOrbits();
        List<OrbitDto> orbitDtos = orbits.stream().map(orbitMapper::toDto).toList();
        return ResponseEntity.ok(orbitDtos);
    }

    @PutMapping(path = "/{orbitId}")
    public ResponseEntity <OrbitDto> updateOrbit(
            @PathVariable UUID orbitId,
            @Valid @RequestBody UpdateOrbitRequestDto updateOrbitRequestDto
    ) {
        UpdateOrbitRequest updateOrbitRequest = orbitMapper.fromDto(updateOrbitRequestDto);
        Orbit orbit = orbitService.updateOrbit(orbitId, updateOrbitRequest);
        OrbitDto orbitDto = orbitMapper.toDto(orbit);
        return ResponseEntity.ok(orbitDto);
    }

    @DeleteMapping(path = "/{orbitId}")
    public ResponseEntity<Void> deleteOrbit(@PathVariable UUID orbitId) {
        orbitService.deleteOrbit(orbitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
