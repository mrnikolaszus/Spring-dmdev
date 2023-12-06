package com.nickz.spring.config;

import com.nickz.spring.database.repository.CompanyRepository;
import com.nickz.web.WebConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
@ComponentScan(basePackages = "com.nickz.spring",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = Component.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        value = CompanyRepository.class),
                @ComponentScan.Filter(type = FilterType.REGEX,
                        pattern = "com\\..+Repository")
        })
public class ApplicationConfiguration {

//        @Bean
//        @Scope(BeanDefinition.SCOPE_SINGLETON)
//        public ConnectionPool pool2(@Value("${db.username") String username){
//                return new ConnectionPool(username, 20);
//        }

//        @Bean
//        @Scope(BeanDefinition.SCOPE_SINGLETON)
//        public ConnectionPool pool2(){
//                return new ConnectionPool("test-pool", 25);
//        }
//


}
