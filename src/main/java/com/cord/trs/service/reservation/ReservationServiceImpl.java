package com.cord.trs.service.reservation;

import com.cord.trs.dto.ReservationDTO;
import com.cord.trs.dto.ReservationResponseDTO;
import com.cord.trs.entity.Reservation;
import com.cord.trs.entity.Tables;
import com.cord.trs.repository.ReservationRepo;
import com.cord.trs.repository.TableRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepo reservationRepo;
    private final TableRepo tablesRepo;

    @Override
    public ReservationResponseDTO addReservation(ReservationDTO reservationDTO) {

        //check if table is already booked
        boolean exists = reservationRepo.existsByReservationDateAndReservationTimeAndTable_TableNumber(
                reservationDTO.getReservationDate(),
                reservationDTO.getReservationTime(),
                reservationDTO.getTableNumber()
        );
        if (exists) {
            throw new RuntimeException("Table is already booked at this time.");
        }

        Tables table = (Tables) tablesRepo.findByTableNumber(reservationDTO.getTableNumber())
                .orElseThrow(() -> new RuntimeException("Table not found with number : " + reservationDTO.getTableNumber()));

        Reservation reservation = Reservation.builder()
                .name(reservationDTO.getName())
                .phoneNumber(reservationDTO.getPhoneNumber())
                .table(table)
                .reservationDate(reservationDTO.getReservationDate())
                .reservationTime(reservationDTO.getReservationTime())
                .build();
        reservationRepo.save(reservation);

        ReservationResponseDTO reservationResponseDTO = ReservationResponseDTO.builder()
                .name(reservationDTO.getName())
                .phoneNumber(reservationDTO.getPhoneNumber())
                .tableNumber(reservation.getTable().getTableNumber())
                .reservationDate(reservationDTO.getReservationDate())
                .reservationTime(reservationDTO.getReservationTime())
                .build();

        return reservationResponseDTO;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservations() {

        return List.of();
    }

    @Override
    public void deleteReservation(long reservationId) {

    }

    @Override
    public boolean getByID(long reservationId) {
        return false;
    }
}
