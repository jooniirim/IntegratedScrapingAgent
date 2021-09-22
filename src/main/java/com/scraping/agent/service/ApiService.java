package com.scraping.agent.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

/**
 * API 관련 Service 인터페이스
 */
public interface ApiService {
    String getApiKey(String apicode) throws Exception;
    ResponseEntity<?> apiGetCall(String apiUrl, HttpEntity<?> request, Object objectClass);
}
