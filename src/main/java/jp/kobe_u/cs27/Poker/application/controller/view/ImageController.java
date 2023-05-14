package jp.kobe_u.cs27.Poker.application.controller.view;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @GetMapping("/image/{type}/{number}")
    public ResponseEntity<Resource> getImage(@PathVariable String type, @PathVariable String number) {
        return fetchImage("src/main/resources/static/card/" + type + "/" + number + ".png");
    }

    private ResponseEntity<Resource> fetchImage(String path) {
        try {
            Resource resource = new UrlResource(Paths.get(path).toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
