package com.naviroq.orbit.service.impl;

import com.naviroq.orbit.domain.CreateOrbitRequest;
import com.naviroq.orbit.domain.UpdateOrbitRequest;
import com.naviroq.orbit.domain.entity.Orbit;
import com.naviroq.orbit.domain.entity.OrbitStatus;
import com.naviroq.orbit.exception.OrbitNotFoundException;
import com.naviroq.orbit.repository.OrbitRepository;
import com.naviroq.orbit.service.OrbitService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class OrbitServiceImpl implements OrbitService {

    private final OrbitRepository orbitRepository;

    public OrbitServiceImpl(OrbitRepository orbitRepository) {
        this.orbitRepository = orbitRepository;
    }

    @Override
    public Orbit createOrbit(CreateOrbitRequest request) {
        Instant now = Instant.now();
        Orbit newOrbit = new Orbit(
                null,
                request.title(),
                request.description(),
                request.dueDate(),
                OrbitStatus.OPEN,
                request.priority(),
                now,
                now
        );
        return orbitRepository.save(newOrbit);
    }

    @Override
    public List<Orbit> listOrbits() {
        return orbitRepository.findAll(Sort.by(Sort.Direction.ASC, "created"));
    }

    @Override
    public Orbit updateOrbit(UUID orbitId, UpdateOrbitRequest request) {
        Orbit orbit = orbitRepository.findById(orbitId)
                .orElseThrow(() -> new OrbitNotFoundException(orbitId));

        orbit.setTitle(request.title());
        orbit.setDescription(request.description());
        orbit.setDueDate(request.dueDate());
        orbit.setStatus(request.status());
        orbit.setPriority(request.priority());
        orbit.setUpdated(Instant.now());

        return orbitRepository.save(orbit);
    }

    public Orbit getOrbitById(UUID id) {
        return orbitRepository.findById(id)
                .orElseThrow(() -> new OrbitNotFoundException(id));
    }

    @Override
    public void deleteOrbit(UUID orbitId) {
        if (!orbitRepository.existsById(orbitId)) {
            throw new OrbitNotFoundException(orbitId);
        }
        orbitRepository.deleteById(orbitId);
    }
}
