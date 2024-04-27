package com.techartistry.flexidorms.security_management.application.dto.response;

import com.techartistry.flexidorms.rental_management.domain.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignInResponseDto {
    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String address;
    private LocalDate birthDate;
    private String profilePicture;
    private EGender gender;
    private String university;
    private boolean isVerified;
    private boolean isEnabled;
    private String dtype;
    private String token;
}
