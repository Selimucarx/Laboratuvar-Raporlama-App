package com.example.labreporting.service;

import com.example.labreporting.dto.PatientDTO;
import com.example.labreporting.mapper.PatientMapper;
import com.example.labreporting.model.Patient;
import com.example.labreporting.repository.PatientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.*;

public class PatientServiceTest {



    private PatientService patientService;


    private  PatientRepository patientRepository;
    private  PatientMapper patientMapper;

    @Before
    public void setUp() throws Exception {

        //Testlerden once calisir ve sahte nesneler olusturup onlari kullanarak PatientService olusturuyor.
        patientRepository = Mockito.mock(PatientRepository.class);
        patientMapper = Mockito.mock(PatientMapper.class);


        patientService = new PatientService(patientRepository,patientMapper);
    }

    @Test
    public void whenCreatePatientCalledWithValidRequest_itShouldReturnValidPatientDTO(){
        // Onceden dogru bilgiler ile tanimlanmis nesneler ile methodlari test eder.
        //Sonuc PatientDTO ile eslesirse dogrular

        PatientDTO patientDTO = generatePatientDTO();
        Patient patient = generatePatient(patientDTO);
        Patient savedPatient = generateSavedPatient(patient);

        Mockito.when(patientMapper.patientDTOToPatient(patientDTO)).thenReturn(patient);
        Mockito.when(patientRepository.save(patient)).thenReturn(savedPatient);
        Mockito.when(patientMapper.patientToPatientDTO(savedPatient)).thenReturn(patientDTO);

        PatientDTO result = patientService.createPatient(patientDTO);

        Assert.assertEquals(result, patientDTO);
        Mockito.verify(patientMapper).patientDTOToPatient(patientDTO);
        Mockito.verify(patientRepository).save(patient);
        Mockito.verify(patientMapper).patientToPatientDTO(savedPatient);

    }


    private PatientDTO generatePatientDTO(){
        return PatientDTO.builder()
                .uuid(UUID.randomUUID())
                .firstName("ozgur")
                .lastName("yazilim")
                .nationalNumber("123456777")
                .build();
    }


    private Patient generatePatient(PatientDTO patientDTO){
        return Patient.builder()
                .uuid(patientDTO.getUuid())
                .firstName(patientDTO.getFirstName())
                .lastName(patientDTO.getLastName())
                .nationalNumber(patientDTO.getNationalNumber())
                .build();
    }

    private Patient generateSavedPatient(Patient patient){
        return Patient.builder()
                .uuid(patient.getUuid())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .nationalNumber(patient.getNationalNumber())
                .build();
    }


}