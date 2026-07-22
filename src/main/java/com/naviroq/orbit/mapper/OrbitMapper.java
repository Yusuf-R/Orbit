package com.naviroq.orbit.mapper;

import com.naviroq.orbit.domain.CreateOrbitRequest;
import com.naviroq.orbit.domain.UpdateOrbitRequest;
import com.naviroq.orbit.domain.dto.CreateOrbitRequestDto;
import com.naviroq.orbit.domain.dto.OrbitDto;
import com.naviroq.orbit.domain.dto.UpdateOrbitRequestDto;
import com.naviroq.orbit.domain.entity.Orbit;

public interface OrbitMapper {
    CreateOrbitRequest fromDto (CreateOrbitRequestDto dto);
    OrbitDto toDto (Orbit orbit);
    UpdateOrbitRequest fromDto(UpdateOrbitRequestDto dto);

}
