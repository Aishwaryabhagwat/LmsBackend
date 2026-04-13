package com.cdac.E_Learning.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(FileValidator.class);

    private static final List<String> ALLOWED_TYPES = Arrays.asList(
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    private static final long MAX_SIZE = 10000 * 1024 * 1024; // 5MB

    public static void validate(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        List<String> allowedTypes = Arrays.asList(
            "video/mp4",
            "video/mpeg",
            "video/quicktime"
        );

        String contentType = file.getContentType();

        if (contentType == null || !allowedTypes.contains(contentType)) {
            logger.warn("Blocked upload - invalid file type: {}", contentType);
            throw new RuntimeException("Invalid file type");
        }

        String filename = file.getOriginalFilename();

        if (filename == null ||
            !(filename.endsWith(".mp4") || filename.endsWith(".mpeg") || filename.endsWith(".mov"))) {
            throw new RuntimeException("Invalid file extension");
        }

        long maxSize = 10000 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            logger.warn("Blocked upload - file too large: {}", file.getSize());
            throw new RuntimeException("File size exceeds limit");
        }
    }

    public static String generateSafeFileName(String originalName) {
        String extension = "";

        if (originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        return UUID.randomUUID().toString() + extension;
    }
    
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/jpg"
    );

    public static void validateImage(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("Image file is empty");
        }

        if (file.getSize() > MAX_SIZE) {
            throw new RuntimeException("Image size exceeds limit");
        }

        if (!ALLOWED_IMAGE_TYPES.contains(file.getContentType())) {
            throw new RuntimeException("Invalid image type");
        }
    }
    
    public static void validateDocument(MultipartFile file) {

        List<String> allowedTypes = Arrays.asList(
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );

        validateCommon(file, allowedTypes, 10000 * 1024 * 1024, "document");
    }
    
 // ================= COMMON METHOD =================
    private static void validateCommon(MultipartFile file, List<String> allowedTypes, long maxSize, String type) {

        if (file.isEmpty()) {
            throw new RuntimeException(type + " file is empty");
        }

        String contentType = file.getContentType();

        logger.info("File type received: {}", contentType);

        if (contentType == null || !allowedTypes.contains(contentType)) {
            logger.warn("Blocked upload - invalid {} type: {}", type, contentType);
            throw new RuntimeException("Invalid file type");
        }

        if (file.getSize() > maxSize) {
            logger.warn("Blocked upload - {} too large: {}", type, file.getSize());
            throw new RuntimeException("File size exceeds limit");
        }
    }
}

