package com.example.md4b8bt2.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Root context (dùng cho DB / Security nếu cần)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ AppConfiguration.class };
    }

    // Servlet context (web)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null; // không cần thêm, mọi thứ đã config trong root
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
