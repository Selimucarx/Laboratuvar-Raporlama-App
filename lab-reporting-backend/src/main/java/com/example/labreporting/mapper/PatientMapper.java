package com.example.labreporting.mapper;

import com.example.labreporting.dto.PatientDTO;
import com.example.labreporting.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    Patient patientDTOToPatient(PatientDTO patientDTO);

    PatientDTO patientToPatientDTO(Patient patient);
}