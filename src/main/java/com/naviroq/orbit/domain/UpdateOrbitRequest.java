package com.naviroq.orbit.domain;

import com.naviroq.orbit.domain.entity.OrbitPriority;
import com.naviroq.orbit.domain.entity.OrbitStatus;

import java.time.LocalDate;

public record UpdateOrbitRequest(
        String title,
        String description,
        LocalDate dueDate,
        OrbitStatus status,
        OrbitPriority priority
) {
}
