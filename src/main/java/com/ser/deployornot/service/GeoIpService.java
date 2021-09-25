package com.ser.deployornot.service;

import com.maxmind.geoip2.DatabaseReader;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
@AllArgsConstructor
public class GeoIpService {

    private final DatabaseReader cityDatabaseReader;
    private final DatabaseReader countryDatabaseReader;

    @SneakyThrows
    public String findCity(@NonNull InetAddress ipAddress) {
        return cityDatabaseReader.tryCity(ipAddress)
                .map(cityResponse -> cityResponse.getCity().getName())
                .orElse("Unknown");
    }

    @SneakyThrows
    public String findCountry(@NonNull InetAddress ipAddress) {
        return countryDatabaseReader.tryCountry(ipAddress).map(countryResponse -> countryResponse.getCountry().getName())
                .orElse("Unknown");
    }
}
