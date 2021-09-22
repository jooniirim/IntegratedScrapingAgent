package com.scraping.agent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * REST API Scraping과 HTML Scraping 요청 처리 가능한 Agent Main 클래스
 */
@Slf4j
@SpringBootApplication
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
		log.info(":::::::::::::::::: Scraping Agent Start ::::::::::::::::::::");
	}

}
