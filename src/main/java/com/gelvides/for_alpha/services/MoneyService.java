package com.gelvides.for_alpha.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class MoneyService {

    public String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(calendar.getTimeInMillis()));
    }

    public boolean comparisonVolume(String today, String yesterday){
        return getDoubleVolume(yesterday) <= getDoubleVolume(today);
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
