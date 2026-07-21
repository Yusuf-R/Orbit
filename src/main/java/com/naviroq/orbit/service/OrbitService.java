package com.naviroq.orbit.service;

import com.naviroq.orbit.domain.CreateOrbitRequest;
import com.naviroq.orbit.domain.entity.Orbit;

public interface OrbitService {
    Orbit createOrbit (CreateOrbitRequest request);
    // we can also like return a create orbit object -- just like the state design pattern for behavioral
}
