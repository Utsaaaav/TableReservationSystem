package com.cord.trs.dto;

import com.cord.trs.entity.Tables;
import com.cord.trs.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder

public class ReservationDTO {

    private String name;
    private String phoneNumber;
    private String tableNumber;
    private LocalDate reservationDate;
    private LocalTime reservationTime;

}
