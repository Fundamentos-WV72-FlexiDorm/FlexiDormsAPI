package com.techartistry.flexidorms.rental_management.infrastructure.repositories;

import com.techartistry.flexidorms.rental_management.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRentalRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStudentAndMovement(String student, String movement);

    List<Reservation> findByArrenderIdAndMovement(String arrenderId, String movement);

    List<Reservation> findByStudentAndFavoriteIsTrue(String student);
}
