package com.example.newpartssertvice.configuration;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean// Определяет bean для конфигурации circuit breaker
    public CircuitBreakerConfig circuitBreakerConfig() {
        // Настраивает параметры circuit breaker (автоматическое отключение при ошибках)
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // Порог срабатывания (50% ошибок вызывает отключение)
                .waitDurationInOpenState(Duration.ofSeconds(60)) // Период ожидания перед повторной попыткой
                .slidingWindowSize(10) // Размер окна для оценки ошибок (здесь последние 10 вызовов)
                .build();
    }

    @Bean // Определяет bean для конфигурации retry
    public RetryConfig retryConfig() {
        // Настраивает параметры retry (повторных попыток)
        return RetryConfig.custom()
                .maxAttempts(3) // Максимальное количество повторных попыток
                .waitDuration(Duration.ofMillis(500)) // Задержка между попытками (500 миллисекунд)
                .build();
    }
    @Bean // Определяет bean для конфигурации time limiter
    public TimeLimiterConfig timeLimiterConfig() {
        // Настраивает параметры time limiter (ограничение времени выполнения)
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2)) // Время ожидания ответа (таймаут)
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
