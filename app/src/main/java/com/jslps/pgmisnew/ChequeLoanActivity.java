package com.jslps.pgmisnew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jslps.pgmisnew.adapter.BankwithdrawcashAdapter;
import com.jslps.pgmisnew.adapter.ChequeLoanAdapter;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.ChequeLoanInteractor;
import com.jslps.pgmisnew.interactor.PgBankWithdrawInteractor;
import com.jslps.pgmisnew.presenter.ChequeLoanPresenter;
import com.jslps.pgmisnew.presenter.PgBankWithdrawPresenter;
import com.jslps.pgmisnew.util.GetCurrentDate;
import com.jslps.pgmisnew.view.ChequeLoanView;
import com.jslps.pgmisnew.view.PgBankWithdrawView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;
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

public class ChequeLoanActivity extends AppCompatActivity implements ChequeLoanView,
        AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.spinner4)
    Spinner spinner4;
    @BindView(R.id.payment_date)
    TextView payment_date;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.et_amount)
    EditText et_amount;
    @BindView(R.id.et_remark)
    TextView et_remark;
    @BindView(R.id.headspinner)
    Spinner headspinner;

    ChequeLoanPresenter presenter;
    List<PgmisChequeLoantbl> pgmisChequeLoantbls;
    ChequeLoanAdapter aAdapter;
    String selectedPayment;
    String pgcode,grpcode,grpmemcode,memname;
    List<TblMstPgPaymentReceipthead> pgChequeHeadModelList;
    public ArrayAdapter<TblMstPgPaymentReceipthead> headSpinAdapter;
    TblMstPgPaymentReceipthead headModelSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque_loan);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        pgcode = intent.getStringExtra("pgcode");
        grpcode = intent.getStringExtra("grpcode");
        grpmemcode = intent.getStringExtra("grpmemcode");
        memname = intent.getStringExtra("membername");
        if(!memname.isEmpty()){
            textView24.setText(memname);
        }
        //edit mode should be false at start
        presenter = new ChequeLoanPresenter(new ChequeLoanInteractor(), this);
        spinner4.setOnItemSelectedListener(this);
        headspinner.setOnItemSelectedListener(this);
        presenter.getHeadList();
        presenter.setSpinnerHead();
        presenter.setRecyclerView();
        presenter.setPgName();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.headspinner:
                if (position == 0) {
                    ((TextView) view).setText("मद का नाम चुने");
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                headModelSelected = (TblMstPgPaymentReceipthead) parent.getSelectedItem();
                break;

            case R.id.spinner4:
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                selectedPayment = parent.getSelectedItem().toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void getHeadList(List<TblMstPgPaymentReceipthead> list) {
        pgChequeHeadModelList = list;
    }

    @Override
    public void setSpinnerHead() {
        headSpinAdapter = new ArrayAdapter<TblMstPgPaymentReceipthead>(this, android.R.layout.simple_spinner_dropdown_item, pgChequeHeadModelList) {
        };
        headspinner.setAdapter(headSpinAdapter);
    }

    @Override
    public void setOpenCalender() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                ChequeLoanActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void blankHead() {
        new StyleableToast
                .Builder(this)
                .text("कृपया मद का नाम चुने")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
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
                .text("कृपया राशि भर ले")
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
        pgmisChequeLoantbls =  Select.from(PgmisChequeLoantbl.class)
                .where(Condition.prop("pgcode").eq(pgcode))
                .where(Condition.prop("grpcode").eq(grpcode))
                .where(Condition.prop("grpmemcode").eq(grpmemcode))
                .where(Condition.prop("appliedforloan").eq("0"))
                .list();

        //presenter.getPgmischequeloan(PgActivity.pgCodeSelected);
        Collections.reverse(pgmisChequeLoantbls);
        aAdapter = new ChequeLoanAdapter(this, pgmisChequeLoantbls, presenter);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
    }

    @Override
    public void setPgName() {
        textView23.setText(PgActivity.pgNameSelected);
    }

    @Override
    public void clearForm() {
        presenter.setSpinnerHead();
        payment_date.setText("");
        payment_date.setHint("चेक तिथि का चयन करे");
        et_amount.setText("");
        et_remark.setText("");
        spinner4.setSelection(0);
    }

    @Override
    public void blankPaymentmode() {
        new StyleableToast
                .Builder(this)
                .text("कृपया चेक का माध्यम चुनें")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void editRecord(PgmisChequeLoantbl item) {

    }

    @OnClick({R.id.call_calender, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_calender:
                presenter.openCalender();
                break;
            case R.id.button4:
                //for saving
                if (validation()) {
                    String budget_code = headModelSelected.getBudgetid();
                    String head_name = headModelSelected.getHeadname();
                    String BMID = headModelSelected.getBMID();
                    String date = payment_date.getText().toString();
                    String amount = et_amount.getText().toString();
                    String remark = et_remark.getText().toString();
                    String pgCode = PgActivity.pgCodeSelected;
                    String[] userDetails = presenter.getUserDetails();
                    String username = userDetails[0];
                    String userid = userDetails[1];
                    String isexported = "0";
                    String uuid = UUID.randomUUID().toString();
                    String entrydate = new GetCurrentDate().getDate();

                    presenter.saveData(uuid,pgCode,grpcode,grpmemcode,isexported,entrydate,username,"0",date,amount,remark,selectedPayment);

                    PgPaymentTranstbl data = new PgPaymentTranstbl(uuid,budget_code,head_name,date,amount,remark,pgCode,username,userid,isexported,"Bank","1","Number",BMID);
                    data.save();
                }
                break;
        }
    }

    private boolean validation() {
        boolean result = false;
        if (headModelSelected.getHeadname().equals("मद का नाम चुने")) {
            presenter.blankHead();
        }
        else if (payment_date.getHint().toString().equals("चेक तिथि का चयन करे") || payment_date.getText().toString().equals("")) {
            presenter.blankDate();
        }
        else if (selectedPayment.equals("चेक का माध्यम")) {
            presenter.blankPaymentmode();
        }
        else if (et_amount.getText().toString().equals("") || et_amount.getText().toString().equals("0")) {
            presenter.blankamount();
        }
        else {
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
            Toast.makeText(ChequeLoanActivity.this, "कृपया मान्य तिथि चुनें", Toast.LENGTH_LONG).show();
        } else {
            payment_date.setText(newDate);
            payment_date.setHint("");
        }
    }
}