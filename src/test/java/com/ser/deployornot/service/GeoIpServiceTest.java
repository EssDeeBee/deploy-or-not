package com.ser.deployornot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootTest
class GeoIpServiceTest {

    @Autowired
    protected GeoIpService geoIpService;


    @Test
    void shouldFindCountryWhenIpIsCorrect() throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("62.76.67.116");
        String country = geoIpService.findCountry(ip);
        Assertions.assertThat(country).isNotEmpty();
    }

    @Test
    void shouldFindCityWhenIpIsCorrect() throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("62.76.67.116");
        String city = geoIpService.findCity(ip);
        Assertions.assertThat(city).isNotEmpty();
    }

}