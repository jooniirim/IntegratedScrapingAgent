package com.scraping.agent.repository.impl;

import com.scraping.agent.repository.ApiRepository;
import com.scraping.agent.vo.ApiCodeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ApiRespositoryImpl implements ApiRepository {

    private final EntityManager em;

    @Override
    public ApiCodeVO getApiCodeInfo(String apiCode) {
        return em.find(ApiCodeVO.class, apiCode);
    }
}
