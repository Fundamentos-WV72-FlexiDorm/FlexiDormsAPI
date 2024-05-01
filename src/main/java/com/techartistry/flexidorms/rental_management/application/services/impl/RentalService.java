package com.techartistry.flexidorms.rental_management.application.services.impl;

import com.techartistry.flexidorms.rental_management.application.dto.request.RegisterRentalRequestDto;
import com.techartistry.flexidorms.rental_management.application.dto.response.RegisterRentalResponseDto;
import com.techartistry.flexidorms.rental_management.application.services.IRentalService;
import com.techartistry.flexidorms.rental_management.domain.entities.Reservation;
import com.techartistry.flexidorms.rental_management.infrastructure.repositories.IRentalRepository;
import com.techartistry.flexidorms.rental_management.infrastructure.repositories.IRoomRepository;
import com.techartistry.flexidorms.shared.exception.ApplicationException;
import com.techartistry.flexidorms.shared.model.dto.response.ApiResponse;
import com.techartistry.flexidorms.shared.model.enums.EStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService implements IRentalService {
    private final IRentalRepository rentalRepository;

    private final IRoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RentalService(IRentalRepository rentalRepository, ModelMapper modelMapper, IRoomRepository roomRepository) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public ApiResponse<RegisterRentalResponseDto> registerRental(RegisterRentalRequestDto request) {
        if(!roomRepository.existsById(request.getRoom())){
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "The roomId given does not exist");
        }

        //Validar que la habitacion no este rentada
        var room = roomRepository.findByRoomId(request.getRoom())
                .orElseThrow(()-> new ApplicationException(HttpStatus.BAD_REQUEST, "The roomId given does not exist"));

        if(room.getStatus().equals("rented")){
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "The room is already rented");
        }

        var reservation = modelMapper.map(request, Reservation.class);
        var reservationCreated = rentalRepository.save(reservation);

        roomRepository.updateStatus(request.getRoom(), "rented");
        
        var reservationResponseDto = modelMapper.map(reservationCreated, RegisterRentalResponseDto.class);

        return new ApiResponse<>("Rental was successfully registered", EStatus.SUCCESS,reservationResponseDto );

    }

    @Override
    public ApiResponse<List<RegisterRentalResponseDto>> getRentalsByStudentId(String student) {

        //Obtener las reservas del estudiante
        List<Reservation> rentals = rentalRepository.findByStudentAndMovement(student,"false");

        //Convertir las reservas a un objeto de tipo RegisterRentalResponseDto (dto)
        List<RegisterRentalResponseDto> rentalsResponseDto = rentals.stream()
                .map(rental -> modelMapper.map(rental, RegisterRentalResponseDto.class))
                .toList();
        return new ApiResponse<>("Rentals were successfully obtained", EStatus.SUCCESS, rentalsResponseDto);

    }

    @Override
    public ApiResponse<List<RegisterRentalResponseDto>> getMovementByStudentId(String student) {

        //Obtener las reservas del estudiante
        List<Reservation> rentals = rentalRepository.findByStudentAndMovement(student,"true");

        //Convertir las reservas a un objeto de tipo RegisterRentalResponseDto (dto)
        List<RegisterRentalResponseDto> rentalsResponseDto = rentals.stream()
                .map(rental -> modelMapper.map(rental, RegisterRentalResponseDto.class))
                .toList();
        return new ApiResponse<>("Rentals were successfully obtained", EStatus.SUCCESS, rentalsResponseDto);

    }

    @Override
    public ApiResponse<List<RegisterRentalResponseDto>> getRentalsByArrenderId(String arrenderId) {

        //Obtener las reservas del estudiante
        List<Reservation> rentals = rentalRepository.findByArrenderIdAndMovement(arrenderId,"false");

        //Convertir las reservas a un objeto de tipo RegisterRentalResponseDto (dto)
        List<RegisterRentalResponseDto> rentalsResponseDto = rentals.stream()
                .map(rental -> modelMapper.map(rental, RegisterRentalResponseDto.class))
                .toList();
        return new ApiResponse<>("Rentals were successfully obtained", EStatus.SUCCESS, rentalsResponseDto);

    }

    @Override
    public ApiResponse<List<RegisterRentalResponseDto>> getMovementByArrenderId(String arrenderId) {

        //Obtener las reservas del estudiante
        List<Reservation> rentals = rentalRepository.findByArrenderIdAndMovement(arrenderId,"true");

        //Convertir las reservas a un objeto de tipo RegisterRentalResponseDto (dto)
        List<RegisterRentalResponseDto> rentalsResponseDto = rentals.stream()
                .map(rental -> modelMapper.map(rental, RegisterRentalResponseDto.class))
                .toList();
        return new ApiResponse<>("Rentals were successfully obtained", EStatus.SUCCESS, rentalsResponseDto);

    }


    @Override
    public ApiResponse<RegisterRentalResponseDto> toggleFavorite(Long reservationId) {
        Optional<Reservation> optionalReservation = rentalRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setFavorite(!reservation.isFavorite());
            Reservation updatedReservation = rentalRepository.save(reservation);

            // Mapear la entidad actualizada a tu DTO
            var reservationResponseDto = modelMapper.map(updatedReservation, RegisterRentalResponseDto.class);

            return new ApiResponse<>("Rental was successfully update", EStatus.SUCCESS,reservationResponseDto );
        } else {
            throw new ApplicationException(HttpStatus.BAD_REQUEST,"Reservation not found with ID: " + reservationId);
        }
    }

    @Override
    public ApiResponse<RegisterRentalResponseDto> toggleEndRental(Long reservationId) {
        Optional<Reservation> optionalReservation = rentalRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            // Establecer el valor deseado, por ejemplo, establecerlo en true
            reservation.setMovement("true");
            Reservation updatedReservation = rentalRepository.save(reservation);
            // Mapear la entidad actualizada a tu DTO
            var reservationResponseDto = modelMapper.map(updatedReservation, RegisterRentalResponseDto.class);

            return new ApiResponse<>("Rental was successfully update", EStatus.SUCCESS,reservationResponseDto );
        } else {
            throw new ApplicationException(HttpStatus.BAD_REQUEST,"Reservation not found with ID: " + reservationId);
        }
    }

    @Override
    public ApiResponse<List<RegisterRentalResponseDto>> findByStudentAndFavorite(String student) {
        List<Reservation> reservations = rentalRepository.findByStudentAndFavoriteIsTrue(student);

        // Mapear las entidades a DTOs
        List<RegisterRentalResponseDto> rentalsResponseDto = reservations.stream()
                .map(reservation -> modelMapper.map(reservation, RegisterRentalResponseDto.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("Rentals favorite true were successfully obtained", EStatus.SUCCESS, rentalsResponseDto);
    }
}
