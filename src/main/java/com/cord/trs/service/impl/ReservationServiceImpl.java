package com.cord.trs.service.impl;

import com.cord.trs.dto.ReservationDTO;
import com.cord.trs.dto.ReservationResponseDTO;
import com.cord.trs.entity.Reservation;
import com.cord.trs.entity.Tables;
import com.cord.trs.enums.TableStatus;
import com.cord.trs.exception.AppException;
import com.cord.trs.repository.ReservationRepo;
import com.cord.trs.repository.TableRepo;
import com.cord.trs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepo reservationRepo;
    private final TableRepo tablesRepo;

    @Override
    public ReservationResponseDTO addReservation(ReservationDTO reservationDTO) {

        //check if table is already booked
        boolean exists = reservationRepo.existsByReservationDateAndReservationTimeAndTable_TableNumber(reservationDTO.getDate(),
                reservationDTO.getTime(),
                reservationDTO.getTableNumber());
        if (exists) {
            throw new AppException("Table is already booked at this time.");
        }

        Tables table = (Tables) tablesRepo.findByTableNumber(reservationDTO.getTableNumber()).orElseThrow(()
                -> new AppException("Table not found with number : " + reservationDTO.getTableNumber()));

        Reservation reservation = Reservation.builder()
                .name(reservationDTO.getName())
                .phoneNumber(reservationDTO.getPhoneNumber())
                .table(table)
                .reservationDate(reservationDTO.getDate())
                .reservationTime(reservationDTO.getTime())
                .build();
        reservationRepo.save(reservation);

        table.setTableStatus(TableStatus.RESERVED);
        tablesRepo.save(table);

        ReservationResponseDTO reservationResponseDTO = ReservationResponseDTO.builder()
                .name(reservationDTO.getName())
                .phoneNumber(reservationDTO.getPhoneNumber())
                .tableNumber(reservation.getTable().getTableNumber())
                .date(reservationDTO.getDate())
                .time(reservationDTO.getTime())
                        .build();

        return reservationResponseDTO;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservations() {

        List<Reservation> reservations = reservationRepo.findAll();
        List<ReservationResponseDTO> reservationResponseDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {

            ReservationResponseDTO reservationResponseDTO = ReservationResponseDTO.builder().reservationId(reservation.getReservationId()).name(reservation.getName()).phoneNumber(reservation.getPhoneNumber()).tableNumber(reservation.getTable().getTableNumber())
                    .date(reservation.getReservationDate()).time(reservation.getReservationTime()).build();
            reservationResponseDTOS.add(reservationResponseDTO);

        }
        return reservationResponseDTOS;
    }

    @Override
    public void deleteReservation(long reservationId) {

        Reservation optionalReservation = reservationRepo.findByTable_Id(reservationId).orElseThrow(() -> new AppException("Reservation not found."));
        Tables table = optionalReservation.getTable();
        table.setTableStatus(TableStatus.AVAILABLE);
        reservationRepo.delete(optionalReservation);
        tablesRepo.save(table);

    }

    @Override
    public boolean getByID(long reservationId) {
        return true;
    }

//    @Override
//    public void updateReservation(ReservationResponseDTO reservationDTo) {
//
//        Optional<Reservation> optionalReservation = reservationRepo.findById(reservationDTo.getReservationId());
//        Reservation existingReservation = optionalReservation.get();
//        existingReservation.setReservationId(reservationDTo.getReservationId());
//        existingReservation.setName(reservationDTo.getName());
//        existingReservation.setPhoneNumber(reservationDTo.getPhoneNumber());
//        existingReservation.setTable(existingReservation.getTable());
//        existingReservation.setReservationTime(reservationDTo.getReservationTime());
//        existingReservation.setReservationDate(reservationDTo.getReservationDate());
//
//        reservationRepo.save(existingReservation);
//
//    }
}
