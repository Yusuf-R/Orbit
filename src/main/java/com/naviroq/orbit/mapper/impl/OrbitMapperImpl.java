package com.naviroq.orbit.mapper.impl;

import com.naviroq.orbit.domain.CreateOrbitRequest;
import com.naviroq.orbit.domain.UpdateOrbitRequest;
import com.naviroq.orbit.domain.dto.CreateOrbitRequestDto;
import com.naviroq.orbit.domain.dto.OrbitDto;
import com.naviroq.orbit.domain.dto.UpdateOrbitRequestDto;
import com.naviroq.orbit.domain.entity.Orbit;
import com.naviroq.orbit.mapper.OrbitMapper;
import org.springframework.stereotype.Component;

@Component
public class OrbitMapperImpl implements OrbitMapper {


    @Override
    public CreateOrbitRequest fromDto(CreateOrbitRequestDto dto) {
        return new CreateOrbitRequest (
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority()
        );
    }

    @Override
    public OrbitDto toDto(Orbit orbit) {
        return new OrbitDto (
                orbit.getId(),
                orbit.getTitle(),
                orbit.getDescription(),
                orbit.getDueDate(),
                orbit.getPriority(),
                orbit.getStatus()
        );
    }

    @Override
    public UpdateOrbitRequest fromDto(UpdateOrbitRequestDto dto) {
        return new UpdateOrbitRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.status(),
                dto.priority()
        );
    }
}
