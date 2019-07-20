package com.leyou.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author l
 * @since 2019/7/17
 */
@Configuration
public class LeyouCorsConfiguration {
    @Bean
    public CorsFilter corsFilter() {
        //添加Cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        //允许的域
        config.addAllowedOrigin("http://manage.leyou.com");
        //允许的请求方式
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.POST);
        //允许的头信息
        config.addAllowedHeader("*");
        //添加映射路径
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);
        //返回CorsFilter
        return new CorsFilter(configurationSource);
    }
}
