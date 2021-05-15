package com.jslps.pgmisnew.presenter;

import android.content.Context;
import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.ChequeLoanInteractor;
import com.jslps.pgmisnew.view.ChequeLoanView;
import java.util.List;

public class ChequeLoanPresenter implements ChequeLoanInteractor.Chequeloaninteractor {

    private ChequeLoanInteractor chequeLoanInteractor;
    private ChequeLoanView chequeLoanView;
    private Context context;

    public ChequeLoanPresenter (Context context) {
        this.context=context;
    }

    public ChequeLoanPresenter(ChequeLoanInteractor chequeLoanInteractor, ChequeLoanView chequeLoanView) {
        this.chequeLoanInteractor = chequeLoanInteractor;
        this.chequeLoanView = chequeLoanView;
    }

    public void getHeadList(){
        chequeLoanInteractor.getHeadList(this);
    }

    @Override
    public void getHeadList(List<TblMstPgPaymentReceipthead> list) {
        chequeLoanView.getHeadList(list);
    }

    public void blankHead() {
        chequeLoanView.blankHead();
    }

    public void setSpinnerHead(){
        chequeLoanView.setSpinnerHead();
    }

    @Override
    public void dataSaved() {
        chequeLoanView.dataSaved();
    }

    public void openCalender(){
        chequeLoanView.setOpenCalender();
    }

    public void blankDate() {
        chequeLoanView.blankDate();
    }

    public void blankamount() {
        chequeLoanView.blankamount();
    }

    public void blankPaymentmode() {
        chequeLoanView.blankPaymentmode();
    }

    public String[] getUserDetails() {
        return chequeLoanInteractor.getUserDetails(this);
    }

    public void saveData(String uuid,String pgcode,String grpcode,String grpmemcode,String isexported,String entrydate,String entryby,String appliedforloan,String chequedate,String amount,String remark,String paymentmode) {
        chequeLoanInteractor.saveData(this,uuid, pgcode, grpcode, grpmemcode, isexported, entrydate, entryby, appliedforloan, chequedate, amount, remark, paymentmode);
    }

    public List<PgmisChequeLoantbl> getPgmischequeloan(String pgCodeSelected) {
        return  chequeLoanInteractor.getPgmischequeloan(this,pgCodeSelected);
    }

    public void editRecord(PgmisChequeLoantbl item) {
        chequeLoanView.editRecord(item);
    }

    public void setRecyclerView() {
        chequeLoanView.setRecyclerView();
    }

    public void setPgName() {
        chequeLoanView.setPgName();
    }

    public void clearForm() {
        chequeLoanView.clearForm();
    }
}

