package com.jslps.pgmisnew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jslps.pgmisnew.adapter.Pgmisloanrepaymentadapter;
import com.jslps.pgmisnew.database.Pgmisloanrepaymenttabl;
import com.jslps.pgmisnew.util.GetCurrentDate;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoanRepaymentFormActivity extends
        AppCompatActivity implements
        AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView116)
    TextView textView116;
    @BindView(R.id.et_no_of_mem)
    TextInputEditText etNoOfMem;
    @BindView(R.id.textInputLayout3)
    TextInputLayout textInputLayout3;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.textView113)
    TextView textView113;
    @BindView(R.id.textView114)
    TextView textView114;
    @BindView(R.id.textView115)
    TextView textView115;
    @BindView(R.id.constraintLayout4)
    ConstraintLayout constraintLayout4;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.textView117)
    TextView textView117;
    @BindView(R.id.textView118)
    TextView textView118;
    @BindView(R.id.parentContainer)
    ConstraintLayout parentContainer;
    String loanamount, loanid;
    double remainingamount;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    String selectedPaymentMode;
    @BindView(R.id.textView71)
     TextView textView71;
    @BindView(R.id.imageView16)
     ImageView imageView16;

    List<Pgmisloanrepaymenttabl> pgmisloanrepaymenttablList;
    Pgmisloanrepaymentadapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_repayment_form);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        loanid = intent.getStringExtra("loanid");
        loanamount = intent.getStringExtra("loanamount");
        spinner3.setOnItemSelectedListener(this);
        textView23.setText(LoanRepaymentActivity.pgname);
        textView116.setText(LoanRepaymentActivity.memname);
        textView117.setText("उधार की राशि: " + loanamount + "(Rs)");
        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOpenCalender();
            }
        });
        findingRemainingamount();
    }

    private void findingRemainingamount() {
        pgmisloanrepaymenttablList = Select.from(Pgmisloanrepaymenttabl.class)
                .where(Condition.prop("loanid").eq(loanid))
                .list();
        double totalpaidamountasloan=0;
        if(pgmisloanrepaymenttablList.size()>0){
            for(int i=0;i<pgmisloanrepaymenttablList.size();i++){
                totalpaidamountasloan = totalpaidamountasloan + Double.parseDouble(pgmisloanrepaymenttablList.get(i).getAmount());
            }
            aAdapter = new Pgmisloanrepaymentadapter(this, pgmisloanrepaymenttablList);
            LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recylerList.setLayoutManager(verticalLayoutmanager);
            recylerList.setAdapter(aAdapter);
        }
        remainingamount = Double.parseDouble(loanamount) -totalpaidamountasloan;
        textView118.setText("शेष राशि: "+remainingamount+"(Rs)");
    }

    @OnClick(R.id.button4)
    public void onViewClicked() {
        //check and update
        if(validation()){
            Pgmisloanrepaymenttabl data = new Pgmisloanrepaymenttabl(
                    PgActivity.pgCodeSelected,
                    UUID.randomUUID().toString(),
                    loanid,
                    new GetCurrentDate().getDate(),
                    etNoOfMem.getText().toString(),
                     "0",selectedPaymentMode,
                    textView71.getText().toString());

            data.save();
            findingRemainingamount();
            clearform();
            new StyleableToast
                    .Builder(this)
                    .text("डेटा सफलतापूर्वक सहेजा गया")
                    .iconStart(R.drawable.right)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    private void clearform() {
        etNoOfMem.setText("");
        spinner3.setSelection(0);
        textView71.setHint("लेन-देन दिनांक दर्ज करे");
    }

    private boolean validation() {
        boolean result = false;
        if (etNoOfMem.getText().toString().equals("")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया राशि दर्ज करें")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (selectedPaymentMode.equals("भुगतान का माध्यम")) {
            new StyleableToast
                    .Builder(this)
                    .text("भुगतान माध्यम का चयन करें")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if(textView71.getHint().toString().equals("लेन-देन दिनांक दर्ज करे")){
            new StyleableToast
                    .Builder(this)
                    .text("लेन-देन दिनांक दर्ज करे")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (remainingamount==0) {
            new StyleableToast
                    .Builder(this)
                    .text("यह ऋण राशि पेड है")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();

        }  else if (!etNoOfMem.getText().toString().equals("")) {
             double enteredamount = Double.parseDouble(etNoOfMem.getText().toString());
             if(enteredamount<=remainingamount && enteredamount>0){
                 result = true;
             }else{
                 new StyleableToast
                         .Builder(this)
                         .text("दर्ज किया गया मान्य राशि दर्ज करें")
                         .iconStart(R.drawable.wrong_icon_white)
                         .textColor(Color.WHITE)
                         .backgroundColor(getResources().getColor(R.color.colorPrimary))
                         .show();
             }
        }
        return result;
    }

    public void setOpenCalender() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                LoanRepaymentFormActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner3:
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                selectedPaymentMode =  parent.getSelectedItem().toString();
                if(selectedPaymentMode.equals("नकद")){
                    selectedPaymentMode="Cash";
                } else if(selectedPaymentMode.equals("बैंक")){
                    selectedPaymentMode="Bank";
                }
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
            Toast.makeText(LoanRepaymentFormActivity.this, "कृपया मान्य तिथि चुनें", Toast.LENGTH_LONG).show();
        } else {
            textView71.setText(newDate);
            textView71.setHint("");
        }
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,LoanRepaymentActivity.class);
        startActivity(intent);
        finish();
    }*/
}
