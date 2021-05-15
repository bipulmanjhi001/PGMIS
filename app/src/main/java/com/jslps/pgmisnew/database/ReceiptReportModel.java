package com.jslps.pgmisnew.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptReportModel {
    private String headname;
    private String receivedamount;
    private String paymentamount;
    private String date;
    private String paymentmode;
    public int sortDateReference;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        //=== update sort date formate
        //=== convert date to sortable formate
        if(date != "")
        setSortDateReference(parseDate(date));
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }

    public String getReceivedamount() {
        return receivedamount;
    }

    public void setReceivedamount(String receivedamount) {
        this.receivedamount = receivedamount;
    }

    public String getPaymentamount() {
        return paymentamount;
    }

    public void setPaymentamount(String paymentamount) {
        this.paymentamount = paymentamount;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public int getSortDateReference() {
        return sortDateReference;
    }

    public void setSortDateReference(int sortDateReference) {
        this.sortDateReference = sortDateReference;
    }


    //=========== change date formate ========
    public int parseDate(String inputDateString) {

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = null;
        int outputDateString=0;
        try {
            date = inputDateFormat.parse(inputDateString);
            outputDateString = Integer.parseInt(outputDateFormat.format(date));
        } catch (ParseException e) {
                e.printStackTrace();
        }
         return outputDateString;
    }

}

