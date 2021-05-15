package com.jslps.pgmisnew.presenter;

import android.content.Context;

import com.jslps.pgmisnew.database.PgPaymentHeadModel;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.PgPaymentInteractor;
import com.jslps.pgmisnew.view.PgPaymentView;

import java.util.List;

public class PgPaymentPresenter implements PgPaymentInteractor.pgpaymentinteractor {
    private PgPaymentInteractor pgPaymentInteractor;
    private PgPaymentView pgPaymentView;
    private Context context;

    public PgPaymentPresenter (Context context) {
        this.context=context;
    }

    public PgPaymentPresenter(PgPaymentInteractor pgPaymentInteractor, PgPaymentView pgPaymentView) {
        this.pgPaymentInteractor = pgPaymentInteractor;
        this.pgPaymentView = pgPaymentView;
    }

    public void getHeadList(){
        pgPaymentInteractor.getHeadList(this);
    }

    @Override
    public void getHeadList(List<TblMstPgPaymentReceipthead> list) {
        pgPaymentView.getHeadList(list);
    }

    @Override
    public void dataSaved() {
        pgPaymentView.dataSaved();
    }

    @Override
    public void dataEdited() {
        pgPaymentView.dataEdited();
    }

    public void setSpinnerHead(){
        pgPaymentView.setSpinnerHead();
    }

    public void openCalender(){
        pgPaymentView.setOpenCalender();
    }

    public void blankHead() {
        pgPaymentView.blankHead();
    }

    public void blankDate() {
        pgPaymentView.blankDate();
    }

    public void blankamount() {
        pgPaymentView.blankamount();
    }

    public String[] getUserDetails() {
        return pgPaymentInteractor.getUserDetails(this);
    }

    public void saveData(String budget_code, String head_name, String date, String amount,
                         String remark, String pgCode, String username,
                         String userid, String isexported,String paymentmode,String qty,String payment_unit,String BMID) {
        pgPaymentInteractor.saveData(this,budget_code,head_name,date,amount,remark,pgCode,username,
                userid,isexported,paymentmode,qty,payment_unit,BMID);
    }


    public List<PgPaymentTranstbl> getPgPaymentTranstblList(String pgCodeSelected) {
        return  pgPaymentInteractor.getPgPaymentTransList(this,pgCodeSelected);
    }

    public void setRecyclerView() {
        pgPaymentView.setRecyclerView();
    }

    public void setPgName() {
        pgPaymentView.setPgName();
    }

    public void clearForm() {
        pgPaymentView.clearForm();
    }

    public void editRecord(PgPaymentTranstbl item) {
        pgPaymentView.editRecord(item);
    }

    public void saveEditedData(String budget_code, String head_name, String date, String amount,
                               String remark, String pgCode, String username,
                               String userid, String isexported,
                               String selectedPaymentMode,String qty,String selectedPaymentUnit,String BMID,
                               PgPaymentTranstbl pgPaymentSelectedItem) {

        pgPaymentInteractor.saveEditedData(this,budget_code,head_name,date,amount,remark,
                pgCode,username,userid,isexported,
                selectedPaymentMode,qty,selectedPaymentUnit,BMID,
                pgPaymentSelectedItem);
    }

    public void blankPaymentMode() {
        pgPaymentView. blankPaymentMode();
    }

    public void blankquantity() {
        pgPaymentView.blankquantity();
    }

    public void blankunit() {
        pgPaymentView.blankunit();
    }
}
