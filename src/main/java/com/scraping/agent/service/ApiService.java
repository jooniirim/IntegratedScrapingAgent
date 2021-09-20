package com.scraping.agent.service;

import com.scraping.agent.repository.impl.ApiRespositoryImpl;
import com.scraping.agent.vo.ApiCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApiService {

    @Autowired
    private ApiRespositoryImpl apiRespository;

    public String getApiKey(String apiCode){
        log.info("API 호출하기 위해 API Key 조회");
        String apiKey = "";
        ApiCodeVO apiInfo = apiRespository.getApiCodeInfo(apiCode);
        apiKey = apiInfo.getApiKey();

        return apiKey;
    }
}