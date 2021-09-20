package com.scraping.agent.client;

import com.scraping.agent.dto.ApiAgentDto;
import com.scraping.agent.dto.HtmlAgentDto;
import com.scraping.agent.service.ApiService;
import com.scraping.agent.service.HtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/*
    HTML Scraping 호출 client
 */
public class HtmlClient {
    @Autowired
    private RestTemplate retryableRestTemplate;
    @Autowired
    HtmlService htmlService;

    public void rpcCall(HtmlAgentDto htmlAgentDto) throws Exception{
        // TODO HTML Scraping module rpc call 해야함
        // TODO  파일 저장과 ETL 호출하는 로직 호출
    }
}
