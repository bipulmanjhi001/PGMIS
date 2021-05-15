package com.jslps.pgmisnew.database;

import com.orm.dsl.Unique;

public class PgMeetingCaders {

    @Unique
    private String Meetingid;
    private String Cadreid;
    private String Pgcode;
    private String Isxported;

    public PgMeetingCaders() {

    }

    public PgMeetingCaders(String meetingid, String cadreid, String pgcode, String isxported) {
        this.Meetingid = meetingid;
        this.Cadreid = cadreid;
        this.Pgcode = pgcode;
        this.Isxported = isxported;
    }

    public String getMeetingid() {
        return Meetingid;
    }

    public void setMeetingid(String meetingid) {
        Meetingid = meetingid;
    }

    public String getCadreid() {
        return Cadreid;
    }

    public void setCadreid(String cadreid) {
        Cadreid = cadreid;
    }

    public String getPgcode() {
        return Pgcode;
    }

    public void setPgcode(String pgcode) {
        Pgcode = pgcode;
    }

    public String getIsxported() {
        return Isxported;
    }

    public void setIsxported(String isxported) {
        Isxported = isxported;
    }
}
