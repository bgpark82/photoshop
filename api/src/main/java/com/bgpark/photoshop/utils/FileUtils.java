package com.bgpark.photoshop.utils;

import com.bgpark.photoshop.domain.file.dto.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.bgpark.photoshop.domain.file.dto.FileType.NONE;
import static com.bgpark.photoshop.domain.file.dto.FileType.PHOTO;

public class FileUtils {

    private static final String tmpdir = System.getProperty("java.io.tmpdir");
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
        return new File(tmpdir, fileName);
    }

    private static String getFileName(MultipartFile multipartFile) {
        final String fileName = UUID.randomUUID().toString();
        final String ext = getExtension(multipartFile.getOriginalFilename());
        return String.format(FILENAME_FORMATTER, fileName, ext);
    }

    public static String getExtension(String fileName) {
        final int position = fileName.lastIndexOf(EXT_DELIMITER);
        String ext = fileName.substring(position + 1);
        if(ext.length() < 1) ext = DEFAULT_EXT;
        return ext;
    }

    // TODO: 더 나은 디자인 고민
    public static FileType getFileType(String fileName) {
        final String ext = getExtension(fileName);
        if(PHOTO.hasType(ext)) {
            return PHOTO;
        }
        return NONE;
    }
}
