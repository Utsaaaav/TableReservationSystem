package com.cord.trs.service.user;

import com.cord.trs.dto.UserBaseDTO;
import com.cord.trs.entity.User;
import com.cord.trs.enums.Role;
import com.cord.trs.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserBaseDTO createUser(UserBaseDTO userdto) {

        User user = User.builder()
                .name(userdto.getName())
                .email(userdto.getEmail())
                .password(userdto.getPassword())
                .role(Role.ADMIN)
                .phoneNumber(userdto.getPhoneNumber())
                .build();

        userRepo.save(user);
        return userdto;
    }

    @Override
    public List<UserBaseDTO> getAllUser() {

        List<User> user = userRepo.findAll();
        List<UserBaseDTO> userdto = new ArrayList<>();
        for(User u : user){

            UserBaseDTO userbasedto = UserBaseDTO.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .email(u.getEmail())
                    .password(u.getPassword())
                    .phoneNumber(u.getPhoneNumber())
                    .build();
            userdto.add(userbasedto);
        }

        return userdto;
    }

    @Override
    public void deleteUser(long id) {

        userRepo.deleteById(id);

    }

    @Override
    public void updateUser(UserBaseDTO userDto) {

        Optional<User> optionalUser = userRepo.findById(userDto.getId());

        User exUser = optionalUser.get();
        exUser.setName(userDto.getName());
        exUser.setEmail(userDto.getEmail());
        exUser.setPassword(userDto.getPassword());
        exUser.setPhoneNumber(userDto.getPhoneNumber());
        userRepo.save(exUser);

    }

    @Override
    public boolean getById(long id) {
        return true;
    }
}
