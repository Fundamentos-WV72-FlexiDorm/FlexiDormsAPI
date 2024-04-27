package com.techartistry.flexidorms.rental_management.infrastructure.repositories;

import com.techartistry.flexidorms.rental_management.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRentalRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStudentAndMoviment(String student, String moviment);

    List<Reservation> findByArrenderIdAndMoviment(String arrenderId, String moviment);


    List<Reservation> findByStudentAndFavoriteIsTrue(String student);

}
