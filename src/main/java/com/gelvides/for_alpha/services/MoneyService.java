package com.gelvides.for_alpha.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gelvides.for_alpha.entity.Price;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MoneyService {
    @Value("${spring.request.currency.convert-currency}")
    private String currency;

    public Price comparisonVolume(String json){
        var price = new Price(getDoubleVolume(json));
        return price;
    }

    private double getDoubleVolume(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            return Double.parseDouble(jsonNode.get("rates").get(currency).asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
