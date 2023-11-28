package com.nickz.spring.service;

import com.nickz.spring.database.entity.Company;
import com.nickz.spring.database.repository.CompanyRepository;
import com.nickz.spring.database.repository.CrudRepository;
import com.nickz.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;


}
