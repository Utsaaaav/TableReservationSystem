package com.cord.trs.entity;

import com.cord.trs.enums.TableStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@jakarta.persistence.Table(name = "table_tbl")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Tables {

    @Id
    @SequenceGenerator(name = "tbl_table_id_sequence", initialValue = 1, allocationSize = 1, sequenceName = "tbl_table_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_table_id_sequence")
    @Column(name = "table_id")
    private long id;

    @Column(name = "table_number", length = 150)
    private String tableNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "table_status", length = 50)
    private TableStatus tableStatus;

}
