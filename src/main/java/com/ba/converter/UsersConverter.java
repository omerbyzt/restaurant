//package com.ba.converter;
//
//import com.ba.dto.UsersDTO;
//import com.ba.entity.Users;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UsersConverter {
//
//    public static List<UsersDTO> convertUsersListToDTO(List<Users> usersList){
//
//        List<UsersDTO> usersListDTO = new ArrayList<>();
//
//        for (Users usersListItem : usersList){
//            UsersDTO usersDTO = new UsersDTO();
//
//            usersDTO.setEnabled(usersListItem.getEnabled());
//            usersDTO.setPassword(usersListItem.getPassword());
//            usersDTO.setUsername(usersListItem.getUsername());
//
//            usersListDTO.add(usersDTO);
//        }
//
//        return usersListDTO;
//    }
//
//    public static Users convertUsersDTOToUsers(UsersDTO usersDTO){
//        Users users = new Users();
//
//        users.setUsername(usersDTO.getUsername());
//        users.setPassword(usersDTO.getPassword());
//        users.setEnabled(usersDTO.getEnabled());
//
//        return users;
//    }
//
//
//}
