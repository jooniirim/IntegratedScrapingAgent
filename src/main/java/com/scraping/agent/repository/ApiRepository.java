package com.scraping.agent.repository;

import com.scraping.agent.vo.ApiCodeVO;

/**
 * API 관련 DB 접근 인터페이스
 */
public interface ApiRepository {
    ApiCodeVO getApiCodeInfo(String apiCode);
}
