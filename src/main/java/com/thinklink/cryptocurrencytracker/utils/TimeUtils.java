package com.thinklink.cryptocurrencytracker.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    public static Date getCurrentUtcTime(long timeInMillis) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        return localDateFormat.parse( simpleDateFormat.format(new Date(timeInMillis)) );
    }

    /**
     * converts milliseconds to the Human readable date format. Also it sets the UTC zone.
     * @param timeInMillis
     * @return
     */
    public static String getUtcTimeFromMillis(long timeInMillis) {
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(timeInMillis).atZone(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(formatter);
    }
}
