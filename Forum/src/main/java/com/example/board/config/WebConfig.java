package com.example.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "upload/**";  // view 에서 접근할 경로
    private String savePath = "file:///c:springboot_img/";  // 실제 파일 저장 경로

    // resourcePath 경로를 찾을경우 savePath 로 찾아준다 라는 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }

}
