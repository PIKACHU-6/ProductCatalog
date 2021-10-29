package com.solution.product_catalog.util;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_INSTANT;

    public static String getISODate(Date date){
      return date == null ? null : isoDateTime.format(date.toInstant());
    }

}
