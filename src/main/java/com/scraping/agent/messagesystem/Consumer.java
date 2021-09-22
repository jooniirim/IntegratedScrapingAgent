package com.scraping.agent.messagesystem;

/**
 * Message System 별 consume 설정 위한 인터페이스
 * @param <T>
 */
public interface Consumer<T> {
    void consume(T t);
}
