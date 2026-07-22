package com.naviroq.orbit.exception;

import lombok.Getter;

import java.util.UUID;

// this is an error that is handled at the service layer --
// lets say you try to find something in the database but it does not exist, then its taken care of here
@Getter
public class OrbitNotFoundException extends RuntimeException {

    private final UUID id;

    public OrbitNotFoundException(UUID id) {
        super(String.format("Orbit object with ID '%s' does not exist.", id));
        this.id = id;
    }

}