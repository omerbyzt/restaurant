package com.ba.service;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.exception.SystemException;
import com.ba.helper.MediaHelper;
import com.ba.mapper.MediaMapper;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaMapper mediaMapper;

    public List<MediaDTO> getWholeFiles(){
        List<Media> mediaList = mediaRepository.findAll();
        if(mediaList.isEmpty()){
            throw new SystemException("Medialist not found");
        }
        return mediaMapper.toDTOList(mediaList);
    }

    public MediaDTO getMediaByID(Long id){
        Optional<Media> media = mediaRepository.findById(id);
        if(media.isEmpty()){
            throw new SystemException("Media not found");
        }
        return mediaMapper.toDTO(media.get());
    }

    public String addFile(MultipartFile file ,String imageName) throws IOException {
        Media media = MediaHelper.addHelper(file,imageName);
        if(media == null){
            throw new SystemException("Media cannot be added...!");
        }
        mediaRepository.save(media);
        return "File Added";
    }

    public String deleteMedia(Long id){
        mediaRepository.deleteById(id);
        return "Media deleted!";
    }
}
