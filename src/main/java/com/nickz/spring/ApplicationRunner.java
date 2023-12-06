package com.nickz.spring;

import com.nickz.spring.config.ApplicationConfiguration;
import com.nickz.spring.database.pool.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.SpringProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationConfiguration.class, args);
        var beanDefinitionCount = context.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
        System.out.println(SpringProperties.getProperty("test.message"));
        var bean = context.getBean(ConnectionPool.class);
        System.out.println(bean.getPoolSize());
    }


}
