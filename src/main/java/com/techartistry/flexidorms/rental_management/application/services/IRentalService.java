package com.techartistry.flexidorms.rental_management.application.services;

import com.techartistry.flexidorms.rental_management.application.dto.request.RegisterRentalRequestDto;
import com.techartistry.flexidorms.rental_management.application.dto.response.RegisterRentalResponseDto;
import com.techartistry.flexidorms.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IRentalService {

    /**
     * Realizar una reserva
     * @param request La reserva a realizar
     * @return La reserva realizada
     */
    ApiResponse<RegisterRentalResponseDto> registerRental(RegisterRentalRequestDto request);

    /**
     * Retornar reservas por estudiante
     * @return La reserva realizada
     */
    ApiResponse<List<RegisterRentalResponseDto>> getRentalsByStudentId(String student);
    ApiResponse<List<RegisterRentalResponseDto>> getMovementByStudentId(String student);

    ApiResponse<List<RegisterRentalResponseDto>> getRentalsByArrenderId(String arrenderId);

    ApiResponse<List<RegisterRentalResponseDto>> getMovementByArrenderId(String arrenderId);

    ApiResponse<RegisterRentalResponseDto> toggleFavorite(Long reservationId);

    ApiResponse<RegisterRentalResponseDto> toggleEndRental(Long reservationId);
    ApiResponse<List<RegisterRentalResponseDto>> findByStudentAndFavorite(String student);
}
