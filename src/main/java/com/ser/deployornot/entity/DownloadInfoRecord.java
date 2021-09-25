package com.ser.deployornot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "download_info")
public class DownloadInfoRecord {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "browser")
    private String browser;

    @Column(name = "download_date")
    private Instant downloadDate;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

}
