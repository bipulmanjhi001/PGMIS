package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PgBankwithdrawcashdeposit  extends SugarRecord {
    private String Amount;
    private String UUID;
    private String PGCode;
    private String Createdby;
    private String Createdid;
    private String isexported;
    private String ID;
    private String Headname;
    private String Date;

    public PgBankwithdrawcashdeposit(){

    }

    public PgBankwithdrawcashdeposit(String amount, String uuid, String PGCode, String createdby, String createdid, String isexported, String ID, String headname, String date) {
        this.Amount = amount;
        this.UUID = uuid;
        this.PGCode = PGCode;
        this.Createdby = createdby;
        this.Createdid = createdid;
        this.isexported = isexported;
        this.ID = ID;
        this.Headname = headname;
        this.Date = date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getUuid() {
        return UUID;
    }

    public void setUuid(String uuid) {
        this.UUID = uuid;
    }

    public String getPGCode() {
        return PGCode;
    }

    public void setPGCode(String PGCode) {
        this.PGCode = PGCode;
    }

    public String getCreatedby() {
        return Createdby;
    }

    public void setCreatedby(String createdby) {
        Createdby = createdby;
    }

    public String getCreatedid() {
        return Createdid;
    }

    public void setCreatedid(String createdid) {
        Createdid = createdid;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHeadname() {
        return Headname;
    }

    public void setHeadname(String headname) {
        Headname = headname;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
