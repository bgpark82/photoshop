package com.bgpark.photoshop.domain.item.ui;

import com.bgpark.photoshop.domain.item.domain.Item;
import com.bgpark.photoshop.domain.item.domain.ItemRepository;
import com.bgpark.photoshop.domain.item.domain.Picture;
import com.bgpark.photoshop.domain.item.dto.PictureRequest;
import com.bgpark.photoshop.domain.item.dto.PictureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @PostMapping("/pictures")
    @Transactional
    public ResponseEntity<PictureResponse> save(@RequestBody PictureRequest request) {
        Item picture = itemRepository.save(request.toEntity());
        PictureResponse response = PictureResponse.of((Picture) picture);
        return ResponseEntity
                .created(URI.create("/pictures/" + picture.getId()))
                .body(response);
    }
}
