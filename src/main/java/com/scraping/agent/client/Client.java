package com.scraping.agent.client;

/**
 * 외부 REST API 혹은 HTML Scraping worker 호출 컴포넌트 위한 인터페이스
 * @param <T>
 */
public interface Client<T> {
    /**
     * 외부 REST API 혹은 HTML Scraping worker 호출 메서드
     * @param t
     * @throws Exception
     */
    void callExternal(T t) throws Exception;
}
