package com.gelvides.for_alpha.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${spring.application.name}", url = "${spring.request.openexchangerates.server-request}")
public interface RestClient {
    @GetMapping("latest.json?${spring.request.openexchangerates.application-id}")
    String getTodayCource();

    @GetMapping("historical/{yesterday-date}.json?${spring.request.openexchangerates.application-id}")
    String getYesterdayCource(@PathVariable("yesterday-date") String date);
}
