package com.example.labreporting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "laborants")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laborant {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "laborant_id")
    private  UUID uuid;

    @Column(name = "first_name")
    private  String firstName;

    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "hospital_number", length = 7)
    @Size(min = 7, max = 7, message =" Hastane numarasi 7 hane olmak zorunda!")
    private String hospitalNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "laborant", cascade = CascadeType.ALL)
    private List<Report> reports;
}
