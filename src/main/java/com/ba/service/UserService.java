package com.ba.service;

import com.ba.dto.RoleDTO;
import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.RoleMapper;
import com.ba.mapper.UserMapper;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<UserDTO> listUsers() {
        List<User> user = userRepository.findAll();
        if (user.isEmpty()) {
            throw new SystemException("Users cannot be found...!");
        }
        return UserMapper.INSTANCE.toDTOList(user);
    }

    public String addUser(UserDTO userDTO) {

        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User Added";
    }

    public String updateUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getId());
        if (user.isEmpty()) {
            throw new SystemException("User not found...!");
        }

        UpdateHelper.userSetCheck(userDTO, user);

        userRepository.saveAndFlush(user.get());
        return "User Updated";
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User Deleted";
    }
}
