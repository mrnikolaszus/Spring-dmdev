package com.nickz.spring.service.integration.service;

import com.nickz.spring.database.pool.ConnectionPool;
import com.nickz.spring.service.UserService;
import com.nickz.spring.service.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
@RequiredArgsConstructor
public class UserServiceIT {


    private UserService userService;

    @SpyBean(name = "pool1")
    private ConnectionPool connectionPool;
    @Test
    void test(){

    }
}
