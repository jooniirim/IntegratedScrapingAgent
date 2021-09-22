package com.scraping.agent.service;

import java.util.Map;

/**
 * HTML Scraping 관련 인터페이스
 */
public interface HtmlService {
    Map<String, Object> getAccountInfo(String accountId) throws Exception;
}
