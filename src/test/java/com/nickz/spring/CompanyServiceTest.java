package com.nickz.spring.service;

import com.nickz.spring.database.entity.Company;
import com.nickz.spring.database.repository.CrudRepository;
import com.nickz.spring.dto.CompanyReadDTO;
import com.nickz.spring.listener.EntityEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private static final Integer COMPANY_ID =1;
    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(new Company(COMPANY_ID)))
                .when(companyRepository).findByID(COMPANY_ID);

        var actualResult = companyService.findById(COMPANY_ID);

        Assertions.assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDTO(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

        Mockito.verify(eventPublisher).publishEvent(Mockito.any(EntityEvent.class));
        Mockito.verifyNoMoreInteractions(eventPublisher, userService);


    }
}