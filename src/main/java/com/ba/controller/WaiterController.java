package com.ba.controller;

import com.ba.dto.WaiterDTO;
import com.ba.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String addWaiter(@RequestBody WaiterDTO waiterDTO){
        return waiterService.addWaiter(waiterDTO);
    }

    @DeleteMapping("/delete-waiter/{id}")
    public String deleteWaiter(@PathVariable Long id){
        return  waiterService.deleteWaiter(id);
    }

    @PutMapping("/update-waiter")
    public WaiterDTO updateWaiter(@RequestBody WaiterDTO waiterDTO){
        return waiterService.updateWaiter(waiterDTO);
    }
}
