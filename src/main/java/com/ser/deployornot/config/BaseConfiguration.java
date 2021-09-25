package com.ser.deployornot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;

@Configuration
public class BaseConfiguration {

    @Bean
    @Primary
    public Clock clock() {
        return Clock.systemUTC();
    }

}
