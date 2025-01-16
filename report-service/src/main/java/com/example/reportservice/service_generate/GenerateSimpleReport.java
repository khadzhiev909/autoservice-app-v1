package com.example.reportservice.service_generate;

import com.example.reportservice.entity.Report;
import com.example.reportservice.entity.SimplePart;
import com.example.reportservice.repository.SimplePartRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class GenerateSimpleReport {

    private final SimplePartRepository simplePartRepository;
    private final Logger logger = Logger.getLogger("GenerateSimpleReport.class");

    public GenerateSimpleReport(SimplePartRepository simplePartRepository) {
        this.simplePartRepository = simplePartRepository;
    }

    public byte[] generateReportToPdf(String reportType) {
        try {
            String templatePath = "C:\\Users\\Эпенди\\IdeaProjects\\AutoServiceApp\\report-service\\src\\main\\resources\\templates\\SimpleReport.jrxml";

            JasperReport jasperReport = JasperCompileManager.compileReport(templatePath);
            List<SimplePart> parts = simplePartRepository.findAll();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(parts);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_TITLE", "Отчет о запчастях");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            logger.info("Отчет успешно сгенерирован");
            return outputStream.toByteArray();
        } catch (JRException e) {
            logger.severe("Ошибка при генерации отчета" + e.getMessage());
            return null;
        }
    }

}
