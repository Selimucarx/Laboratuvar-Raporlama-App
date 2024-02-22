package com.example.labreporting.service;

import com.example.labreporting.dto.PatientDTO;
import com.example.labreporting.mapper.PatientMapper;
import com.example.labreporting.model.Patient;
import com.example.labreporting.repository.PatientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {


    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }


    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient newPatient = patientMapper.patientDTOToPatient(patientDTO);
        Patient savedPatient = patientRepository.save(newPatient);
        return patientMapper.patientToPatientDTO(savedPatient);
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::patientToPatientDTO)
                .collect(Collectors.toList());
    }





    public List<PatientDTO> searchPatients(String searchQuery) {
        Specification<Patient> spec = PatientSpecifications.withDynamicQuery(searchQuery);
        List<Patient> patients = patientRepository.findAll(spec);
        return patients.stream().map(patientMapper::patientToPatientDTO).collect(Collectors.toList());
    }

    public void deletePatient(UUID id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new RuntimeException("Duzelcemburayi" + id);
        }
        patientRepository.deleteById(id);
    }


}
