package com.bgpark.photoshop.dto.upload;

import com.bgpark.photoshop.domain.place.MediaType;
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

    @Builder
    public UploadResponse(int height, int width, long size, MediaType mediaType) {
        this.height = height;
        this.width = width;
        this.size = size;
        this.mediaType = mediaType;
    }
}
