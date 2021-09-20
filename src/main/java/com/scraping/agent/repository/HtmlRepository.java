package com.scraping.agent.repository;

import com.scraping.agent.vo.HtmlAccountVO;

public interface HtmlRepository {
    HtmlAccountVO getHtmlAccountInfo(String accountId);
}
