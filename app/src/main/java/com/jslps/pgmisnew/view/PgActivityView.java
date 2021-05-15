package com.jslps.pgmisnew.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jslps.pgmisnew.database.PgActivityModel;
import com.jslps.pgmisnew.database.Pgtbl;

import java.util.List;

public interface PgActivityView {

    void setPgActivityList(List<PgActivityModel> list);

    void setRecyclerView();

    void setZoomIn();

    void setViewAdapter(TextView text1, TextView text2, ImageView icon1, ImageView icon2, ConstraintLayout layout1, ConstraintLayout layout2, int adapterPostion, View viewLayout);

    void setPgSpinner(List<Pgtbl> list);

    void uploadHide();

    void uploadUnhide();

    void pgMemsShgMems();

    void callUploadApi(String sData,String sData1);

    void callUploadApiMeeting(String sData);

    void callUploadApiShareCapital(String json5);

    void callDownloadWebServicesMeetingtbl();

    void callUploadApiBRS(String json6);

    void callDownloadWebservicePaymentReceiptDis();

    void callDownloadWebservicePgMis();

    void callDownloadPaymentTranst();

    void callDownloadCapitalSave();

    void callDownloadMemShipFeeSave();

    void callDownloadmisbrstrans();

    void callDownloadLoadbrstransaddsub();

    void callDownloadLoan();

    void callDownloadReceiptTrans();

    void callDownloadItempurchasedbypg();

    void callDownloadBatchLoan();

    void callDownloadloanrepayment();

    void callDownloadBRSImage();

    void callDownloadPurchaseItems();

    void callDownloadBankbankwithdrawcashdepositst();

    void callDownloadChequeLoan();

    void callDownloadCaders();

    void callDownloadCadersList();

    void callUploadPgPaymentTrans(String json4);

    void callUploadPgMemberFee(String json7);

    void callUploadPgbrstransaddsub(String json8);

    void callUploadPhInput(String json18);

    void callUploadPgReceiptTrans(String json10);

    void callUploadItempurchasedbypg(String json11);

    void callUploadPgmisBatchLoan(String json12);

    void callUploadPgmisloanrepayment(String json13);

    void callUploadPgmisBankwithdrawcashdeposit(String json15);

    void callUploadPgmisChequeLoan(String json16);

    void callUploadCadersList(String json17);

}
