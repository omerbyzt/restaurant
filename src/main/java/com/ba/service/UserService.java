package com.ba.service;

import com.ba.dto.RoleDTO;
import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.exception.SystemException;
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

        if(!user.get().getUsername().equals(userDTO.getUsername())){
            user.get().setUsername(userDTO.getUsername());
        }
        if(!user.get().getEmail().equals(userDTO.getEmail())){
            user.get().setEmail(userDTO.getEmail());
        }
        if(!user.get().getPassword().equals(userDTO.getPassword())){
            user.get().setPassword(userDTO.getPassword());
        }
        if (user.get().getRoles().isEmpty() && !userDTO.getRoles().isEmpty()) {
            user.get().setRoles(RoleMapper.INSTANCE.toList(userDTO.getRoles()));
        }


//        List<Role> tempRoleList = user.get().getRoles();
//        List<Long> dbRoleIdList = new ArrayList<>();
//        tempRoleList.forEach(role->{dbRoleIdList.add(role.getId());});
//
//        List<RoleDTO> tempDTORoleList = userDTO.getRoles();
//        List<Long> inputRoleIdList = new ArrayList<>();
//        tempDTORoleList.forEach(role->{inputRoleIdList.add(role.getId());});
//
//        List<Long> newRoleList = new ArrayList<>();
//        if(!inputRoleIdList.equals(dbRoleIdList)){
//            if(inputRoleIdList.size()>dbRoleIdList.size()){
//                inputRoleIdList.removeAll(dbRoleIdList);
//                inputRoleIdList.forEach(inputRoleId->{
////                    user.get().getRoles().add(roleRepository.findById(inputRoleId).get());//
//                    userDTO.getRoles().forEach(role->{
//                        if(role.getId().equals(inputRoleId)){
//                            user.get().getRoles().add(RoleMapper.INSTANCE.toEntity(userDTO.getRoles().get(Math.toIntExact(inputRoleId))));
//                        }
//                    });
//                });
//            }else{
//                dbRoleIdList.removeAll(inputRoleIdList);
//                newRoleList = dbRoleIdList;
//            }
//        }


//        User user2 = UserMapper.INSTANCE.toEntity(userDTO);
//        user2.setRoles(roleList);
//        userRepository.saveAndFlush(user2);
//        userRepository.saveAndFlush(UserConverter.convertDTOToEntity(userDTO,roleList));
        return "User Updated";
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User Deleted";
    }
}
