package com.naviroq.orbit.service;

import com.naviroq.orbit.domain.CreateOrbitRequest;
import com.naviroq.orbit.domain.UpdateOrbitRequest;
import com.naviroq.orbit.domain.entity.Orbit;

import java.util.List;
import java.util.UUID;

public interface OrbitService {
    // this is the class that will directly do things to my database
    Orbit createOrbit(CreateOrbitRequest request);

    List<Orbit> listOrbits();

    Orbit getOrbitById(UUID orbitId);

    Orbit updateOrbit(UUID orbitId, UpdateOrbitRequest request);

    void deleteOrbit(UUID orbitId);
}
