package com.jslps.pgmisnew;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ablanco.imageprovider.ImageProvider;
import com.ablanco.imageprovider.ImageSource;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.Pgcapitalsavetbl;
import com.jslps.pgmisnew.database.Pgmemshipfeesavetbl;
import com.jslps.pgmisnew.database.Pgmisbrsimgtbl;
import com.jslps.pgmisnew.database.Pgmisbrstransaddsubtbl;
import com.jslps.pgmisnew.database.Pgmisbrstranstbl;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.util.AlertView;
import com.jslps.pgmisnew.util.AppConstant;
import com.jslps.pgmisnew.util.CheckConnectivity;
import com.jslps.pgmisnew.util.GetCurrentDate;
import com.jslps.pgmisnew.util.VolleyString;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BRSActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,VolleyString.VolleyListner {

    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
    };

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView123)
    TextView textView123;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView23)
    ImageView imageView23;
    @BindView(R.id.textView124)
    TextView textView124;
    @BindView(R.id.textView128)
    TextView textView128;
    @BindView(R.id.textView130)
    TextView textView130;
    @BindView(R.id.textView132)
    TextView textView132;
    @BindView(R.id.textView125)
    TextView textView125;
    @BindView(R.id.textView129)
    TextView textView129;
    @BindView(R.id.textView131)
    TextView textView131;
    @BindView(R.id.textView126)
    TextView textView126;
    @BindView(R.id.textView127)
    TextView textView127;
    @BindView(R.id.textView133)
    TextView textView133;
    @BindView(R.id.textView134)
    TextView textView134;
    @BindView(R.id.textView135)
    TextView textView135;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.textView1244)
    TextView textView1244;
    @BindView(R.id.textView138)
    TextView textView138;
    @BindView(R.id.textView140)
    TextView textView140;
    @BindView(R.id.textView142)
    TextView textView142;
    @BindView(R.id.textView144)
    TextView textView144;
    @BindView(R.id.textView146)
    TextView textView146;
    @BindView(R.id.textView148)
    TextView textView148;
    @BindView(R.id.textView136)
    TextView textView136;
    @BindView(R.id.textView1255)
    TextView textView1255;
    @BindView(R.id.textView139)
    TextView textView139;
    @BindView(R.id.textView141)
    TextView textView141;
    @BindView(R.id.textView143)
    TextView textView143;
    @BindView(R.id.textView145)
    TextView textView145;
    @BindView(R.id.textView147)
    TextView textView147;

    @BindView(R.id.textView157)
    TextView textView157;
    @BindView(R.id.textView149)
    TextView textView149;
    @BindView(R.id.textView150)
    TextView textView150;
    @BindView(R.id.textView151)
    TextView textView151;
    @BindView(R.id.textView153)
    TextView textView153;
    @BindView(R.id.textView154)
    TextView textView154;
    @BindView(R.id.textView155)
    TextView textView155;
    @BindView(R.id.textView156)
    TextView textView156;
    @BindView(R.id.textView137)
    TextView textView137;
    @BindView(R.id.textView152)
    TextView textView152;
    @BindView(R.id.editText3)

    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.editText5)
    EditText editText5;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.editText7)
    EditText editText7;
    @BindView(R.id.editText8)
    EditText editText8;
    @BindView(R.id.textView12444)
    TextView textView12444;
    @BindView(R.id.textView158)
    TextView textView158;
    @BindView(R.id.textView160)
    TextView textView160;
    @BindView(R.id.textView162)
    TextView textView162;
    @BindView(R.id.textView165)
    TextView textView165;
    @BindView(R.id.textView168)
    TextView textView168;
    @BindView(R.id.textView170)
    TextView textView170;
    @BindView(R.id.textView12555)
    TextView textView12555;
    @BindView(R.id.textView159)
    TextView textView159;

    @BindView(R.id.textView161)
    TextView textView161;
    @BindView(R.id.textView163)
    TextView textView163;
    @BindView(R.id.textView167)
    TextView textView167;
    @BindView(R.id.textView169)
    TextView textView169;
    @BindView(R.id.textView171)
    TextView textView171;
    @BindView(R.id.textView172)
    TextView textView172;
    @BindView(R.id.textView173)
    TextView textView173;
    @BindView(R.id.textView175)
    TextView textView175;
    @BindView(R.id.textView176)
    TextView textView176;
    @BindView(R.id.textView177)
    TextView textView177;

    @BindView(R.id.textView178)
    TextView textView178;
    @BindView(R.id.textView174)
    TextView textView174;
    @BindView(R.id.textView179)
    TextView textView179;
    @BindView(R.id.editText9)
    EditText editText9;
    @BindView(R.id.editText10)
    EditText editText10;
    @BindView(R.id.editText11)
    EditText editText11;
    @BindView(R.id.editText12)
    EditText editText12;
    @BindView(R.id.editText13)
    EditText editText13;

    @BindView(R.id.imageView24)
    ImageView imageView24;
    @BindView(R.id.imageView25)
    ImageView imageView25;
    @BindView(R.id.textView164)
    TextView textView164;
    @BindView(R.id.textView166)
    TextView textView166;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.textView180)
    TextView textView180;

    double bankbalanceaspercashbook = 0;
    @BindView(R.id.textView181)
    TextView textView181;
    @BindView(R.id.imageView26)
    ImageView imageView26;
    @BindView(R.id.textView182)
    TextView textView182;
    @BindView(R.id.imageView27)
    ImageView imageView27;
    @BindView(R.id.imageView28)
    ImageView imageView28;
    @BindView(R.id.textView183)
    TextView textView183;
    double receivedSum = 0;
    double paymentSum = 0;

    double ADDsum = 0;
    double LESSsum = 0;
    @BindView(R.id.actual_difference)
    TextView actualDifference;
    @BindView(R.id.calc)
     Button calc;
    Dialog dilogCamPopUp;
    String clickedImageViewName = "";
    String imageFileName1="";
    String imageFileName2="";
    List<Pgmisbrstranstbl> pgmisbrstranstbls;
    List<Integer> listcheckmax;
    List<String> listdates;
    String newestDate="";
    String imagefilename,image1="",image2="",sUID;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_r_s);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        try {
            textView23.setText(PgActivity.pgNameSelected);
            if (!hasPermissions(BRSActivity.this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
            }
            dilogCamPopUp = new Dialog(this);
            blockingDatesInCalender();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void blockingDatesInCalender() {
        try {
            pgmisbrstranstbls = Select.from(Pgmisbrstranstbl.class)
                    .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                    .list();
            listcheckmax = new ArrayList<>();
            listdates = new ArrayList<>();
            if (pgmisbrstranstbls.size() > 0) {
                for (int i = 0; i < pgmisbrstranstbls.size(); i++) {
                    try {
                        String date = pgmisbrstranstbls.get(i).getDate();
                        try {
                            int intdate = Integer.parseInt(pgmisbrstranstbls.get(i).getDate().replace("-", ""));
                            listcheckmax.add(intdate);
                            listdates.add(date);
                            System.out.println("");
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    int number = Collections.max(listcheckmax);
                    int position = listcheckmax.indexOf(Collections.max(listcheckmax));
                    newestDate = listdates.get(position);
                    System.out.print("");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        String month = (monthOfYear + 1) + "";

        int lastdayofpreviousmonth;
        String lastdateofprevious;
        if (monthOfYear == 0) {
            int newyear = year - 1;
            int newmonth = 12;
            lastdayofpreviousmonth = leapyearandlastday(newyear, newmonth);
            lastdateofprevious = newyear + "/" + newmonth + "/" + lastdayofpreviousmonth;
        } else {
            lastdayofpreviousmonth = leapyearandlastday(year, monthOfYear);
            int digit = Integer.toString(monthOfYear).length();
            if (digit == 1) {
                lastdateofprevious = year + "/" + "0" + monthOfYear + "/" + lastdayofpreviousmonth;
            } else {
                lastdateofprevious = year + "/" + monthOfYear + "/" + lastdayofpreviousmonth;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        Date date1 = null, date2 = null;
        try {
            date1 = sdf.parse(date);
            date2 = sdf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int maxdate=0;
        String yearmonth="";
        int enteredsetdate = Integer.parseInt(lastdateofprevious.replace("/",""));

        if(!newestDate.equals("")){
            maxdate = Integer.parseInt(newestDate.replace("/",""));
            String[] monthyearlist = lastdateofprevious.split("/");
            yearmonth=monthyearlist[0]+"/"+monthyearlist[1];
        }
        if (date1 != null && date1.compareTo(date2) > 0) {
            Toast.makeText(BRSActivity.this, "Please Select Valid Date", Toast.LENGTH_LONG).show();
        }else if(enteredsetdate<=maxdate){
            new AlertView(BRSActivity.this,"BRS Exists","BRS for year/month "+yearmonth+" is already prepared or blocked","Ok");
        } else {
            editText.setText(lastdateofprevious);
            bankbalanceaspercashbook=0.0;
            receivedSum=0.0;
            paymentSum=0.0;
            calculateBankBalanceaspercashbook(lastdateofprevious);
            setthevalues();
        }
    }

    private void setthevalues() {
        textView180.setText(bankbalanceaspercashbook + "");
    }

    private void calculateBankBalanceaspercashbook(String lastdateofprevious) {
        try {
            //getting data from disbursement tbl of pg
            List<PgReceiptDisData> receiptDisDataList = Select.from(PgReceiptDisData.class)
                    .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                    .where(Condition.prop("approveddate").lt(lastdateofprevious)).or(Condition.prop("approveddate").eq(lastdateofprevious))
                    .list();

            for (int i = 0; i < receiptDisDataList.size(); i++) {
                double amount = Double.parseDouble(receiptDisDataList.get(i).getEkoshamount());
                receivedSum = receivedSum + amount;
            }

            //getting data from ReceiptTranstbl of pg
            List<PgReceiptTranstbl> receiptData = Select.from(PgReceiptTranstbl.class)
                    .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                    .where(Condition.prop("Paymentmode").eq("Bank"))
                    .where(Condition.prop("isexported").eq("1"))
                    .where(Condition.prop("date").lt(lastdateofprevious)).or(Condition.prop("date").eq(lastdateofprevious))
                    .list();

            for (int i = 0; i < receiptData.size(); i++) {
                double amount = Double.parseDouble(receiptData.get(i).getAmount());
                receivedSum = receivedSum + amount;
            }

            //getting data from Membershipfee tbl of pg
            List<Pgmemshipfeesavetbl> pgmemshipfeesavetbls = Select.from(Pgmemshipfeesavetbl.class)
                    .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                    .where(Condition.prop("paymentmode").eq("bank"))
                    .where(Condition.prop("isexported").eq("1"))
                    .where(Condition.prop("paymentdate").lt(lastdateofprevious)).or(Condition.prop("paymentdate").eq(lastdateofprevious))
                    .list();

            for (int i = 0; i < pgmemshipfeesavetbls.size(); i++) {
                double amount = Double.parseDouble(pgmemshipfeesavetbls.get(i).getAmount());
                receivedSum = receivedSum + amount;
            }

            //getting data from Share Capital tbl of pg
            List<Pgcapitalsavetbl> pgcapitalsavetbls = Select.from(Pgcapitalsavetbl.class)
                    .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                    .where(Condition.prop("paymentmode").eq("bank"))
                    .where(Condition.prop("isexported").eq("1"))
                    .where(Condition.prop("paymentdate").lt(lastdateofprevious)).or(Condition.prop("paymentdate").eq(lastdateofprevious))
                    .list();

            for (int i = 0; i < pgcapitalsavetbls.size(); i++) {
                double amount = Double.parseDouble(pgcapitalsavetbls.get(i).getAmount());
                receivedSum = receivedSum + amount;
            }

            //getting data from pgpaymenttbl
            List<PgPaymentTranstbl> pgPaymentTranstblList = Select.from(PgPaymentTranstbl.class)
                    .where(Condition.prop("PG_Code").eq(PgActivity.pgCodeSelected))
                    .where(Condition.prop("paymentmode").eq("bank"))
                    .where(Condition.prop("isexported").eq("1"))
                    .where(Condition.prop("date").lt(lastdateofprevious)).or(Condition.prop("date").eq(lastdateofprevious))
                    .list();

            for (int i = 0; i < pgPaymentTranstblList.size(); i++) {
                double amount = Double.parseDouble(pgPaymentTranstblList.get(i).getAmount());
                paymentSum = paymentSum + amount;
            }

            //================== puarchase ammout with payment amount =======
            List<Itempurchasedbypgtbl> ItempurchasedbypgtblList = Select.from(Itempurchasedbypgtbl.class)
                    .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                    .where(Condition.prop("paymentmode").eq("Bank"))
                    .where(Condition.prop("isexported").eq("1"))
                    .where(Condition.prop("selecteddate").lt(lastdateofprevious)).or(Condition.prop("selecteddate").eq(lastdateofprevious))
                    .list();

            for (int i = 0; i < ItempurchasedbypgtblList.size(); i++) {
                Double amount = Double.valueOf(ItempurchasedbypgtblList.get(i).getRate()) * Double.valueOf(ItempurchasedbypgtblList.get(i).getQuantity());
                paymentSum = paymentSum + amount;
            }
            bankbalanceaspercashbook = receivedSum - paymentSum;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.imageView23, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView23:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        BRSActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;

            case R.id.btn_save:
                try {
                    if (calculateAMinusB()) {
                        caculateAddsum();
                        caculateLESSSUM();
                        if (calculateActualDifference() == 0) {
                            //save data
                            if (imageFileName1.length() > 5 && imageFileName2.length() > 5) {
                                String uuid = UUID.randomUUID().toString();
                                Pgmisbrstranstbl data = new Pgmisbrstranstbl(
                                        uuid,
                                        editText.getText().toString(),
                                        textView180.getText().toString(),
                                        editText2.getText().toString(),
                                        imageFileName1,
                                        imageFileName2,
                                        "0",
                                        new GetCurrentDate().getDate(),
                                        PgActivity.pgCodeSelected);

                                data.save();
                                saveinadtbl(uuid);
                                saveinlesstbl(uuid);

                                //saving image in Imagetbl
                                if (imageFileName1.length() > 5) {
                                    Pgmisbrsimgtbl pgmisbrsimgtbl = new Pgmisbrsimgtbl("0",
                                            imageFileName1, image1, PgActivity.pgCodeSelected);
                                    pgmisbrsimgtbl.save();

                                    CheckConnectivity checkConnectivity = new CheckConnectivity();
                                    if (checkConnectivity.CheckConnection(this)) {
                                        DialogShow();
                                        RequestQueue mRequestQueue;
                                        StringRequest mStringRequest;
                                        mRequestQueue = Volley.newRequestQueue(this);
                                        mStringRequest = new VolleyString(AppConstant.domain + "/" + AppConstant.Upload_Image, AppConstant.UploadBRSImage, this).postPgmisBrsImage(image1, imageFileName1);
                                        mRequestQueue.add(mStringRequest);
                                    }
                                }

                                if (imageFileName2.length() > 5) {
                                    Pgmisbrsimgtbl pgmisbrsimgtbl = new Pgmisbrsimgtbl("0",
                                            imageFileName2, image2, PgActivity.pgCodeSelected);
                                    pgmisbrsimgtbl.save();

                                    CheckConnectivity checkConnectivity = new CheckConnectivity();
                                    if(checkConnectivity.CheckConnection(this)) {
                                        DialogShow();
                                        RequestQueue mRequestQueue;
                                        StringRequest mStringRequest;
                                        mRequestQueue = Volley.newRequestQueue(this);
                                        mStringRequest = new VolleyString(AppConstant.domain + "/" + AppConstant.Upload_Image, AppConstant.UploadBRSImage, this).postPgmisBrsImage(image2, imageFileName2);
                                        mRequestQueue.add(mStringRequest);
                                    }
                                }
                                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_LONG).show();
                                finish();

                                Intent intent = new Intent(this, BRSActivity.class);
                                startActivity(intent);

                            } else {
                                new AlertView(BRSActivity.this, "Error", "Upload Images", "Try Again");
                            }
                        } else {
                            new AlertView(BRSActivity.this, "Error", "Actual Difference should be Zero", "Try Again");
                        }
                    }
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }
        }
    }

    private void saveinlesstbl(String uuid) {
        try {
            double amount1 = 0;
            double amount2 = 0;
            double amount3 = 0;
            double amount4 = 0;
            double amount5 = 0;

            if (!editText9.getText().toString().equals("")) {
                amount1 = Double.parseDouble(editText9.getText().toString());
                Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid, "7",
                        "LESS", amount1 + "", new GetCurrentDate().getDate(),
                        "0", PgActivity.pgCodeSelected);

                data.save();
            }

            if (!editText10.getText().toString().equals("")) {
                amount2 = Double.parseDouble(editText10.getText().toString());
                Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid, "8", "LESS",
                        amount2 + "", new GetCurrentDate().getDate(), "0", PgActivity.pgCodeSelected);

                data.save();
            }

            if (!editText11.getText().toString().equals("")) {
                amount3 = Double.parseDouble(editText11.getText().toString());
                Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid, "9",
                        "LESS", amount3 + "", new GetCurrentDate().getDate(),
                        "0", PgActivity.pgCodeSelected);

                data.save();
            }

            if (!editText12.getText().toString().equals("")) {
                amount4 = Double.parseDouble(editText12.getText().toString());
                Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid, "10",
                        "LESS", amount4 + "", new GetCurrentDate().getDate(),
                        "0", PgActivity.pgCodeSelected);

                data.save();
            }

            if (!editText13.getText().toString().equals("")) {
                amount5 = Double.parseDouble(editText13.getText().toString());
                Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid, "11", "LESS",
                        amount5 + "", new GetCurrentDate().getDate(),
                        "0", PgActivity.pgCodeSelected);

                data.save();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveinadtbl(String uuid) {
        try{
        double amount1 = 0;
        double amount2 = 0;
        double amount3 = 0;
        double amount4 = 0;
        double amount5 = 0;
        double amount6 = 0;

        if (!editText3.getText().toString().equals("")) {
            amount1 = Double.parseDouble(editText3.getText().toString());
            Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid,"1",
                    "ADD",amount1+"",new GetCurrentDate().getDate(),
                    "0",PgActivity.pgCodeSelected);
            data.save();
        }

        if (!editText4.getText().toString().equals("")) {
            amount2 = Double.parseDouble(editText4.getText().toString());
            Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid,"2",
                    "ADD",amount2+"",new GetCurrentDate().getDate(),
                    "0",PgActivity.pgCodeSelected);

            data.save();
        }

        if (!editText5.getText().toString().equals("")) {
            amount3 = Double.parseDouble(editText5.getText().toString());
            Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid,"3",
                    "ADD",amount3+"",
                    new GetCurrentDate().getDate(),
                    "0",PgActivity.pgCodeSelected);

            data.save();
        }

        if (!editText6.getText().toString().equals("")) {
            amount4 = Double.parseDouble(editText6.getText().toString());
            Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid,"4",
                    "ADD",amount4+"",
                    new GetCurrentDate().getDate(),
                    "0",PgActivity.pgCodeSelected);

            data.save();
        }

        if (!editText7.getText().toString().equals("")) {
            amount5 = Double.parseDouble(editText7.getText().toString());
            Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid,"5","ADD",
                    amount5+"",new GetCurrentDate().getDate(),
                    "0",PgActivity.pgCodeSelected);

            data.save();
        }

        if (!editText8.getText().toString().equals("")) {
            amount6 = Double.parseDouble(editText8.getText().toString());
            Pgmisbrstransaddsubtbl data = new Pgmisbrstransaddsubtbl(uuid,"6","ADD",
                    amount6+"",new GetCurrentDate().getDate(),
                    "0",PgActivity.pgCodeSelected);

            data.save();
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private int leapyearandlastday(int year, int month) {
            boolean leapyear = false;
            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                //leap year
                leapyear = true;
            }
            if (month == 1) {
                return 31;
            } else if (month == 2) {
                if (leapyear) {
                    return 29;
                } else {
                    return 28;
                }
            } else if (month == 3) {
                return 31;
            } else if (month == 4) {
                return 30;
            } else if (month == 5) {
                return 31;
            } else if (month == 6) {
                return 30;
            } else if (month == 7) {
                return 31;
            } else if (month == 8) {
                return 31;
            } else if (month == 9) {
                return 30;
            } else if (month == 10) {
                return 31;
            } else if (month == 11) {
                return 30;
            } else if (month == 12) {
                return 31;
            } else {
                return 31;
            }
    }


    @OnClick(R.id.imageView26)
    public void onViewClicked() {
        calculateAMinusB();
    }

    private boolean calculateAMinusB() {
        boolean result = false;
        if (textView180.getText().toString().equals("")) {
            Toast.makeText(BRSActivity.this, "Bank Balance as per cash book can't be empty", Toast.LENGTH_SHORT).show();
        } else if (editText2.getText().toString().equals("")) {
            Toast.makeText(BRSActivity.this, "Bank Balance as per pass book can't be empty", Toast.LENGTH_SHORT).show();
        } else {
            double difference = bankbalanceaspercashbook - Double.parseDouble(editText2.getText().toString());
            textView181.setText(difference + "");
            result = true;
        }
        return result;
    }

    @OnClick(R.id.imageView27)
    public void onViewClicked1() {
        caculateAddsum();
    }

    private void caculateAddsum() {
        try {
            double amount1 = 0;
            double amount2 = 0;
            double amount3 = 0;
            double amount4 = 0;
            double amount5 = 0;
            double amount6 = 0;

            if (!editText3.getText().toString().equals("")) {
                amount1 = Double.parseDouble(editText3.getText().toString());
            }

            if (!editText4.getText().toString().equals("")) {
                amount2 = Double.parseDouble(editText4.getText().toString());
            }

            if (!editText5.getText().toString().equals("")) {
                amount3 = Double.parseDouble(editText5.getText().toString());
            }

            if (!editText6.getText().toString().equals("")) {
                amount4 = Double.parseDouble(editText6.getText().toString());
            }

            if (!editText7.getText().toString().equals("")) {
                amount5 = Double.parseDouble(editText7.getText().toString());
            }

            if (!editText8.getText().toString().equals("")) {
                amount6 = Double.parseDouble(editText8.getText().toString());
            }

            ADDsum = amount1 + amount2 + amount3 + amount4 + amount5 + amount6;
            textView182.setText(ADDsum + "");
        }catch (Exception e){
        e.printStackTrace();
    }
    }

    @OnClick(R.id.imageView28)
    public void onViewClicked2() {
        caculateLESSSUM();
    }

    private void caculateLESSSUM() {
        try {
            double amount1 = 0;
            double amount2 = 0;
            double amount3 = 0;
            double amount4 = 0;
            double amount5 = 0;

            if (!editText9.getText().toString().equals("")) {
                amount1 = Double.parseDouble(editText9.getText().toString());
            }

            if (!editText10.getText().toString().equals("")) {
                amount2 = Double.parseDouble(editText10.getText().toString());
            }

            if (!editText11.getText().toString().equals("")) {
                amount3 = Double.parseDouble(editText11.getText().toString());
            }

            if (!editText12.getText().toString().equals("")) {
                amount4 = Double.parseDouble(editText12.getText().toString());
            }

            if (!editText13.getText().toString().equals("")) {
                amount5 = Double.parseDouble(editText13.getText().toString());
            }

            LESSsum = amount1 + amount2 + amount3 + amount4 + amount5;
            textView183.setText(LESSsum + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.calc)
    public void onViewClicked4() {
        calculateActualDifference();
    }

    private double calculateActualDifference() {
            double diff = 100;
            //making sure it is not zero without calculation
        try{
            if (textView181.getText().toString().equals("")) {
                Toast.makeText(BRSActivity.this, "Please Calculate (A-B) first", Toast.LENGTH_SHORT).show();
            } else {
                double C = Double.parseDouble(textView181.getText().toString());
                double actualdif = C + ADDsum - LESSsum;
                actualDifference.setText("Actual Difference(C+D-E)= " + actualdif);
                diff = actualdif;
            }
    } catch (Exception e) {
        e.printStackTrace();
    }
            return diff;
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

    @OnClick({R.id.imageView24, R.id.imageView25})
    public void onViewClicked6(View view) {
        switch (view.getId()) {
            case R.id.imageView24:
                clickedImageViewName="imageView24";
                openCameraGalleryDialog();
            break;
            case R.id.imageView25:
                clickedImageViewName="imageView25";
                openCameraGalleryDialog();
                break;
        }
    }

    public void openCameraGalleryDialog() {
     try {
         ImageView camera, gallery;
         TextView tv_camera, tv_gallery;
         LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view = inflater.inflate(R.layout.camera_gallery_option_popup, null, false);
         dilogCamPopUp.setCanceledOnTouchOutside(true);
         dilogCamPopUp.setContentView(view);
         dilogCamPopUp.getWindow().setBackgroundDrawable(new ColorDrawable(0));
         dilogCamPopUp.show();
         camera = dilogCamPopUp.findViewById(R.id.newsfeedcamera);
         gallery = dilogCamPopUp.findViewById(R.id.newsfeedgallery);
         tv_camera = dilogCamPopUp.findViewById(R.id.textView3);
         tv_gallery = dilogCamPopUp.findViewById(R.id.textView4);

         camera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openCamera();
                 dilogCamPopUp.dismiss();
             }
         });

         gallery.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openGallery();
                 dilogCamPopUp.dismiss();
             }
         });

         tv_camera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openCamera();
                 dilogCamPopUp.dismiss();
             }
         });

         tv_gallery.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openGallery();
                 dilogCamPopUp.dismiss();
             }
         });
     }catch (Exception e){
         e.printStackTrace();
     }
    }

    private void openGallery() {
        ImageProvider imageProvider = new ImageProvider(this);
        imageProvider.getImage(ImageSource.GALLERY,bitmap -> {
            if(bitmap!=null) {
                loadandsaveimage(bitmap);
            }
            return null;
        });
    }

    private void openCamera() {
        ImageProvider imageProvider = new ImageProvider(this);
        imageProvider.getImage(ImageSource.CAMERA,bitmap -> {
            if(bitmap!=null){
                loadandsaveimage(bitmap);
            }
            return null;
        });
    }

    private void loadandsaveimage(Bitmap thumbnail){
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            File temp = new File(AppConstant.imagePath);
            if (!temp.exists()) {
                temp.mkdirs();
            }
            sUID = UUID.randomUUID().toString();
            imagefilename = UUID.randomUUID().toString() + ".jpg";

            File outputFile = new File(temp, imagefilename);
            FileOutputStream fo;
            try {
                if (!outputFile.exists())
                    outputFile.createNewFile();
                fo = new FileOutputStream(outputFile);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //setting image
            //imageView.setImageBitmap(thumbnail);
            if (clickedImageViewName.equals("imageView24")) {
                imageFileName1 = imagefilename;
                imageView24.setImageBitmap(thumbnail);
                image1 = getStringImage(thumbnail);
                Log.d("base64", image1);

            } else {
                imageFileName2 = imagefilename;
                imageView25.setImageBitmap(thumbnail);
                image2 = getStringImage(thumbnail);
                Log.d("base64", image2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getStringImage(Bitmap bmp){
        //added resize image bmp
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        bmp.createScaledBitmap(bmp,60,60,true);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void DialogShow() {
        progress= new ProgressDialog(this);
        progress = new ProgressDialog(this);
        progress.setMessage("अपलोड जारी है, कृपया प्रतीक्षा करें");
        progress.setCancelable(false);
        progress.show();
    }

    private void DialogClose(){
        if(progress!=null){
            progress.dismiss();
        }
    }

    @Override
    public void onResponseSuccess(String tableIndentifier, String result) {
        if(tableIndentifier.equals(AppConstant.UploadBRSImage)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    for(int i=0;i<PgActivity.pgmisbrsimgtbls.size();i++){
                        PgActivity.pgmisbrsimgtbls.get(i).setIsexported("1");
                        PgActivity.pgmisbrsimgtbls.get(i).save();
                    }
                    new StyleableToast
                            .Builder(this)
                            .text("Successfully Uploaded")
                            .iconStart(R.drawable.right)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                    DialogClose();

                } if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgmisbrsimgtbls")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                    DialogClose();
                }
            } catch (JSONException e) {
                e.printStackTrace();
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
}
