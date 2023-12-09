package com.nickz.spring.database.repository;

import com.nickz.spring.database.entity.Role;
import com.nickz.spring.database.entity.User;
import com.nickz.spring.dto.PersonalInfoDTO;
import com.nickz.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfoDTO> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);
    void updateCompanyAndRoleNamed(List<User> users);
}
