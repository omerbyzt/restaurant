package com.ba.service;

import com.ba.dto.UserDTO;
import com.ba.entity.User;
import com.ba.exception.BusinessRuleException;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.RoleMapper;
import com.ba.mapper.UserMapper;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<UserDTO> listUsers() {
        List<User> user = userRepository.findAll();
        if (user == null) {
            throw new SystemException("Users cannot be found...!");
        }
        return userMapper.toDTOList(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String addUser(UserDTO userDTO) {

        User user = userMapper.toEntity(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        //response entity
        return "User Added";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String updateUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getId());
        if (user == null) {
            throw new SystemException("User not found...!");
        }

        UpdateHelper.userSetCheck(userDTO, user.get(), roleMapper);

        userRepository.save(user.get());
        //saveandFlush
        return "User Updated";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteUser(Long id) {
        //try catch
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new BusinessRuleException("Id error");
        }

        return "User Deleted";
    }
}
