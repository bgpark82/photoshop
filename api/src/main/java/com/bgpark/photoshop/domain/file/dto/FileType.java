package com.bgpark.photoshop.domain.file.dto;

import java.util.Arrays;

public enum FileType {
    
    PHOTO("photo", new String[]{"jpg", "jpeg", "png", "gif", "bmp", "tiff", "jpe"}),
    NONE("none", new String[]{});

    private String name;
    private String[] types;

    FileType(String name, String[] types) {
        this.name = name;
        this.types = types;
    }

    public boolean hasType(String ext) {
        return Arrays.stream(this.types)
                .anyMatch(t -> t.equals(ext));
    }
}
