package com.scraping.agent.messagesystem.kafka.consumer;

import com.scraping.agent.job.JobLauncherService;
import com.scraping.agent.messagesystem.Consumer;
import com.scraping.agent.messagesystem.Producer;
import com.scraping.agent.util.ConvertUtil;
import com.scraping.agent.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer implements Consumer<ConsumerRecord<String, String>> {

    @Autowired
    private JobLauncherService jobLauncherService;

    @Autowired
    @Qualifier("kafkaProducer")
    private Producer producer;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    ConvertUtil convertUtil;

    @Override
    @KafkaListener(topics = "${kafka.topic.name}", groupId="${kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        try {
            String topicMessage = consumerRecord.value();
            JSONObject message = jsonUtil.jsonParser(topicMessage);

            String agentType = jsonUtil.getType(message);

            log.info("Consumer ::::: 1. 작업 시작 : Topic Consume / Type 구분(HTML or API)");
            jobLauncherService.executeJob(agentType, message);
        } catch (ParseException parseException) {
            log.info("Consumer ::::: Parse 에러 Alert queue");
            producer.errorLogSend(consumerRecord);
        } catch (Exception e) {
            log.info("Consumer ::::: 에러 Alert queue");
            producer.errorLogSend(consumerRecord);
        }
    }


}
