package com.nickz.spring.integration.database.repository;

import com.nickz.spring.database.entity.Company;
import com.nickz.spring.database.repository.CompanyRepository;
import com.nickz.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
//@Transactional
//@Commit
class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 5;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;


    @Test
    void CheckFindByQueries(){
        companyRepository.findByName("Google");
        companyRepository.findAllByNameContainingIgnoreCase("a");
    }
    @Test
    void delete(){
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }
    @Test
    void findById(){
        transactionTemplate.executeWithoutResult(tx ->{

            var company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            Assertions.assertThat(company.getLocales()).hasSize(2);

        });

    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", " ru apple descr",
                        "en", " en apple descr"
                        ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }


}