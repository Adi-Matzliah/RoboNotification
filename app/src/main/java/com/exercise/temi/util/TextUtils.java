package com.exercise.temi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class TextUtils {

    public static String changeDateStringFormat(String originDate, String oldFormat, String newFormat) {
        String resultDate = "N/A";
        try {
            SimpleDateFormat fromFormat = new SimpleDateFormat(oldFormat);
            SimpleDateFormat toFormat = new SimpleDateFormat(newFormat);
            resultDate = toFormat.format(fromFormat.parse(originDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    public static String convertDateToLocalDate(String originDate, String oldTimeZone, String oldFormat, String newFormat){
        String resultDate = "N/A";
        try {
            SimpleDateFormat oldFormatter = new SimpleDateFormat(oldFormat); //Create the date as it is in the server
            oldFormatter.setTimeZone(TimeZone.getTimeZone(oldTimeZone));
            Date oldDate = oldFormatter.parse(originDate);

            SimpleDateFormat localFormatter = new SimpleDateFormat(newFormat);
            localFormatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
            resultDate = localFormatter.format(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

}
