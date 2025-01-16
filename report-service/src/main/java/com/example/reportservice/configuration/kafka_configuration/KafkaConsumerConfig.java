//package com.example.reportservice.configuration.kafka_configuration;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.DefaultErrorHandler;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import com.example.clientservice.dto.AppointmentDTO;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConsumerConfig {
//    @Bean
//    public ConsumerFactory<String, AppointmentDTO> consumerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        // Указываем сервер Kafka
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        // Десериализация ключа и значения в строки
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        // Групп ID для консистентности и гарантии того, что сообщение будет обработано только одним из consumers в группе
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "appointment-group");
//
//
//        // Используем ErrorHandlingDeserializer для обработки ошибок десериализации
//        JsonDeserializer<AppointmentDTO> valueDeserializer = new JsonDeserializer<>(AppointmentDTO.class);
//        valueDeserializer.addTrustedPackages("*");
//        valueDeserializer.setRemoveTypeHeaders(false);
//        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), valueDeserializer);
//    }
//
//
//    // Listener factory для создания контейнера слушателя сообщений
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, AppointmentDTO> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, AppointmentDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//
//        // Обработчик ошибок
//        factory.setCommonErrorHandler(new DefaultErrorHandler());
//        return factory;
//    }
//
//}
