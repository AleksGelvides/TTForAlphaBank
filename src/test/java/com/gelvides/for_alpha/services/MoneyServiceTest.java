package com.gelvides.for_alpha.services;

import com.gelvides.for_alpha.controllers.CurrencyRestClient;
import com.gelvides.for_alpha.servicemethod.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class MoneyServiceTest {
    @Value("${spring.request.openexchangerates.today-cource-json-file-for-test-services}")
    private String todayCourse;
    @Value("${spring.request.openexchangerates.yesterday-course-json-file-for-test-services}")
    private String yesterdayCource;

    @MockBean
    CurrencyRestClient currencyRestClient;

    @Autowired
    MoneyService moneyService;

    @Test
    public void getTodayCourseDoubleParsingTargetCurrency(){
        when(currencyRestClient.getCource()).thenReturn(Json.getJSONString(todayCourse));

        var todayUsdRub = 73.6685;
        var jsonTodayCourse = currencyRestClient.getCource();
        var todayCoursePrice = moneyService.comparisonVolume(jsonTodayCourse);

        assertNotNull(todayCoursePrice);
        assertEquals(todayUsdRub, todayCoursePrice.priceCurrency());

        verify(currencyRestClient, times(1)).getCource();
    }

    @Test
    public void getYesterdayCourseDoubleParsingTargetCurrency(){
        when(currencyRestClient.getCource(any())).thenReturn(Json.getJSONString(yesterdayCource));

        var yesterdayUsdRub = 73.394;
        var jsonYesterdayCourse = currencyRestClient.getCource("abstractYesterdayDate");
        var yesterdayCoursePrice = moneyService.comparisonVolume(jsonYesterdayCourse);

        assertNotNull(yesterdayCoursePrice);
        assertEquals(yesterdayUsdRub, yesterdayCoursePrice.priceCurrency());

        verify(currencyRestClient, times(1)).getCource("abstractYesterdayDate");
    }
}
