package com.scraping.agent.client;

import com.scraping.agent.dto.HtmlAgentDto;
import com.scraping.agent.messagesystem.Producer;
import com.scraping.agent.service.HtmlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Map;

/*
    HTML Scraping 호출 client
 */
@Slf4j
@Service
@AllArgsConstructor
@EnableRetry
public class HtmlClient implements Client<HtmlAgentDto>{
    @Autowired
    HtmlService htmlService;

    @Autowired
    @Qualifier("kafkaProducer")
    private Producer producer;

    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void callExternal(HtmlAgentDto htmlAgentDto) {
        try {
            log.info("HtmlClient - scrapingCall ::::: 1. 스크래핑 계정 정보 조회");
            Map<String, Object> getAccountInfo = htmlService.getAccountInfo(htmlAgentDto.getAccountId());

            log.info("HtmlClient - scrapingCall ::::: 2. 외부 스크래핑 라이브러리 호출");
            log.info("HtmlClient - scrapingCall ::::: 3. 원본 파일 저장 및 ETL 호출하는 로직 호출");
        } catch (AuthenticationException authenticationException) {
            log.info("HtmlClient - scrapingCall ::::: 인증 에러 Alert queue");
            producer.accountErrorLogSend(htmlAgentDto);
        } catch (Exception e) {
            log.info("HtmlClient - scrapingCall ::::: 에러 Alert queue");
            producer.errorLogSend(htmlAgentDto);
        }

    }

}
