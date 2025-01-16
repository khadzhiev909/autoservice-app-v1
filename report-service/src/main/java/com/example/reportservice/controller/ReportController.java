package com.example.reportservice.controller;

import com.example.reportservice.entity.Report;
import com.example.reportservice.service_generate.GenerateSimpleReport;
import com.example.reportservice.service_generate.ReportService;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;
    private final GenerateSimpleReport simpleReport;

    public ReportController(ReportService reportService, GenerateSimpleReport simpleReport) {
        this.reportService = reportService;
        this.simpleReport = simpleReport;
    }
//    private final RevenueReportService revenueReportService;

    /**
     * Генерирует отчет по запросу.
     *
     * @param reportType Тип отчета для генерации.
     * @return HTTP ответ со статусом создания.
     */
    @PostMapping("/generate")
    public ResponseEntity<String> generateReport(@RequestParam String reportType) {
        try {
            reportService.generateReport(reportType);
//            simpleReport.generateReportToPdf(reportType);
            return ResponseEntity.status(HttpStatus.CREATED).body("Report generated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate report: " + e.getMessage());
        }
    }

    @GetMapping("/simple-generate")
    public ResponseEntity<byte[]> generateSimpleReport(@RequestParam String reportType) {
        try {
            byte[] bytes = simpleReport.generateReportToPdf(reportType);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.pdf\"") // Убрали экранирование
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/download/service/{reportId}")
    @Timed("reportApiCounting")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Long reportId) {
        Optional<Report> reportOptional = reportService.getReportById(reportId);
        if (reportOptional.isPresent()) {
            Report report = reportOptional.get();
            byte[] content = report.getContent();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.pdf\"") // Убрали экранирование
                    .body(content);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


//    @GetMapping("/download/revenue")
//    public ResponseEntity<byte[]> downloadRevenueReport(
//            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//
//        try {
//            Report report = revenueReportService.generateRenevueReport(startDate.atStartOfDay(), endDate.atStartOfDay());
//
//            byte[] content = report.getContent();
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RevenueReport.pdf\"") // Убрали экранирование
//                    .body(content);
//        } catch (JRException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//
//
//    }
}
