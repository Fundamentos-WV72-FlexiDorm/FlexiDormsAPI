package com.techartistry.flexidorms.rental_management.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rental")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String observation;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String hourInit;

    @Column(nullable = false)
    private String hourFinal;

    @Column(nullable = true)
    private String imageUrl=null;

    @Column(nullable = false)
    private String student;

    @Column(nullable = false)
    private String arrenderId;

    @Column(nullable = false)
    private Long room;

    @Column(nullable = false)
    private boolean favorite = false;

    @Column(nullable = true, columnDefinition = "VARCHAR(5) DEFAULT 'false'")
    private String moviment;

}

