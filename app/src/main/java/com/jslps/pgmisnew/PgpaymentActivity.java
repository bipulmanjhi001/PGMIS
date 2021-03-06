package com.jslps.pgmisnew;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jslps.pgmisnew.adapter.PgPaymentAdapter;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.PgPaymentInteractor;
import com.jslps.pgmisnew.presenter.PgPaymentPresenter;
import com.jslps.pgmisnew.util.AppConstant;
import com.jslps.pgmisnew.util.SetSpinnerText;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PgpaymentActivity extends AppCompatActivity implements PgPaymentView,
        AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.spinner2)
    SearchableSpinner spinner2;
    @BindView(R.id.textView71)
    TextView textView71;
    @BindView(R.id.imageView16)
    ImageView imageView16;
    @BindView(R.id.textInputLayout6)
    TextInputLayout textInputLayout6;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
    @BindView(R.id.button4)
    Button button4;

    @BindView(R.id.parentContainer)
    ConstraintLayout parentContainer;
    @BindView(R.id.et_enter_amount)
    TextInputEditText etEnterAmount;
    @BindView(R.id.et_enter_remark)
    TextInputEditText etEnterRemark;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    @BindView(R.id.quantity)
    TextInputEditText quantity;
    @BindView(R.id.spinner_units)
    Spinner spinner_unit;

    /*Defining objects*/
    PgPaymentPresenter presenter;
    List<TblMstPgPaymentReceipthead> pgPaymentHeadModelList;
    public ArrayAdapter<TblMstPgPaymentReceipthead> headSpinAdapter;
    TblMstPgPaymentReceipthead headModelSelected;
    String selectedPaymentMode,selectedPaymentUnit,qty="0.0";
    PgPaymentAdapter aAdapter;
    List<PgPaymentTranstbl> pgPaymentTranstblList;
    private PgPaymentTranstbl pgPaymentSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgpayment);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();
    }

    private void init() {
        //edit mode should be false at start
        AppConstant.editpgpaymentrecord = false;
        presenter = new PgPaymentPresenter(new PgPaymentInteractor(), this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner_unit.setOnItemSelectedListener(this);
        presenter.getHeadList();
        presenter.setSpinnerHead();
        presenter.setRecyclerView();
        presenter.setPgName();
    }

    @Override
    public void getHeadList(List<TblMstPgPaymentReceipthead> list) {
        pgPaymentHeadModelList = list;
    }

    @Override
    public void setSpinnerHead() {
        headSpinAdapter = new ArrayAdapter<TblMstPgPaymentReceipthead>(this, android.R.layout.simple_spinner_dropdown_item, pgPaymentHeadModelList) {
        };
        spinner2.setAdapter(headSpinAdapter);
    }

    @Override
    public void setOpenCalender() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                PgpaymentActivity.this,
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
                .text("??????????????? ?????? ?????? ????????? ????????????")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void blankDate() {
        new StyleableToast
                .Builder(this)
                .text("?????????????????? ??????????????? ???????????? ?????? ????????????")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void blankamount() {
        new StyleableToast
                .Builder(this)
                .text("??????????????? ????????? ?????? ??????")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void dataSaved() {
        new StyleableToast
                .Builder(this)
                .text("???????????? ????????????????????????????????? ??????????????? ?????????")
                .iconStart(R.drawable.right)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();

        presenter.setRecyclerView();
        presenter.clearForm();
    }

    @Override
    public void setRecyclerView() {
        pgPaymentTranstblList = presenter.getPgPaymentTranstblList(PgActivity.pgCodeSelected);
        Collections.reverse(pgPaymentTranstblList);
        aAdapter = new PgPaymentAdapter(this, pgPaymentTranstblList, presenter);
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
        textView71.setText("");
        textView71.setHint("?????????????????? ?????? ????????????");
        etEnterAmount.setText("");
        etEnterRemark.setText("");
        quantity.setText("");
        quantity.setHint("??????????????????");
        spinner3.setSelection(0);
        spinner_unit.setSelection(0);
    }

    @Override
    public void editRecord(PgPaymentTranstbl item) {
        new SetSpinnerText(spinner2, item.getHeadname());
        textView71.setText(item.getDate());
        textView71.setHint("");
        etEnterAmount.setText(item.getAmount());
        etEnterRemark.setText(item.getRemark());
        qty=item.getQty();
        if(item.getQty().equals("")) {
            quantity.setText(qty);
        }else {
            quantity.setText(item.getQty());
        }
        new SetSpinnerText(spinner3, item.getPaymentmode());
        if(item.getPaymentunit().equals("Kilo")){
            spinner_unit.setSelection(1);
        }
        else if(item.getPaymentunit().equals("Gram")){
            spinner_unit.setSelection(2);
        }
        else if(item.getPaymentunit().equals("Number")){
            spinner_unit.setSelection(3);
        }else {
            spinner_unit.setSelection(0);
        }
        AppConstant.editpgpaymentrecord = true;
        pgPaymentSelectedItem = item;
        if(item.getPaymentmode().equals("Cash")){
            spinner3.setSelection(1);
        }else if(item.getPaymentmode().equals("Bank")){
            spinner3.setSelection(2);
        }
    }

    @Override
    public void dataEdited() {
        new StyleableToast
                .Builder(this)
                .text("???????????? ????????????????????????????????? ??????????????? ?????????")
                .iconStart(R.drawable.right)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();

        presenter.setRecyclerView();
        presenter.clearForm();
        AppConstant.editpgpaymentrecord = false;
    }

    @Override
    public void blankPaymentMode() {
        new StyleableToast
                .Builder(this)
                .text("?????????????????? ?????? ??????????????????")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void blankquantity() {
        new StyleableToast
                .Builder(this)
                .text("?????????????????? ???????????? ?????? ??????????????? ???????????? ?????? ????????????")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void blankunit() {
        new StyleableToast
                .Builder(this)
                .text("???????????? ?????? ????????????")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner2:
                if (position == 0) {
                    ((TextView) view).setText("?????? ?????? ????????? ????????????");
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                headModelSelected = (TblMstPgPaymentReceipthead) parent.getSelectedItem();
                break;
            case R.id.spinner3:
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                selectedPaymentMode =  parent.getSelectedItem().toString();
                if(selectedPaymentMode.equals("?????????")){
                    selectedPaymentMode="Cash";
                } else if(selectedPaymentMode.equals("????????????")){
                    selectedPaymentMode="Bank";
                }
                break;
        case R.id.spinner_units:
        if (position == 0) {
            ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
           }
          selectedPaymentUnit =  parent.getSelectedItem().toString();
            if(selectedPaymentUnit.equals("????????????")){
                selectedPaymentUnit="Kilo";
            } else if(selectedPaymentUnit.equals("???????????????")){
                selectedPaymentUnit="Gram";
            }else if(selectedPaymentUnit.equals("??????????????????")){
                selectedPaymentUnit="Number";
            }
        break;
    }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                    String budget_code = headModelSelected.getBudgetid();
                    String head_name = headModelSelected.getHeadname();
                    String date = textView71.getText().toString();
                    String amount = etEnterAmount.getText().toString();
                    String remark = etEnterRemark.getText().toString();
                    String BMID = headModelSelected.getBMID();
                    String pgCode = PgActivity.pgCodeSelected;
                    String[] userDetails = presenter.getUserDetails();
                    String username = userDetails[0];
                    String userid = userDetails[1];
                    String isexported = "0";
                    if(!quantity.getText().toString().equals("")){
                        qty=quantity.getText().toString();
                    }else {
                        qty="0.0";
                    }
                    if(selectedPaymentUnit.equals("???????????? ?????? ????????????")){
                        selectedPaymentUnit="Select unit";
                    }
                    if (AppConstant.editpgpaymentrecord) {
                        presenter.saveEditedData(budget_code, head_name, date, amount, remark, pgCode,
                                username, userid, isexported,selectedPaymentMode,qty,selectedPaymentUnit,BMID,
                                pgPaymentSelectedItem);
                    } else {
                        presenter.saveData(budget_code, head_name, date, amount, remark, pgCode,
                                username, userid, isexported,selectedPaymentMode,qty,selectedPaymentUnit,BMID);
                    }
                }
                break;
        }
    }

    private boolean validation() {
        boolean result = false;
        if (headModelSelected.getBudgetcode().equals("0")) {
            presenter.blankHead();
        } else if (textView71.getHint().toString().equals("?????????????????? ?????? ????????????")) {
            presenter.blankDate();
        } else if (etEnterAmount.getText().toString().equals("")) {
            presenter.blankamount();
        } else if (etEnterAmount.getText().toString().equals("0")) {
            presenter.blankamount();
        } else if (selectedPaymentMode.equals("?????????????????? ?????? ??????????????????")) {
            presenter.blankPaymentMode();
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
            Toast.makeText(PgpaymentActivity.this, "??????????????? ??????????????? ???????????? ???????????????", Toast.LENGTH_LONG).show();
        } else {
            textView71.setText(newDate);
            textView71.setHint("");
        }
    }
}
