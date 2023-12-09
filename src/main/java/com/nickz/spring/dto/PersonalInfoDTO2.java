package com.nickz.spring.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PersonalInfoDTO2 {

    String getFirstname();
    String getLastname();

    String getBirthdate();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
