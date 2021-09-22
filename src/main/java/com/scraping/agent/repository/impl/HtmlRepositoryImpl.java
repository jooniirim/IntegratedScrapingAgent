package com.scraping.agent.repository.impl;

import com.scraping.agent.repository.HtmlRepository;
import com.scraping.agent.vo.HtmlAccountVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * HTML Scraping 위한 계정 정보 및 DB 접근 관련 클래스
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class HtmlRepositoryImpl implements HtmlRepository {
    private final EntityManager em;

    /**
     * Account ID 에 따른 Account 정보 조회
     * @param accountId
     * @return HtmlAccountVO 형식에 맞는 정보 리턴
     */
    @Override
    public HtmlAccountVO getHtmlAccountInfo(String accountId) {
        log.info("HtmlRepositoryImpl - getHtmlAccountInfo ::::: accountId 값으로 인증 정보 관련 조회");
        return em.find(HtmlAccountVO.class, accountId);
    }
}
