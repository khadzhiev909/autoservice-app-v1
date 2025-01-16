package com.example.reportservice.service_generate;

//import com.example.reportservice.dto.AppointmentDTO;
import com.example.reportservice.dto.AppointmentDTO;
import com.example.reportservice.entity.Appointment;
import com.example.reportservice.entity.Customer;
import com.example.reportservice.entity.Report;
//import com.example.reportservice.mapper.ToAppointmentDTO;
import com.example.reportservice.repository.ReportRepository;
import com.example.reportservice.service.AppointmentService;
import io.micrometer.core.instrument.MeterRegistry;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Service
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final AppointmentService appointmentService;

    private final Logger logger = Logger.getLogger("ReportService.class");

//    private RedisTemplate<String, Object> redisTemplate;
//    private final ToAppointmentDTO toAppointmentDTO;
//    KafkaTemplate<String, Customer> kafka;

//    public void kafkaTest(String topic, String message, int partition) {
//        ProducerRecord<String, String> customerProducerRecord = new ProducerRecord<>(topic, partition, null, message);
//
//    }



    private AtomicInteger count;

    @Autowired
    public ReportService(ReportRepository reportRepository, AppointmentService appointmentService, MeterRegistry meterRegistry) {
        this.reportRepository = reportRepository;
        this.appointmentService = appointmentService;
//        this.toAppointmentDTO = toAppointmentDTO;
        count = new AtomicInteger();
        meterRegistry.gauge("count_generated_reports", count);
    }

    private static final String DAILY_REPORT_KEY = "daily_report";
    private static final String WEEKLY_REPORT_KEY = "weekly_report";
    //TODO: реализовать логику редиса

    //TODO: реализовать методы для генерации отчетов по фильтрам, например, за день, за неделю, за месяц и за год
//    public Report generateReportForDay() throws JRException {
//        //Проверяем есть ли в кэше
//        Report cachedReport = (Report) redisTemplate.opsForValue().get(DAILY_REPORT_KEY);
//        if (cachedReport != null) {
//            return cachedReport; // Возвращаем из кэша
//        }
//        //Если нет в кэше, генерируем отчет из БД
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime startOfDay = now.minusDays(1);
//
//        List<Appointment> appointments = appointmentService.getAllAppointmentsByDate(startOfDay);
//        Report report = generateReport(" ");//добавить логику в отдельный метод, где будет
//        // передоваться appointments(data course), тип(pdf,exel), и какой именно
//        //может попытаться реализовать через паттерн стратегия
//
//
//        return report;
//    }

    public Report generateReportForWeek() {
        return null;
    }
    public Report generateReportForMonth() {
        return null;
    }

    public Report generateReportForYears() {
        return null;
    }


    /**
     * Генерирует PDF-отчет и сохраняет его в базе данных.
     *
     * @param reportType Тип отчета (например, "ServiceReport")
     * @return Объект Report, представляющий сгенерированный отчет.
     * @throws JRException если возникла ошибка во время генерации отчета.
     */
    public Report generateReport(String reportType) {
        try {

            // 1. Путь к шаблону
            String templatePath = "/templates/ServiceReport2.jrxml";
            // 2. Получаем данные для отчета (например, записи о встречах)
            List<Appointment> appointments = appointmentService.getAllAppointments();

            if (appointments == null) {
                throw new NullPointerException("Appointments is null");
            }
//        List<AppointmentDTO> appointmentDTOS = toAppointmentDTO.mapToAppointmentDTO(appointments);
            // 3. Подготавливаем данные в формате, поддерживаемом JasperReports
        /*Источник данных для отчета.
        Определяет, как отчёт получает данные. JasperReports поддерживает:
        JRBeanCollectionDataSource — Коллекции Java объектов.
        JRResultSetDataSource — Результаты SQL-запросов.*/
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mapToAppointmentDTO(appointments));
//         Представляет параметры, которые передаются в отчет для фильтрации данных или настройки поведения.
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_TITLE", "Отчеты по сервисам");
            // 4. Компилируем шаблон в .jasper
            JasperReport jasperReport = compileTemplate(templatePath);
            // 5. Заполняем отчет данными и компилированным шаблоном
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            /*
            Содержит готовый отчет с данными, который можно экспортировать в PDF, Excel, HTML и другие форматы.
            Генерируется после заполнения шаблона отчетными данными.*/

            /* JasperFillManager Заполняет шаблон (JasperReport) данными из источника (например, базы данных, коллекций).
            * Генерирует объект JasperPrint для экспорта или отображения.
             fillReport(JasperReport, Map<String, Object>, JRDataSource): Заполняет отчет данными.
             fillReportToStream(...): Заполняет и записывает результат в поток.  */

            // 6. Создаем поток для PDF и экспортируем отчет в формат PDF
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        /*Экспортирует готовый отчет (JasperPrint) в различные форматы: PDF, HTML, XML.
        * exportReportToPdfFile(JasperPrint, String): Экспорт в PDF.
        exportReportToHtmlFile(JasperPrint, String): Экспорт в HTML.*/
            JasperExportManager.exportReportToPdfStream(jasperPrint, pdfStream);
            // 7. Создаем объект Report и сохраняем его в базе данных
            Report report = new Report();
            report.setReportType(reportType);
            report.setGeneratedAt(LocalDateTime.now());
            report.setStatus("GENERATED");
            report.setContent(pdfStream.toByteArray());

            System.out.println(report);
            return reportRepository.save(report);
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Компилирует шаблон JasperReports.
     *
     * @param templatePath Путь к файлу шаблона JRXML.
     * @return Скомпилированный JasperReport.
     * @throws JRException если шаблон не найден или произошла ошибка компиляции.
     */
    private JasperReport compileTemplate(String templatePath) throws JRException {
        try (InputStream templateStream = getClass().getResourceAsStream(templatePath)) {
            if (templateStream == null) {
                throw new IllegalArgumentException("Шаблон не найден: " + templatePath);
            }
            return JasperCompileManager.compileReport(templateStream);
        } catch (IOException | JRException e) {
            throw new RuntimeException("Ошибка при компиляции шаблона", e);
        }
    }

//    @Cacheable(value = "reports", key = "#reportId")
    public Optional<Report> getReportById(Long reportId) {
        count.incrementAndGet();
        return reportRepository.findById(reportId);
    }

    private List<AppointmentDTO> mapToAppointmentDTO(List<Appointment> appointments) {
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentDTOS.add(new AppointmentDTO(
                    appointment.getCustomerName(),
                    appointment.getServiceNames(),
                    appointment.getAppointmentDate(),
                    appointment.getTotalServicePrice(),
                    "Master Шифу"));
        }
        return appointmentDTOS;
    }




//    @Scheduled(fixedDelay = 15000)
//    public void setDBdate() {
//        appointmentService.saveAppointment(new Appointment());
//    }
}
