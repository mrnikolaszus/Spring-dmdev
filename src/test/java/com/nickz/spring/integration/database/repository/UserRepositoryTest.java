package com.nickz.spring.integration.database.repository;

import com.nickz.spring.database.entity.Role;
import com.nickz.spring.database.entity.User;
import com.nickz.spring.database.repository.UserRepository;
import com.nickz.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

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