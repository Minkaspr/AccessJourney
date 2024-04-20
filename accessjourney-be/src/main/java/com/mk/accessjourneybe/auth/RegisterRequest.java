package com.mk.accessjourneybe.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String identityDocumentType;

    private String identityDocumentNumber;

    private LocalDate dateOfBirth;

    private Long roleId;
}
