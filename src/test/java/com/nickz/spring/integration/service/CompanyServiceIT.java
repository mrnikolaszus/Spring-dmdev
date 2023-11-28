package com.nickz.spring.service.integration.service;

import com.nickz.spring.config.DatabaseProperties;
import com.nickz.spring.dto.CompanyReadDTO;
import com.nickz.spring.service.CompanyService;
import com.nickz.spring.service.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
@RequiredArgsConstructor
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class,
//initializers = ConfigDataApplicationContextInitializer.class)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID =1;
    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;
    @Test
    void findById(){

        var actualResult = companyService.findById(COMPANY_ID);
        Assertions.assertTrue(actualResult.isPresent());
        var expectedResult = new CompanyReadDTO(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

    }
}
