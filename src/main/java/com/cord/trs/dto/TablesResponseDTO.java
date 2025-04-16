package com.cord.trs.dto;

import com.cord.trs.enums.TableStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TablesResponseDTO {

    private long id;
    private String tableNumber;
    private TableStatus tableStatus;

}
