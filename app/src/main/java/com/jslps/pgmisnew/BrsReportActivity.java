package com.jslps.pgmisnew;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jslps.pgmisnew.adapter.BrsReportAdapter;
import com.jslps.pgmisnew.adapter.PgReceiptReportAdapter;
import com.jslps.pgmisnew.database.BrsReportModel;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PaymentReceiptReportModel;
import com.jslps.pgmisnew.database.PgCapitalSavetbl;
import com.jslps.pgmisnew.database.PgMemShipFeeSavetbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.database.Pgmisbrstranstbl;
import com.jslps.pgmisnew.database.ReceiptAmountSumModel;
import com.jslps.pgmisnew.database.ReceiptReportModel;
import com.jslps.pgmisnew.interactor.BrsRepotInteractor;
import com.jslps.pgmisnew.interactor.ReceiptRepotInteractor;
import com.jslps.pgmisnew.presenter.BrsReportPresenter;
import com.jslps.pgmisnew.presenter.ReceiptReportPresenter;
import com.jslps.pgmisnew.view.BrsReport;
import com.jslps.pgmisnew.view.ReceiptReport;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
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

public class BrsReportActivity extends AppCompatActivity implements BrsReport, DatePickerDialog.OnDateSetListener {
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
    BrsReportPresenter presenter;
    BrsReportAdapter aAdapter;

    List<Pgmisbrstranstbl> pgBrstranstblList;


    List<PgReceiptDisData> receiptDisDataList;
    List<ReceiptAmountSumModel> receiptAmountSumModelList;
    boolean isMatched;

    List<PgPaymentTranstbl> pgPaymentTranstblList;
    List<PgReceiptTranstbl> pgReceiptTranstblList;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList;
    List<PgMemShipFeeSavetbl> pgMemShipFeeList;
    List<PgCapitalSavetbl> pgMemCapitalList;
    List<BrsReportModel> finalReportList;

    Double totalCashbook= 0.0;
    Double totalPassbook = 0.0;
    public static List<BrsReportModel> pdfGenerateDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brs_report);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();
    }
    private void init() {
        //initialization
        presenter = new BrsReportPresenter(new BrsRepotInteractor(), this);
        presenter.setPgName();
      // receiptDisDataList = presenter.getReceiptAmountData(PgActivity.pgCodeSelected);

        //adding amount for same budgetid for receiptDisDataList
        //addAmountreceiptDisDataList();
    }

