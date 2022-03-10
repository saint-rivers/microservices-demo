package com.exmaple.users.config;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailClientConfig {

    @Bean
    public OkHttpClient client(){
        return new OkHttpClient();
    }
}
