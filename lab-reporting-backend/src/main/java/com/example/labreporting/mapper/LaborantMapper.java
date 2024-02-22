package com.example.labreporting.mapper;

import com.example.labreporting.dto.LaborantDTO;
import com.example.labreporting.model.Laborant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LaborantMapper {

    LaborantMapper INSTANCE = Mappers.getMapper(LaborantMapper.class);

    LaborantDTO laborantToLaborantDTO(Laborant laborant);

    Laborant laborantDTOToLaborant(LaborantDTO laborantDTO);
}