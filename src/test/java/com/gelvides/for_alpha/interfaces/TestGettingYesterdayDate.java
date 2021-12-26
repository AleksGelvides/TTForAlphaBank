package com.gelvides.for_alpha.interfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class TestGettingYesterdayDate {
    @Spy
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

        verify(gettingYesterdayDate, times(3)).getYesterdayDate();
    }
}

