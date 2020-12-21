//package com.ba.converter;
//
//import com.ba.dto.RoleDTO;
//import com.ba.entity.Role;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RoleConverter {
//
//    public static List<Role> convertDTOListToList(List<RoleDTO> roleDTOList){
//        List<Role> roleList= new ArrayList<>();
//
////        for (RoleDTO roleDTOListItem : roleDTOList){
////            Role role = new Role();
////
////            role.setId(roleDTOListItem.getId());
////            role.setName(roleDTOListItem.getName());
////
////            roleList.add(role);
////        }
//
//        roleDTOList.forEach(roleDTOListItem -> {
//            Role role = new Role();
//
//            role.setId(roleDTOListItem.getId());
//            role.setName(roleDTOListItem.getName());
//
//            roleList.add(role);
//        });
//
//        return roleList;
//    }
//
//    public static List<RoleDTO> convertListToDTOList(List<Role> roleList){
//        List<RoleDTO> roleDTOList = new ArrayList<>();
//
//        roleList.forEach(roleListItem -> {
//            RoleDTO dtoRole = new RoleDTO();
//
//            dtoRole.setId(roleListItem.getId());
//            dtoRole.setName(roleListItem.getName());
//
//            roleDTOList.add(dtoRole);
//        });
//
//        return roleDTOList;
//    }
//
//    public static Role convertDTOToEntity(RoleDTO roleDTO){
//        Role role = new Role();
//
//        role.setId(roleDTO.getId());
//        role.setName(roleDTO.getName());
//
//        return role;
//    }
//
//    public static RoleDTO convertEntityToDTO(Role role){
//        RoleDTO roleDTO = new RoleDTO();
//
//        roleDTO.setId(role.getId());
//        roleDTO.setName(role.getName());
//
//        return roleDTO;
//    }
//}
