package com.gelvides.for_alpha.controllers;

import com.gelvides.for_alpha.feign.MediaRestClient;
import com.gelvides.for_alpha.feign.RestClient;
import com.gelvides.for_alpha.services.MediaService;
import com.gelvides.for_alpha.services.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @Autowired
    MediaService mediaService;
    @Autowired
    MediaRestClient mediaRestClient;
    @Autowired
    MoneyService moneyService;
    @Autowired
    RestClient restClient;

    @GetMapping()
    public String goPage(Model model){
        if(moneyService.comparisonVolume(
                restClient.getTodayCource(),
                restClient.getYesterdayCource(moneyService.getYesterdayDate()))){
            model.addAttribute("media",
                    mediaService.createMedia(mediaRestClient.getPositiveMedia()));
        } else
            model.addAttribute("media",
                    mediaService.createMedia(mediaRestClient.getNegativeMedia()));
        return "index";
    }
}
