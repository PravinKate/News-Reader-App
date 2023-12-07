package com.example.newsreader.news_reader.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtils {

    public static String convertDate(String inputDateString) {
        // Input date format
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.getDefault());

        // Output date format
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault());

        try {
            Date date = inputFormat.parse(inputDateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
