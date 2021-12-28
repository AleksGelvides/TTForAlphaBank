package com.gelvides.for_alpha.controllers;

import com.gelvides.for_alpha.entity.Media;
import com.gelvides.for_alpha.entity.Price;
import com.gelvides.for_alpha.servicemethod.Json;
import com.gelvides.for_alpha.services.MediaService;
import com.gelvides.for_alpha.services.MoneyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class ViewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Value("${spring.request.giphy.rich-json-file-for-test}")
    private String richJson;
    @Value("${spring.request.giphy.broke-json-file-for-test}")
    private String brokeJson;
    @Value("${spring.request.openexchangerates.rich-today-course-json-file-for-test-client}")
    private String richTodayCourse;
    @Value("${spring.request.openexchangerates.rich-yesterday-course-json-file-for-test-client}")
    private String richYesterdayCourse;
    @Value("${spring.request.openexchangerates.broke-today-course-json-file-for-test-client}")
    private String brokeTodayCourse;
    @Value("${spring.request.openexchangerates.broke-yesterday-course-json-file-for-test-client}")
    private String brokeYesterdayCourse;

    @MockBean
    MediaRestClient mediaRestClient;
    @MockBean
    CurrencyRestClient currencyRestClient;
    @Autowired
    MediaService mediaService;
    @Autowired
    MoneyService moneyService;
    @Autowired
    ViewController viewController;


    @Test
    public void getRichContentForFrontPage() throws Exception {
        var jsonTodayCourse = 1;
        var jsonYesterdayCourse = 5;
        var richMedia = "https://i.giphy.com/RNWCDUDZiK49C8n1J2.gif";

        when(mediaRestClient.getPositiveMedia()).thenReturn(Json.getJSONString(richJson));
        when(currencyRestClient.getCource()).thenReturn(Json.getJSONString(richTodayCourse));
        when(currencyRestClient.getCource(any())).thenReturn(Json.getJSONString(richYesterdayCourse));


        MvcResult mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("priceToday"))
                .andExpect(model().attributeExists("priceYesterday"))
                .andExpect(model().attributeExists("media")).andReturn();

        Price mvcTodayCourse = (Price) mvcResult.getModelAndView().getModel().get("priceToday");
        Price mvcYesterdayCourse = (Price) mvcResult.getModelAndView().getModel().get("priceYesterday");
        Media media = (Media) mvcResult.getModelAndView().getModel().get("media");

        assertNotNull(mvcTodayCourse);
        assertNotNull(mvcYesterdayCourse);
        assertNotNull(media);

        assertEquals(mvcTodayCourse.priceCurrency(), jsonTodayCourse);
        assertEquals(mvcYesterdayCourse.priceCurrency(), jsonYesterdayCourse);
        assertEquals(media.uri(), richMedia);

        verify(mediaRestClient, times(1)).getPositiveMedia();
        verify(currencyRestClient, times(1)).getCource();
        verify(currencyRestClient, times(1)).getCource(any());
    }

    @Test
    public void getBrokeContentForFrontPage() throws Exception {
        var jsonTodayCourse = 5;
        var jsonYesterdayCourse = 1;
        var brokeMedia = "https://i.giphy.com/UU108BDSM6VWrzPHho.gif";

        when(mediaRestClient.getNegativeMedia()).thenReturn(Json.getJSONString(brokeJson));
        when(currencyRestClient.getCource()).thenReturn(Json.getJSONString(brokeTodayCourse));
        when(currencyRestClient.getCource(any())).thenReturn(Json.getJSONString(brokeYesterdayCourse));


        MvcResult mvcResult = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("priceToday"))
                .andExpect(model().attributeExists("priceYesterday"))
                .andExpect(model().attributeExists("media")).andReturn();

        Price mvcTodayCourse = (Price) mvcResult.getModelAndView().getModel().get("priceToday");
        Price mvcYesterdayCourse = (Price) mvcResult.getModelAndView().getModel().get("priceYesterday");
        Media media = (Media) mvcResult.getModelAndView().getModel().get("media");

        assertNotNull(mvcTodayCourse);
        assertNotNull(mvcYesterdayCourse);
        assertNotNull(media);

        assertEquals(mvcTodayCourse.priceCurrency(), jsonTodayCourse);
        assertEquals(mvcYesterdayCourse.priceCurrency(), jsonYesterdayCourse);
        assertEquals(media.uri(), brokeMedia);

        verify(mediaRestClient, times(1)).getNegativeMedia();
        verify(currencyRestClient, times(1)).getCource();
        verify(currencyRestClient, times(1)).getCource(any());
    }
}
