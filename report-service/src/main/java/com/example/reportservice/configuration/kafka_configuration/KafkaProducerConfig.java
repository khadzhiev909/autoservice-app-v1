//package com.example.reportservice.configuration.kafka_configuration;
//
//import com.example.reportservice.entity.Customer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//
////    @Bean
////    ProducerFactory<String, Customer> kafkaProducer() {
////        Map<String, Object> properties = new HashMap<>();
////
////        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////
////
////        // Настройки, которые мы будем описывать
////        properties.put(ProducerConfig.ACKS_CONFIG, "all");
////        properties.put(ProducerConfig.LINGER_MS_CONFIG, 10);
////        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
////        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
////
////        return new DefaultKafkaProducerFactory<String, Customer>(properties);
////    }
////
////    //KafkaTemplate используется для отправки сообщений в Kafka
////    @Bean
////    public KafkaTemplate<String, Customer> kafkaTemplate() {
////        return new KafkaTemplate<>(kafkaProducer());
////    }
//
//}
