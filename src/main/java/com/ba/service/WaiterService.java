package com.ba.service;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.WaiterMapper;
import com.ba.repository.MediaRepository;
import com.ba.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private WaiterMapper waiterMapper;

    public List<WaiterDTO> listAllWaiters(){
        List<Waiter> waiterList = waiterRepository.findAll();
        if(waiterList == null){
            throw new SystemException("Waiters not found...!");
        }
        return  waiterMapper.toDTOList(waiterRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String addWaiter(WaiterDTO waiterDTO){
        mediaRepository.deleteById(waiterDTO.getMediaDTO().getId());

        Waiter tempWaiter = waiterMapper.toEntity(waiterDTO);

        Media tempMedia = tempWaiter.getMedia();
        tempMedia.setId(null);
        tempWaiter.setMedia(tempMedia);

        waiterRepository.save(tempWaiter);
        return "Waiter Added";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteWaiter(Long id){
        waiterRepository.deleteById(id);
        return "Waiter Deleted";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public WaiterDTO updateWaiter(WaiterDTO waiterDTO){
        Optional<Waiter> waiter = waiterRepository.findById(waiterDTO.getId());
        if(waiter == null){
            throw new SystemException("Waiter not found...!");
        }

        UpdateHelper.waiterSetCheck(waiterDTO, waiter);

        waiterRepository.saveAndFlush(waiter.get());
        return waiterMapper.toDTO(waiter.get());
    }
}
