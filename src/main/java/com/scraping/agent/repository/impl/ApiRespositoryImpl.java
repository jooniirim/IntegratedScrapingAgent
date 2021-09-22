package com.scraping.agent.repository.impl;

import com.scraping.agent.repository.ApiRepository;
import com.scraping.agent.vo.ApiCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ApiRespositoryImpl implements ApiRepository {

    private final EntityManager em;

    @Override
    public ApiCodeVO getApiCodeInfo(String apiCode) {
        log.info("ApiRespositoryImpl - getApiCodeInfo ::::: apiCode 값으로 인증 정보 관련 조회");
        return em.find(ApiCodeVO.class, apiCode);
    }
}
