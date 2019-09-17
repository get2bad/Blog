package top.tinx.blog.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String getFileType(MultipartFile file){
        String fileName = file.getOriginalFilename();
        int fileTypeIndex = fileName.indexOf(".");
        int fileNameLength = fileName.length();
        String fileType = fileName.substring(fileTypeIndex,fileNameLength);
        return fileType;
    }
}
