package com.example.demo_zalo_part1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.TimeUnit;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

//    private Logger log = LoggerFactory.getLogger(WebConfig.class);

//  @Bean
//  public CorsFilter corsFilter() {
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//    CorsConfiguration config = new CorsConfiguration();
//    config.addAllowedOrigin("*");
//    config.addAllowedHeader("Accept,Accept-Encoding,Accept-Language,Access-Control-Request-Method,Access-Control-Request-Headers," +
//      "Cache-Control,Connection,Content-Length,Content-Type,Host,Origin,Pragma,Referer,User-Agent,Authorization,StoreManagerNo");
//    config.addAllowedMethod("GET,POST,DELETE,PUT,OPTIONS");
//    config.addExposedHeader("Authorization");
//    config.setMaxAge(3600L);
//
//    if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
//      log.debug("Registering CORS filter");
//      source.registerCorsConfiguration("/demo-api/**", config);
//    }
//    return new CorsFilter(source);
//  }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/images/**").addResourceLocations("classpath:/static/assets/images/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
        registry.addResourceHandler("/assets/fonts/**").addResourceLocations("classpath:/static/assets/fonts/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
        registry.addResourceHandler("/assets/js/**").addResourceLocations("classpath:/static/assets/js/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
        registry.addResourceHandler("/assets/css/**").addResourceLocations("classpath:/static/assets/css/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
//        registry.addResourceHandler("/adminlte/**").addResourceLocations("classpath:/static/adminlte/")
//                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}
