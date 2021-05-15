package com.jslps.pgmisnew.database;

public class PgSaveMemberShipFee {
    private String paymentdate;
    private String paymentmode;

    public PgSaveMemberShipFee() {

    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }
}
