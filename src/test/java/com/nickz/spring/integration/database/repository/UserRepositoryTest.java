package com.nickz.spring.integration.database.repository;

import com.nickz.spring.config.DatabaseProperties;
import com.nickz.spring.database.entity.Role;
import com.nickz.spring.database.entity.User;
import com.nickz.spring.database.repository.UserRepository;
import com.nickz.spring.dto.PersonalInfoDTO;
import com.nickz.spring.dto.UserFilter;
import com.nickz.spring.integration.IntegrationTestBase;
import com.nickz.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.Audited;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {
    private final UserRepository userRepository;


    @Test
    void checkBatch(){
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
    }
    @Test
    void checkJdbcTemplate(){
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        org.assertj.core.api.Assertions.assertThat(users).hasSize(1);

    }


    @Test
    void checkAuditing(){
        var ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();
//        System.err.println(ivan.getModifiedBy());
    }

    @Test
    void checkCustomImplementation(){
        UserFilter userFilter = new UserFilter(
                null, "ov", LocalDate.now()
        );
        var users = userRepository.findAllByFilter(userFilter);
        org.assertj.core.api.Assertions.assertThat(users).hasSize(4);
    }

    @Test
    void checkProjection(){
        var users = userRepository.findAllByCompanyId(1);
        org.assertj.core.api.Assertions.assertThat(users).hasSize(2);
//        System.err.println(users);
    }
    @Test
    void checkPageable(){
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while(slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }
    @Test
    void checkSort(){
        var sortBy = Sort.sort(User.class);
        var sort = sortBy.by(User::getFirstName).and(sortBy.by(User::getLastName));

        var sortById = Sort.by("firstname").and(Sort.by("lastname"));
        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        org.assertj.core.api.Assertions.assertThat(allUsers).hasSize(3);

    }

    @Test
    void checkFirstTop(){
         var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdate(){
        var ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());
        ivan.setBirthDate(LocalDate.now());

        var count = userRepository.updateRole(Role.USER, 1L, 5L);
        Assertions.assertEquals(2, count);

//        ivan.getCompany().getName(); lazy ex

        var sameIvan = userRepository.getById(1L);
        assertSame(Role.USER, sameIvan.getRole());
    }

    @Test
    void checkQueries(){
        var users = userRepository.findAllBy("a", "ov");
        org.assertj.core.api.Assertions.assertThat(users).hasSize(3);
        users.forEach(user -> System.err.println(user.getFirstName()));
    }

}