package com.scraping.agent.service;

import com.scraping.agent.repository.impl.ApiRespositoryImpl;
import com.scraping.agent.vo.ApiCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class ApiService {

    @Autowired
    private ApiRespositoryImpl apiRespository;

    @Autowired
    private RestTemplate retryableRestTemplate;

    public String getApiKey(String apiCode) throws Exception {
        String apiKey = "";
        ApiCodeVO apiInfo = apiRespository.getApiCodeInfo(apiCode);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        if ("N".equals(apiInfo.getUseYN()) || formatedNow.compareTo(apiInfo.getExpireDate()) < 0) {
            throw new AuthenticationException("사용 불가한 계정, 계정 정보 확인 필요");
        } else {
            apiKey = apiInfo.getApiKey();
            return apiKey;
        }
    }

    public ResponseEntity<?> apiGetCall(String apiUrl, HttpEntity<?> request, Object objectClass){
        return retryableRestTemplate.exchange(apiUrl, HttpMethod.GET, request, objectClass.getClass());
    }
}
