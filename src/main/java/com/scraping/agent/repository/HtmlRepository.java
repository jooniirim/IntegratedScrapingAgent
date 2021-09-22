package com.scraping.agent.repository;

import com.scraping.agent.vo.HtmlAccountVO;

/**
 * HTML Scraping 관련 DB 접근 인터페이스
 */
public interface HtmlRepository {
    HtmlAccountVO getHtmlAccountInfo(String accountId);
}
