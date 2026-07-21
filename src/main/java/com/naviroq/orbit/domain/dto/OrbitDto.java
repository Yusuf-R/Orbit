package com.naviroq.orbit.domain.dto;

import com.naviroq.orbit.domain.entity.OrbitPriority;
import com.naviroq.orbit.domain.entity.OrbitStatus;

import java.time.LocalDate;
import java.util.UUID;

// this dto is to be used to structure the api response
public record OrbitDto(UUID id, String title, String description, LocalDate dueDate, OrbitPriority priority, OrbitStatus status) {
}
