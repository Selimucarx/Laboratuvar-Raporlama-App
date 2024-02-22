package com.example.labreporting.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReportDTO {
     private    UUID uuid;
     private    String fileNumber;
     private   String patientFirstName;
     private   String patientLastName;
     private    String nationalNumber;
     private   String diagnosisTitle;
     private    String diagnosisDetails;
     private   Date reportDate;
     private byte[] reportImage;
     private  LaborantDTO laborantDTO;
}
