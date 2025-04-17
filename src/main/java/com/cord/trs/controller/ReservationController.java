package com.cord.trs.controller;

import com.cord.trs.dto.GlobalApiResponse;
import com.cord.trs.dto.ReservationDTO;
import com.cord.trs.dto.ReservationResponseDTO;
import com.cord.trs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController extends BaseClass {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> reserve(@RequestBody ReservationDTO reservationdto) {
        ReservationResponseDTO reservationResponseDTO = reservationService.addReservation(reservationdto);
            return new ResponseEntity<>(success("Reservation created successfully", reservationResponseDTO), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<GlobalApiResponse> listAllReservations() {

        List<ReservationResponseDTO> reservationResponseDTOS = reservationService.getAllReservations();
        if (reservationResponseDTOS != null) {
            return new ResponseEntity<>(success("Reservations Listed Successfully", reservationResponseDTOS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failure("Unable to list all reservations", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/delete/{reservationId}")
    public ResponseEntity<GlobalApiResponse> deleteReservation(@PathVariable long reservationId){

        reservationService.deleteReservation(reservationId);
        boolean flag = reservationService.getByID(reservationId);
        if(flag == true){
            return new ResponseEntity<>(success("Reservation deleted successfully", reservationId), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(failure("Unable to delete reservation",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateReservation(@RequestBody ReservationResponseDTO responseDTO){

        reservationService.updateReservation(responseDTO);
        if(responseDTO != null){
            return new ResponseEntity<>(success("Reservation Updated Successfully", responseDTO), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(failure("Unable to update reservation", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
