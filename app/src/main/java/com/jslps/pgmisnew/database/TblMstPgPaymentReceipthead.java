package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class TblMstPgPaymentReceipthead extends SugarRecord {

    private String BMID;
    private String budgetcode;
    private String budgetid;
    private String budgettype;
    private String headname;
    private String flag;
    private String showin;

    public TblMstPgPaymentReceipthead() {

    }

    public TblMstPgPaymentReceipthead(String BMID, String budgetcode, String budgetid, String budgettype, String headname, String flag, String showin) {
        this.BMID = BMID;
        this.budgetcode = budgetcode;
        this.budgetid = budgetid;
        this.budgettype = budgettype;
        this.headname = headname;
        this.flag = flag;
        this.showin = showin;
    }

    public String getBMID() {
        return BMID;
    }

    public void setBMID(String BMID) {
        this.BMID = BMID;
    }

    public String getBudgetcode() {
        return budgetcode;
    }

    public void setBudgetcode(String budgetcode) {
        this.budgetcode = budgetcode;
    }

    public String getBudgetid() {
        return budgetid;
    }

    public void setBudgetid(String budgetid) {
        this.budgetid = budgetid;
    }

    public String getBudgettype() {
        return budgettype;
    }

    public void setBudgettype(String budgettype) {
        this.budgettype = budgettype;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getShowin() {
        return showin;
    }

    public void setShowin(String showin) {
        this.showin = showin;
    }

    @Override
    public String toString() {
        return headname;
    }
}
