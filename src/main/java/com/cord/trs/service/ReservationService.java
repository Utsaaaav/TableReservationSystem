package com.cord.trs.service;

import com.cord.trs.dto.ReservationDTO;
import com.cord.trs.dto.ReservationResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReservationService {

    ReservationResponseDTO addReservation(ReservationDTO reservationDTO);

    List<ReservationResponseDTO> getAllReservations();

    void deleteReservation(long reservationId);

    boolean getByID(long reservationId);

    void updateReservation(ReservationResponseDTO reservationDTo);

}
