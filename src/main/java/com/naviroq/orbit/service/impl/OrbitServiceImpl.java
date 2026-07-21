package com.naviroq.orbit.service.impl;

import com.naviroq.orbit.domain.CreateOrbitRequest;
import com.naviroq.orbit.domain.entity.Orbit;
import com.naviroq.orbit.domain.entity.OrbitStatus;
import com.naviroq.orbit.repository.OrbitRepository;
import com.naviroq.orbit.service.OrbitService;
import org.springframework.stereotype.Service;

import java.time.Instant;

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
}
