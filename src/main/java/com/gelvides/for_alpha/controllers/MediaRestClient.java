package com.gelvides.for_alpha.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${spring.application.name}", url = "${spring.request.giphy.server-request}")
public interface MediaRestClient {
    @GetMapping("?${spring.request.giphy.positive-gif}&${spring.request.giphy.application-id}")
    String getPositiveMedia();

    @GetMapping("?${spring.request.giphy.negative-gif}&${spring.request.giphy.application-id}")
    String getNegativeMedia();
}
