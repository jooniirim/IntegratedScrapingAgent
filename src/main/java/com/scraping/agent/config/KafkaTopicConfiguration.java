package com.scraping.agent.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    /*
        Agent 가 Job을 실행하는 trigger가 되는 queue를 consume 시 해당 topic에 produce
     */
    @Bean
    public NewTopic agentJobRequestLoggingTopic() {
        return TopicBuilder.name("logging.agent.job.request").build();
    }

    /*
        Agent가 API call retry 설정 횟수 초과 시, doJob 시
        해당 topic에 consume
     */
    @Bean
    public NewTopic agentJobErrorTopic() {
        return TopicBuilder.name("alert.agent.job.error").build();
    }

    /*
        Agent가 들어온 request에 대해 Job 수행 완료 후 해당 topic 에 produce
     */
    @Bean
    public NewTopic agentJobCompleteLoggingTopic(){
        return TopicBuilder.name("logging.agent.job.complete").build();
    }
}
