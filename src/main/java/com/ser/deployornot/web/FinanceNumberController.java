package com.ser.deployornot.web;

import com.ser.deployornot.service.InfoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("/finance-number")
public class FinanceNumberController {

    private final InfoService infoService;

    @GetMapping(value = "/download/{fileName}", produces = "application/pdf")
    public ResponseEntity<Resource> download(@PathVariable String fileName,
                                             @RequestHeader HttpHeaders httpHeaders) {
        try {
            File file = new File(requireNonNull(getClass().getResource("/download/" + fileName)).getFile());
            infoService.registerDownloading(fileName, httpHeaders);
            return ok().body(new FileSystemResource(file));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
