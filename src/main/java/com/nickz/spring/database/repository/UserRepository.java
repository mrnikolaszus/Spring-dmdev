package com.nickz.spring.database.repository;

import com.nickz.spring.database.entity.Role;
import com.nickz.spring.database.entity.User;
import com.nickz.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
        "where u.firstName like %:firstname% and u.lastName like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "select u.* from users u where username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

}
