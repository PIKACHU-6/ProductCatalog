package com.solution.product_catalog.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

    @Test
    void getISODate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        assertEquals("2021-10-25T09:49:16.094Z", DateUtils.getISODate(format.parse("2021-10-25 15:19:16.94")));
        assertEquals("2020-09-20T07:30:08.091Z", DateUtils.getISODate(format.parse("2020-09-20 13:00:08.91")));
    }

}
