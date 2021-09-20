package com.scraping.agent.vo;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class HtmlAccountVO {

    private String accountId;
    private String accountPassword;
    private String useYN;
    private String expireDate;
    private String registDate;

}
