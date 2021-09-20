package com.scraping.agent.vo;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class ApiCodeVO {
    private String apiCode;
    private String apiKey;
    private String useYN;
    private String expireDate;
    private String registDate;
}
