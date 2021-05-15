package com.jslps.pgmisnew.presenter;

import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PgCapitalSavetbl;
import com.jslps.pgmisnew.database.PgMemShipFeeSavetbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.interactor.ReceiptRepotInteractor;
//import com.jslps.pgmisnew.view.PaymentReceiptReport;
import com.jslps.pgmisnew.view.ReceiptReport;

import java.util.List;

public class ReceiptReportPresenter implements ReceiptRepotInteractor.paymentReceiptReport {

    private ReceiptRepotInteractor interactor;
    private ReceiptReport view;

    public ReceiptReportPresenter(ReceiptRepotInteractor interactor, ReceiptReport view) {
        this.interactor = interactor;
        this.view = view;
    }

    public void setPgName() {
        view.setPgName();
    }

    public void openCalender(String from) {
        view.openCalender(from);
    }

    public void selectAtLeastOneCalender() {
        view.selectAtLeastOneCalender();
    }


    public List<PgReceiptDisData> getReceiptAmountData(String pgcode) {
        return interactor.getPgReceiptList(this,pgcode);
    }

    //=================== PgPaymentTranstbl =======================
    public List<PgPaymentTranstbl> getListPaymentTranstableDateWise(String fromDate, String toDate,String pgcode) {
        return interactor.getPgPaymentTransList(fromDate,toDate,pgcode);
    }

    //================== get data from PgReceiptTranList ============
    public List<PgReceiptTranstbl> getListReceiptTranstableDateWise(String fromDate, String toDate, String pgcode) {
        return interactor.getPgReceiptTranList(fromDate,toDate,pgcode);
    }

    //================== get data from Itempurchasedbypgtbl ============
    public List<Itempurchasedbypgtbl> getItempurchasedbypgDateWise(String fromDate, String toDate, String pgcode) {
        return interactor.getItempurchasedbypgList(fromDate,toDate,pgcode);
    }

    //================== get data from Pgmemtbl ====================
    public List<PgmisLoantbl> gePgmisLoantblDateWise(String fromDate, String toDate, String pgcode) {
        return interactor.getPgmisLoanList(fromDate,toDate,pgcode);
    }
    //================== get data from Share capital  ============
    public List<PgCapitalSavetbl> getPgCapitalSavetblList(String fromDate, String toDate, String pgcode) {
        return interactor.getPgCapitalSavetblList(fromDate,toDate,pgcode);
    }

    //================== get data from member fee  ====================
    public List<PgMemShipFeeSavetbl> getPgMemShipFeeSavetblList(String fromDate, String toDate, String pgcode) {
        return interactor.getPgMemShipFeeSavetblList(fromDate,toDate,pgcode);
    }


    public void getReport() {
        view.getReport();
    }

    public void clearDates() {
        view.clearDates();
    }
}
