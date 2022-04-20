package com.gelvides.for_alpha.interfaces;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public interface GettingYesterdayDate {
    default String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(calendar.getTimeInMillis()));
    }
}
