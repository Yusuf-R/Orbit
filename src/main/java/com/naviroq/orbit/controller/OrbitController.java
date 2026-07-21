package com.naviroq.orbit.controller;

import com.naviroq.orbit.mapper.OrbitMapper;
import com.naviroq.orbit.service.OrbitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "api/v1/orbit")
public class OrbitController {

    private final OrbitService orbitService;
    private final OrbitMapper orbitMapper;

    public OrbitController(OrbitService orbitService, OrbitMapper orbitMapper) {
        this.orbitService = orbitService;
        this.orbitMapper = orbitMapper;
    }

}
