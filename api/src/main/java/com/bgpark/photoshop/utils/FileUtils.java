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

    public static String getExtension(String fileName) {
        final int position = fileName.lastIndexOf(EXT_DELIMITER);
        String ext = fileName.substring(position + 1);
        if(ext.length() < 1) ext = DEFAULT_EXT;
        return ext;
    }

    private static File getFile(MultipartFile multipartFile) {
        return new File(tmpdir, getFileName(multipartFile));
    }

    private static String getFileName(MultipartFile multipartFile) {
        return String.format(FILENAME_FORMATTER, getRandomFileName(), getExtension(multipartFile.getOriginalFilename()));
    }

    private static String getRandomFileName() {
        return UUID.randomUUID().toString();
    }

    // TODO: 더 나은 디자인 고민, FileType이 Utils에 의존해서는 안될 것 같다
    public static FileType getFileType(String fileName) {
        final String ext = getExtension(fileName);
        if(PHOTO.hasType(ext)) {
            return PHOTO;
        }
        return NONE;
    }
}
