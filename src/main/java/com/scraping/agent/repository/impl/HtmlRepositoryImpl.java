package com.scraping.agent.repository.impl;

import com.scraping.agent.repository.HtmlRepository;
import com.scraping.agent.vo.HtmlAccountVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class HtmlRepositoryImpl implements HtmlRepository {
    private final EntityManager em;

    @Override
    public HtmlAccountVO getHtmlAccountInfo(String agentId) {
        log.info("HtmlRepositoryImpl - getHtmlAccountInfo ::::: agentId 값으로 인증 정보 관련 조회");
        return em.find(HtmlAccountVO.class, agentId);
    }
}
