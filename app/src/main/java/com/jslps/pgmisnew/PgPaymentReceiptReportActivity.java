package com.jslps.pgmisnew;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jslps.pgmisnew.adapter.PgPaymentReceiptReportAdapter;
import com.jslps.pgmisnew.adapter.PgReceiptReportAdapter;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PaymentReceiptReportModel;
import com.jslps.pgmisnew.database.PgCapitalSavetbl;
import com.jslps.pgmisnew.database.PgMemShipFeeSavetbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.database.ReceiptAmountSumModel;
import com.jslps.pgmisnew.database.ReceiptReportModel;
import com.jslps.pgmisnew.interactor.PaymentReceiptRepotInteractor;
import com.jslps.pgmisnew.interactor.ReceiptRepotInteractor;
import com.jslps.pgmisnew.presenter.PaymentReceiptReportPresenter;
import com.jslps.pgmisnew.presenter.ReceiptReportPresenter;
import com.jslps.pgmisnew.view.ReceiptReport;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PgPaymentReceiptReportActivity extends AppCompatActivity implements ReceiptReport, DatePickerDialog.OnDateSetListener {
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
    boolean isMatched1;
    boolean isMatched2;
    List<PgPaymentTranstbl> pgPaymentTranstblList;
    List<PgPaymentTranstbl> pgPaymentTranstblListSum;
    List<PaymentReceiptReportModel> paymentReceiptReportModelList = new ArrayList<>();
    public static List<PaymentReceiptReportModel> pdfGenerateList = new ArrayList<>();
    List<PaymentReceiptReportModel> paymentReceiptReportModelListNotMatched;
    PgReceiptReportAdapter aAdapter;
    List<String> budgetidreportfinallist;

    List<PgReceiptTranstbl> pgReceiptTranstblList;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList;
    List<PgMemShipFeeSavetbl> pgMemShipFeeList;
    List<PgCapitalSavetbl> pgMemCapitalList;

    List<PgmisLoantbl> PgmisLoantblList;
    List<ReceiptReportModel> finalReportList;
    List<ReceiptReportModel> paymentReceiptReportMlist = new ArrayList<>();
    Double totalRecived= 0.0;
    Double totalPayment = 0.0;
    public static List<ReceiptReportModel> pdfGenerateDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_receipt_report);
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
                    //
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
                    pgPaymentTranstblList = presenter.getListPaymentTranstableDateWise(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    pgReceiptTranstblList = presenter.getListReceiptTranstableDateWise(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    itempurchasedbypgtblList = presenter.getItempurchasedbypgDateWise(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //=========== share capital and member fee ====
                    pgMemShipFeeList = presenter.getPgMemShipFeeSavetblList(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    pgMemCapitalList = presenter.getPgCapitalSavetblList(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //================= loan received ====

                    //==================loan given ========


                    generateReport();

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
                        ActivityCompat
                                .requestPermissions(this, PERMISSIONS, 1);

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

    private boolean validation() {
        boolean result;
        String dateFrom = textView82.getText().toString();
        String dateTo = textView84.getText().toString();
        if (dateFrom.equals("Select Date") && dateTo.equals("Select Date")) {
            result = false;
            presenter.selectAtLeastOneCalender();
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


        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String currentDate = new SimpleDateFormat("d/M/yyyy", Locale.getDefault()).format(new Date());

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

                //date corvert
//                String strDate = pgPaymentTranstblList.get(i).getDate();
//                item.setDate(parseDate(strDate));
                item.setDate(parseDate(pgPaymentTranstblList.get(i).getDate()));
                item.setHeadname(pgPaymentTranstblList.get(i).getHeadname());
                item.setPaymentamount(pgPaymentTranstblList.get(i).getAmount());
                item.setPaymentmode(pgPaymentTranstblList.get(i).getPaymentmode());
                finalReportList.add(item);

                totalPayment = totalPayment + Double.valueOf(pgPaymentTranstblList.get(i).getAmount());
            }
        }

        //=================== get received details ( pgReceiptTranstbl table ) ======
        if (pgReceiptTranstblList.size() > 0) {
            for (int i = 0; i < pgReceiptTranstblList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                item.setDate(pgReceiptTranstblList.get(i).getDate());
                item.setHeadname(pgReceiptTranstblList.get(i).getHeadname());
                item.setReceivedamount(pgReceiptTranstblList.get(i).getAmount());
                item.setPaymentmode(" "); //payment mode not added table that's why it has added as temporary
                finalReportList.add(item);
                totalRecived = totalRecived + Double.valueOf(pgReceiptTranstblList.get(i).getAmount());
            }
        }
        //================================ itempurchasedbypgtblList ====================
        if (itempurchasedbypgtblList.size() > 0) {
            for (int i = 0; i < itempurchasedbypgtblList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                item.setDate(itempurchasedbypgtblList.get(i).getEntrydate());
                String ItemHead = itempurchasedbypgtblList.get(i).getItemname(); // + " Puarchase";
                item.setHeadname(ItemHead);
                Double amount = Double.valueOf(itempurchasedbypgtblList.get(i).getRate()) * Double.valueOf(itempurchasedbypgtblList.get(i).getQuantity()) ;
                totalRecived = totalRecived + amount;
                item.setReceivedamount(String.valueOf(amount));
                item.setPaymentmode(itempurchasedbypgtblList.get(i).getPaymentmode());
                finalReportList.add(item);
            }
        }

        //=================== get share capital details ( pgReceiptTranstbl table ) ======
        if (pgMemCapitalList.size() > 0) {
            for (int i = 0; i < pgMemCapitalList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                item.setDate(pgMemCapitalList.get(i).getPayment_date());
                item.setHeadname("Share Capital");
                item.setReceivedamount(pgMemCapitalList.get(i).getAmount());
                item.setPaymentmode(pgMemCapitalList.get(i).getPayment_mode());
                finalReportList.add(item);
                totalRecived = totalRecived + Double.valueOf(pgMemCapitalList.get(i).getAmount());
            }
        }
        //=================== get member fee registration details ( pgReceiptTranstbl table ) ======
        if (pgMemShipFeeList.size() > 0) {
            for (int i = 0; i < pgMemShipFeeList.size(); i++) {
                ReceiptReportModel item = new ReceiptReportModel();
                item.setDate(pgMemCapitalList.get(i).getPayment_date());
                item.setHeadname("Member Register");
                item.setReceivedamount(pgMemCapitalList.get(i).getAmount());
                item.setPaymentmode(pgMemCapitalList.get(i).getPayment_mode());
                finalReportList.add(item);
                totalRecived = totalRecived + Double.valueOf(pgMemShipFeeList.get(i).getAmount());
            }
        }
        // loan table




        //================ update total amount =============
        totalPaymentAmt.setText(String.valueOf(totalPayment));
        totalReceivedAmt.setText(String.valueOf(totalRecived));

        //=========== sort the array date =====
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

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat inputDateFormat2 = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        Date date = null;
        String outputDateString = null;
        try {
            date = inputDateFormat.parse(inputDateString);
            outputDateString = outputDateFormat.format(date);
        } catch (ParseException f) {
            try {
                date = inputDateFormat2.parse(inputDateString);
                outputDateString = outputDateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return outputDateString;
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
        Footer.setReceivedamount(String.valueOf(totalRecived));
        Footer.setPaymentamount(String.valueOf(totalPayment));
        Footer.setDate("");
        Footer.setPaymentmode("");
        pdfGenerateDataList.add(Footer);

    }

}
