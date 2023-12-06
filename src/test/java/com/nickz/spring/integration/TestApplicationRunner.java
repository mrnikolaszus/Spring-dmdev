package com.nickz.spring.integration;

import com.nickz.spring.UserService;
import com.nickz.spring.database.pool.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;

@TestConfiguration
public class TestApplicationRunner {



    @SpyBean(name = "pool1")
    private ConnectionPool pool1;
}
