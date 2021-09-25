package com.ser.deployornot.service;

import com.ser.deployornot.entity.DownloadInfoRecord;
import com.ser.deployornot.entity.DownloadInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Clock;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@AllArgsConstructor
public class InfoService {

    private final Clock clock;
    private final GeoIpService geoIpService;
    private final DownloadInfoRepository downloadInfoRepository;

    public void registerDownloading(@NonNull String fileName, @NonNull HttpHeaders httpHeaders) {
        String ipAddress = findIpAddress(httpHeaders);
        String browser = findBrowser(httpHeaders);

        var downloadInfoRecord = new DownloadInfoRecord()
                .setFileName(fileName)
                .setIpAddress(ipAddress)
                .setBrowser(browser)
                .setDownloadDate(clock.instant());
        try {
            var ip = InetAddress.getByName(ipAddress);
            downloadInfoRecord.setCountry(geoIpService.findCountry(ip)).setCity(geoIpService.findCity(ip));
        } catch (UnknownHostException e) {
            log.warn("Could not get city/country, ip address: {}", ipAddress);
        }

        downloadInfoRepository.save(downloadInfoRecord);

    }

    @Nullable
    private String findIpAddress(@NonNull HttpHeaders httpHeaders) {
        return ofNullable(httpHeaders.get("X-Forwarded-For"))
                .map(ipAddressList -> ipAddressList.get(0))
                .orElseGet(() -> ofNullable(httpHeaders.get("Host"))
                        .map(hosts -> hosts.get(0))
                        .orElse(null));
    }

    @Nullable
    private String findBrowser(@NonNull HttpHeaders httpHeaders) {
        return ofNullable(httpHeaders.get("User-Agent"))
                .map(ipAddressList -> ipAddressList.get(0))
                .orElse(null);
    }
}
