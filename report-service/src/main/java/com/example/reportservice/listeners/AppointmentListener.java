//package com.example.reportservice.listeners;
//
//import com.example.clientservice.dto.AppointmentDTO;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AppointmentListener {
//    private final KafkaTemplate<String, AppointmentDTO> customerKafkaTemplate;
//
//    public AppointmentListener(KafkaTemplate<String, AppointmentDTO> customerKafkaTemplate) {
//        this.customerKafkaTemplate = customerKafkaTemplate;
//    }
//
//    @KafkaListener(groupId = "appointment-group", topicPartitions = @TopicPartition(topic = "report-topic",  partitions = {"0"}))
//    public void listeners(AppointmentDTO appointmentDTO) {
//        System.out.println(appointmentDTO.toString());
//    }
//}
