package com.jslps.pgmisnew.view;

import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;

import java.util.List;

public interface ChequeLoanView {

    void getHeadList(List<TblMstPgPaymentReceipthead> list);

    void setSpinnerHead();

    void blankHead();

    void setOpenCalender();

    void blankDate();

    void blankamount();

    void dataSaved();

    void setRecyclerView();

    void setPgName();

    void clearForm();

    void blankPaymentmode();

    void editRecord(PgmisChequeLoantbl item);
}
