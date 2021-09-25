package com.ser.deployornot.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GeoIpConfig {


    @Bean
    public DatabaseReader asnDatabaseReader() throws IOException {
        InputStream inputStream = getClass().getResource("/geoip/GeoLite2-ASN.mmdb").openStream();
        return new DatabaseReader.Builder(inputStream).build();
    }

    @Bean
    public DatabaseReader cityDatabaseReader() throws IOException {
        InputStream database = getClass().getResource("/geoip/GeoLite2-City.mmdb").openStream();
        return new DatabaseReader.Builder(database).build();
    }

    @Bean
    public DatabaseReader countryDatabaseReader() throws IOException {
        InputStream database = getClass().getResource("/geoip/GeoLite2-Country.mmdb").openStream();
        return new DatabaseReader.Builder(database).build();
    }
}
