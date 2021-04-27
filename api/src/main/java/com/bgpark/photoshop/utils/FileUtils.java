package com.bgpark.photoshop.utils;

import com.bgpark.photoshop.domain.file.dto.FileType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;

public class FileUtils {

    private static final String[] IMAGE_TYPES = {"jpg", "jpeg", "png", "gif", "bmp", "tiff", "jpe"};
    private static final String TEMP_DIRECTORY = "java.io.tmpdir";
    private static final String DEFAULT_EXT = "jpg";
    private static final String FILENAME_FORMATTER = "%s.%s";
    private static final String EXT_DELIMITER = ".";

    public static File convert(MultipartFile multipartFile) {
        final File storeFile = getFile(multipartFile);
        try {
            multipartFile.transferTo(storeFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일이 생성되지 않았습니다");
        }
        return storeFile;
    }

    private static File getFile(MultipartFile multipartFile) {
        final String fileName = getFileName(multipartFile);
        final String tmpdir = System.getProperty(TEMP_DIRECTORY);
        return new File(tmpdir, fileName);
    }

    private static String getFileName(MultipartFile multipartFile) {
        final String filename = UUID.randomUUID().toString();
        final String ext = getExtension(multipartFile.getOriginalFilename());
        return String.format(FILENAME_FORMATTER, filename, ext);
    }

    public static String getExtension(String fileName) {
        int position = fileName.lastIndexOf(EXT_DELIMITER);
        String ext = fileName.substring(position + 1);
        if(ext.length() < 1) ext = DEFAULT_EXT;
        return ext;
    }

    public static FileType getFileType(String fileName) {
        final String ext = getExtension(fileName);
        final boolean isImageType = Arrays.stream(IMAGE_TYPES).anyMatch(t -> t.equals(ext));

        if(isImageType) {
            return FileType.photo;
        }
        return FileType.photo;
    }
}
