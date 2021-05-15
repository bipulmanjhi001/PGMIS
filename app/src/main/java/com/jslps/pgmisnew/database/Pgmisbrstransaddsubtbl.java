package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class Pgmisbrstransaddsubtbl extends SugarRecord {
    private String uuid;
    private String particularcode;
    private String particularflag;
    private String amount;
    private String entrydate;
    private String isexported;
    private String pgcode;

    public Pgmisbrstransaddsubtbl() {
    }

    public Pgmisbrstransaddsubtbl(String uuid, String particularcode, String particularflag, String amount, String entrydate, String isexported,String pgcode) {
        this.uuid = uuid;
        this.particularcode = particularcode;
        this.particularflag = particularflag;
        this.amount = amount;
        this.entrydate = entrydate;
        this.isexported = isexported;
        this.pgcode = pgcode;
    }

    public String getPgcode() {
        return pgcode;
    }

    public void setPgcode(String pgcode) {
        this.pgcode = pgcode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParticularcode() {
        return particularcode;
    }

    public void setParticularcode(String particularcode) {
        this.particularcode = particularcode;
    }

    public String getParticularflag() {
        return particularflag;
    }

    public void setParticularflag(String particularflag) {
        this.particularflag = particularflag;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }
}
