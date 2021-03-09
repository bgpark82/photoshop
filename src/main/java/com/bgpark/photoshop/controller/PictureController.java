package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.domain.item.Picture;
import com.bgpark.photoshop.dto.PictureDto;
import com.bgpark.photoshop.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PictureController {

    private final PictureRepository pictureRepository;

    @PostMapping("/pictures")
    public ResponseEntity<PictureDto.Res> save(@RequestBody PictureDto.Req request) {
        Picture picture = pictureRepository.save(request.toEntity());
        PictureDto.Res response = PictureDto.Res.of(picture);
        return ResponseEntity
                .created(URI.create("/pictures/" + picture.getId()))
                .body(response);
    }
}
