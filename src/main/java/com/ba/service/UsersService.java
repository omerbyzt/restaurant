package com.ba.service;

import com.ba.model.Users;
import com.ba.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public void loadAdmin(Users users){
        usersRepository.save(users);
    }

    public List<Users> listUsers(){
        return usersRepository.findAll();
    }

    public void addUsers(Users users){
        usersRepository.save(users);
    }

    public List<Users> deleteUsers(String username){
        usersRepository.deleteById(username);
        return listUsers();
    }

    public List<Users> updateUsers (Users users){
        usersRepository.saveAndFlush(users);
        return listUsers();
    }
}
