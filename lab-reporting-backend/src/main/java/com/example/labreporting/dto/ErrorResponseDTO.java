package com.example.labreporting.dto;



import java.util.Date;



public record ErrorResponseDTO(int resultCode, String result, String errorMessage, Date time) {
}