package com.techartistry.flexidorms.security_management.application.controllers;

import com.techartistry.flexidorms.security_management.application.dto.response.ArrenderResponseDto;
import com.techartistry.flexidorms.security_management.application.dto.response.StudentSignUpResponseDto;
import com.techartistry.flexidorms.security_management.application.dto.response.UserSignInResponseDto;
import com.techartistry.flexidorms.security_management.application.services.IUserService;
import com.techartistry.flexidorms.security_management.application.dto.request.*;
import com.techartistry.flexidorms.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @SecurityRequirements
    @Operation(summary = "Sign up a student")
    @PostMapping("signUp/student")
    public ResponseEntity<ApiResponse<StudentSignUpResponseDto>> signUpStudent(@Valid @RequestBody SignUpStudentRequestDto request) {
        var res = userService.signUpStudent(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @SecurityRequirements
    @Operation(summary = "Sign up an arrender")
    @PostMapping("signUp/arrender")
    public ResponseEntity<ApiResponse<ArrenderResponseDto>> signUpArrender(@Valid @RequestBody SignUpArrenderRequestDto request) {
        var res = userService.signUpArrender(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @SecurityRequirements
    @Operation(summary = "Sign in a user")
    @PostMapping("/signIn")
    public ResponseEntity<?> signInUser(@Valid @RequestBody SignInUserRequestDto request) {
        var res = userService.signIn(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a student")
    @PutMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<StudentSignUpResponseDto>> updateStudent(@Valid @RequestBody UpdateStudentRequestDto request, @PathVariable Long studentId){
        var res = userService.updateStudent(request, studentId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update an arrender")
    @PutMapping("/arrender/{arrenderId}")
    public ResponseEntity<ApiResponse<ArrenderResponseDto>> updateArrender(@Valid @RequestBody UpdateArrenderRequestDto request, @PathVariable Long arrenderId){
        var res = userService.updateArrender(request, arrenderId);
        return new ResponseEntity<>(res,  HttpStatus.OK);
    }

    @Operation(summary = "Logically delete a user")
    @PutMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteUserById(@PathVariable Long userId){
        var res = userService.logicDeleteUserById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //solo los administradores pueden acceder a este endpoint
    @Operation(summary = "Reactive an account")
    @PutMapping("/reactivate/{userId}")
    public ResponseEntity<ApiResponse<UserSignInResponseDto>> reactivateUserAccount(@PathVariable Long userId){
        var res = userService.reactivateAccount(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
