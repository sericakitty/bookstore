package hh.sof03.bookstore;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @description - This class is used to configure the base path for the API.
 * You need to add spring.data.rest.base-path=/api to the application.properties file.
 * @class ApiBasePathConfiguration
 */
@Configuration
public class ApiBasePathConfiguration implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // This will add the /api prefix to all the @RestController endpoints
        configurer.addPathPrefix("/api", (clazz)->clazz.isAnnotationPresent(org.springframework.web.bind.annotation.RestController.class));
    }
}