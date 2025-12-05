package com.example.md4b13bt1.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // Không cần root context
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfiguration.class}; // DispatcherServlet sẽ scan tất cả beans
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // Map root path
    }
}
