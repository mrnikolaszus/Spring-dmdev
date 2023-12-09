package com.nickz.spring.database.repository;

import com.nickz.spring.database.entity.Role;
import com.nickz.spring.database.entity.User;
import com.nickz.spring.dto.PersonalInfoDTO;
import com.nickz.spring.dto.PersonalInfoDTO2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer>,
        QuerydslPredicateExecutor<User> {

//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    @Query(value = "SELECT firstname," +
            "lastname," +
            "birth_date birthDate " +
            "FROM users " +
            "WHERE company_id = :companyId",
    nativeQuery = true)
    List<PersonalInfoDTO2> findAllByCompanyId(Integer companyId);

    @Query("select u from User u " +
        "where u.firstName like %:firstname% and u.lastName like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "select u.* from users u where username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);


    Optional<User> findTopByOrderByIdDesc();

    @QueryHints(@QueryHint(name ="org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    //Collection, Stream
    // Streamable обертка вокруг итератера
    // Slice наследник от Streamable -> Page(имеет count)
    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
            countQuery = "select count(distinct u.firstName) from User u")
    Page<User> findAllBy(Pageable pageable);


}
