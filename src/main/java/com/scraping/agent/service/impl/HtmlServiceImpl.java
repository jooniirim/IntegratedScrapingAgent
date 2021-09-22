package com.scraping.agent.service.impl;

import com.scraping.agent.repository.impl.HtmlRepositoryImpl;
import com.scraping.agent.service.HtmlService;
import com.scraping.agent.vo.HtmlAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * HTML Scraping 관련 서비스 구현체
 */
@Slf4j
@Service
public class HtmlServiceImpl implements HtmlService {
    @Autowired
    private HtmlRepositoryImpl htmlRepository;

    /**
     * HTML Scraping 위한 계정 정보 조회
     * @param accountId
     * @return 요청 Account id 에 맞는 비밀번호 포함 정보 리턴, 계정 사용 불가 상태시 에러 처리
     * @throws Exception
     */
    @Override
    public Map<String, Object> getAccountInfo(String accountId) throws Exception{
        Map<String, Object> account = new HashMap<>();
        HtmlAccountVO accountInfo = htmlRepository.getHtmlAccountInfo(accountId);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        if("N".equals(accountInfo.getUseYN()) || formatedNow.compareTo(accountInfo.getExpireDate()) < 0){
            throw new AuthenticationException("사용 불가한 계정, 계정 정보 확인 필요");
        }else {
            account.put("accountId", accountInfo.getAccountId());
            account.put("accountPassword", accountInfo.getAccountPassword());
            return account;

        }

    }


}
