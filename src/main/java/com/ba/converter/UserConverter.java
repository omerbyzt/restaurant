package com.ba.converter;

import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static List<User> convertDTOListToList(List<UserDTO> userDTOList){

        List<User> userList = new ArrayList<>();

        userDTOList.forEach(userDTOListItem -> {
            User user = new User();

            user.setId(userDTOListItem.getId());
            user.setUsername(userDTOListItem.getUsername());
            user.setPassword(encoder.encode(userDTOListItem.getPassword()));
            user.setEmail(userDTOListItem.getEmail());
            user.setEnabled(userDTOListItem.getEnabled());
            user.setRoles(RoleConverter.convertDTOListToList(userDTOListItem.getRoles()));

            userList.add(user);
        });
        return userList;
    }

    public static List<UserDTO> convertListToDTOList(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();

//        for(User userListItem:userList){
//            UserDTO userDTO = new UserDTO();
//
//            userDTO.setId(userListItem.getId());
//            userDTO.setUsername(userListItem.getUsername());
//            userDTO.setPassword(userListItem.getPassword());
//            userDTO.setEmail(userListItem.getEmail());
//            userDTO.setEnabled(userListItem.isEnabled());
//            userDTO.setRoles(RoleConverter.convertListToDTOList(userListItem.getRoles()));
//
//            userDTOList.add(userDTO);
//        }

        userList.forEach(userListItem -> {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(userListItem.getId());
            userDTO.setUsername(userListItem.getUsername());
            userDTO.setPassword(encoder.encode(userListItem.getPassword()));
            userDTO.setEmail(userListItem.getEmail());
            userDTO.setEnabled(userListItem.isEnabled());
            userDTO.setRoles(RoleConverter.convertListToDTOList(userListItem.getRoles()));

            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    public static User convertDTOToEntity(UserDTO userDTO,List<Role> roleList) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setEnabled(userDTO.getEnabled());
        user.getRoles().addAll(roleList);
        return user;
    }

    public static UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(encoder.encode(user.getPassword()));
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setRoles(RoleConverter.convertListToDTOList(user.getRoles()));

        return userDTO;
    }

}
