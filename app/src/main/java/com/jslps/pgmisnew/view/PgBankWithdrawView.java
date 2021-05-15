package com.jslps.pgmisnew.view;

import com.jslps.pgmisnew.BankWithdrawCashDeposit;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;

public interface PgBankWithdrawView {

    void setOpenCalender();

    void blankDate();

    void blankamount();

    void dataSaved();

    void setRecyclerView();

    void setPgName();

    void clearForm();

    void blankPaymentmode();

    void editRecord(PgBankwithdrawcashdeposit item);
}
