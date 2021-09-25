package com.ser.deployornot.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

@Configuration
public class GeoIpConfig {

    @Bean
    public DatabaseReader asnDatabaseReader() throws IOException {
        File database = new File(requireNonNull(getClass().getResource("/geoip/GeoLite2-ASN.mmdb")).getFile());
        return new DatabaseReader.Builder(database).build();
    }

    @Bean
    public DatabaseReader cityDatabaseReader() throws IOException {
        File database = new File(requireNonNull(getClass().getResource("/geoip/GeoLite2-City.mmdb")).getFile());
        return new DatabaseReader.Builder(database).build();
    }

    @Bean
    public DatabaseReader countryDatabaseReader() throws IOException {
        File database = new File(requireNonNull(getClass().getResource("/geoip/GeoLite2-Country.mmdb")).getFile());
        return new DatabaseReader.Builder(database).build();
    }
}
