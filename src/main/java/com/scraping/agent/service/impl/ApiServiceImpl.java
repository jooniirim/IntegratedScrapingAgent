package com.scraping.agent.service.impl;

import com.scraping.agent.repository.impl.ApiRespositoryImpl;
import com.scraping.agent.service.ApiService;
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

/**
 * API 관련 서비스 구현체
 */
@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiRespositoryImpl apiRespository;

    @Autowired
    private RestTemplate retryableRestTemplate;

    /**
     * API Key 정보 조회
     * @param apiCode
     * @return API key 정보 리턴, 계정 사용 불가시 에러 처리
     * @throws Exception
     */
    @Override
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

    /**
     * REST API GET 방식 호출
     * @param apiUrl
     * @param request
     * @param objectClass
     * @return 호출 결과 담은 Entity 리턴
     */
    @Override
    public ResponseEntity<?> apiGetCall(String apiUrl, HttpEntity<?> request, Object objectClass) {
        return retryableRestTemplate.exchange(apiUrl, HttpMethod.GET, request, objectClass.getClass());
    }

}
