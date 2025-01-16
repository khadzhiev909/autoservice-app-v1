package com.example.newpartssertvice.kafka;

import com.example.newpartssertvice.entity.Condition;
import com.example.newpartssertvice.entity.Part;
import com.example.newpartssertvice.entity.SupplierPart;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartsProducer {
    private static final String TOPIC = "parts-search-topic";
    private final KafkaTemplate<String, Part> kafkaTemplate;

    public PartsProducer(KafkaTemplate<String, Part> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() {
        List<Part> parts = new ArrayList<>();
        LocalDate date = LocalDate.of(2000, 7, 12);
        parts.add(new Part(1l, "глушитель", "такая категория", "BMW", new BigDecimal("1230"), Condition.USED, 12, "description", "123123", date, List.of(new SupplierPart())));
        for (Part part : parts) {
            kafkaTemplate.send(TOPIC, part);
        }
    }

    @Scheduled(fixedRate = 60000)
    public void sendEveryOneMinute() {
        sendMessage();
    }
}
