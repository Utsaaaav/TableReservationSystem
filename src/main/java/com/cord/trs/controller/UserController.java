package com.cord.trs.controller;

import com.cord.trs.dto.GlobalApiResponse;
import com.cord.trs.dto.UserBaseDTO;
import com.cord.trs.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController extends BaseClass {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createUser(@RequestBody UserBaseDTO userBaseDTO) {

        UserBaseDTO user = userService.createUser(userBaseDTO);

        if (user != null) {
            return new ResponseEntity<>(success("User Created Successfully", user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failure("User Creation Failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<GlobalApiResponse> listUser() {

        List<UserBaseDTO> userBaseDTOS = userService.getAllUser();

        if (userBaseDTOS != null) {
            return new ResponseEntity<>(success("User List Fetched Successfully", userBaseDTOS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failure("Unable to fetch user list", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        boolean flag = userService.getById(id);

        if (flag == true) {
            return new ResponseEntity<>(success("User Deleted Successfully", id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failure("Unable to delete user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateUser(@RequestBody UserBaseDTO userBaseDTO) {

        userService.updateUser(userBaseDTO);

        if (userBaseDTO != null) {
            return new ResponseEntity<>(success("User Updated Successfully", userBaseDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failure("Unable to update user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
