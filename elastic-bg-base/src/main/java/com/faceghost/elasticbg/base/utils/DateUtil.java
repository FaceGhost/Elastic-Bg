package com.faceghost.elasticbg.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	 /**
     * Date -> String
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date,DateStyleEnum pattern) {
        if (date == null) date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(pattern.value().toString());
        return sf.format(date);
    }


    /**
     * String -> Date 
     * @param date
     * @param pattern
     * @return
     * @throws Exception
     */
    public static Date formatDate(String date,DateStyleEnum pattern) throws Exception {
        if (date == null) throw new Exception("dataFormat date str is null ");
        SimpleDateFormat sf = new SimpleDateFormat(pattern.value().toString());
        return sf.parse(date);
    }


    /**
     * Test
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println(dateFormat(new Date(), DateStyleEnum.yyyy_mm_dd_hh_mm_ss));
        System.out.println(formatDate("2017-08-02 16:29:56", DateStyleEnum.yyyy_mm_dd_hh_mm_ss));
    }
}
