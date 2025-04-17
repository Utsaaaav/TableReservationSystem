package com.cord.trs.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReservationResponseDTO {

    private long reservationId;
    private String name;
    private String phoneNumber;
    private String tableNumber;
    private LocalDate reservationDate;
    private LocalTime reservationTime;

}
