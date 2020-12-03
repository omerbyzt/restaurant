package com.ba.service;

import com.ba.converter.UsersConverter;
import com.ba.dto.UsersDTO;
import com.ba.entity.Users;
import com.ba.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;


    public void loadAdmin(Users users) {
        usersRepository.save(users);
    }

    public List<UsersDTO> listUsers() {
        return UsersConverter.convertUsersListToDTO(usersRepository.findAll());
    }

    public void addUsers(UsersDTO usersDTO) {
        usersRepository.save(UsersConverter.convertUsersDTOToUsers(usersDTO));
    }

    public List<UsersDTO> deleteUsers(String username) {
        usersRepository.deleteById(username);
        return listUsers();
    }

    public List<UsersDTO> updateUsers(UsersDTO usersDTO) {
        usersRepository.saveAndFlush(UsersConverter.convertUsersDTOToUsers(usersDTO));
        return listUsers();
    }
}
