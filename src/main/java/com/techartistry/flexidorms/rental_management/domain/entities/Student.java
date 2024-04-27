package com.techartistry.flexidorms.rental_management.domain.entities;

import com.techartistry.flexidorms.security_management.domain.entities.User;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends User {
    @Column(length = 50)
    private String university;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isVerified;
}
