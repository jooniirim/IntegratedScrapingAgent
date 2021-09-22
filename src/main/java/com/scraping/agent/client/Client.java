package com.scraping.agent.client;

public interface Client<T> {
    void callExternal(T t) throws Exception;
}
