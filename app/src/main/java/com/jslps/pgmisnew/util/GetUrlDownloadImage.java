package com.jslps.pgmisnew.util;

public class GetUrlDownloadImage {
    private String domain;
    private String method;
    private String DocumentName;

    public GetUrlDownloadImage(String domain, String method, String documentName) {
        this.domain = domain;
        this.method = method;
        DocumentName = documentName;
    }

    public String getUrl() {
        String url;
        url = domain + "/" + method + "?DocumentName=" + DocumentName;
        return url;
    }

}