package com.bgpark.photoshop.domain.file.dto;

import com.bgpark.photoshop.domain.place.domain.MediaType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UploadResponse {

    private int height;
    private int width;
    private long size;
    private MediaType mediaType;
    private String url;

    @Builder
    public UploadResponse(int height, int width, long size, MediaType mediaType, String url) {
        this.height = height;
        this.width = width;
        this.size = size;
        this.mediaType = mediaType;
        this.url = url;
    }
}
