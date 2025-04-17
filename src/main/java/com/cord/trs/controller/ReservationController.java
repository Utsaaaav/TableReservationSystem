package com.cord.trs.controller;

import com.cord.trs.dto.GlobalApiResponse;
import com.cord.trs.dto.ReservationDTO;
import com.cord.trs.dto.ReservationResponseDTO;
import com.cord.trs.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController extends BaseClass {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> reserve(@RequestBody ReservationDTO reservationdto) {
        ReservationResponseDTO reservationResponseDTO = reservationService.addReservation(reservationdto);
        if (reservationResponseDTO != null) {
            return new ResponseEntity<>(success("Reservation created successfully", reservationResponseDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failure("Failed to create reservation", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
