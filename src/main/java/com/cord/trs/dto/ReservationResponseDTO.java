package com.cord.trs.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder

public class ReservationResponseDTO {

    private long reservationId;
    private String name;
    private String phoneNumber;
    private String tableNumber;
    private LocalDate date;
    private LocalTime time;

}
