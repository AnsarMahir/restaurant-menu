package com.toticone.restuarant_menu.controller;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/uploads")
public class ImageUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name) {
        try {
            // 1️⃣ Validate file type
            validateImageFile(file);

            // 2️⃣ Save image (delete old one if exists)
            String filePath = saveImage(file, name);

            return ResponseEntity.ok("Image uploaded successfully: " + filePath);
        } catch (IllegalArgumentException e) {
            // Validation error — bad request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image: " + e.getMessage());
        }
    }

    private void validateImageFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isEmpty()) {
            throw new IllegalArgumentException("File name is missing");
        }

        // Extract extension
        String extension = originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();

        // Allowed extensions
        List<String> allowedExtensions = List.of("jpg", "jpeg", "png", "webp");

        if (!allowedExtensions.contains(extension)) {
            throw new IllegalArgumentException("Invalid file type. Only JPG, JPEG, PNG, and WEBP are allowed.");
        }

        // Double-check content type (for extra safety)
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Invalid content type. Must be an image.");
        }
    }

    private String saveImage(MultipartFile file, String name) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Supported extensions for deletion
        String[] extensions = {".jpeg", ".jpg", ".png", ".webp"};

        // Delete any existing files with same logical name
        for (String ext : extensions) {
            Path existingFile = uploadPath.resolve(name + ext);
            if (Files.exists(existingFile)) {
                Files.delete(existingFile);
                System.out.println("Deleted old image: " + existingFile);
            }
        }

        // Get new file’s extension
        String extension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."))
                .toLowerCase();

        String fileName = name + extension;
        Path filePath = uploadPath.resolve(fileName);

        // Save the file
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Saved new image: " + filePath.toAbsolutePath());

        return filePath.toString();
    }


    @GetMapping("/images/{name}")
    public ResponseEntity<Resource> getImage(@PathVariable String name) {
        try {
            Path uploadPath = Paths.get(uploadDir);

            // Find file by name (ignore extension)
            Path imagePath = Files.list(uploadPath)
                    .filter(p -> p.getFileName().toString().matches(name + "\\..+"))
                    .findFirst()
                    .orElse(null);

            if (imagePath == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Resource resource = new UrlResource(imagePath.toUri());
            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) contentType = "application/octet-stream";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
