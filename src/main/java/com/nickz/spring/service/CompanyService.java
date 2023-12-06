package com.nickz.spring.service;

import com.nickz.spring.UserService;
import com.nickz.spring.database.repository.CompanyRepository;
import com.nickz.spring.dto.CompanyReadDTO;
import com.nickz.spring.listener.entity.AccessType;
import com.nickz.spring.listener.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;


    @Transactional
    public Optional<CompanyReadDTO> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                        return new CompanyReadDTO(entity.getId());
                });
    }

}
