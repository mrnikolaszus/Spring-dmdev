package com.nickz.spring.dto;

import java.time.LocalDate;

public record PersonalInfoDTO(String firstName,
                              String lastName,
                              LocalDate birthDate) {
}
