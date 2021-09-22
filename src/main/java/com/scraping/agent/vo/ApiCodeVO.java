package com.scraping.agent.vo;

import lombok.Getter;

/**
 *  API Code 정보 Get 위한 VO
 */
@Getter
public class ApiCodeVO {
    private String apiCode;
    private String apiKey;
    private String useYN;
    private String expireDate;
    private String registDate;
}
