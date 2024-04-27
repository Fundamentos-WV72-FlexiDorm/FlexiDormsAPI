package com.techartistry.flexidorms.rental_management.application.controllers;

import com.techartistry.flexidorms.rental_management.application.dto.request.RegisterRoomRequestDto;
import com.techartistry.flexidorms.rental_management.application.dto.response.RegisterRoomResponseDto;
import com.techartistry.flexidorms.rental_management.application.services.IRoomService;
import com.techartistry.flexidorms.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Room")
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(summary = "Register a room")
    @PostMapping("/registerRoom")
    public ResponseEntity<ApiResponse<RegisterRoomResponseDto>> registerRoom(@Valid @RequestBody RegisterRoomRequestDto request){
        var res = roomService.registerRoom(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Get rooms by arrenderId")
    @GetMapping("/getRoomsByArrenderId/{arrenderId}")
    public ResponseEntity<ApiResponse<List<RegisterRoomResponseDto>>> getRoomsByArrenderId(@PathVariable Long arrenderId){
        var res = roomService.getRoomsByRenderId(arrenderId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get rooms by status")
    @GetMapping("/getRoomsByStatusId/{status}")
    public ResponseEntity<ApiResponse<List<RegisterRoomResponseDto>>> getRoomsByStatus(@PathVariable String status){
        var res = roomService.getByState(status);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Get rooms by roomId")
    @GetMapping("/getRoomsByRoomId/{roomId}")
    public ResponseEntity<ApiResponse<List<RegisterRoomResponseDto>>> getRoomsById(@PathVariable Long roomId){
        var res = roomService.getById(roomId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}