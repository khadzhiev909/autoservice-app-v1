//package com.example.reportservice.service_generate;
//
//import com.example.reportservice.entity.Invoice;
//import com.example.reportservice.entity.Report;
//import com.example.reportservice.repository.ReportRepository;
//import com.example.reportservice.service.InvoiceService;
//import lombok.RequiredArgsConstructor;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class RevenueReportService {
//
//    private final InvoiceService revenueService;  // Сервис для получения данных
//    private final ReportRepository reportRepository;
//
//    public Report generateRenevueReport(LocalDateTime startDate, LocalDateTime endDate) throws JRException {
//        // 1. Получаем данные о доходах за период
//        List<Invoice> revenues = revenueService.getRevenueData(startDate, endDate);
//
//        // 2. Подготавливаем данные для отчета
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(revenues);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("StartDate", startDate);
//        parameters.put("EndDate", endDate);
//
//        // 4. Компилируем шаблон
//        JasperReport jasperReport = compileTemplate("/templates/RevenueReport.jrxml");
//
//        // 5. Заполняем отчет данными и компилированным шаблоном
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//        // 6. Создаем поток для PDF и экспортируем отчет в формат PDF
//        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, pdfStream);
//
//        // 6. Сохраняем отчет в базе данных
//        Report report = new Report();
//        report.setReportType("RevenueReport");
//        report.setGeneratedAt(LocalDateTime.now());
//        report.setStatus("GENERATED");
//        report.setContent(pdfStream.toByteArray());
//
//        return reportRepository.save(report);
//
//    }
//
//    private JasperReport compileTemplate(String templatePath) throws JRException {
//        try (InputStream templateStream = getClass().getResourceAsStream(templatePath)) {
//            if (templateStream == null) {
//                throw new JRException("Шаблон не найден по пути: " + templatePath);
//            }
//            return JasperCompileManager.compileReport(templateStream);
//        } catch (Exception e) {
//            throw new JRException("Ошибка при компиляции шаблона", e);
//        }
//    }
//}
