//package com.ba.converter;
//
//import com.ba.dto.WaiterDTO;
//import com.ba.entity.Waiter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class WaiterConverter {
//
//    public static List<WaiterDTO> convertListToDTOList(List<Waiter> waiterList){
//        List<WaiterDTO> waiterListDTO = new ArrayList<>();
//
//        for(Waiter waiterListItem : waiterList){
//            WaiterDTO waiterDTO = new WaiterDTO();
//
//            waiterDTO.setId(waiterListItem.getId());
//            waiterDTO.setAddress(waiterListItem.getAddress());
//            waiterDTO.setMail(waiterListItem.getMail());
//            waiterDTO.setName(waiterListItem.getName());
//            waiterDTO.setPhoneNumber(waiterListItem.getPhoneNumber());
//            waiterDTO.setSalary(waiterListItem.getSalary());
//            waiterDTO.setUrlToImage(waiterListItem.getUrlToImage());
//            waiterDTO.setMediaDTO(MediaConverter.convertMediaToMediaDTO(waiterListItem.getMedia()));
//            waiterListDTO.add(waiterDTO);
//        }
//        return waiterListDTO;
//    }
//
//    public static Waiter convertDTOTOWaiter(WaiterDTO waiterDTO){
//        Waiter waiter = new Waiter();
//
//        waiter.setAddress(waiterDTO.getAddress());
//        waiter.setId(waiterDTO.getId());
//        waiter.setMail(waiterDTO.getMail());
//        waiter.setName(waiterDTO.getName());
//        waiter.setPhoneNumber(waiterDTO.getPhoneNumber());
//        waiter.setSalary(waiterDTO.getSalary());
//        waiter.setUrlToImage(waiterDTO.getUrlToImage());
//        waiter.setMedia(MediaConverter.convertMediaDTOToMedia(waiterDTO.getMediaDTO()));
//
//        return waiter;
//    }
//
//    public static Waiter addWaiterConverter(WaiterDTO waiterDTO){
//        Waiter waiter = new Waiter();
//
//        waiter.setAddress(waiterDTO.getAddress());
//        waiter.setId(waiterDTO.getId());
//        waiter.setMail(waiterDTO.getMail());
//        waiter.setName(waiterDTO.getName());
//        waiter.setPhoneNumber(waiterDTO.getPhoneNumber());
//        waiter.setSalary(waiterDTO.getSalary());
//        waiter.setUrlToImage(waiterDTO.getUrlToImage());
//        waiter.setMedia(MediaConverter.convertMediaDTOToMediaOneToOne(waiterDTO.getMediaDTO()));
//
//        return waiter;
//    }
//}
