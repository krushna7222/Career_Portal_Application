package com.org.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Component
public class CloudinaryUtil {

    private final Cloudinary cloudinary;

    public CloudinaryUtil(@Value("${cloudinary.cloud_name}") String cloudName,
                          @Value("${cloudinary.api_key}") String apiKey,
                          @Value("${cloudinary.api_secret}") String apiSecret) {

        cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
        ));
    }

    public String uploadFile(File file, String folder) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "folder", folder,
                "resource_type", "auto"
            ));
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("Upload to Cloudinary failed: " + e.getMessage());
        } finally {
            file.delete(); 
        }
    }

    public File convertToFile(String filename, byte[] content) throws IOException {
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content);
        }
        return file;
    }
}
