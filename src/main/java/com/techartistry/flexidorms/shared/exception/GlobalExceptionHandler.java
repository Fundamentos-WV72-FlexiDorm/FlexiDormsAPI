package com.techartistry.flexidorms.shared.exception;

import com.techartistry.flexidorms.shared.exception.dto.ErrorMessageResponse;
import com.techartistry.flexidorms.shared.model.dto.response.ApiResponse;
import com.techartistry.flexidorms.shared.model.enums.EStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorMessageResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        var errorMessage = new ErrorMessageResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );

        return new ApiResponse<>("Resource not found", EStatus.ERROR, errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ApiResponse<?> handleValidationErrors(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return new ApiResponse<>("Validation error", EStatus.ERROR, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ErrorMessageResponse> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {
        var errorMessage = new ErrorMessageResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );

        return new ApiResponse<>("An unexpected error has ocurred", EStatus.ERROR, errorMessage);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<ErrorMessageResponse>> handleAppException(
            ApplicationException exception,
            WebRequest webRequest
    ) {
        var errorDetails = new ErrorMessageResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );

        var response = new ApiResponse<>("An error has ocurred", EStatus.ERROR, errorDetails);
        return new ResponseEntity<>(response, exception.getStatus());
    }
}
