package com.asset.foundation.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date stringToDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DateToString(Date date) {
        return dateFormat.format(date);
    }

}
