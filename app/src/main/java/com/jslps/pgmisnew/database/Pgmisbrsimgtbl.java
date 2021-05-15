package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class Pgmisbrsimgtbl extends SugarRecord {
    private String isexported;
    private String fileName;
    private String filebytes;
    private String sUID;

    public Pgmisbrsimgtbl() {
    }

    public Pgmisbrsimgtbl(String isexported, String fileName, String filebytes, String sUID) {
        this.isexported = isexported;
        this.fileName = fileName;
        this.filebytes = filebytes;
        this.sUID = sUID;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilebytes() {
        return filebytes;
    }

    public void setFilebytes(String filebytes) {
        this.filebytes = filebytes;
    }

    public String getsUID() {
        return sUID;
    }

    public void setsUID(String sUID) {
        this.sUID = sUID;
    }
}
