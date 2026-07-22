package com.naviroq.orbit.domain.dto;

import com.naviroq.orbit.domain.entity.OrbitPriority;
import com.naviroq.orbit.domain.entity.OrbitStatus;

import java.time.LocalDate;
import java.util.UUID;

// General response shape for returning an Orbit to the client — used for create, read, and update responses
// this feels like the Return DTO -- this will always be the structure to be returned to the client as the need be
public record OrbitDto(UUID id, String title, String description, LocalDate dueDate, OrbitPriority priority, OrbitStatus status) {
}
