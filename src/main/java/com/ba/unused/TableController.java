package com.ba.unused;

import com.ba.unused.Tables;
import com.ba.unused.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;

    @GetMapping("/listall")
    public List<Tables> listAllTables(){
        return tableService.listAllTables();
    }

    @PostMapping("/add/{id}")
    public List<Tables> addTable(@RequestBody Tables table,@PathVariable Long id){
        return tableService.addTable(table,id);
    }

    @DeleteMapping("/delete/{id}")
    public List<Tables> deleteTable(@PathVariable Long id){
        return tableService.deleteTable(id);
    }

    @PutMapping("/update")
    public List<Tables> updateTable(@RequestBody Tables table){
        return tableService.updateTable(table);
    }
}
