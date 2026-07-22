package com.naviroq.orbit.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;


// see it like your database model
@Entity
@Table(name = "orbit")
@NoArgsConstructor // constructor without args
@AllArgsConstructor // constructor with all the args passed
@Getter
@Setter
public class Orbit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrbitStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private OrbitPriority priority;


    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant created;

    @Column(name = "updated_at", nullable = false)
    private Instant updated;

    // lombok will help generate -- all getters, all setters, constructor

    @Override
    public String toString() {
        return "Orbit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", priority=" + priority +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
