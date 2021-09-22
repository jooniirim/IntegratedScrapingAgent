package com.scraping.agent.messagesystem;

public interface Consumer<T> {
    void consume(T t);
}
