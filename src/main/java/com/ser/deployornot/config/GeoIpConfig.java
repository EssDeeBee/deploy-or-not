package com.ser.deployornot.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@Configuration
public class GeoIpConfig {

    @Bean
    public DatabaseReader asnDatabaseReader() throws IOException {
        File database = ResourceUtils.getFile("classpath:geoip/GeoLite2-ASN.mmdb");
        return new DatabaseReader.Builder(database).build();
    }

    @Bean
    public DatabaseReader cityDatabaseReader() throws IOException {
        File database = ResourceUtils.getFile("classpath:geoip/GeoLite2-City.mmdb");
        return new DatabaseReader.Builder(database).build();
    }

    @Bean
    public DatabaseReader countryDatabaseReader() throws IOException {
        File database = ResourceUtils.getFile("classpath:geoip/GeoLite2-Country.mmdb");
        return new DatabaseReader.Builder(database).build();
    }
}
