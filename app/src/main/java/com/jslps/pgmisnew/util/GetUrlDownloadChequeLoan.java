package com.jslps.pgmisnew.util;

public class GetUrlDownloadChequeLoan {
    private String domain;
    private String method;
    private String whr;
    private String flag;
    private String whr1;
    private String whr2;

    public GetUrlDownloadChequeLoan(String domain, String method, String whr, String flag, String whr1, String whr2) {
        this.domain = domain;
        this.method = method;
        this.whr = whr;
        this.flag = flag;
        this.whr1 = whr1;
        this.whr2 = whr2;
    }

    public String getUrl() {
        String url;
        url = domain + "/" + method + "?whr=" + whr + "&flag=" + flag + "&whr1=" + whr1 + "&whr2=" + whr2;
        return url;
    }
}
