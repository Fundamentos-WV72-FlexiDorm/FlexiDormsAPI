package com.techartistry.flexidorms.rental_management.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRentalResponseDto {

    private Long reservationId;
    private Date date;
    private String phone;
    private String email;
    private String observation;
    private double totalPrice;
    private String hourInit;
    private String hourFinal;
    private String imageUrl;
    private String student;
    private Long room;
    private boolean favorite;
    private String arrenderId;
    private String movement;
}
