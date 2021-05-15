package com.jslps.pgmisnew.database;

public class ReceiptAmountSumModel {
    private String budgetid;
    private String amount;
    private String headname;
    private String BMID;

    public String getBMID() {
        return BMID;
    }

    public void setBMID(String BMID) {
        this.BMID = BMID;
    }

    public String getBudgetid() {
        return budgetid;
    }

    public void setBudgetid(String budgetid) {
        this.budgetid = budgetid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }
}
