package com.cord.trs.service;

import com.cord.trs.dto.TableRequestDTO;
import com.cord.trs.dto.TablesResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TableService {

    TablesResponseDTO addTable(TableRequestDTO tabledto);

    List<TablesResponseDTO> getAllTables();

    void deleteTable(long tableId);

    void updateTable(TablesResponseDTO tabledto);

    boolean getById(long tableId);
}
