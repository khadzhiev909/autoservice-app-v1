package com.example.clientservice.configs;

import com.example.clientservice.dto.AppointmentDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka // Включает поддержку Kafka аннотаций, например, для @KafkaListener
@Configuration
public class KafkaConfigs {
    @Bean
    ProducerFactory<String, AppointmentDTO> kafkaProducer() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        // Настройки, которые мы будем описывать
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        // Настройка для лучшего управления потоком
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip"); // Использование сжатия для уменьшения трафика

        return new DefaultKafkaProducerFactory<String, AppointmentDTO>(properties);
    }

    //KafkaTemplate используется для отправки сообщений в Kafka
    @Bean
    public KafkaTemplate<String, AppointmentDTO> kafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducer());
    }

}

//    @Bean// Определяет bean для фабрики продюсера Kafka
//    public ProducerFactory<String, Customer> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        // Указываем сервер Kafka
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//
//        // Ключ и значение сериализуются в строки
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Customer> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//команда для создания топика
//docker exec -it kafka bash -c "kafka-topics --create --topic report-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1"

//команда для проверки инфы про кафку
//docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092