package com.nickz.spring.dto;

import java.time.LocalDate;

public record UserFilter(String firstName,
                         String lastName,
                         LocalDate birthDate) {
}
