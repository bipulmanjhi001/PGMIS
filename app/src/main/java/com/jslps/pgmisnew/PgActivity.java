package com.jslps.pgmisnew;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jslps.pgmisnew.adapter.PgActivityAdapter;
import com.jslps.pgmisnew.database.CadreTypeMaster;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PgActivityModel;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PhCollectingInputnewtbl;
import com.jslps.pgmisnew.database.PhCollectingInputtbl;
import com.jslps.pgmisnew.database.tblPgMeetingCaders;
import com.jslps.pgmisnew.database.Pgcapitalsavetbl;
import com.jslps.pgmisnew.database.PgMeetingtbl;
import com.jslps.pgmisnew.database.Pgmemshipfeesavetbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.database.PgmisBatchLoantbl;
import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.database.Pgmisbrsimgtbl;
import com.jslps.pgmisnew.database.Pgmisbrstransaddsubtbl;
import com.jslps.pgmisnew.database.Pgmisbrstranstbl;
import com.jslps.pgmisnew.database.Pgmisloanrepaymenttabl;
import com.jslps.pgmisnew.database.Pgmisstockmsttbl;
import com.jslps.pgmisnew.database.Pgtbl;
import com.jslps.pgmisnew.database.Shgmemberslocallyaddedtbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.PgActivityInteractor;
import com.jslps.pgmisnew.presenter.PgActivityPresenter;
import com.jslps.pgmisnew.util.AppConstant;
import com.jslps.pgmisnew.util.CheckConnectivity;
import com.jslps.pgmisnew.util.GetUrlDownloadBankCashDraw;
import com.jslps.pgmisnew.util.GetUrlDownloadBatchLoan;
import com.jslps.pgmisnew.util.GetUrlDownloadCapitalSave;
import com.jslps.pgmisnew.util.GetUrlDownloadChequeLoan;
import com.jslps.pgmisnew.util.GetUrlDownloadItempurchasedbypg;
import com.jslps.pgmisnew.util.GetUrlDownloadLoan;
import com.jslps.pgmisnew.util.GetUrlDownloadMeeting;
import com.jslps.pgmisnew.util.GetUrlDownloadMemberFee;
import com.jslps.pgmisnew.util.GetUrlDownloadPaymentMIs;
import com.jslps.pgmisnew.util.GetUrlDownloadPaymentReceiptDis;
import com.jslps.pgmisnew.util.GetUrlDownloadPaymentTranst;
import com.jslps.pgmisnew.util.GetUrlDownloadPurchaseItem;
import com.jslps.pgmisnew.util.GetUrlDownloadReceiptTrans;
import com.jslps.pgmisnew.util.GetUrlDownloadbrsload;
import com.jslps.pgmisnew.util.GetUrlDownloadbrstransaddsub;
import com.jslps.pgmisnew.util.GetUrlDownloadloanrepayment;
import com.jslps.pgmisnew.util.GetUrlUploadSHGANDPG;
import com.jslps.pgmisnew.util.ManualJsonConvert;
import com.jslps.pgmisnew.util.VolleyString;
import com.jslps.pgmisnew.view.PgActivityView;
import com.jslps.pgmisnew.view.PhCollectingInputView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class PgActivity extends AppCompatActivity implements PgActivityView, VolleyString.VolleyListner {

    @BindView(R.id.spinner)
    SearchableSpinner spinner;
    @BindView(R.id.recyler_list)
    RecyclerView recylerView;
    @BindView(R.id.imageView2)
    ImageView imgHeaderLogo;
    @BindView(R.id.upload)
    ImageView upload;
    /*Defining objects*/
    PgActivityPresenter presenter;
    PgActivityAdapter aAdapter;
    Dialog myDialog;
    int i;

    /*Class Globals*/
    List<PgActivityModel> listPgActivity;
    public static String pgCodeSelected = "";
    public static String pgNameSelected = "";
    public static List<Pgmemtbl> pgmemtblList;
    public static List<Shgmemberslocallyaddedtbl> shgmemberslocallyaddedtblList;
    public static List<PgMeetingtbl> pgMeetingtblList;
    public static List<PgPaymentTranstbl> pgPaymentTranstblList;
    public static List<Pgcapitalsavetbl> pgcapitalsavetbls;
    public static List<Pgmisbrstranstbl> pgmisbrstranstbls;
    public static List<Pgmemshipfeesavetbl> pgmemshipfeesavetbls;
    public static List<Pgmisbrstransaddsubtbl> pgmisbrstransaddsubtbls;
    public static List<PgmisLoantbl> pgmisLoantbls;
    public static List<PgReceiptTranstbl> pgReceiptTranstbls;
    public static List<Itempurchasedbypgtbl> itempurchasedbypgtbls;
    public static List<PgmisBatchLoantbl> pgmisBatchLoantbls;
    public static List<Pgmisloanrepaymenttabl> pgmisloanrepaymenttabls;
    public static List<Pgmisbrsimgtbl> pgmisbrsimgtbls;
    public static List<PgBankwithdrawcashdeposit> pgBankwithdrawcashdeposits;
    public static List<PgmisChequeLoantbl> pgmisChequeLoantbls;
    public static List<PhCollectingInputnewtbl> phCollectingInputtbls;

    public static List<CadreTypeMaster> cadreTypeMasters;
    public static List<tblPgMeetingCaders> tblPgMeetingCaders;

    ProgressDialog progress;
    Button btnFollow;

    String when,headname,headnamehindi,budgetcode;
    String json1="",json2="",json3="",json4="",
            json5="",json6="",json7="",
            json8="",json9="",json10="",
            json11="",json12="",json13="",json18="",
            json14="",json15="",json16="",json17="";

    /*Warning message*/
    public static final String WarningSave = "SaveWarning" ;
    public static final String Status = "false";
    SharedPreferences warningpref;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        myDialog=new Dialog(PgActivity.this);
        init();

    }

    private void init() {
        /*initialiazation*/
        presenter = new PgActivityPresenter(this, new PgActivityInteractor());
        /*calling presenter methods*/
        presenter.getList();
        presenter.setRecyclerView();
        presenter.setZoomIn();
        presenter.getSpinnerList();
        presenter.getPgMemberstblandShgMemrtbl();

        //getting valued passed from Login Activity
        Intent intent = getIntent();
        when = intent.getStringExtra("when");
        warningpref = getSharedPreferences(WarningSave, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = warningpref.edit();
        editor.putString(Status, "true");
        editor.apply();

        if(when.equals("first")){
            //calling download websevices here
            DialogShowDownload();
            presenter.callDownloadWebServicesMeetingtbl();
            presenter.callDownloadWebservicePaymentReceiptDis();
            presenter.callDownloadWebservicePgMis();
            presenter.callDownloadPaymentTranst();
            presenter.callDownloadCapitalSave();
            presenter.callDownloadMemShipFeeSave();
            presenter.callDownloadmisbrstrans();
            presenter.callDownloadLoadbrstransaddsub();
            //presenter.callDownloadLoan();
            presenter.callDownloadReceiptTrans();
            presenter.callDownloadItempurchasedbypg();
            presenter.callDownloadBatchLoan();
            presenter.callDownloadloanrepayment();
            presenter.callDownloadPurchaseItems();
            presenter.callDownloadBankbankwithdrawcashdepositst();
            presenter.callDownloadChequeLoan();
            presenter.callDownloadCaders();
            presenter.callDownloadCadersList();
           //   presenter.callDownloadBRSImage();
        }
    }

    @Override
    public void setPgActivityList(List<PgActivityModel> list) {
        listPgActivity = list;
    }

    @Override
    public void setRecyclerView() {
        aAdapter = new PgActivityAdapter(presenter, listPgActivity);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerView.setLayoutManager(verticalLayoutmanager);
        recylerView.setAdapter(aAdapter);
    }

    @Override
    public void setZoomIn() {
        Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        imgHeaderLogo.startAnimation(zoom);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void setViewAdapter(TextView text1, TextView text2, ImageView icon1, ImageView icon2, ConstraintLayout layout1, ConstraintLayout layout2, int adapterPostion, View viewLayout) {
        PgActivityModel item = listPgActivity.get(adapterPostion);
        if (item.getId1() == 1) {
            viewLayout.setVisibility(View.VISIBLE);
        } else {
            viewLayout.setVisibility(View.GONE);
        }
        text1.setText(item.getName1());
        text2.setText(item.getName2());
        icon1.setImageResource(item.getImageIcon1());
        icon2.setImageResource(item.getImageIcon2());

      /*  if(item.getId2()==14){
            layout2.setVisibility(View.GONE);
        }*/

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_layout);
                icon1.startAnimation(zoom);
                zoom.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (item.getId1() == 1) {
                            Intent intent = new Intent(PgActivity.this, MemberDetailsActivity.class);
                            startActivity(intent);
                        } else if (item.getId1() == 3) {
                            Intent intent = new Intent(PgActivity.this, ShareCapitalActivity.class);
                            startActivity(intent);
                        } else if(item.getId1() == 5){
                            Intent intent = new Intent(PgActivity.this, PgpaymentActivity.class);
                            startActivity(intent);
                        } else if(item.getId1() == 7){
                            Intent intent = new Intent(PgActivity.this, StockPurchase.class);
                            startActivity(intent);
                        }  else if(item.getId1() == 9){
                            Intent intent = new Intent(PgActivity.this, LoanMembersDetail.class);
                            intent.putExtra("fromactivity","repayment");
                            startActivity(intent);
                        } else if(item.getId1() == 11){
                            Intent intent = new Intent(PgActivity.this, BankWithdrawCashDeposit.class);
                            startActivity(intent);
                        }else if(item.getId1() == 13){
                            Intent intent = new Intent(PgActivity.this, BrsReportActivity.class);
                            startActivity(intent);
                        }else if(item.getId1() == 15){
                            Intent intent = new Intent(PgActivity.this, StockReport.class);
                            startActivity(intent);
                        }else if(item.getId1() == 17){
                            Intent intent = new Intent(PgActivity.this, PhCollectingOutputMarketing.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(PgActivity.this, Test.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_layout);
                icon2.startAnimation(zoom);
                zoom.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (item.getId2() == 2) {
                            Intent intent = new Intent(PgActivity.this, MemberShipFeeActivity.class);
                            startActivity(intent);
                        } else if (item.getId2() == 4) {
                            Intent intent = new Intent(PgActivity.this, MeetingDetailsOfPg.class);
                            startActivity(intent);
                        }else if (item.getId2() == 6) {
                            Intent intent = new Intent(PgActivity.this, PgReceiptActivity.class);
                            startActivity(intent);
                        }else if (item.getId2() == 8) {
                            //LoanMembersDetailNew
                            Intent intent = new Intent(PgActivity.this, LoanMembersDetailsNew.class);
                            intent.putExtra("fromactivity","loan");
                            startActivity(intent);
                        }else if (item.getId2() == 10) {
                            Intent intent = new Intent(PgActivity.this, BRSActivity.class);
                            startActivity(intent);
                        }else if(item.getId2() == 12){
                            Intent intent = new Intent(PgActivity.this, PgPaymentReceiptReportActivity.class);
                            startActivity(intent);
                        }else if(item.getId2() == 14){
                            Intent intent = new Intent(PgActivity.this, PgPaymentReceiptReport.class);
                            startActivity(intent);
                        }else if(item.getId2() == 16){
                            Intent intent = new Intent(PgActivity.this, PhCollectingInputMarketing.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(PgActivity.this, Test.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    @Override
    public void setPgSpinner(List<Pgtbl> list) {
        List<String> pgCodeList, pgNameList;
        ArrayAdapter<String> spinnerAdapter;
        spinner.setTitle(getString(R.string.select_pg));
        spinner.setPositiveButton(getString(R.string.close));
        if (list.size() > 0) {
            pgCodeList = new ArrayList<>();
            pgNameList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String pgCode = list.get(i).getPgcode();
                String pgName = list.get(i).getPgname();
                pgCodeList.add(pgCode);
                pgNameList.add(pgName);
            }
            spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pgNameList) {
            };
            spinner.setAdapter(spinnerAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        try {
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                        }catch (NullPointerException e){
                            e.printStackTrace();
                            ShowPopup();
                        }
                        ((TextView) parent.getChildAt(0)).setTextSize(20);
                        pgCodeSelected = pgCodeList.get(position);
                        pgNameSelected = pgNameList.get(position);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                        ShowPopup();
                    }
                    }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {
            ShowPopup();
        }
    }

    @Override
    public void uploadHide() {
        upload.setVisibility(View.GONE);
    }

    @Override
    public void uploadUnhide() {
        upload.setVisibility(View.VISIBLE);
    }

    @Override
    public void pgMemsShgMems() {
        pgmemtblList = Select.from(Pgmemtbl.class)
                .whereOr(Condition.prop("Isexported").eq(0),Condition.prop("Isupdated").eq(1))
                .list();

        shgmemberslocallyaddedtblList =Select.from(Shgmemberslocallyaddedtbl.class)
                .where(Condition.prop("Isexported").eq(0))
                .list();

        pgMeetingtblList = Select.from(PgMeetingtbl.class)
                .where(Condition.prop("Isxported").eq(0))
                .list();

        pgPaymentTranstblList = Select.from(PgPaymentTranstbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgcapitalsavetbls = Select.from(Pgcapitalsavetbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmisbrstranstbls = Select.from(Pgmisbrstranstbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmemshipfeesavetbls = Select.from(Pgmemshipfeesavetbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmisbrstransaddsubtbls = Select.from(Pgmisbrstransaddsubtbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmisLoantbls = Select.from(PgmisLoantbl.class)
                .where(Condition.prop("isexported").eq(0))
                .where(Condition.prop("appliedforloan").eq(1))
                .list();

        pgReceiptTranstbls= Select.from(PgReceiptTranstbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        itempurchasedbypgtbls= Select.from(Itempurchasedbypgtbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmisBatchLoantbls= Select.from(PgmisBatchLoantbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmisloanrepaymenttabls= Select.from(Pgmisloanrepaymenttabl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmisbrsimgtbls= Select.from(Pgmisbrsimgtbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgBankwithdrawcashdeposits= Select.from(PgBankwithdrawcashdeposit.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        pgmisChequeLoantbls= Select.from(PgmisChequeLoantbl.class)
                .where(Condition.prop("isexported").eq(0))
                .list();

        tblPgMeetingCaders = Select.from(tblPgMeetingCaders.class)
                .where(Condition.prop("Isxported").eq(0)).list();

        phCollectingInputtbls=Select.from(PhCollectingInputnewtbl.class)
                .where(Condition.prop("isexported").eq(0)).list();

       if(pgmemtblList.size()>0){
            presenter.uploadButtonUnhide();
        }

       else if(shgmemberslocallyaddedtblList.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(pgMeetingtblList.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(pgcapitalsavetbls.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(pgPaymentTranstblList.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(pgmemshipfeesavetbls.size()>0){
            presenter.uploadButtonUnhide();
        }
       else if(pgmisbrstranstbls.size()>0){
           presenter.uploadButtonUnhide();
       }
      else if(pgmisbrstransaddsubtbls.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(itempurchasedbypgtbls.size()>0){
            presenter.uploadButtonUnhide();
        }
       else if(pgReceiptTranstbls.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(pgmisBatchLoantbls.size()>0){
            presenter.uploadButtonUnhide();
        }
       else if(pgmisloanrepaymenttabls.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(pgBankwithdrawcashdeposits.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(pgmisChequeLoantbls.size()>0){
           presenter.uploadButtonUnhide();
       }
       else if(tblPgMeetingCaders.size()>0){
          presenter.uploadButtonUnhide();
       }
       else if(phCollectingInputtbls.size()>0){
           presenter.uploadButtonUnhide();
       }
       else{
           upload.setVisibility(View.GONE);
       }
    }

    @Override
    public void callUploadApi(String sData,String sData1) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new GetUrlUploadSHGANDPG(AppConstant.domain,
                    AppConstant.Upload_tblMstGroupMembers_Johar,sData,sData1).getUrl(),
                    AppConstant.tblMstGroupMembers_Johar,this).getString();
            mRequestQueue.add(mStringRequest);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadApiMeeting(String sData) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            StringRequest mStringRequest;
            RequestQueue mRequestQueue;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_PgMeetingtbl,
                    AppConstant.PgMeetingtbl, this).postmeeting(json3);
            mRequestQueue.add(mStringRequest);
        }
        else{
            new StyleableToast
                     .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadWebServicesMeetingtbl() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");

            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new GetUrlDownloadMeeting(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,"","",
                    AppConstant.meetingtblflag).getUrl(),
                    AppConstant.PgMeetingtblDownload,this).getString();

            mRequestQueue.add(mStringRequest);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                     .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadWebservicePaymentReceiptDis() {
        //herez
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new GetUrlDownloadPaymentReceiptDis(AppConstant.domain,
                    AppConstant.GetDisbursementList,pgCSV).getUrl(), AppConstant.PgPaymentReceiptDisDownload,this)
                    .getString();
            mRequestQueue.add(mStringRequest);
        } else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadWebservicePgMis() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new GetUrlDownloadPaymentMIs(AppConstant.domain,
                    AppConstant.DownLoadPGMIS,pgCSV, AppConstant.DownLoadPGMISflag).getUrl(),
                    AppConstant.PGMISDOWNLOADIdentifier,this).getString();
            mRequestQueue.add(mStringRequest);

        }else{
            new SweetAlertDialog(PgActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("कृपया फिर लॉग इन करें")
                    .setContentText("कोई डेटा नहीं मिला")
                    .setConfirmText("बाहर जाएं")
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.cancel();
                            clearAppData();
                        }
                    })
                    .show();

            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadPaymentTranst() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new GetUrlDownloadPaymentTranst(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadPaymentTranst,"","").getUrl(),
                    AppConstant.Downloadpgpaymentupload,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadCapitalSave() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");

            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new GetUrlDownloadCapitalSave(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadCapitalSave,"","").getUrl(),
                    AppConstant.DownloadPgCapitalSavetbl,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
     }

    @Override
    public void callDownloadMemShipFeeSave() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");

            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new
                    GetUrlDownloadMemberFee(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,
                    pgCSV,AppConstant.DownLoadMemShipFeeSave,"","").getUrl(),
                    AppConstant.DownloadPgMemShipFeeSavetbl,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadmisbrstrans() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");

            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadbrsload(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadbrstrans,"","").getUrl(),
                    AppConstant.DownloadPgmisbrstranstbl,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    //Download Second
    @Override
    public void callDownloadLoadbrstransaddsub() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");

            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadbrstransaddsub(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadbrstransaddsub,"","").getUrl(),
                    AppConstant.DownloadPgmisbrstransaddsubtbl,this).getString();

            mRequestQueue.add(mStringRequest);

        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadLoan() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){

        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadReceiptTrans() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new
                    GetUrlDownloadReceiptTrans(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadReceiptTrans,"","").getUrl(),
                    AppConstant.DownloadPgReceiptTranstbl,this).getString();

            mRequestQueue.add(mStringRequest);

        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadItempurchasedbypg() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(new
                    GetUrlDownloadItempurchasedbypg(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadItempurchasedbypg,"","").getUrl(),
                    AppConstant.DownloadItempurchasedbypgtbl,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadBatchLoan() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadBatchLoan(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadBatchLoan,"","").getUrl(),
                    AppConstant.DownloadPgmisBatchLoan,this).getString();

            mRequestQueue.add(mStringRequest);

        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadloanrepayment() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");

            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadloanrepayment(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownLoadloanrepayment,"","").getUrl(),
                    AppConstant.DownloadPgmisloanrepayment,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadBRSImage() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){

        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadPurchaseItems() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadPurchaseItem(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,"",
                    AppConstant.DownloadPurcaseItems,"","").getUrl(),
                    AppConstant.DownloadPgmispurchaseitems,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadBankbankwithdrawcashdepositst() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadBankCashDraw(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownloadPgmisbankwithdrawcashdeposit,"","").getUrl(),
                    AppConstant.DownloadPgmisbankwithdrawcashdepositstatus,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadChequeLoan() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadChequeLoan(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownloadPgmisChequeLoan,"","").getUrl(),
                    AppConstant.Downloadpgmichequeloan,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadCaders() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadPurchaseItem(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,"",
                    AppConstant.DownloadCadreType,"","").getUrl(),
                    AppConstant.DownloadCadreTypeMaster,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callDownloadCadersList() {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            List<Pgtbl> pgtblList = Pgtbl.listAll(Pgtbl.class);
            List<String> pgCodeList = new ArrayList<>();
            if(pgtblList.size()>0){
                for(int i=0;i<pgtblList.size();i++){
                    pgCodeList.add(pgtblList.get(i).getPgcode());
                }
            }
            String pgCSV =pgCodeList.toString().replace("[", "").replace("]", "")
                    .replace(", ", ",");
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);

            mStringRequest = new VolleyString(new
                    GetUrlDownloadPurchaseItem(AppConstant.domain,
                    AppConstant.Download_Johar_TabletData_Service,pgCSV,
                    AppConstant.DownloadCadersList,"","").getUrl(),
                    AppConstant.PgmisCadersList,this).getString();

            mRequestQueue.add(mStringRequest);
        }else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgPaymentTrans(String json4) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.UpLoad_PgPaymentTranst,
                    AppConstant.pgpaymentupload, this).postpaymenttrans(json4);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadApiShareCapital(String json5) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.UpLoad_PgCapitalSave,
                    AppConstant.PgCapitalSavetbl, this).postsharecapital(json5);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadApiBRS(String json6) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.UpLoad_Pgmisbrstrans,
                    AppConstant.Pgmisbrstranstbl, this).postbrscall(json6);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgMemberFee(String json7) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.UpLoad_PgMemShipFeeSave,
                    AppConstant.PgMemShipFeeSavetbl, this).postMembershipFee(json7);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgbrstransaddsub(String json8) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_Pgmisbrstransaddsub,
                    AppConstant.Pgmisbrstransaddsubtbl, this).postBrstransaddsub(json8);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPhInput(String json18) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_tblPhCollectingInput,
                    AppConstant.PhCollectingInput, this).postPhInput(json18);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgReceiptTrans(String json10) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_PgReceiptTrans,
                    AppConstant.PgReceiptTranstbl, this).postPgReceiptTrans(json10);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadItempurchasedbypg(String json11) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_Itempurchasedbypg,
                    AppConstant.Itempurchasedbypgtbl, this).postItempurchasedbypg(json11);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgmisBatchLoan(String json12) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_PgmisBatchLoan,
                    AppConstant.PgmisBatchLoan, this).postPgmisBatchLoan(json12);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgmisloanrepayment(String json13) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_Pgmisloanrepayment,
                    AppConstant.Pgmisloanrepayment, this).postPgmisloanrepayment(json13);
            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgmisBankwithdrawcashdeposit(String json15) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_Pgmisbankwithdrawcashdeposit,
                    AppConstant.Pgmisbankwithdrawcashdeposit, this).postPgmisBankwithdrawcashdeposit(json15);

            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadPgmisChequeLoan(String json16) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_Pgmischequebank,
                    AppConstant.PgmisChequeLoan, this).postPgmisChequeBank(json16);

            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void callUploadCadersList(String json17) {
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        if(checkConnectivity.CheckConnection(this)){
            RequestQueue mRequestQueue1;
            StringRequest mStringRequest1;
            mRequestQueue1 = Volley.newRequestQueue(this);
            mStringRequest1 = new VolleyString(AppConstant.domain+"/"+AppConstant.Upload_CadreList,
                    AppConstant.PgmisCadreList, this).postPgmisChequeBank(json17);

            mRequestQueue1.add(mStringRequest1);
        }
        else{
            new StyleableToast
                    .Builder(this)
                    .text(getString(R.string.internet_error))
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @OnClick(R.id.imageView2)
    public void onViewClicked() {

    }

    @SuppressLint("LongLogTag")
    @OnClick(R.id.upload)
    public void onViewClicked1() {
        upload.setVisibility(View.GONE);
        if(pgmemtblList.size()>0){
            json1 = new ManualJsonConvert("tblProducerGroupMembers",getApplicationContext()).ConvertJson();
        }

        if(shgmemberslocallyaddedtblList.size()>0){
            json2 = new ManualJsonConvert("tblMstGroupMembers_Johar",getApplicationContext()).ConvertJson();
        }

        if(pgMeetingtblList.size()>0){
            json3 = new ManualJsonConvert("PgMeetingtbl",getApplicationContext()).ConvertJson();
        }

        if(pgPaymentTranstblList.size()>0){
            json4 = new ManualJsonConvert("tblmst_PgPaymentTranst",getApplicationContext()).ConvertJson();
        }

       if(pgcapitalsavetbls.size()>0){
            json5 = new ManualJsonConvert("tblmst_PgCapitalSave",getApplicationContext()).ConvertJson();
        }

       if(pgmemshipfeesavetbls.size()>0){
           json7 = new ManualJsonConvert("tblmst_PgMemShipFeeSave",getApplicationContext()).ConvertJson();
       }

        if(pgReceiptTranstbls.size()>0){
            json10 = new ManualJsonConvert("tblmst_PgReceiptTrans",getApplicationContext()).ConvertJson();
        }

        if(itempurchasedbypgtbls.size()>0){
            json11 = new ManualJsonConvert("tblmst_Itempurchasedbypg",getApplicationContext()).ConvertJson();
        }

       /* if(pgmisLoantbls.size()>0){
            json9 = new ManualJsonConvert("tblmst_PgmisLoan",getApplicationContext()).ConvertJson();
        }
*/
        if(pgmisBatchLoantbls.size()>0){
            json12 = new ManualJsonConvert("tblmst_PgmisBatchLoan",getApplicationContext()).ConvertJson();
        }

        if(pgmisloanrepaymenttabls.size()>0){
            json13 = new ManualJsonConvert("tblmst_Pgmisloanrepayment",getApplicationContext()).ConvertJson();
        }

        if(pgmisbrstranstbls.size()>0){
            json6 = new ManualJsonConvert("tblmst_Pgmisbrstrans",getApplicationContext()).ConvertJson();
        }

        if(pgmisbrstransaddsubtbls.size()>0){
            json8 = new ManualJsonConvert("tblmst_Pgmisbrstransaddsub",getApplicationContext()).ConvertJson();
        }

        if(pgBankwithdrawcashdeposits.size()>0){
            json15 = new ManualJsonConvert("tblmst_PgBankwithdrawcashdeposit",getApplicationContext()).ConvertJson();
        }

        if(pgmisChequeLoantbls.size()>0){
            json16 = new ManualJsonConvert("tblmst_PgmisChequeLoan",getApplicationContext()).ConvertJson();
        }

        if(tblPgMeetingCaders.size()>0){
            json17 = new ManualJsonConvert("tblPgMeetingCaders",getApplicationContext()).ConvertJson();
        }

        if(phCollectingInputtbls.size()>0){
            json18 = new ManualJsonConvert("PhCollectingInputtbl",getApplicationContext()).ConvertJson();
        }

        DialogShow();

        if(pgmemtblList.size()>0||shgmemberslocallyaddedtblList.size()>0){
            presenter.callUploadApi(json2,json1);
        }

        if(pgMeetingtblList.size()>0){
            presenter.callUploadApiMeeting(json3);
        }

        if(pgPaymentTranstblList.size()>0){
            presenter.callUploadPgPaymentTrans(json4);
        }

        if(pgcapitalsavetbls.size()>0){
            presenter.callUploadApiShareCapital(json5);
        }

        if(pgmisbrstranstbls.size()>0){
            presenter.callUploadApiBRS(json6);
        }

        if(pgmisbrstransaddsubtbls.size()>0){
            presenter.callUploadPgbrstransaddsub(json8);
        }

        if(pgmemshipfeesavetbls.size()>0){
          presenter.callUploadPgMemberFee(json7);
        }

        /*if(pgmisLoantbls.size()>0){
            presenter.callUploadPgmisLoan(json9);
        }*/

        if(pgReceiptTranstbls.size()>0){
            presenter.callUploadPgReceiptTrans(json10);
        }

        if(itempurchasedbypgtbls.size()>0){
            presenter.callUploadItempurchasedbypg(json11);
        }

        if(pgmisBatchLoantbls.size()>0){
            presenter.callUploadPgmisBatchLoan(json12);
        }

        if(pgmisloanrepaymenttabls.size()>0){
            presenter.callUploadPgmisloanrepayment(json13);
        }

        if(pgBankwithdrawcashdeposits.size()>0){
            presenter.callUploadPgmisBankwithdrawcashdeposit(json15);
        }

        if(pgmisChequeLoantbls.size()>0){
            presenter.callUploadPgmisChequeLoan(json16);
        }

        if(tblPgMeetingCaders.size()>0){
            presenter.callUploadCadersList(json17);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getPgMemberstblandShgMemrtbl();
    }

    @Override
    public void onResponseSuccess(String tableIndentifier, String result) {
        if(tableIndentifier.equals(AppConstant.tblMstGroupMembers_Johar)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("Upload Failed Due to no Response from server for producer group table")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Table");
                     JSONObject object = jsonArray.getJSONObject(0);
                    String value = object.optString("RetValue");
                    if(value.equals("1")){
                        if(pgmemtblList.size()>0){
                            for(int i=0;i<pgmemtblList.size();i++){
                                pgmemtblList.get(i).setIsexported("1");
                                pgmemtblList.get(i).setIsupdated("0");
                                pgmemtblList.get(i).save();
                            }
                        }
                        if(shgmemberslocallyaddedtblList.size()>0){
                            for(int i=0;i<shgmemberslocallyaddedtblList.size();i++){
                                shgmemberslocallyaddedtblList.get(i).setIsexported("1");
                                shgmemberslocallyaddedtblList.get(i).save();
                            }
                        }
                        DialogClose();

                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }else{
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Upload Failed Response is Other than 1")
                                .iconStart(R.drawable.wrong_icon_white)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.PgMeetingtbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("Upload Failed Due to no Response from server for meeting table")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String data = jsonObject.optString("RetValue");
                    if(data.equals("1")){
                        if(pgMeetingtblList.size()>0){
                            for(int i=0;i<pgMeetingtblList.size();i++){
                                pgMeetingtblList.get(i).setIsxported("1");
                                pgMeetingtblList.get(i).save();
                            }
                        }
                        DialogClose();

                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }else{
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Upload Failed Response is Other than 1")
                                .iconStart(R.drawable.wrong_icon_white)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.PgMeetingtblDownload)){
            if(result.equals("[]")){

                new StyleableToast
                        .Builder(this)
                        .text("No data found for meeting table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<PgMeetingtbl>>(){}.getType();
                    List<PgMeetingtbl> list = new GsonBuilder().create().fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                       PgMeetingtbl data = new PgMeetingtbl(list.get(i).getMeetingid(),
                               list.get(i).getMeetingdate(),list.get(i).getNoofpeople(),
                               list.get(i).getCadres(),list.get(i).getPgcode(),"1");
                       data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.Downloadpgpaymentupload)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for PaymentTranst table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<PgPaymentTranstbl>>(){}.getType();
                    List<PgPaymentTranstbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        PgPaymentTranstbl data = new PgPaymentTranstbl(
                                list.get(i).getUuid(),
                                list.get(i).getBudgetcode(),
                                list.get(i).getHeadname(),
                                list.get(i).getDate(),
                                list.get(i).getAmount() + "",
                                list.get(i).getRemark(),
                                list.get(i).getPGCode(),
                                list.get(i).getCreatedby(),
                                list.get(i).getCreatedid(),
                                "1",
                                list.get(i).getPaymentmode(),
                                list.get(i).getQty(),
                                list.get(i).getPaymentunit(),
                                list.get(i).getBMID());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadPgCapitalSavetbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for CapitalSave table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();

            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<Pgcapitalsavetbl>>(){}.getType();
                    List<Pgcapitalsavetbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        Pgcapitalsavetbl data = new Pgcapitalsavetbl(
                                list.get(i).getPgcode(),
                                list.get(i).getGrpmemcode(),
                                list.get(i).getGrpcode(),
                                list.get(i).getMembername(),
                                list.get(i).getAmount() + "",
                                "1",
                                list.get(i).getUuid(),
                                list.get(i).getCreateddate(),
                                list.get(i).getPaymentdate(),
                                list.get(i).getPaymentmode());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadPgMemShipFeeSavetbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for MemShipFeeSave table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<Pgmemshipfeesavetbl>>(){}.getType();
                    List<Pgmemshipfeesavetbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        Pgmemshipfeesavetbl data = new Pgmemshipfeesavetbl(
                                list.get(i).getPgcode(),
                                list.get(i).getGrpmemcode(),
                                list.get(i).getGrpcode(),
                                list.get(i).getMembername(),
                                list.get(i).getAmount(),
                                "1",
                                list.get(i).getUid(),
                                list.get(i).getCreatedDate(),
                                list.get(i).getPaymentdate(),
                                list.get(i).getPaymentmode());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadPgmisbrstranstbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for Pgmisbrstrans table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<Pgmisbrstranstbl>>(){}.getType();
                    List<Pgmisbrstranstbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        Pgmisbrstranstbl data = new Pgmisbrstranstbl(
                                list.get(i).getUuid(),
                                list.get(i).getDate(),
                                list.get(i).getBalcashbook(),
                                list.get(i).getBalpassbook(),
                                list.get(i).getPassbooklastpageimg(),
                                list.get(i).getCashbooklastpageimg(),
                                "1",
                                list.get(i).getEntrydate(),
                                list.get(i).getPgcode());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(tableIndentifier.equals(AppConstant.DownloadPgmisbrstransaddsubtbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for BRSTransactionaddsub table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<Pgmisbrstransaddsubtbl>>(){}.getType();
                    List<Pgmisbrstransaddsubtbl> list = new GsonBuilder().
                            create().fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(
                                list.get(i).getUuid(),
                                list.get(i).getParticularcode(),
                                list.get(i).getParticularflag(),
                                list.get(i).getAmount() + "",
                                list.get(i).getEntrydate(),
                                "1",
                                list.get(i).getPgcode());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

       /* if(tableIndentifier.equals(AppConstant.DownloadPgmisLoantbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for Loan table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();

            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<PgmisLoantbl>>(){}.getType();
                    List<PgmisLoantbl> list = new GsonBuilder().create().fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        if(!list.get(i).getAppliedforloan().equals("1")) {
                            PgmisLoantbl data = new PgmisLoantbl(
                                    list.get(i).getUuid(),
                                    list.get(i).getPgcode(),
                                    list.get(i).getGrpcode(),
                                    list.get(i).getGrpmemcode(),
                                    list.get(i).getItemcode(),
                                    list.get(i).getItemname(),
                                    list.get(i).getRate(),
                                    list.get(i).getUnit(),
                                    "1",
                                    list.get(i).getEntrydate(),
                                    list.get(i).getEntryby(),
                                    list.get(i).getAppliedforloan(),
                                    list.get(i).getQuantity(),
                                    list.get(i).getPaymentdate(), "");

                            data.save();
                        }
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }
*/
        if(tableIndentifier.equals(AppConstant.DownloadPgReceiptTranstbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for ReceiptTrans table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<PgReceiptTranstbl>>(){}.getType();
                    List<PgReceiptTranstbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        PgReceiptTranstbl data = new PgReceiptTranstbl(
                                list.get(i).getUuid(),
                                list.get(i).getBudgetcode(),
                                list.get(i).getHeadname(),
                                list.get(i).getDate(),
                                list.get(i).getAmount() + "",
                                list.get(i).getRemark(),
                                list.get(i).getPgcode(),
                                list.get(i).getCreatedby(),
                                list.get(i).getCreatedid(),
                                "1",
                                list.get(i).getQuantity(),
                                list.get(i).getUnittype(),
                                list.get(i).getPaymentmode(),
                                list.get(i).getBMID());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadItempurchasedbypgtbl)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for Itempurchasedbypg table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<Itempurchasedbypgtbl>>(){}.getType();
                    List<Itempurchasedbypgtbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        Itempurchasedbypgtbl data = new Itempurchasedbypgtbl(
                                list.get(i).getUuid(),
                                list.get(i).getItemcode(),
                                list.get(i).getItemname(),
                                list.get(i).getUnit(),
                                list.get(i).getRate(),
                                list.get(i).getQuantity(),
                                list.get(i).getBudgetname(),
                                list.get(i).getBudgetcode(),
                                list.get(i).getPgcode(),
                                list.get(i).getEntrydate(),
                                "1",
                                list.get(i).getPaymentmode(),
                                list.get(i).getSelecteddate(),
                                list.get(i).getBMID());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadPgmisBatchLoan)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for BatchLoan table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<PgmisBatchLoantbl>>(){}.getType();
                    List<PgmisBatchLoantbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        PgmisBatchLoantbl data = new PgmisBatchLoantbl(
                                list.get(i).getLoanid(),
                                list.get(i).getItemuuids(),
                                list.get(i).getPgcode(),
                                list.get(i).getGrpcode(),
                                list.get(i).getGrpmemcode(),
                                list.get(i).getEntrydate(),
                                "1",
                                list.get(i).getAmount(),
                                list.get(i).getPaymentmode());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadPgmisloanrepayment)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for Loanrepayment table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            } else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<Pgmisloanrepaymenttabl>>(){}.getType();
                    List<Pgmisloanrepaymenttabl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);

                    for(int i=0;i<list.size();i++){
                        Pgmisloanrepaymenttabl data = new Pgmisloanrepaymenttabl(
                                list.get(i).getPgcode(),
                                list.get(i).getUuid(),
                                list.get(i).getLoanid(),
                                list.get(i).getEntrydate(),
                                list.get(i).getAmount(),
                                "1",
                                list.get(i).getSelectedPaymentMode(),
                                list.get(i).getPaymentdate());

                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(tableIndentifier.equals(AppConstant.DownloadPgmispurchaseitems)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for Purchase item table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
                DialogClose();
            } else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<Pgmisstockmsttbl>>(){}.getType();
                    List<Pgmisstockmsttbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);
          try {
              Pgmisstockmsttbl datainit = new Pgmisstockmsttbl("0", "आइटम का नाम चुनें", "0");
               datainit.save();

        for (int i = 0; i < list.size(); i++) {
             Pgmisstockmsttbl data = new Pgmisstockmsttbl(
                list.get(i).getItemcode(),
                list.get(i).getItemname(),
                list.get(i).getItemunit());

                data.save();
             }
         }catch (SQLException e){
           e.printStackTrace();
             }
              DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

       if(tableIndentifier.equals(AppConstant.PgPaymentReceiptDisDownload)){
            if(result.equals("[]")){
            /*    new StyleableToast
                        .Builder(this)
                        .text("No data found for PgPaymentReceiptDisDownload ")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
            */
            DialogClose();
            }else{
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String budget_code = jsonObject.optString("ActivityCode");
                        String budget_head = jsonObject.optString("Budget");
                        String BMID = jsonObject.optString("BMID");
                        String account_no = jsonObject.optString("AccountNumber");
                        String ekoshamount = jsonObject.optString("EKoshAmount");
                        String pgcode = jsonObject.optString("PGCode");
                        String approveddate = jsonObject.optString("ApprovedOn");
                        String[] list;
                        String day="";
                        String mon="";
                        String year="";
                        String newDate="";
                        try {
                            if (!approveddate.equals("")) {
                                list = approveddate.split("-");
                                day = list[0];
                                mon = list[1];
                                year = list[2];
                                newDate = year + "/" + mon + "/" + day;
                            }
                        }catch(ArrayIndexOutOfBoundsException e){
                            e.printStackTrace();
                        }
                        String budget_id = jsonObject.optString("ID");
                        PgReceiptDisData data = new PgReceiptDisData(budget_head,budget_code,account_no,ekoshamount,pgcode,newDate,budget_id,BMID);
                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.PGMISDOWNLOADIdentifier)){
            if(result.equals("[]")){
                DialogClose();
                ShowPopup();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Table");
                    //enter in first position
                    TblMstPgPaymentReceipthead datainit = new TblMstPgPaymentReceipthead("0","0","0","","मद का नाम चुने","","");
                    datainit.save();

                    for(int i=0 ;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String BMIDs = object.optString("BMID");
                        budgetcode= object.optString("BudgetCode").toString();
                        String budgetid= object.optString("ID").toString();
                        String budgettype= object.optString("BudgetType").toString();
                        String flag= object.optString("Flag").toString();
                        headname= object.optString("Disbursement").toString();
                        String showin= object.optString("ShowIn").toString();

                        TblMstPgPaymentReceipthead data = new TblMstPgPaymentReceipthead(BMIDs,budgetcode,budgetid,budgettype,headname,flag,showin);
                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                    ShowPopup();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadPgmisbankwithdrawcashdepositstatus)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for Bankwithdrawcashdeposit table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<PgBankwithdrawcashdeposit>>(){}.getType();
                    List<PgBankwithdrawcashdeposit> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);
                    try {
                        for (int i = 0; i < list.size(); i++) {
                            PgBankwithdrawcashdeposit data = new PgBankwithdrawcashdeposit(list.get(i).getAmount()+"",list.get(i).getUuid(),list.get(i).getPGCode(),list.get(i).getCreatedby(),list.get(i).getCreatedid(),"1","",list.get(i).getHeadname(),list.get(i).getDate());
                            data.save();
                        }
                   }catch (SQLException e){
                        e.printStackTrace();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.Downloadpgmichequeloan)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for PgmisChequeLoan table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<PgmisChequeLoantbl>>(){}.getType();
                    List<PgmisChequeLoantbl> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);
                    try {
                        for (int i = 0; i < list.size(); i++) {
                            PgmisChequeLoantbl data = new PgmisChequeLoantbl(
                                    list.get(i).getUuid(),
                                    list.get(i).getPgcode(),
                                    list.get(i).getGrpcode(),
                                    list.get(i).getGrpmemcode(),
                                    "1",
                                    list.get(i).getEntrydate(),
                                    list.get(i).getEntryby(),
                                    list.get(i).getAppliedforloan(),
                                    list.get(i).getChequedate(),
                                    list.get(i).getAmount(),
                                    list.get(i).getRemark(),
                                    list.get(i).getPaymentmode());

                            data.save();
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.DownloadCadreTypeMaster)){
            if(result.equals("[]")){
                DialogClose();
                ShowPopup();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");

                    for(int i=0 ;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String CadreTypeId = object.optString("CadreTypeId");
                        String CadreCbsId= object.optString("CadreCbsId").toString();
                        String Cadre_Designation= object.optString("Cadre_Designation").toString();
                        String Cadre_DesignationHin= object.optString("Cadre_DesignationHin").toString();

                        CadreTypeMaster data = new CadreTypeMaster(CadreTypeId, CadreCbsId, Cadre_Designation,Cadre_DesignationHin);
                        data.save();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                    ShowPopup();
                }
            }
        }

        if(tableIndentifier.equals(AppConstant.PgmisCadersList)){
            if(result.equals("[]")){
                new StyleableToast
                        .Builder(this)
                        .text("No data found for PgmisCadersList table download")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();

                DialogClose();
            }else{
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("Master");
                    Type listType = new TypeToken<ArrayList<tblPgMeetingCaders>>(){}.getType();
                    List<tblPgMeetingCaders> list = new GsonBuilder().create().
                            fromJson(jsonArray.toString(), listType);
                    try {
                        for (int i = 0; i < list.size(); i++) {
                            tblPgMeetingCaders data = new tblPgMeetingCaders(
                                    list.get(i).getMeetingid(),
                                    list.get(i).getCadreid(),
                                    list.get(i).getPgcode(),
                                    "1");

                            data.save();
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        if(tableIndentifier.equals(AppConstant.pgpaymentupload)){
            try {
                    JSONObject jsonObject = new JSONObject(result);
                    String data = jsonObject.optString("Data");
                    if(data.equals("1")){
                        if(pgPaymentTranstblList.size()>0){
                            for(int i=0;i<pgPaymentTranstblList.size();i++){
                                pgPaymentTranstblList.get(i).setIsexported("1");
                                pgPaymentTranstblList.get(i).save();
                            }
                            DialogClose();
                            new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                            presenter.getPgMemberstblandShgMemrtbl();
                       }
                    }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgpaymenttrans")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                    DialogClose();
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogClose();
                }
            }

        if(tableIndentifier.equals(AppConstant.PgCapitalSavetbl)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgcapitalsavetbls.size()>0){
                        for(int i = 0; i< pgcapitalsavetbls.size(); i++){
                            pgcapitalsavetbls.get(i).setIsexported("1");
                            pgcapitalsavetbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgCapitalSave")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.PgMemShipFeeSavetbl)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgmemshipfeesavetbls.size()>0){
                        for(int i = 0; i< pgmemshipfeesavetbls.size(); i++){
                            pgmemshipfeesavetbls.get(i).setIsexported("1");
                            pgmemshipfeesavetbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                } if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgMemShipFeeSavetbls")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.Pgmisbrstranstbl)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgmisbrstranstbls.size()>0){
                        for(int i=0;i<pgmisbrstranstbls.size();i++){
                            pgmisbrstranstbls.get(i).setIsexported("1");
                            pgmisbrstranstbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for Pgmisbrstranstbl")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();

            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.Pgmisbrstransaddsubtbl)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgmisbrstransaddsubtbls.size()>0){
                        for(int i=0;i<pgmisbrstransaddsubtbls.size();i++){
                            pgmisbrstransaddsubtbls.get(i).setIsexported("1");
                            pgmisbrstransaddsubtbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();
                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgmisbrstransaddsub")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        /*if(tableIndentifier.equals(AppConstant.PgmisLoantbl)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgmisLoantbls.size()>0){
                        for(int i=0;i<pgmisLoantbls.size();i++){
                            pgmisLoantbls.get(i).setIsexported("1");
                            pgmisLoantbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgmisLoantbls")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }
*/
        if(tableIndentifier.equals(AppConstant.PgReceiptTranstbl)) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if (data.equals("1")) {
                    if (pgReceiptTranstbls.size() > 0) {
                        for (int i = 0; i < pgReceiptTranstbls.size(); i++) {
                            pgReceiptTranstbls.get(i).setIsexported("1");
                            pgReceiptTranstbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgReceiptTranstbls")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
            }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.Itempurchasedbypgtbl)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(itempurchasedbypgtbls.size()>0){
                        for(int i=0;i<itempurchasedbypgtbls.size();i++){
                            itempurchasedbypgtbls.get(i).setIsexported("1");
                            itempurchasedbypgtbls.get(i).save();
                        }
                        DialogClose();

                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                    if (!data.equals("1")){
                        new StyleableToast
                                .Builder(this)
                                .text("Upload failed for itempurchasedbypg")
                                .iconStart(R.drawable.wrong_icon_white)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();
                    }
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.PgmisBatchLoan)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgmisBatchLoantbls.size()>0){
                        for(int i=0;i<pgmisBatchLoantbls.size();i++){
                            pgmisBatchLoantbls.get(i).setIsexported("1");
                            pgmisBatchLoantbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();
                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgmisBatchLoan")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }

                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.Pgmisloanrepayment)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgmisloanrepaymenttabls.size()>0){
                        for(int i=0;i<pgmisloanrepaymenttabls.size();i++){
                            pgmisloanrepaymenttabls.get(i).setIsexported("1");
                            pgmisloanrepaymenttabls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();
                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                } if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgmisloanrepayment")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.Pgmisbankwithdrawcashdeposit)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgBankwithdrawcashdeposits.size()>0){
                        for(int i=0;i<pgBankwithdrawcashdeposits.size();i++){
                            pgBankwithdrawcashdeposits.get(i).setIsexported("1");
                            pgBankwithdrawcashdeposits.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for Bankwithdrawcashdeposits")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.PgmisChequeLoan)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(pgmisChequeLoantbls.size()>0){
                        for(int i=0;i<pgmisChequeLoantbls.size();i++){
                            pgmisChequeLoantbls.get(i).setIsexported("1");
                            pgmisChequeLoantbls.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for PgmisChequeLoan")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }

        if(tableIndentifier.equals(AppConstant.PgmisCadreList)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    if(tblPgMeetingCaders.size()>0){
                        for(int i=0;i<tblPgMeetingCaders.size();i++){
                            tblPgMeetingCaders.get(i).setIsxported("1");
                            tblPgMeetingCaders.get(i).save();
                        }
                        DialogClose();
                        new StyleableToast
                                .Builder(this)
                                .text("Successfully Uploaded")
                                .iconStart(R.drawable.right)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        presenter.getPgMemberstblandShgMemrtbl();
                    }
                }
                if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for tblPgMeetingCaders")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                DialogClose();
            } catch (JSONException e) {
                e.printStackTrace();
                DialogClose();
            }
        }
    }

    @Override
    public void onResponseFailure(String tableIdentifier) {
        DialogClose();
        new StyleableToast
                .Builder(this)
                .text("server error,Please check internet Connection")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
        progress.cancel();
    }

    private void DialogShow() {
        progress= new ProgressDialog(this);
        progress = new ProgressDialog(this);
        progress.setMessage("अपलोड जारी है, कृपया प्रतीक्षा करें");
        progress.setCancelable(false);
        progress.show();
    }

    private void DialogShowDownload() {
        progress= new ProgressDialog(this);
        progress = new ProgressDialog(this);
        progress.setMessage("कृपया प्रतीक्षा करें");
        progress.setCancelable(false);
        progress.show();

        //Add loader
       Runnable progressrun = new Runnable() {
            @Override
            public void run() {
                progress.cancel();
            }
        };
        Handler pdCanceller3 = new Handler();
        pdCanceller3.postDelayed(progressrun, 3000);
    }

    private void DialogClose(){
        if(progress!=null){
            progress.dismiss();
        }
    }

    public void ShowPopup() {
        try {
            myDialog.setContentView(R.layout.show_exception_popup);
            myDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            btnFollow = (Button) myDialog.findViewById(R.id.buttonUpdate);
            btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                    clearAppData();
                }
            });
            myDialog.setCanceledOnTouchOutside(false);
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void clearAppData() {
        try {
            // clearing app data
            if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager)getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
                Intent intent= new Intent(PgActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                String packageName = getApplicationContext().getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear "+packageName);
                Intent intent= new Intent(PgActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
