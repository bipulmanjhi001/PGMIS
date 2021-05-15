package com.jslps.pgmisnew;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jslps.pgmisnew.adapter.PgReceiptReportAdapter;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.Pgcapitalsavetbl;
import com.jslps.pgmisnew.database.Pgmemshipfeesavetbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.PgmisBatchLoantbl;
import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.database.Pgmisloanrepaymenttabl;
import com.jslps.pgmisnew.database.ReceiptAmountSumModel;
import com.jslps.pgmisnew.database.ReceiptReportModel;
import com.jslps.pgmisnew.interactor.ReceiptRepotInteractor;
import com.jslps.pgmisnew.presenter.ReceiptReportPresenter;
import com.jslps.pgmisnew.view.ReceiptReport;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class PgPaymentReceiptReportActivity extends AppCompatActivity implements ReceiptReport, Comparator<ReceiptReportModel>, DatePickerDialog.OnDateSetListener {

    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView81)
    TextView textView81;
    @BindView(R.id.textView82)
    TextView textView82;
    @BindView(R.id.imageView19)
    ImageView imageView19;
    @BindView(R.id.textView83)
    TextView textView83;
    @BindView(R.id.textView84)
    TextView textView84;
    @BindView(R.id.imageView20)
    ImageView imageView20;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
    @BindView(R.id.constraintLayout4)
    ConstraintLayout constraintLayout4;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.parentContainer)
    ConstraintLayout parentContainer;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.imageView21)
    ImageView imageView21;
    @BindView(R.id.imageView22)
    ImageView pdf;
    @BindView(R.id.totalPaymentAmt)
    TextView totalPaymentAmt;
    @BindView(R.id.totalReceivedAmt)
    TextView totalReceivedAmt;

    //Class Globals
    ReceiptReportPresenter presenter;
    List<PgReceiptDisData> receiptDisDataList;
    List<ReceiptAmountSumModel> receiptAmountSumModelList;

    boolean isMatched;
    List<PgPaymentTranstbl> pgPaymentTranstblList;
    PgReceiptReportAdapter aAdapter;
    List<PgReceiptTranstbl> pgReceiptTranstblList;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList;
    List<Pgmemshipfeesavetbl> pgMemShipFeeList;
    List<Pgcapitalsavetbl> pgMemCapitalList;
    List<PgmisBatchLoantbl> pgmisBatchLoantblList;
    List<Pgmisloanrepaymenttabl> pgmisLoanrepaymentList;
    List<PgmisLoantbl> pgmisLoantblList;
    List<PgReceiptDisData> pgReceiptDisData;
    List<PgBankwithdrawcashdeposit> pgBankwithdrawcashdeposits;
    List<PgmisChequeLoantbl> pgmisChequeLoantbls;

    List<ReceiptReportModel> finalReportList;
    Double totalRecived= 0.0;
    Double totalPayment = 0.0;
    public static List<ReceiptReportModel> pdfGenerateDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_receipt_report);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();
    }

    private void init() {
        //initialization
        presenter = new ReceiptReportPresenter(new ReceiptRepotInteractor(), this);
        presenter.setPgName();
        receiptDisDataList = presenter.getReceiptAmountData(PgActivity.pgCodeSelected);
        //adding amount for same budgetid for receiptDisDataList
        addAmountreceiptDisDataList();
    }

    private void addAmountreceiptDisDataList() {
        receiptAmountSumModelList = new ArrayList<>();
        if (receiptDisDataList.size() > 0) {
            for (int i = 0; i < receiptDisDataList.size(); i++) {
                isMatched = false;
                ReceiptAmountSumModel item = new ReceiptAmountSumModel();
                item.setBudgetid(receiptDisDataList.get(i).getBudgetid());
                item.setAmount(receiptDisDataList.get(i).getEkoshamount());
                item.setHeadname(receiptDisDataList.get(i).getBudgethead());
                if (receiptAmountSumModelList.size() == 0) {
                    //for size zero
                    receiptAmountSumModelList.add(item);
                } else {
                    for (int j = 0; j < receiptAmountSumModelList.size(); j++) {
                        String budgetid = receiptAmountSumModelList.get(j).getBudgetid();
                        String amount = receiptAmountSumModelList.get(j).getAmount();
                        if (budgetid.equals(receiptDisDataList.get(i).getBudgetid())) {
                            double newAmount = Double.parseDouble(amount) + Double.parseDouble(receiptDisDataList.get(i).getEkoshamount());
                            item.setAmount(newAmount + "");
                            receiptAmountSumModelList.set(j, item);
                            isMatched = true;
                            //since matched terminate internal loop
                            j = receiptAmountSumModelList.size();
                        }
                    }
                    if (!isMatched) {
                        receiptAmountSumModelList.add(item);
                    }
                }
            }
        }
    }

    @OnClick({R.id.imageView19, R.id.imageView20, R.id.button5,R.id.imageView21,R.id.imageView22})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView19:
                presenter.openCalender("from");
                break;
            case R.id.imageView20:
                presenter.openCalender("to");
                break;
            case R.id.button5:
                boolean result = validation();
                if (result) {
                    pgPaymentTranstblList = getPgPaymentTransList(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    pgReceiptTranstblList = presenter.getListReceiptTranstableDateWise(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    itempurchasedbypgtblList = presenter.getItempurchasedbypgDateWise(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //================= loan received ====
                    pgmisBatchLoantblList = presenter.getPgmisBatchLoantblList(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //================== loan given ========
                    pgmisLoanrepaymentList = presenter.getPgmisloanrepaymenttablList(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //add membership fee
                    pgMemShipFeeList = getPgMemShipFeeSavetblList(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //=========== share capital and member fee ====
                    pgMemCapitalList = getPgCapitalSavetblList(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    // add Receipt Disbursment
                    pgReceiptDisData = getReceiptDisbursment(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //add Bank
                    pgBankwithdrawcashdeposits =getPgBankwithdrawcashdeposits(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //add chequebank
                    pgmisChequeLoantbls = getChequeLoan(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    generateReport();
                }else {
                    new SweetAlertDialog(PgPaymentReceiptReportActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Select at least One date")
                            .setContentText("No data found")
                            .setConfirmText("Exit")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                    finish();
                                }
                            })
                            .show();
                }
                break;
            case R.id.imageView21:
                presenter.clearDates();
                break;

            case R.id.imageView22:
                String dateFrom = textView82.getText().toString();
                String dateTo = textView84.getText().toString();
                if ((dateFrom.equals("Select Date") && dateTo.equals("Select Date"))||pdfGenerateDataList.size()==0) {
                    Toast.makeText(PgPaymentReceiptReportActivity.this,"Please see report first",Toast.LENGTH_SHORT).show();
                }else{
                    if (!hasPermissions(PgPaymentReceiptReportActivity.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
                    }else{
                        //generate pdf here
                        Intent intent = new Intent(this,GeneratePdfReceiptReportActivity.class);
                        intent.putExtra("from",dateFrom);
                        intent.putExtra("to",dateTo);
                        startActivity(intent);
                    }
                }
                break;
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    //================== get data from PgMemShipFeeSavetbl ===========
    public List<Pgmemshipfeesavetbl> getPgMemShipFeeSavetblList(String fromDate, String toDate, String pgcode){
        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(Pgmemshipfeesavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("paymentdate").lt(toDate))
                    .or(Condition.prop("paymentdate").eq(toDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
                     //only to date present
            return Select.from(Pgmemshipfeesavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("paymentdate").lt(toDate))
                    .or(Condition.prop("paymentdate").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(Pgmemshipfeesavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("paymentdate")
                            .gt(fromDate),Condition.prop("paymentdate")
                            .lt(toDate)).or(Condition.prop("paymentdate")
                            .eq(fromDate)).or(Condition.prop("paymentdate").eq(toDate))
                    .list();
        }
    }

    //  ===================get ChequeLoan details (PgmisChequeLoantbl) ======
    public List<PgmisChequeLoantbl> getChequeLoan(String fromDate, String toDate, String pgcode){
        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgmisChequeLoantbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("chequedate").gt(fromDate)).or(Condition.prop("chequedate").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgmisChequeLoantbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("chequedate").lt(toDate)).or(Condition.prop("chequedate").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgmisChequeLoantbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("chequedate").gt(fromDate),Condition.prop("chequedate").lt(toDate))
                    .or(Condition.prop("chequedate").eq(fromDate)).or(Condition.prop("chequedate").eq(toDate))
                    .list();
        }
    }

    //  ===================get payment details (PgPaymentTranstbl) ======
    public List<PgPaymentTranstbl> getPgPaymentTransList(String fromDate,String toDate,String pgcode){
        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgPaymentTranstbl.class)
                    .where(Condition.prop("PG_Code").eq(pgcode))
                    .where(Condition.prop("date").gt(fromDate)).or(Condition.prop("date").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgPaymentTranstbl.class)
                    .where(Condition.prop("PG_Code").eq(pgcode))
                    .where(Condition.prop("date").lt(toDate)).or(Condition.prop("date").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgPaymentTranstbl.class)
                    .where(Condition.prop("PG_Code").eq(pgcode))
                    .where(Condition.prop("date").gt(fromDate),Condition.prop("date").lt(toDate))
                    .or(Condition.prop("date").eq(fromDate)).or(Condition.prop("date").eq(toDate))
                    .list();
        }
    }

    //================== get data from PgCapitalSavetbl ===========
    public List<Pgcapitalsavetbl> getPgCapitalSavetblList(String fromDate, String toDate, String pgcode){
        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(Pgcapitalsavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("Paymentdate").gt(fromDate))
                    .or(Condition.prop("Paymentdate")
                            .eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(Pgcapitalsavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("Paymentdate").lt(toDate))
                    .or(Condition.prop("Paymentdate").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(Pgcapitalsavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("Paymentdate").gt(fromDate),
                            Condition.prop("Paymentdate").lt(toDate))
                    .or(Condition.prop("Paymentdate").eq(fromDate))
                    .or(Condition.prop("Paymentdate").eq(toDate))
                    .list();
        }
    }

    //================== get data from  PgReceiptDisData  for disbursment amount   ===========
    public List<PgReceiptDisData> getReceiptDisbursment(String fromDate, String toDate, String pgcode){
        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgReceiptDisData.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("approveddate").gt(fromDate))
                    .or(Condition.prop("approveddate")
                            .eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgReceiptDisData.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("approveddate").lt(toDate))
                    .or(Condition.prop("approveddate").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgReceiptDisData.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("approveddate").gt(fromDate),
                            Condition.prop("approveddate").lt(toDate))
                    .or(Condition.prop("approveddate").eq(fromDate))
                    .or(Condition.prop("approveddate").eq(toDate))
                    .list();
        }
    }

    //================== get data from  PgBankwithdrawcashdeposit amount   ===========
    public List<PgBankwithdrawcashdeposit> getPgBankwithdrawcashdeposits(String fromDate, String toDate, String pgcode){
        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgBankwithdrawcashdeposit.class)
                    .where(Condition.prop("PG_Code").eq(pgcode))
                    .where(Condition.prop("Date").gt(fromDate))
                    .or(Condition.prop("Date")
                            .eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgBankwithdrawcashdeposit.class)
                    .where(Condition.prop("PG_Code").eq(pgcode))
                    .where(Condition.prop("Date").lt(toDate))
                    .or(Condition.prop("Date").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgBankwithdrawcashdeposit.class)
                    .where(Condition.prop("PG_Code").eq(pgcode))
                    .where(Condition.prop("Date").gt(fromDate),
                            Condition.prop("Date").lt(toDate))
                    .or(Condition.prop("Date").eq(fromDate))
                    .or(Condition.prop("Date").eq(toDate))
                    .list();
        }
    }

    private boolean validation() {
        boolean result;
        String dateFrom = textView82.getText().toString();
        String dateTo = textView84.getText().toString();
        if (dateFrom.equals("Select Date") && dateTo.equals("Select Date")) {
            result = false;

            //  presenter.selectAtLeastOneCalender();
            new SweetAlertDialog(PgPaymentReceiptReportActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Select at least One date")
                    .setContentText("No data found")
                    .setConfirmText("Exit")
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.cancel();
                        }
                    })
                    .show();
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public void setPgName() {
        textView23.setText(PgActivity.pgNameSelected);
    }

    @Override
    public void openCalender(String from) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                PgPaymentReceiptReportActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        dpd.show(getFragmentManager(), from);
    }

    @Override
    public void selectAtLeastOneCalender() {

    }

    @Override
    public void getReport() {

    }

    @Override
    public void clearDates() {
        textView82.setText("Select Date");
        textView84.setText("Select Date");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        String newDay = dayOfMonth + "";
        String newMonth = (monthOfYear + 1) + "";
        if ((monthOfYear + 1) < 10) {
            newMonth = "0" + newMonth;
        }
        if (dayOfMonth < 10) {
            newDay = "0" + dayOfMonth;
        }
        String newDate = year + "/" + newMonth + "/" + newDay;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        Date date1 = null, date2 = null;
        try {
            date1 = sdf.parse(date);
            date2 = sdf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date1 != null && date1.compareTo(date2) > 0) {
            Toast.makeText(PgPaymentReceiptReportActivity.this, "Please Select Valid Date", Toast.LENGTH_LONG).show();
        } else {
            if (view.getTag().equals("from")) {
                //condition to check from date from date should be less than to date
                if (textView84.getText().toString().equals("Select Date")) {
                    textView82.setText(newDate);
                } else {
                    int newDateCom = Integer.parseInt(newDate.replaceAll("/", ""));
                    int toDate = Integer.parseInt(textView84.getText().toString().replaceAll("/", ""));
                    if (newDateCom <= toDate) {
                        textView82.setText(newDate);
                    } else {
                        Toast.makeText(PgPaymentReceiptReportActivity.this, "From Date Can't be Greater than To Date", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //condition to check from date to date should be greater than from date
                if (textView82.getText().toString().equals("Select Date")) {
                    textView84.setText(newDate);
                } else {
                    int newDateCom = Integer.parseInt(newDate.replaceAll("/", ""));
                    int fromDate = Integer.parseInt(textView82.getText().toString().replaceAll("/", ""));
                    if (fromDate <= newDateCom) {
                        textView84.setText(newDate);
                    } else {
                        Toast.makeText(PgPaymentReceiptReportActivity.this, "To Date Can't be less than From Date", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    //================ generate report ==================
    private void generateReport(){
        finalReportList = new ArrayList<>();
        totalRecived= 0.0;
        totalPayment = 0.0;
        //============================== user paid details ====================
        //=================== get payment details (PgPayment Trasaction) ======
        if (pgPaymentTranstblList.size() > 0) {
            for (int i = 0; i < pgPaymentTranstblList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                // item.setDate(parseDate(pgPaymentTranstblList.get(i).getDate()));
                item.setDate(pgPaymentTranstblList.get(i).getDate());

                item.setHeadname(pgPaymentTranstblList.get(i).getHeadname());
                item.setPaymentamount(pgPaymentTranstblList.get(i).getAmount());
                item.setPaymentmode(pgPaymentTranstblList.get(i).getPaymentmode());
                finalReportList.add(item);

                totalPayment = totalPayment + Double.valueOf(pgPaymentTranstblList.get(i).getAmount());
            }
        }

        //=================== get received details ( pgReceiptTranstbl table ) ======
        if (pgReceiptDisData.size() > 0) {
            for (int i = 0; i < pgReceiptDisData.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                //  item.setDate(parseDate(pgReceiptDisData.get(i).getApproveddate()));
                item.setDate(pgReceiptDisData.get(i).getApproveddate());

                item.setHeadname(pgReceiptDisData.get(i).getBudgethead());
                item.setReceivedamount(pgReceiptDisData.get(i).getEkoshamount());
                item.setPaymentmode("Bank");

                //payment mode not added table that's why it has added as temporary
                finalReportList.add(item);
                totalRecived = totalRecived + Double.valueOf(pgReceiptDisData.get(i).getEkoshamount());
            }
        }

        //=================== get received details ( pgReceiptTranstbl table ) ======
        if (pgReceiptTranstblList.size() > 0) {
            for (int i = 0; i < pgReceiptTranstblList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                //item.setDate(parseDate(pgReceiptTranstblList.get(i).getDate()));
                item.setDate(pgReceiptTranstblList.get(i).getDate());
                item.setHeadname(pgReceiptTranstblList.get(i).getHeadname());
                item.setReceivedamount(pgReceiptTranstblList.get(i).getAmount());
                item.setPaymentmode(pgReceiptTranstblList.get(i).getPaymentmode());

                //payment mode not added table that's why it has added as temporary
                finalReportList.add(item);
                totalRecived = totalRecived + Double.valueOf(pgReceiptTranstblList.get(i).getAmount());
            }
        }

        //================================ itempurchasedbypgtblList ====================
        if (itempurchasedbypgtblList.size() > 0) {
            for (int i = 0; i < itempurchasedbypgtblList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                // item.setDate(parseDate(itempurchasedbypgtblList.get(i).getSelecteddate()));
                item.setDate(itempurchasedbypgtblList.get(i).getSelecteddate());

                String ItemHead = itempurchasedbypgtblList.get(i).getBudgetname(); // + " Puarchase";
                item.setHeadname(ItemHead);
                Double amount = Double.valueOf(itempurchasedbypgtblList.get(i).getRate()) * Double.valueOf(itempurchasedbypgtblList.get(i).getQuantity()) ;
                item.setPaymentamount(String.valueOf(amount));
                item.setPaymentmode(itempurchasedbypgtblList.get(i).getPaymentmode());
                finalReportList.add(item);
                totalPayment = totalPayment + amount;
            }
        }

        //////////////////////////////////////////////////////////////////////////////////
        try {
            if (pgMemCapitalList.size() > 0) {
                for (int i = 0; i < pgMemCapitalList.size(); i++) {
                    ReceiptReportModel item = new ReceiptReportModel();
                    //   item.setDate(parseDate(pgMemCapitalList.get(i).getPaymentdate()));
                    item.setDate(pgMemCapitalList.get(i).getPaymentdate());

                    item.setHeadname("Share Capital");
                    item.setReceivedamount(pgMemCapitalList.get(i).getAmount());
                    item.setPaymentmode(pgMemCapitalList.get(i).getPaymentmode());

                    //payment mode not added table that's why it has added as temporary
                    finalReportList.add(item);
                    totalRecived = totalRecived + Double.valueOf(pgMemCapitalList.get(i).getAmount());
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        try {
            if (pgMemShipFeeList.size() > 0) {
                for (int i = 0; i < pgMemShipFeeList.size(); i++) {
                    ReceiptReportModel item = new ReceiptReportModel();
                    //item.setDate(parseDate(pgMemShipFeeList.get(i).getPaymentdate()));
                    item.setDate(pgMemShipFeeList.get(i).getPaymentdate());

                    item.setHeadname("Membership Fee");
                    item.setReceivedamount(pgMemShipFeeList.get(i).getAmount());
                    item.setPaymentmode(pgMemShipFeeList.get(i).getPaymentmode());

                    //payment mode not added table that's why it has added as temporary
                    finalReportList.add(item);
                    totalRecived = totalRecived + Double.valueOf(pgMemShipFeeList.get(i).getAmount());
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        ////////////////////////////////////////////////////////////////////////////////////
        //        //================== Bank Table=====================
        if (pgBankwithdrawcashdeposits.size() > 0) {
            for (int i = 0; i < pgBankwithdrawcashdeposits.size(); i++) {
                if (pgBankwithdrawcashdeposits.get(i).getHeadname().equals("बैंक से नगद निकासी")) {
                    ReceiptReportModel item = new ReceiptReportModel();
                    item.setDate(pgBankwithdrawcashdeposits.get(i).getDate());

                    item.setHeadname(pgBankwithdrawcashdeposits.get(i).getHeadname());
                    item.setPaymentmode("Cash");
                    Double amount = Double.valueOf(pgBankwithdrawcashdeposits.get(i).getAmount());
                    item.setReceivedamount(String.valueOf(amount));

                    finalReportList.add(item);
                    totalRecived = totalRecived + Double.valueOf(pgBankwithdrawcashdeposits.get(i).getAmount());

                    ReceiptReportModel item2 = new ReceiptReportModel();
                    item2.setDate(pgBankwithdrawcashdeposits.get(i).getDate());

                    item2.setHeadname(pgBankwithdrawcashdeposits.get(i).getHeadname());
                    Double amount2 = Double.valueOf(pgBankwithdrawcashdeposits.get(i).getAmount());
                    item2.setPaymentamount(String.valueOf(amount2));

                    item2.setPaymentmode("Bank");
                    finalReportList.add(item2);

                    totalPayment = totalPayment + amount2;
                }
                else {
                    ReceiptReportModel item = new ReceiptReportModel();
                    item.setDate(pgBankwithdrawcashdeposits.get(i).getDate());

                    item.setHeadname(pgBankwithdrawcashdeposits.get(i).getHeadname());
                    item.setPaymentmode("Bank");
                    Double amount = Double.valueOf(pgBankwithdrawcashdeposits.get(i).getAmount());
                    item.setReceivedamount(String.valueOf(amount));

                    finalReportList.add(item);
                    totalRecived = totalRecived + Double.valueOf(pgBankwithdrawcashdeposits.get(i).getAmount());

                    ReceiptReportModel item2 = new ReceiptReportModel();
                    item2.setDate(pgBankwithdrawcashdeposits.get(i).getDate());

                    item2.setHeadname(pgBankwithdrawcashdeposits.get(i).getHeadname());
                    Double amount2 = Double.valueOf(pgBankwithdrawcashdeposits.get(i).getAmount());
                    item2.setPaymentamount(String.valueOf(amount2));

                    item2.setPaymentmode("Cash");
                    finalReportList.add(item2);

                    totalPayment = totalPayment + amount2;
                }
            }
        }

        //================== loan payment=====================
        if (pgmisLoanrepaymentList.size() > 0) {
            for (int i = 0; i < pgmisLoanrepaymentList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                item.setDate(pgmisLoanrepaymentList.get(i).getEntrydate());
                item.setHeadname("Advance to member");
                item.setPaymentmode(pgmisLoanrepaymentList.get(i).getSelectedPaymentMode());
                Double amount = Double.valueOf(pgmisLoanrepaymentList.get(i).getAmount()) ;
                item.setReceivedamount(String.valueOf(amount));

                finalReportList.add(item);
                totalRecived = totalRecived  + Double.valueOf(pgmisLoanrepaymentList.get(i).getAmount());
            }
        }

        //========= loan and sale
        if (pgmisBatchLoantblList.size() > 0) {
            for (int i = 0; i < pgmisBatchLoantblList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                item.setDate(pgmisBatchLoantblList.get(i).getEntrydate());
                item.setHeadname("Advance to member");
                Double amount = Double.valueOf(pgmisBatchLoantblList.get(i).getAmount()) ;
                item.setPaymentamount(String.valueOf(amount));

                item.setPaymentmode("Adjustment");
                finalReportList.add(item);

                totalPayment = totalPayment  + amount;
            }
        }
        //========= loan and sale
        if (pgmisBatchLoantblList.size() > 0) {
            for (int i = 0; i < pgmisBatchLoantblList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                item.setDate(pgmisBatchLoantblList.get(i).getEntrydate());
                item.setHeadname("Revolving fund");
                Double amount = Double.valueOf(pgmisBatchLoantblList.get(i).getAmount());
                item.setReceivedamount(String.valueOf(amount));

                item.setPaymentmode("Adjustment");
                finalReportList.add(item);

                totalRecived = totalRecived + amount;
            }

            ///////////////////////////////////////////////////ChequeLoan//////////////////////////////////////////
            if (pgmisChequeLoantbls.size() > 0) {
                for (int i = 0; i < pgmisChequeLoantbls.size(); i++) {
                    ReceiptReportModel item = new ReceiptReportModel();
                    item.setDate(pgmisChequeLoantbls.get(i).getChequedate());
                    item.setHeadname("चेक  भुगतान");
                    Double amount = Double.valueOf(pgmisChequeLoantbls.get(i).getAmount());
                    item.setPaymentamount(String.valueOf(amount));

                    item.setPaymentmode("Bank");
                    finalReportList.add(item);

                    totalPayment = totalPayment + amount;
                }
            }

            //========= Cheque Loan======================================//
            if (pgmisChequeLoantbls.size() > 0) {
                for (int i = 0; i < pgmisChequeLoantbls.size(); i++) {
                    ReceiptReportModel item = new ReceiptReportModel();
                    item.setDate(pgmisChequeLoantbls.get(i).getChequedate());
                    item.setHeadname("चेक  भुगतान");
                    Double amount = Double.valueOf(pgmisChequeLoantbls.get(i).getAmount());
                    item.setReceivedamount(String.valueOf(amount));

                    item.setPaymentmode("Adjustment");
                    finalReportList.add(item);

                    totalRecived = totalRecived + amount;
                }
            }
        }
        try {
            BigDecimal bd = new BigDecimal(totalRecived).setScale(2, RoundingMode.HALF_UP);
            double Recived = bd.doubleValue();

            BigDecimal bd2 = new BigDecimal(totalPayment).setScale(2, RoundingMode.HALF_UP);
            double Payment = bd2.doubleValue();

            //================ update total amount =============
            totalPaymentAmt.setText(String.valueOf(totalPayment));
            totalReceivedAmt.setText(String.valueOf(totalRecived));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        //=========== sort the array date =====
        Collections.sort(finalReportList,this::compare);
        //=========== update on recycler ===========
        aAdapter = new PgReceiptReportAdapter(this, finalReportList);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
        generatePdfReport();//============ adding this for generate pdf file
        System.out.println("");

    }

    //=========== change date formate ========
    public static String parseDate(String inputDateString) {

        SimpleDateFormat  outputDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        //  SimpleDateFormat inputDateFormat2 = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        SimpleDateFormat inputDateFormat  = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        Date date = null;
        String outputDateString = null;
        try {
            try {
                date = inputDateFormat.parse(inputDateString);
                outputDateString = outputDateFormat.format(date);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        } catch (ParseException f) {
            f.printStackTrace();
        }
        return outputDateString;
        //return inputDateString; //this is for testing ====
    }

    //============= generate pdf
    public void generatePdfReport(){
        pdfGenerateDataList = new ArrayList<>();
        ReceiptReportModel header = new ReceiptReportModel();
        header.setHeadname("Head");
        header.setReceivedamount("Received Amount");
        header.setPaymentamount("Payment Amount ");
        header.setDate("Date");
        header.setPaymentmode("Payment Mode");
        pdfGenerateDataList.add(header);

        pdfGenerateDataList.addAll(finalReportList);
        ReceiptReportModel Footer = new ReceiptReportModel();
        Footer.setHeadname("Total");

        try {
            BigDecimal bd = new BigDecimal(totalRecived).setScale(2, RoundingMode.HALF_UP);
            double Recived = bd.doubleValue();

            BigDecimal bd2 = new BigDecimal(totalPayment).setScale(2, RoundingMode.HALF_UP);
            double Payment = bd2.doubleValue();

            Footer.setReceivedamount(String.valueOf(totalRecived));
            Footer.setPaymentamount(String.valueOf(totalPayment));
            Footer.setDate("");
            Footer.setPaymentmode("");
            pdfGenerateDataList.add(Footer);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    //============ sorting funtion
    public int compare(ReceiptReportModel a, ReceiptReportModel b)
    {
        return a.sortDateReference - b.sortDateReference;
    }
}

