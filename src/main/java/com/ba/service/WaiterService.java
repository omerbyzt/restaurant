package com.ba.service;

import com.ba.converter.WaiterConverter;
import com.ba.dto.WaiterDTO;
import com.ba.repository.MediaRepository;
import com.ba.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public List<WaiterDTO> listAllWaiters(){
        return WaiterConverter.convertListToDTOList(waiterRepository.findAll());
    }

    public String addWaiter(WaiterDTO waiterDTO){
        mediaRepository.deleteById(waiterDTO.getMediaDTO().getId());
        waiterRepository.save(WaiterConverter.addWaiterConverter(waiterDTO));
        return "Waiter Added";
    }

    public String deleteWaiter(Long id){
        waiterRepository.deleteById(id);
        return "Waiter Deleted";
    }

    public WaiterDTO updateWaiter(WaiterDTO waiterDTO){
        waiterRepository.saveAndFlush(WaiterConverter.convertDTOTOWaiter(waiterDTO));
        return waiterDTO;
    }
}
