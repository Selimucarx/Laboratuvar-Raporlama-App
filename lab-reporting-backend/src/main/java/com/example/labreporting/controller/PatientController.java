package com.example.labreporting.controller;

import com.example.labreporting.dto.PatientDTO;
import com.example.labreporting.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientService.createPatient(patientDTO);
        return ResponseEntity.ok().body(createdPatient);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") UUID id){
        patientService.deletePatient(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientDTO>> searchPatients(@RequestParam(required = false) String searchQuery){
        List<PatientDTO> patients = patientService.searchPatients(searchQuery);
        return ResponseEntity.ok().body(patients);
    }

}
