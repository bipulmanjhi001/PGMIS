package com.jslps.pgmisnew;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jslps.pgmisnew.adapter.BankwithdrawcashAdapter;
import com.jslps.pgmisnew.adapter.PgPaymentAdapter;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.PgBankWithdrawInteractor;
import com.jslps.pgmisnew.interactor.PgPaymentInteractor;
import com.jslps.pgmisnew.presenter.PgBankWithdrawPresenter;
import com.jslps.pgmisnew.presenter.PgPaymentPresenter;
import com.jslps.pgmisnew.util.AppConstant;
import com.jslps.pgmisnew.util.SetSpinnerText;
import com.jslps.pgmisnew.view.PgBankWithdrawView;
import com.jslps.pgmisnew.view.PgPaymentView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankWithdrawCashDeposit extends AppCompatActivity implements PgBankWithdrawView,
        AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.textView71)
    TextView textView71;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.et_enter_amount)
    EditText et_enter_amount;
    @BindView(R.id.textView6)
    TextView textView6;
    PgBankWithdrawPresenter presenter;
    List<PgBankwithdrawcashdeposit> pgBankwithdrawcashdeposits;
    BankwithdrawcashAdapter aAdapter;
    String selectedPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_withdraw_cash_deposit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //edit mode should be false at start
        presenter = new PgBankWithdrawPresenter(new PgBankWithdrawInteractor(), this);
        spinner2.setOnItemSelectedListener(this);
        presenter.setRecyclerView();
        presenter.setPgName();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner2:
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                selectedPayment =  parent.getSelectedItem().toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void setOpenCalender() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                BankWithdrawCashDeposit.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void blankDate() {
        new StyleableToast
                .Builder(this)
                .text("दिनांक रिक्त नहीं हो सकता")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void blankamount() {
        new StyleableToast
                .Builder(this)
                .text("कृपया रकम भर ले")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void dataSaved() {
        new StyleableToast
                .Builder(this)
                .text("डेटा सफलतापूर्वक सहेजा गया")
                .iconStart(R.drawable.right)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
        presenter.setRecyclerView();
        presenter.clearForm();
    }

    @Override
    public void setRecyclerView() {
        pgBankwithdrawcashdeposits = presenter.getPgBankwithdrawcashdeposit(PgActivity.pgCodeSelected);
        Collections.reverse(pgBankwithdrawcashdeposits);
        aAdapter = new BankwithdrawcashAdapter(this, pgBankwithdrawcashdeposits, presenter);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
    }

    @Override
    public void setPgName() {
        textView6.setText(PgActivity.pgNameSelected);
    }

    @Override
    public void clearForm() {
        textView71.setText("");
        textView71.setHint("भुगतान की तिथि");
        et_enter_amount.setText("");
        spinner2.setSelection(0);
    }

    @Override
    public void blankPaymentmode() {
        new StyleableToast
                .Builder(this)
                .text("कृपया विकल्प चुनें")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void editRecord(PgBankwithdrawcashdeposit item) {

    }

    @OnClick({R.id.imageView16, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView16:
                presenter.openCalender();
                break;
            case R.id.button4:
                //for saving
                if (validation()) {
                    String date = textView71.getText().toString();
                    String amount = et_enter_amount.getText().toString();
                    String pgCode = PgActivity.pgCodeSelected;
                    String[] userDetails = presenter.getUserDetails();
                    String username = userDetails[0];
                    String userid = userDetails[1];
                    String isexported = "0";
                    String uuid = UUID.randomUUID().toString();

                    presenter.saveData(amount,uuid,pgCode,username, userid,isexported,"0" ,selectedPayment,date);
                }
                break;
        }
    }

    private boolean validation() {
        boolean result = false;
        if (selectedPayment.equals("विकल्प चुनें")) {
            presenter.blankPaymentmode();
        }else if (textView71.getHint().toString().equals("भुगतान की तिथि") || textView71.getText().toString().equals("")) {
            presenter.blankDate();
        } else if (et_enter_amount.getText().toString().equals("") || et_enter_amount.getText().toString().equals("0")) {
            presenter.blankamount();
        } else {
            result = true;
        }
        return result;
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
            Toast.makeText(BankWithdrawCashDeposit.this, "कृपया मान्य तिथि चुनें", Toast.LENGTH_LONG).show();
        } else {
            textView71.setText(newDate);
            textView71.setHint("");
        }
    }
}
