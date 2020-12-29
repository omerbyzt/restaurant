package com.ba.controller;

import com.ba.dto.WaiterDTO;
import com.ba.exception.BusinessRuleException;
import com.ba.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/waiter")
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @GetMapping("/list-waiters")
    public List<WaiterDTO> listAllWaiters(){
        return waiterService.listAllWaiters();
    }

    @PostMapping("/add-waiter")
    public String addWaiter(@Valid @RequestBody WaiterDTO waiterDTO){
        return waiterService.addWaiter(waiterDTO);
    }

    @DeleteMapping("/delete-waiter/{id}")
    public String deleteWaiter(@PathVariable Long id){
        if(id == null){
            throw new BusinessRuleException("Id cannot be empty...!");
        }
        return  waiterService.deleteWaiter(id);
    }

    @PutMapping("/update-waiter")
    public WaiterDTO updateWaiter(@Valid @RequestBody WaiterDTO waiterDTO){
        if(waiterDTO.getId() == null){
            throw new BusinessRuleException("Waiter cannot be empty...!");
        }
        return waiterService.updateWaiter(waiterDTO);
    }
}
