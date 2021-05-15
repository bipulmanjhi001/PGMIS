package com.jslps.pgmisnew.database;

public class BrsReportModel {
    private String headname;
    private String receivedamount;
    private String paymentamount;
    //private String date;
    private String paymentmode;

    private String year;
    private String month;
    private String balanceCashbook;
    private String balancePassbook;
    private String imageCashbook;
    private String imagePassbook;
    private String date;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBalanceCashbook() {
        return balanceCashbook;
    }

    public void setBalanceCashbook(String balanceCashbook) {
        this.balanceCashbook = balanceCashbook;
    }

    public String getBalancePassbook() {
        return balancePassbook;
    }

    public void setBalancePassbook(String balancePassbook) {
        this.balancePassbook = balancePassbook;
    }

    public String getImageCashbook() {
        return imageCashbook;
    }

    public void setImageCashbook(String imageCashbook) {
        this.imageCashbook = imageCashbook;
    }

    public String getImagePassbook() {
        return imagePassbook;
    }

    public void setImagePassbook(String imagePassbook) {
        this.imagePassbook = imagePassbook;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

}
