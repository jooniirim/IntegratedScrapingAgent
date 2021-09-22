package com.scraping.agent.config.messagestystem.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka Topic 설정
 */
@Configuration
public class KafkaTopicConfiguration {

    /**
     * Agent 가 Job을 실행하는 trigger가 되는 queue를 consume 시 해당 topic에 produce
     * @return Request 에 따라 Agent Job 실행 Topic
     */
    @Bean
    public NewTopic agentJobRequestLoggingTopic() { return TopicBuilder.name("logging.agent.job.request").build(); }

    /**
     * Agent 에러 발생 시 해당 topic에 consume
     * @return Agent 에러 정보 Topic
     */
    @Bean
    public NewTopic agentJobErrorTopic() {
        return TopicBuilder.name("alert.agent.job.error").build();
    }

    /**
     * Agent가 들어온 request에 대해 Job 수행 완료 후 해당 topic 에 produce
     * @return Agent가 들어온 request에 대해 Job 수행 완료 정보 Topic
     */
    @Bean
    public NewTopic agentJobCompleteLoggingTopic(){
        return TopicBuilder.name("logging.agent.job.complete").build();
    }

    /**
     * 요청 계정 정보 오류 시 해당 topic에 produce
     * @return 요청 계정 정보 오류 Topic
     */
    @Bean
    public NewTopic authenticationErrorTopic(){ return TopicBuilder.name("alert.agent.account.error").build(); }
}
