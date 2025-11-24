package com.example.md4b5bt1.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.File;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 🆕 SỬA: Dùng đường dẫn tuyệt đối trên máy bạn
    private static final String UPLOAD_DIR = "C:/Users/DungG/Downloads/music-ytdlp/uploads/";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            System.out.println("Upload directory created: " + created + " at " + uploadDir.getAbsolutePath());
        }

        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                UPLOAD_DIR,
                100 * 1024 * 1024,   // 100MB
                200 * 1024 * 1024,  // 200MB
                1 * 1024 * 1024     // 1MB
        );
        registration.setMultipartConfig(multipartConfig);
        System.out.println("Multipart config set for: " + UPLOAD_DIR);
    }
}