package com.cord.trs.controller;

import com.cord.trs.dto.GlobalApiResponse;
import com.cord.trs.dto.TableRequestDTO;
import com.cord.trs.dto.TablesResponseDTO;
import com.cord.trs.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tables")
public class TableController extends BaseClass {

    private final TableService tableService;

    @PostMapping("/add-table")
    public ResponseEntity<GlobalApiResponse> addTables(@RequestBody TableRequestDTO tablesDTO){

        try{
            TablesResponseDTO tabledto = tableService.addTable(tablesDTO);
            return new ResponseEntity<>(success("Table added successfully", tabledto), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(failure(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/list-table")
    public ResponseEntity<GlobalApiResponse> listTables(){

        try{
            List<TablesResponseDTO> tabledto = tableService.getAllTables();
            return new ResponseEntity<>(success("Table Listed Successfully", tabledto),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(failure(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping ("/delete/{tableId}")
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

    @PutMapping("/update-table")
    public ResponseEntity<GlobalApiResponse> updateTable(@RequestBody TablesResponseDTO tabledto){

        try{
            tableService.updateTable(tabledto);
            return new ResponseEntity<>(success("Table Updated Successfully", tabledto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(failure(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
