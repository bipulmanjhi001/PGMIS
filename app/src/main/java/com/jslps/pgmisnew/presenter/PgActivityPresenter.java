package com.jslps.pgmisnew.presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jslps.pgmisnew.database.PgActivityModel;
import com.jslps.pgmisnew.database.Pgtbl;
import com.jslps.pgmisnew.interactor.PgActivityInteractor;
import com.jslps.pgmisnew.view.PgActivityView;
import java.util.List;

public class PgActivityPresenter implements PgActivityInteractor.pgActivityInteractor {

    private PgActivityView pgActivityView;
    private PgActivityInteractor pgActivityInteractor;

    public PgActivityPresenter(PgActivityView pgActivityView, PgActivityInteractor pgActivityInteractor) {
        this.pgActivityView = pgActivityView;
        this.pgActivityInteractor = pgActivityInteractor;
    }

    public void getList(){
        pgActivityInteractor.getPgActivityList(this);
    }

    public void getSpinnerList(){
        pgActivityInteractor.getPgList(this);
    }

    @Override
    public void getPgActivityList(List<PgActivityModel> list) {
        pgActivityView.setPgActivityList(list);
    }

    @Override
    public void getPgList(List<Pgtbl> list) {
        pgActivityView.setPgSpinner(list);
    }

    public void setRecyclerView(){
        pgActivityView.setRecyclerView();
    }

    public void setZoomIn(){
        pgActivityView.setZoomIn();
    }

    public void setViewAdapter(TextView text1, TextView text2, ImageView icon1, ImageView icon2, ConstraintLayout layout1, ConstraintLayout layout2, int adapterPostion, View viewLayout){
        pgActivityView.setViewAdapter(text1,text2,icon1,icon2,layout1,layout2,adapterPostion,viewLayout);
    }

    public void uploadButtonHide(){
        pgActivityView.uploadHide();
    }

    public void uploadButtonUnhide(){
        pgActivityView.uploadUnhide();
    }

    public void getPgMemberstblandShgMemrtbl(){
        pgActivityView.pgMemsShgMems();
    }

    public void callUploadApi(String sData, String sData1){
        pgActivityView.callUploadApi(sData,sData1);
    }

    public void callUploadApiMeeting(String json3){
        pgActivityView.callUploadApiMeeting(json3);
    }

    public void callUploadPgPaymentTrans(String json4) {
        pgActivityView.callUploadPgPaymentTrans(json4);
    }

    public void callUploadApiShareCapital(String json5){
        pgActivityView.callUploadApiShareCapital(json5);
    }

    public void callUploadApiBRS(String json6){
        pgActivityView.callUploadApiBRS(json6);
    }

    public void callUploadPgMemberFee(String json7) {
        pgActivityView.callUploadPgMemberFee(json7);
    }

    public void callUploadPgbrstransaddsub(String json8) {
        pgActivityView.callUploadPgbrstransaddsub(json8);
    }

    public void callUploadPhInput(String json18) {
        pgActivityView.callUploadPhInput(json18);
    }

    public void callUploadPgReceiptTrans(String json10) {
        pgActivityView.callUploadPgReceiptTrans(json10);
    }

    public void callUploadItempurchasedbypg(String json11) {
        pgActivityView.callUploadItempurchasedbypg(json11);
    }

    public void callUploadPgmisBatchLoan(String json12) {
        pgActivityView.callUploadPgmisBatchLoan(json12);
    }

    public void callUploadPgmisloanrepayment(String json13) {
        pgActivityView.callUploadPgmisloanrepayment(json13);
    }

    public void callUploadPgmisBankwithdrawcashdeposit(String json15) {
        pgActivityView.callUploadPgmisBankwithdrawcashdeposit(json15);
    }

    public void callUploadPgmisChequeLoan(String json16){
        pgActivityView.callUploadPgmisChequeLoan(json16);
    }

    public void callUploadCadersList(String json17) {
        pgActivityView.callUploadCadersList(json17);
    }


    public void callDownloadWebServicesMeetingtbl(){
        pgActivityView.callDownloadWebServicesMeetingtbl();
    }

    public void callDownloadWebservicePaymentReceiptDis() {
        pgActivityView.callDownloadWebservicePaymentReceiptDis();
    }

    public void callDownloadWebservicePgMis() {
        pgActivityView.callDownloadWebservicePgMis();
    }

    public void callDownloadPaymentTranst(){
        pgActivityView.callDownloadPaymentTranst();
    }

    public void callDownloadCapitalSave(){
        pgActivityView.callDownloadCapitalSave();
    }

    public void callDownloadMemShipFeeSave(){
        pgActivityView.callDownloadMemShipFeeSave();
    }

    public void callDownloadmisbrstrans(){
        pgActivityView.callDownloadmisbrstrans();
    }

    public void callDownloadLoadbrstransaddsub(){
        pgActivityView.callDownloadLoadbrstransaddsub();
    }

    public void callDownloadLoan(){
        pgActivityView.callDownloadLoan();
    }

    public void callDownloadReceiptTrans(){
        pgActivityView.callDownloadReceiptTrans();
    }

    public void callDownloadItempurchasedbypg(){
        pgActivityView.callDownloadItempurchasedbypg();
    }

    public void callDownloadBatchLoan(){
        pgActivityView.callDownloadBatchLoan();
    }

    public void callDownloadloanrepayment(){
        pgActivityView.callDownloadloanrepayment();
    }

    public void callDownloadBRSImage(){
        pgActivityView.callDownloadBRSImage();
    }

    public void callDownloadPurchaseItems(){
        pgActivityView.callDownloadPurchaseItems();
    }

    public void callDownloadBankbankwithdrawcashdepositst(){
        pgActivityView.callDownloadBankbankwithdrawcashdepositst();
    }

    public void callDownloadChequeLoan(){
        pgActivityView.callDownloadChequeLoan();
    }

    public void callDownloadCaders(){
        pgActivityView.callDownloadCaders();
    }

    public void callDownloadCadersList(){
        pgActivityView.callDownloadCadersList();
    }
}
