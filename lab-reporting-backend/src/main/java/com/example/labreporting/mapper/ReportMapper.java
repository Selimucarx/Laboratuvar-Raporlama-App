package com.example.labreporting.mapper;

import com.example.labreporting.dto.ReportDTO;
import com.example.labreporting.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = LaborantMapper.class)
public interface ReportMapper {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    ReportDTO reportToReportDTO(Report report);

    Report reportDTOToReport(ReportDTO reportDTO);

    @Mapping(target = "laborant", source = "laborantDTO")
    Report toEntity(ReportDTO dto);

    @Mapping(target = "laborantDTO", source = "laborant")
    ReportDTO toDto(Report entity);

    void updateReportFromDto(ReportDTO reportDTO, @MappingTarget Report report);


}