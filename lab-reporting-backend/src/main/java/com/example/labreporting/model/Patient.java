package com.example.labreporting.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "patients")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "patient_id")
    private UUID uuid;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "national_number")
    private String nationalNumber;

}
