package com.example.labreporting.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "report_id")
    private UUID uuid;

    @Column(name = "file_number")
    private String fileNumber;

    @Column(name = "patient_first_name")
    private String patientFirstName;

    @Column(name = "patient_last_name")
    private String patientLastName;

    @Column(name = "national_number")
    private String nationalNumber;

    @Column(name = "diagnosis_title")
    private String diagnosisTitle;

    @Column(name = "diagnosis_details")
    private String diagnosisDetails;

    @Column(name = "report_date")
    private  Date reportDate;

    @Column(name = "report_image")
    @Lob
    private byte[] reportImage;

    @ManyToOne
    @JoinColumn(name = "laborant_id")
    private Laborant laborant;
}