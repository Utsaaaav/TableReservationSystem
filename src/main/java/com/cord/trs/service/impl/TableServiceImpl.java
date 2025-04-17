package com.cord.trs.service.impl;

import com.cord.trs.dto.TableRequestDTO;
import com.cord.trs.dto.TablesResponseDTO;
import com.cord.trs.entity.Tables;
import com.cord.trs.enums.TableStatus;
import com.cord.trs.repository.TableRepo;
import com.cord.trs.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TableServiceImpl implements TableService {

    private final TableRepo tableRepo;

    @Override
    public TablesResponseDTO addTable(TableRequestDTO tabledto) {

        Tables tables = Tables.builder()
                .tableNumber(tabledto.getTableNumber())
                .tableStatus(TableStatus.AVAILABLE)
                .build();
        tableRepo.save(tables);

//        Tables tables = tableRepo.save(Tables.builder()
//                .tableNumber(tabledto.getTableNumber())
//                .tableStatus(TableStatus.AVAILABLE)
//                .build());

        TablesResponseDTO responseDTO = TablesResponseDTO.builder()
                .id(tables.getId())
                .tableNumber(tabledto.getTableNumber())
                .tableStatus(tables.getTableStatus())
                .build();

        return responseDTO;
    }

    @Override
    public List<TablesResponseDTO> getAllTables() {

       List<Tables> tables = tableRepo.findAll();
       List<TablesResponseDTO> tablesDTO = new ArrayList<>();

       for(Tables t : tables){
           TablesResponseDTO tdto = TablesResponseDTO.builder()
                   .id(t.getId())
                   .tableNumber(t.getTableNumber())
                   .tableStatus(t.getTableStatus())
                   .build();
           tablesDTO.add(tdto);
       }
       return tablesDTO;
    }

    @Override
    public void deleteTable(long tableId) {
        Tables exTable = tableRepo.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found with ID: " + tableId));
        tableRepo.delete(exTable);

    }

    @Override
    public void updateTable(TablesResponseDTO tablesdto) {

        Tables exTable = tableRepo.findById(tablesdto.getId())
                .orElseThrow(() -> new RuntimeException("Table not found with ID: " + tablesdto.getId()));
        exTable.setTableNumber(tablesdto.getTableNumber());
        exTable.setTableStatus(tablesdto.getTableStatus());

        tableRepo.save(exTable);

    }

    @Override
    public boolean getById(long id) {
        return true;
    }
}
