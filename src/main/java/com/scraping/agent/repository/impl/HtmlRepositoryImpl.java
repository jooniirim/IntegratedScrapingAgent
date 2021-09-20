package com.scraping.agent.repository.impl;

import com.scraping.agent.repository.HtmlRepository;
import com.scraping.agent.vo.HtmlAccountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class HtmlRepositoryImpl implements HtmlRepository {
    private final EntityManager em;

    @Override
    public HtmlAccountVO getHtmlAccountInfo(String agentId) {
        return em.find(HtmlAccountVO.class, agentId);
    }
}
