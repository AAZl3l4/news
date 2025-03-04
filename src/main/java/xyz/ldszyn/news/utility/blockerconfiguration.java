package xyz.ldszyn.news.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class blockerconfiguration implements WebMvcConfigurer {
    @Autowired
    blocker blocker;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(blocker)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/reg","/list","/getallnews","/getalltype","/getnewsdata","/looktype");
    }
}
