package com.scraping.agent.repository;

import com.scraping.agent.vo.ApiCodeVO;

public interface ApiRepository {
    ApiCodeVO getApiCodeInfo(String apiCode);
}
