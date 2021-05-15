package com.jslps.pgmisnew.presenter;

import com.jslps.pgmisnew.BrsReportActivity;
import com.jslps.pgmisnew.database.BrsReportModel;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.view.PgReceiptView;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.PgReceiptInteractor;

import com.jslps.pgmisnew.view.BrsReport;
import com.jslps.pgmisnew.database.Pgmisbrstranstbl;
import com.jslps.pgmisnew.interactor.BrsRepotInteractor;
import java.util.List;

public class BrsReportPresenter implements BrsRepotInteractor.pgBrsReport {

    private BrsRepotInteractor interactor;
    private BrsReport view;


    public BrsReportPresenter(BrsRepotInteractor pgBrsReportInteractor, BrsReport pgBrsReceiptView) {
        this.interactor = pgBrsReportInteractor;
        this.view = pgBrsReceiptView;
    }

    //================== get data from PgReceiptTranList ============
    public List<Pgmisbrstranstbl> getListBrsTranstableDateWise(String fromDate, String toDate, String pgcode) {
        return interactor.getPgmisbrstranstblList(fromDate,toDate,pgcode);
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

    public void clearDates() {
        view.clearDates();
    }

    public void showImage(String imageName){
        view.showImage(imageName);
    }


}
