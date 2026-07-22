package com.naviroq.orbit.repository;

import com.naviroq.orbit.domain.entity.Orbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrbitRepository extends JpaRepository <Orbit, UUID> {
    // this will be use to query the database of Orbit table, using the UUID as our id
}
