package com.ba.helper;

import com.ba.entity.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

public class MediaHelper {

    private static final String JPG_EXTENSION = ".jpg";
    private static final String PNG_EXTENSION = ".png";
    private static final String BMP_EXTENSION = ".bmp";

    private static final String BMP_CONTENT = "image/bmp";
    private static final String PNG_CONTENT = "image/png";

    @Value("C:/Users/omerb/IdeaProjects/rest-api/target/media/")
    private static String uploadDir;

    public static String generateFullFilePath(MultipartFile file){
        String extension = JPG_EXTENSION;

        if(BMP_CONTENT.equals(file.getContentType())){
            extension = BMP_EXTENSION;
        }else if(PNG_CONTENT.equals(file.getContentType())){
            extension = PNG_EXTENSION;
        }
        return uploadDir + generateUUID()+extension;
    }

    public static String generateUUID(){
        return String.valueOf(java.util.UUID.randomUUID());
    }

    public static Media addHelper(MultipartFile file,String imageName) throws IOException {
        Files.createDirectories(Paths.get("C:/Users/omerb/IdeaProjects/rest-api/target/media/"));
        String filePath = MediaHelper.generateFullFilePath(file);

        Path targetLocation = FileSystems.getDefault().getPath(filePath);

        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

        byte[] bytes = file.getBytes();

        Media media = new Media();
        media.setFileContent(bytes);
        media.setName(imageName);

        return media;
    }
}
