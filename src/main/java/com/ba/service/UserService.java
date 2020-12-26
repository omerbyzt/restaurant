package com.ba.service;

import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.exception.SystemException;
import com.ba.mapper.UserMapper;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<UserDTO> listUsers() {
        List<User> user = userRepository.findAll();
        if(user.isEmpty()){
            throw new SystemException("Users cannot be found...!");
        }
        return UserMapper.INSTANCE.toDTOList(user);
    }

    public String addUser(UserDTO userDTO) {
        List<Role> roleList= new ArrayList<>();
        userDTO.getRoles().forEach(role->{roleList.add(roleRepository.findById(role.getId()).get());});

        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setRoles(roleList);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.save(user);

        return "User Added";
    }

    public String updateUser(UserDTO userDTO) {
        List<Role> roleList= new ArrayList<>();
        for (int i=0; i<userDTO.getRoles().size(); i++){
            Role role=roleRepository.findById(userDTO.getRoles().get(i).getId()).get();
            roleList.add(role);
        }
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setRoles(roleList);
        userRepository.saveAndFlush(user);
//        userRepository.saveAndFlush(UserConverter.convertDTOToEntity(userDTO,roleList));
        return "User Updated";
    }

    public String deleteUser(Long id){
        User user = userRepository.findById(id).get();
        List<Role>tempRoleList = new ArrayList<>();
        user.setRoles(tempRoleList);

        userRepository.deleteById(id);
        return "User Deleted";
    }
}
