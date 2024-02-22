package com.example.labreporting.service;


import com.example.labreporting.dto.ReportDTO;
import com.example.labreporting.mapper.ReportMapper;
import com.example.labreporting.model.Report;
import com.example.labreporting.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.io.IOException;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportService {


    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    public ReportDTO saveReport(String reportDTOJson, MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ReportDTO reportDTO = objectMapper.readValue(reportDTOJson, ReportDTO.class);

        byte[] imageBytes = file.getBytes();
        reportDTO.setReportImage(imageBytes);

        Report report = reportMapper.toEntity(reportDTO);
        report.setReportDate(new Date());
        report = reportRepository.save(report);

        return reportMapper.toDto(report);
    }

    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::reportToReportDTO)
                .collect(Collectors.toList());
    }



    public Optional<ReportDTO> getReport(UUID id) {
        return reportRepository.findById(id)
                .map(reportMapper::toDto);
    }






    public ReportDTO updateReport(UUID id, String reportDTOJson, MultipartFile file) throws IOException {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            throw new RuntimeException("Report not found for ID: " + id);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ReportDTO reportDTO = objectMapper.readValue(reportDTOJson, ReportDTO.class);

        Report existingReport = optionalReport.get();

        if (file != null && !file.isEmpty()) {
            byte[] imageBytes = file.getBytes();
            reportDTO.setReportImage(imageBytes);
        }

        existingReport.setFileNumber(reportDTO.getFileNumber());
        existingReport.setPatientFirstName(reportDTO.getPatientFirstName());
        existingReport.setPatientLastName(reportDTO.getPatientLastName());
        existingReport.setNationalNumber(reportDTO.getNationalNumber());
        existingReport.setDiagnosisTitle(reportDTO.getDiagnosisTitle());
        existingReport.setDiagnosisDetails(reportDTO.getDiagnosisDetails());
        existingReport.setReportDate(new Date());
        if (reportDTO.getReportImage() != null) {
            existingReport.setReportImage(reportDTO.getReportImage());
        }


        Report updatedReport = reportRepository.save(existingReport);

        return reportMapper.toDto(updatedReport);
    }


    public void deleteReport(UUID id) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            throw new RuntimeException("Duzelcemburayi" + id);
        }

        reportRepository.deleteById(id);
    }

    public List<ReportDTO> getAllReportsSortedByDateAsc() {
        List<Report> reports =reportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportDate"));
        return reports.stream().map(reportMapper::reportToReportDTO).collect(Collectors.toList());

    }

    public List<ReportDTO> getAllReportsSortedByDateDesc() {
        List<Report> reports =reportRepository.findAll(Sort.by(Sort.Direction.ASC, "reportDate"));
        return reports.stream().map(reportMapper::reportToReportDTO).collect(Collectors.toList());

    }


    public List<ReportDTO> searchReports(String searchQuery) {
        Specification<Report> spec = ReportSpecifications.withDynamicQuery(searchQuery);
        List<Report> reports = reportRepository.findAll(spec);
        return reports.stream().map(reportMapper::reportToReportDTO).collect(Collectors.toList());
    }



}
