package com.ser.deployornot.web;

import com.ser.deployornot.service.InfoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("/finance-number")
public class FinanceNumberController {

    private final InfoService infoService;
    private final ResourceLoader resourceLoader;

    @GetMapping(value = "/download/{fileName}", produces = "application/pdf")
    public ResponseEntity<Resource> download(@PathVariable String fileName,
                                             HttpServletRequest request) {
        try {
            request.getRemoteUser();
            Resource file = resourceLoader.getResource("classpath:/download/" + fileName);
            infoService.registerDownloading(fileName, request);
            return ok().body(file);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
