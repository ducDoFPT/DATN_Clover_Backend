package com.datn.clover.services.sellers;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class UploadServices {
    public String uploadFile(MultipartFile file) {

        if (!file.isEmpty()) {
            Path root = Paths.get("uploads");

            try {
                Files.createDirectories(root);

                String name = String.valueOf(new Date().getTime());
                String fileName = String.format("%s%s", name, ".jpg");

                Files.copy(file.getInputStream(), root.resolve(fileName));

                return fileName;

            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("Could not initialize folder for upload!");
                // TODO: handle exception
            }
        }

        return null;
    }

    public boolean remove(String name) {
        try {
            Path root = Paths.get(String.format("uploads/%s", name));

            return Files.deleteIfExists(root);

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
