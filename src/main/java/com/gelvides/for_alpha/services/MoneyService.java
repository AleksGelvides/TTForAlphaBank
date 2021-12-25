package com.gelvides.for_alpha.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gelvides.for_alpha.entity.Price;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class MoneyService {

    public Price comparisonVolume(String json){
        var price = new Price(getDoubleVolume(json));
        return price;
    }

    private double getDoubleVolume(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            return Double.parseDouble(jsonNode.get("rates").get("RUB").asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
