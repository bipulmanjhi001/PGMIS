package com.jslps.pgmisnew.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetCurrentDate {

    public GetCurrentDate() {

    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String ChangeFormat(String dates) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formatter.parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatterMonth = new SimpleDateFormat("dd/MMM/yyyy");
        return formatterMonth.format(new Date(date.getTime()));
    }

}
