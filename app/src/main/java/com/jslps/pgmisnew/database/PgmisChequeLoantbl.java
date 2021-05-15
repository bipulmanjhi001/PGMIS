package com.jslps.pgmisnew.database;

import com.orm.dsl.Unique;

public class PgmisChequeLoan {

    @Unique
    private String uuid;
    private String pgcode;
    private String grpcode;
    private String grpmemcode;
    private String isexported;
    private String entrydate;
    private String entryby;
    private String appliedforloan;
    private String chequedate;
    private String amount;
    private String remark;
    private String paymentmode;
    private String BMID;

    public PgmisChequeLoan() {

    }

    public PgmisChequeLoan(String uuid, String pgcode, String grpcode, String grpmemcode, String isexported, String entrydate, String entryby, String appliedforloan, String chequedate, String amount, String remark, String paymentmode, String BMID) {
        this.uuid = uuid;
        this.pgcode = pgcode;
        this.grpcode = grpcode;
        this.grpmemcode = grpmemcode;
        this.isexported = isexported;
        this.entrydate = entrydate;
        this.entryby = entryby;
        this.appliedforloan = appliedforloan;
        this.chequedate = chequedate;
        this.amount = amount;
        this.remark = remark;
        this.paymentmode = paymentmode;
        this.BMID = BMID;
    }
}
