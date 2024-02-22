package com.example.labreporting.controller;

import com.example.labreporting.dto.ReportDTO;
import com.example.labreporting.mapper.ReportMapper;
import com.example.labreporting.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {


    private final ReportMapper reportMapper;
    private final ReportService reportService;

    public ReportController(ReportMapper reportMapper, ReportService reportService) {
        this.reportMapper = reportMapper;
        this.reportService = reportService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReportDTO> uploadReport(@RequestParam("report") String reportDTOJson,
                                                  @RequestParam("file") MultipartFile file) {
        try {
            ReportDTO createdReportDTO = reportService.saveReport(reportDTOJson, file);
            return new ResponseEntity<>(createdReportDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAllReports(){
        List<ReportDTO> reports = reportService.getAllReports();
        return new ResponseEntity<>(reports, OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getReportById(@PathVariable UUID id) {
        return reportService.getReport(id)
                .map(report -> ResponseEntity.ok(report))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



   @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReportDTO> updateReport(@PathVariable UUID id,
                                                  @RequestParam("report") String reportDTOJson,
                                                  @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            ReportDTO updatedReport = reportService.updateReport(id, reportDTOJson, file);
            return new ResponseEntity<>(updatedReport, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sorted/asc")
    public ResponseEntity<List<ReportDTO>> getAllReportsSortedByDataAsc(){
        List<ReportDTO> sortedReports = reportService.getAllReportsSortedByDateAsc();
      return   ResponseEntity.ok(sortedReports);
    }

    @GetMapping("/sorted/desc")
    public ResponseEntity<List<ReportDTO>> getAllReportsSortedByDataDesc(){
        List<ReportDTO> sortedReports = reportService.getAllReportsSortedByDateDesc();
      return   ResponseEntity.ok(sortedReports);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReportDTO>> searchReports(@RequestParam(required = false) String searchQuery){
        List<ReportDTO> reports = reportService.searchReports(searchQuery);
        return ResponseEntity.ok().body(reports);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteReport(@PathVariable("id") UUID id){
        reportService.deleteReport(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
