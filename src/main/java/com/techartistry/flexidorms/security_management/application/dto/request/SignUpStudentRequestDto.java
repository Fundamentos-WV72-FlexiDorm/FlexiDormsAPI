package com.techartistry.flexidorms.security_management.application.dto.request;

import com.techartistry.flexidorms.rental_management.domain.enums.EGender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpStudentRequestDto {
    @NotBlank(message = "Firstname is required")
    @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
    private String lastname;

    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Size(min = 9, max = 9, message = "Phone number must be 9 characters")
    @Pattern(regexp = "\\d{9}", message = "Phone number must be 9 digits")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 100, message = "Address must be between 3 and 100 characters")
    private String address;

    @NotNull(message = "Birthdate is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Profile picture URL is required")
    private String profilePicture;

    private EGender gender;

    private String university;
}
