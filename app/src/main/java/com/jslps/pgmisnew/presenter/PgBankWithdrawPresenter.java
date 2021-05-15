package com.jslps.pgmisnew.presenter;

import android.content.Context;

import com.jslps.pgmisnew.BankWithdrawCashDeposit;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.interactor.PgBankWithdrawInteractor;
import com.jslps.pgmisnew.view.PgBankWithdrawView;
import java.util.List;

public class PgBankWithdrawPresenter implements PgBankWithdrawInteractor.Pgbankwithdrawinteractor {

    private PgBankWithdrawInteractor pgBankWithdrawInteractor;
    private PgBankWithdrawView pgBankWithdrawView;
    private Context context;

    public PgBankWithdrawPresenter (Context context) {
        this.context=context;
    }

    public PgBankWithdrawPresenter(PgBankWithdrawInteractor pgBankWithdrawInteractor, PgBankWithdrawView pgBankWithdrawView) {
        this.pgBankWithdrawInteractor = pgBankWithdrawInteractor;
        this.pgBankWithdrawView = pgBankWithdrawView;
    }

    @Override
    public void dataSaved() {
        pgBankWithdrawView.dataSaved();
    }

    public void openCalender(){
        pgBankWithdrawView.setOpenCalender();
    }

    public void blankDate() {
        pgBankWithdrawView.blankDate();
    }

    public void blankamount() {
        pgBankWithdrawView.blankamount();
    }

    public void blankPaymentmode() {
        pgBankWithdrawView.blankPaymentmode();
    }

    public String[] getUserDetails() {
        return pgBankWithdrawInteractor.getUserDetails(this);
    }

    public void saveData(String amount,String uuid,String PGCode,String username,String userid,String isexported,String ID,String head_name,String date) {

        pgBankWithdrawInteractor.saveData(this,amount,uuid,PGCode,username,userid,isexported,ID,head_name,date);
    }

    public List<PgBankwithdrawcashdeposit> getPgBankwithdrawcashdeposit(String pgCodeSelected) {
        return  pgBankWithdrawInteractor.getPgBankwithdrawcashdeposit(this,pgCodeSelected);
    }

    public void editRecord(PgBankwithdrawcashdeposit item) {
        pgBankWithdrawView.editRecord(item);
    }

    public void setRecyclerView() {
        pgBankWithdrawView.setRecyclerView();
    }

    public void setPgName() {
        pgBankWithdrawView.setPgName();
    }

    public void clearForm() {
        pgBankWithdrawView.clearForm();
    }

}
