package com.scraping.agent.vo;

import lombok.Getter;

/**
 * HTML Scraping Account 정보 Get 위한 VO
 */
@Getter
public class HtmlAccountVO {
    private String accountId;
    private String accountPassword;
    private String useYN;
    private String expireDate;
    private String registDate;
}
