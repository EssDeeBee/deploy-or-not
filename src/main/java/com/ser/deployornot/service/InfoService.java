package com.ser.deployornot.service;

import com.ser.deployornot.entity.DownloadInfoRecord;
import com.ser.deployornot.entity.DownloadInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    public void registerDownloading(@NonNull String fileName, @NonNull HttpServletRequest request) {
        String ipAddress = findIpAddress(request);
        String browser = findBrowser(request);

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
    private String findIpAddress(@NonNull HttpServletRequest request) {
        return ofNullable(request.getHeader("X-Forwarded-For"))
                .map(ipAddressList -> ipAddressList.split(",")[0])
                .orElseGet(request::getRemoteAddr);
    }

    @Nullable
    private String findBrowser(@NonNull HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }
}
