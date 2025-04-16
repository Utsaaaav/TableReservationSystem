package com.cord.trs.repository;

import com.cord.trs.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    boolean existsByReservationDateAndReservationTimeAndTable_TableNumber(LocalDate reservationDate, LocalTime reservationTime, String tableNumber);
}
