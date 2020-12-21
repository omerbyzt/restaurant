package com.ba.service;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import com.ba.mapper.WaiterMapper;
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
        return  WaiterMapper.INSTANCE.toDTOList(waiterRepository.findAll());
//        return WaiterConverter.convertListToDTOList(waiterRepository.findAll());
    }

    public String addWaiter(WaiterDTO waiterDTO){
        mediaRepository.deleteById(waiterDTO.getMediaDTO().getId());

        Waiter tempWaiter = WaiterMapper.INSTANCE.toEntity(waiterDTO);

        Media tempMedia = tempWaiter.getMedia();
        tempMedia.setId(null);
        tempWaiter.setMedia(tempMedia);

        waiterRepository.save(tempWaiter);
//        waiterRepository.save(WaiterConverter.addWaiterConverter(waiterDTO));
        return "Waiter Added";
    }

    public String deleteWaiter(Long id){
        waiterRepository.deleteById(id);
        return "Waiter Deleted";
    }

    public WaiterDTO updateWaiter(WaiterDTO waiterDTO){
        waiterRepository.saveAndFlush(WaiterMapper.INSTANCE.toEntity(waiterDTO));
//        waiterRepository.saveAndFlush(WaiterConverter.convertDTOTOWaiter(waiterDTO));
        return waiterDTO;
    }
}
