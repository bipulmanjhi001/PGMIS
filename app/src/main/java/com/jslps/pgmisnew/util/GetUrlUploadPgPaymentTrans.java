package com.jslps.pgmisnew.util;

public class GetUrlUploadPgPaymentTrans {
    private String domain;
    private String method;
    private String sData;


    public GetUrlUploadPgPaymentTrans(String domain, String method, String sData) {
        this.domain = domain;
        this.method = method;
        this.sData = sData;

    }

    public String getUrl(){
        String url;
        url = domain+"/"+method+"?sData="+sData;
        return url;
    }

}
