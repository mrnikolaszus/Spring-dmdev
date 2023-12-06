package com.nickz.spring.integration.service;

import com.nickz.spring.database.pool.ConnectionPool;
import com.nickz.spring.integration.annotation.IT;
import com.nickz.spring.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT {


    private final UserService userService;
    private final ConnectionPool connectionPool;
    @Test
    void test(){
        System.out.println("1");

    }
    @Test
    void test2(){
        System.out.println("2");

    }
}
