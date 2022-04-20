package com.gelvides.for_alpha.interfaces;

import com.gelvides.for_alpha.ForAlphaApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestGettingYesterdayDate extends ForAlphaApplicationTests {
    @Autowired
    GettingYesterdayDate gettingYesterdayDate;

    @Test
    public void testingGettingYesterdayDate() {
        var todayDate = Calendar.getInstance();
        var simpleDateFormatToday = new SimpleDateFormat("yyyy-MM-dd");
        var todayDateInString = simpleDateFormatToday.format(new Date(todayDate.getTimeInMillis()));

        var tomorrowDate = Calendar.getInstance();
        tomorrowDate.add(Calendar.DAY_OF_MONTH, 1);
        var simpleDateFormatTomorrow = new SimpleDateFormat("yyyy-MM-dd");
        var tomorrowDateInString = simpleDateFormatTomorrow.format(new Date(tomorrowDate.getTimeInMillis()));

        var yesterdayDate = Calendar.getInstance();
        yesterdayDate.add(Calendar.DAY_OF_MONTH, -1);
        var simpleDateFormatYesterday = new SimpleDateFormat("yyyy-MM-dd");
        var yesterdayDateInString = simpleDateFormatYesterday.format(new Date(yesterdayDate.getTimeInMillis()));


        Assertions.assertNotEquals(gettingYesterdayDate.getYesterdayDate(),
                todayDateInString);
        Assertions.assertNotEquals(gettingYesterdayDate.getYesterdayDate(),
                tomorrowDateInString);
        Assertions.assertEquals(gettingYesterdayDate.getYesterdayDate(), yesterdayDateInString);
    }
}

