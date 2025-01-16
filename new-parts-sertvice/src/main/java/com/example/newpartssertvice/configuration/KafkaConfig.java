package com.example.newpartssertvice.configuration;

import com.example.newpartssertvice.entity.Part;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka // Включает поддержку Kafka аннотаций, например, для @KafkaListener
@Configuration
public class KafkaConfig {

    @Bean// Определяет bean для фабрики продюсера Kafka
    public ProducerFactory<String, Part> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        // Указываем сервер Kafka
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Ключ и значение сериализуются в строки
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Part> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, Part> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        // Указываем сервер Kafka
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // Десериализация ключа и значения в строки
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Групп ID для консистентности и гарантии того, что сообщение будет обработано только одним из consumers в группе
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "part-search-group");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }


    // Listener factory для создания контейнера слушателя сообщений
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Part> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Part> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // Обработчик ошибок
        factory.setCommonErrorHandler(new DefaultErrorHandler());
        return factory;
    }


    @Bean
    public NewTopic partsSearchTopic() {
        return new NewTopic("parts-search-topic", 1, (short) 1); // 1 партиция, 1 реплика
    }
}
