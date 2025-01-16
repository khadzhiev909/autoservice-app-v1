//package com.example.reportservice.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//public class RedisConfiguration {
//    /**
//     * Создает бин JedisConnectionFactory.
//     * Этот бин отвечает за создание подключений к Redis.
//     *
//     * @return JedisConnectionFactory, настроенный с указанием хоста и порта.
//     */
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        return new JedisConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
//    }
//
//    /**
//     * Создает бин RedisTemplate.
//     * Этот бин используется для взаимодействия с Redis и предоставляет несколько методов
//     * для выполнения распространенных операций с Redis.
//     *
//     * @return RedisTemplate, настроенный с сериализаторами и фабрикой подключений.
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory());
//        template.setExposeConnection(true);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new JdkSerializationRedisSerializer());
//        template.setHashValueSerializer(new JdkSerializationRedisSerializer());
//        template.setEnableTransactionSupport(true);
//        template.afterPropertiesSet();
//        return template;
//    }
//}
