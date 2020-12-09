package com.ba.controller;

import com.ba.dto.MediaDTO;
import com.ba.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/file")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/list")
    public List<MediaDTO> getWholeFiles(){
        return mediaService.getWholeFiles();
    }

    @PostMapping("add")
    public String addFile(@RequestParam("file") MultipartFile file , @RequestParam String imageName) throws IOException{
        mediaService.addFile(file,imageName);
        return "File Added";
    }

}
