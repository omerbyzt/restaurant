package com.ba.service;

import com.ba.converter.MediaConverter;
import com.ba.converter.UserConverter;
import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserDTO> listUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return UserConverter.convertListToDTOList(userList);
    }

    public String addUser(UserDTO userDTO) {
        List<Role> roleList= new ArrayList<>();
        for (int i=0; i<userDTO.getRoles().size(); i++){
            Role role=roleRepository.findById(userDTO.getRoles().get(i).getId()).get();
            roleList.add(role);
        }

        User user = UserConverter.convertDTOToEntity(userDTO,roleList);
        userRepository.save(user);

        return "User Added";
    }

    public String updateUser(UserDTO userDTO) {
        List<Role> roleList= new ArrayList<>();
        for (int i=0; i<userDTO.getRoles().size(); i++){
            Role role=roleRepository.findById(userDTO.getRoles().get(i).getId()).get();
            roleList.add(role);
        }

        userRepository.saveAndFlush(UserConverter.convertDTOToEntity(userDTO,roleList));
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
