package com.naviroq.orbit.domain;

import com.naviroq.orbit.domain.entity.OrbitPriority;

import java.time.LocalDate;

public record CreateOrbitRequest(String title, String description, LocalDate dueDate, OrbitPriority priority) {
}
