package com.ba.unused;

import com.ba.unused.User;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    public List<User> addUser(User user){
        userRepository.save(user);
        return listAllUsers();
    }

    public List<User> deleteUser(Long id){
        userRepository.deleteById(id);
        return listAllUsers();
    }

    public User updateUser(User user){
        userRepository.saveAndFlush(user);
        return user;
    }
}
