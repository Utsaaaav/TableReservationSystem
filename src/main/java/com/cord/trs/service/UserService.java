package com.cord.trs.service;

import com.cord.trs.dto.UserBaseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    UserBaseDTO createUser(UserBaseDTO userdto);

    List<UserBaseDTO> getAllUser();

    void deleteUser(long id);

    void updateUser(UserBaseDTO userdto);

    boolean getById(long id);

}
