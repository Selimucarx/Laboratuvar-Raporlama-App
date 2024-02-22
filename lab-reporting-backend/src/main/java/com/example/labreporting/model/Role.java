package com.example.labreporting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "role_id")
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;
}
