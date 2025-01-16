package com.example.clientservice.sender;

import com.example.clientservice.dto.AppointmentDTO;
import com.example.clientservice.entity.Appointment;
import com.example.clientservice.repository.AppointmentRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AppointmentSender {

    private final KafkaTemplate<String, AppointmentDTO> kafkaTemplate;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentSender(KafkaTemplate<String, AppointmentDTO> kafkaTemplate, AppointmentRepository appointmentRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    @Scheduled(fixedDelay = 10000)
    public void sendToKafkaMessage() {
        for (long i = 0; i < 4; i++) {
            Appointment appointmentList = appointmentRepository.getById(i);

            AppointmentDTO appointmentDTO = new AppointmentDTO(
                    appointmentList.getCustomer().getFirstName() + appointmentList.getCustomer().getLastName(),
                    appointmentList.getDescription(),
                    appointmentList.getAppointmentDate(),
                    new BigDecimal("10000"),
                    "Имя работника Юэль Ромеро"
            );
            ProducerRecord<String, AppointmentDTO> record = new ProducerRecord<>(
                    "report-topic",
                    0,
                    null,
                    appointmentDTO
            );


            kafkaTemplate.send(record);
            System.out.println("Сообщение отправлено " + record);
        }

    }
}
