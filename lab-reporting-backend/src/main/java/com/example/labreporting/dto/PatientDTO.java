package com.example.labreporting.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PatientDTO {
     private  UUID uuid;
     private    String firstName;
     private   String lastName;
     private  String nationalNumber;
}
