package com.cord.trs.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation_tbl")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Reservation {

    @Id
    @SequenceGenerator(name = "tbl_reservation_id_sequence",initialValue = 1, allocationSize = 1, sequenceName = "tbl_reservation_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_reservation_id_sequence")
    @Column(name = "reservation_id")
    private long reservationId;

    @Column(name = "reservation_date", length = 150)
    private LocalDate reservationDate;

    @Column(name = "reservation_time", length = 150)
    private LocalTime reservationTime;

    @Column(name = "user_name", length = 250)
    private String name;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tables.class)
    @JoinColumn(name = "table_id", foreignKey = @ForeignKey(name = "FK_RESERVATION_TABLE"))
    private Tables table;

}
