package com.nickz.spring;

import com.nickz.spring.database.entity.Company;
import com.nickz.spring.database.repository.CrudRepository;
import com.nickz.spring.dto.CompanyReadDTO;
import com.nickz.spring.entity.AccessType;
import com.nickz.spring.listener.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;



    public Optional<CompanyReadDTO> findById(Integer id){
        return companyRepository.findByID(id)
                .map(entity -> {
                    //TODO
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                        return new CompanyReadDTO(entity.getId());
                });
    }

}
