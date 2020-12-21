package com.ba.service;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.mapper.MediaMapper;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class MediaService {

    private static final String JPG_EXTENSION = ".jpg";
    private static final String PNG_EXTENSION = ".png";
    private static final String BMP_EXTENSION = ".bmp";

    private static final String BMP_CONTENT = "image/bmp";
    private static final String PNG_CONTENT = "image/png";

    @Value("C:/Users/omerb/IdeaProjects/rest-api/target/media/")
    private String uploadDir;

    @Autowired
    private MediaRepository mediaRepository;

    public List<MediaDTO> getWholeFiles(){
        List<MediaDTO> mediaDTOList = MediaMapper.INSTANCE.toDTOList(mediaRepository.findAll());
        return mediaDTOList;
//        return MediaConverter.convertListToListDTO(mediaRepository.findAll());
    }

    public MediaDTO getMediaByID(Long id){
        MediaDTO mediaDTO = MediaMapper.INSTANCE.toDTO(mediaRepository.findById(id).get());
        return mediaDTO;
//        return MediaConverter.convertMediaToMediaDTO(mediaRepository.findById(id).get());
    }

    public String addFile(MultipartFile file ,String imageName) throws IOException {

        Files.createDirectories(Paths.get("C:/Users/omerb/IdeaProjects/rest-api/target/media/"));
        String filePath = generateFullFilePath(file);

        Path targetLocation = FileSystems.getDefault().getPath(filePath);

        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

        byte[] bytes = file.getBytes();

        Media media = new Media();
        media.setFileContent(bytes);
        media.setName(imageName);

        mediaRepository.save(media);

        return "File Added";
    }

    private String generateFullFilePath(MultipartFile file){
        String extension = JPG_EXTENSION;

        if(BMP_CONTENT.equals(file.getContentType())){
            extension = BMP_EXTENSION;
        }else if(PNG_CONTENT.equals(file.getContentType())){
            extension = PNG_EXTENSION;
        }
        return uploadDir + generateUUID()+extension;
    }

    private String generateUUID(){
        return String.valueOf(java.util.UUID.randomUUID());
    }

}
