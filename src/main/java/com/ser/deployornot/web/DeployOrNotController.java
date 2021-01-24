package com.ser.deployornot.web;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DeployOrNotController {


    @GetMapping("/")
    public Mono<String> deployOrNot() {

        return WebClient.builder()
                .baseUrl("https://shouldideploy.today/?tz=Etc%2FGMT%2B1")
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .map(Jsoup::parse)
                .map(document -> document.getElementById("text"))
                .map(Element::text);
    }

    @PostMapping("/")
    public Mono<String> postDeployOrNot() {

        return WebClient.builder()
                .baseUrl("https://shouldideploy.today/?tz=Etc%2FGMT%2B1")
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .map(Jsoup::parse)
                .map(document -> document.getElementById("text"))
                .map(Element::text);
    }
}
