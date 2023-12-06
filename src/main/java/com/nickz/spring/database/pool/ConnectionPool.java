package com.nickz.spring.database.pool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("pool1")
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("${db.username}")
    private  String username;
    @Value("${db.pool.size}")
    private  Integer poolSize;

    public Integer getPoolSize(){
        return poolSize;
    }
    @PostConstruct
    private void init(){
        log.info("Init connection pool");
    }

    @PreDestroy
    public void destroy(){
        log.info("Clean connection pool");
    }
}