//    private void addAmountreceiptDisDataList() {
//        receiptAmountSumModelList = new ArrayList<>();
//        if (receiptDisDataList.size() > 0) {
//            for (int i = 0; i < receiptDisDataList.size(); i++) {
//                isMatched = false;
//                ReceiptAmountSumModel item = new ReceiptAmountSumModel();
//                item.setBudgetid(receiptDisDataList.get(i).getBudgetid());
//                item.setAmount(receiptDisDataList.get(i).getEkoshamount());
//                item.setHeadname(receiptDisDataList.get(i).getBudgethead());
//                if (receiptAmountSumModelList.size() == 0) {
//                    //for size zero
//                    receiptAmountSumModelList.add(item);
//                } else {
//                    for (int j = 0; j < receiptAmountSumModelList.size(); j++) {
//                        String budgetid = receiptAmountSumModelList.get(j).getBudgetid();
//                        String amount = receiptAmountSumModelList.get(j).getAmount();
//                        if (budgetid.equals(receiptDisDataList.get(i).getBudgetid())) {
//                            double newAmount = Double.parseDouble(amount) + Double.parseDouble(receiptDisDataList.get(i).getEkoshamount());
//                            item.setAmount(newAmount + "");
//                            receiptAmountSumModelList.set(j, item);
//                            isMatched = true;
//                            //since matched terminate internal loop
//                            j = receiptAmountSumModelList.size();
//                        }
//                    }
//                    //
//                    if (!isMatched) {
//                        receiptAmountSumModelList.add(item);
//                    }
//                }
//
//            }
//        }
//    }

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
                    pgBrstranstblList = presenter.getListBrsTranstableDateWise(textView82.getText().toString(), textView84.getText().toString(), PgActivity.pgCodeSelected);
                    //=========== generate reort =========
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
                    Toast.makeText(BrsReportActivity.this,"Please see report first",Toast.LENGTH_SHORT).show();
                }else{
                    if (!hasPermissions(BrsReportActivity.this, PERMISSIONS)) {
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
                BrsReportActivity.this,
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
            Toast.makeText(BrsReportActivity.this, "Please Select Valid Date", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(BrsReportActivity.this, "From Date Can't be Greater than To Date", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(BrsReportActivity.this, "To Date Can't be less than From Date", Toast.LENGTH_LONG).show();
                    }

                }
            }

        }
    }

    //================ generate report ==================
    private void generateReport(){
        finalReportList = new ArrayList<>();
        totalCashbook= 0.0;
        totalPassbook = 0.0;
        //============================== user paid details ====================
        //=================== get payment details (PgPayment Trasaction) ======
        if (pgBrstranstblList.size() > 0) {
            for (int i = 0; i < pgBrstranstblList.size(); i++) {
                BrsReportModel item = new BrsReportModel();

                //date corvert
                String[] fineDate = parseDate2(pgBrstranstblList.get(i).getDate());
                item.setMonth(fineDate[0]);
                item.setDate(parseDate(pgBrstranstblList.get(i).getDate()));
                item.setYear(fineDate[1]);
                item.setBalanceCashbook(pgBrstranstblList.get(i).getBalcashbook());
                item.setBalancePassbook(pgBrstranstblList.get(i).getBalpassbook());
                item.setImageCashbook(pgBrstranstblList.get(i).getCashbooklastpageimg());
                item.setImagePassbook(pgBrstranstblList.get(i).getPassbooklastpageimg());

                finalReportList.add(item);

                totalPassbook = totalPassbook + Double.valueOf(pgBrstranstblList.get(i).getBalpassbook());
                totalCashbook = totalCashbook + Double.valueOf(pgBrstranstblList.get(i).getBalcashbook());

            }
        }


        //================ update total amount =============
        totalPaymentAmt.setText(String.valueOf(totalPassbook));
        totalReceivedAmt.setText(String.valueOf(totalCashbook));

        //=========== sort the array date =====
        //=========== update on recycler ===========
        aAdapter = new BrsReportAdapter(this, finalReportList,presenter);
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

    public static String[] parseDate2(String inputDateString) {

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat outputDateFormatMonth = new SimpleDateFormat("MMMM");
        SimpleDateFormat outputDateFormatYear = new SimpleDateFormat("YYYY");

        Date date = null;
        String outputDateString = null;
        String year = null;
        String month = null;
        try {
            date = inputDateFormat.parse(inputDateString);
            month = outputDateFormatMonth.format(date);
            year = outputDateFormatYear.format(date);
        } catch (ParseException e) {
                e.printStackTrace();
        }
        String[] fineDate  ={
                month,
                year
        };
        return fineDate;
    }


//============= generate pdf =======
    public void generatePdfReport(){

        pdfGenerateDataList = new ArrayList<>();

        BrsReportModel header = new BrsReportModel();

        header.setMonth("Month");
        header.setYear("Year");
        header.setBalanceCashbook("Balance ( CashBook)");
        header.setBalancePassbook("Balance (Passbook)");
        header.setDate("Date");
        header.setPaymentmode("View");
        pdfGenerateDataList.add(header);


        pdfGenerateDataList.addAll(finalReportList);

        BrsReportModel Footer = new BrsReportModel();
        Footer.setYear("Total");
        Footer.setBalanceCashbook(String.valueOf(totalCashbook));
        Footer.setBalancePassbook(String.valueOf(totalPassbook));
//        Footer.setDate("");
//        Footer.setPaymentmode("");
        pdfGenerateDataList.add(Footer);

    }

    //============ open full image =====
    @Override
    public void showImage(String imageName){
        //open new fragment and pass image name ========
        Intent intent = new Intent(this, BrsImageActivity.class);
        intent.putExtra("imageName",imageName);
        startActivity(intent);
         //Toast.makeText(this,"View cashbook Image :"+imageName,Toast.LENGTH_SHORT).show();
    }

}
