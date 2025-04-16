package com.cord.trs.controller;

import com.cord.trs.dto.GlobalApiResponse;
import com.cord.trs.dto.TableRequestDTO;
import com.cord.trs.dto.TablesResponseDTO;
import com.cord.trs.service.table.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tables")
public class TableController extends BaseClass {

    private final TableService tableService;

    @PostMapping("/addTable")
    public ResponseEntity<GlobalApiResponse> addTables(@RequestBody TableRequestDTO tablesDTO){

        TablesResponseDTO tabledto = tableService.addTable(tablesDTO);

        if(tabledto != null){
            return new ResponseEntity<>(success("Table added successfully", tabledto), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(failure("Unable to add table", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listTable")
    public ResponseEntity<GlobalApiResponse> listTables(){

        List<TablesResponseDTO> tabledto = tableService.getAllTables();

        if(tabledto != null){
            return new ResponseEntity<>(success("Table Listed Successfully", tabledto),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(failure("Unable to list tables", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{tableId}")
    public ResponseEntity<GlobalApiResponse> deleteTable(@PathVariable int tableId){

        tableService.deleteTable(tableId);
        boolean flag = tableService.getById(tableId);
        if(flag == true){
            return new ResponseEntity<>(success("Table Deleted Successfully", tableId), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(failure("Unable to delete table", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateTable")
    public ResponseEntity<GlobalApiResponse> updateTable(@RequestBody TablesResponseDTO tabledto){

        tableService.updateTable(tabledto);
        if(tabledto != null){
            return new ResponseEntity<>(success("Table Updated Successfully", tabledto), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(failure("Unable to update table", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
