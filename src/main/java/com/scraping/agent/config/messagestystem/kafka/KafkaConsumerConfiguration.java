package com.scraping.agent.config.messagestystem.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Kafka Consumer 설정
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

    @Value("${kafka.retry-count}")
    private int retryCount;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.offset.reset}")
    private String offsetResetMode;

    @Value("${kafka.ackmode}")
    private ContainerProperties.AckMode ackMode;

    /**
     * ListenerContainerFactory 설정
     * @return ConcurrentKafkaListenerContainerFactory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Object> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ackMode);
        factory.setConcurrency(3);
        // IllegalArgumentException, TimeoutException에 대해 3회 재시도
        factory.setRetryTemplate(retryTemplate());
        return factory;
    }

    /**
     * consumer 프로퍼티
     * @param bootstrapServers
     * @param autoCommit
     * @return properties
     */
    public static Map<String, Object> getProperties(String bootstrapServers, String autoCommit){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return properties;
    }

    /**
     * Consumer 프로퍼티
     * @return DefaultKafkaConsumerFactory
     */
    @Bean
    public ConsumerFactory<String, Object> consumerFactory(){
        return new DefaultKafkaConsumerFactory(getProperties(bootstrapServers, offsetResetMode)
        , new StringDeserializer(), new StringDeserializer());
    }

    /**
     * Retry 설정
     * @return RetryTemplate
     */
    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
        return retryTemplate;
    }

    /**
     * consume 오류 시 재시도 Policy 설정
     * @return SimpleRetryPolicy
     */
    private SimpleRetryPolicy getSimpleRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();

        exceptionMap.put(IllegalArgumentException.class, false);
        exceptionMap.put(TimeoutException.class, true);

        return new SimpleRetryPolicy(retryCount,exceptionMap,true);
    }
}
