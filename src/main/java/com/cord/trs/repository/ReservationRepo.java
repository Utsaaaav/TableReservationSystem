package com.cord.trs.repository;

import com.cord.trs.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByTable_Id(Long tableId);

    boolean existsByReservationDateAndReservationTimeAndTable_TableNumber(LocalDate reservationDate, LocalTime reservationTime, String tableNumber);
}
