package com.jslps.pgmisnew;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jslps.pgmisnew.adapter.PgReceiptAdapter;
import com.jslps.pgmisnew.adapter.PgReceiptDisAdapter;
import com.jslps.pgmisnew.database.PgPaymentHeadModel;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.PgReceiptInteractor;
import com.jslps.pgmisnew.presenter.PgReceiptPresenter;
import com.jslps.pgmisnew.util.AppConstant;
import com.jslps.pgmisnew.util.RevealClass;
import com.jslps.pgmisnew.util.SetSpinnerText;
import com.jslps.pgmisnew.view.PgReceiptView;
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

public class PgReceiptActivity extends AppCompatActivity implements PgReceiptView,
        DatePickerDialog.OnDateSetListener {

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
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.quantity)
    TextInputEditText quantity;
    @BindView(R.id.spinner_unit)
    Spinner spinner_unit;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    String unit_type,selectedPaymentMode,qty="0.0";
    RecyclerView listDisbursment;
    PgReceiptPresenter presenter;
    List<TblMstPgPaymentReceipthead> pgPaymentHeadModelList;

    public ArrayAdapter<TblMstPgPaymentReceipthead> headSpinAdapter;
    TblMstPgPaymentReceipthead headModelSelected;
    PgReceiptAdapter aAdapter;
    List<PgReceiptTranstbl> pgReceiptTranstblList;
    List<PgReceiptDisData> pgReceiptDisDataList;
    private PgReceiptTranstbl pgReceiptSelectedItem;
    PgReceiptDisAdapter aAdapterDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_receipt);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();

    }

    private void init() {
        AppConstant.editpgreceiptrecord = false;
        presenter = new PgReceiptPresenter(new PgReceiptInteractor(), this);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(PgReceiptActivity.this, R.color.colorGrayHint));
                }
                headModelSelected = (TblMstPgPaymentReceipthead) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(PgReceiptActivity.this, R.color.colorGrayHint));
                }
                unit_type = spinner_unit.getSelectedItem().toString();
                if(unit_type.equals("किलो")){
                    unit_type="Kilo";
                } else if(unit_type.equals("ग्राम")){
                    unit_type="Gram";
                }else if(unit_type.equals("संख्या")){
                    unit_type="Number";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(PgReceiptActivity.this, R.color.colorGrayHint));
                }
                selectedPaymentMode =  parent.getSelectedItem().toString();
                if(selectedPaymentMode.equals("नकद")){
                    selectedPaymentMode="Cash";
                } else if(selectedPaymentMode.equals("बैंक")){
                    selectedPaymentMode="Bank";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                PgReceiptActivity.this,
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
                .text("मद का नाम चुने")
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
                .text("राशि रिक्त या शून्य नहीं हो सकती")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void blankquanity() {
        new StyleableToast
                .Builder(this)
                .text("मात्रा खाली या शून्य नहीं हो सकती")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void blankunit() {
        new StyleableToast
                .Builder(this)
                .text("इकाई प्रकार का चयन करें")
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
        pgReceiptTranstblList = presenter.getPgReceiptTranstblList(PgActivity.pgCodeSelected);
        Collections.reverse(pgReceiptTranstblList);
        aAdapter = new PgReceiptAdapter(this, pgReceiptTranstblList, presenter);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
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
        textView71.setHint("दिनांक दर्ज करें");
        etEnterAmount.setText("");
        etEnterRemark.setText("");
        quantity.setText("");
        quantity.setHint("मात्रा");
        spinner_unit.setSelection(0);
        spinner3.setSelection(0);
    }

    @Override
    public void blankpaymentmode() {
        new StyleableToast
                .Builder(this)
                .text("भुगतान का माध्यम")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void editRecord(PgReceiptTranstbl item) {
        new SetSpinnerText(spinner2, item.getHeadname());
        textView71.setText(item.getDate());
        textView71.setHint("");
        etEnterAmount.setText(item.getAmount());
        etEnterRemark.setText(item.getRemark());
        qty=item.getQuantity();
        if(item.getQuantity().equals("")) {
            quantity.setText(qty);
        }else {
            quantity.setText(item.getQuantity());
        }
        new SetSpinnerText(spinner_unit, item.getUnittype());
        if(item.getUnittype().equals("Kilo")){
            spinner_unit.setSelection(1);
        } else if(item.getUnittype().equals("Gram")){
            spinner_unit.setSelection(2);
        }else if(item.getUnittype().equals("Number")){
            spinner_unit.setSelection(3);
        }else {
            spinner_unit.setSelection(0);
        }
        AppConstant.editpgreceiptrecord = true;
        pgReceiptSelectedItem = item;
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
                .text("डेटा सफलतापूर्वक संपादित किया गया")
                .iconStart(R.drawable.right)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();

        presenter.setRecyclerView();
        presenter.clearForm();
        AppConstant.editpgreceiptrecord = false;
    }

    @Override
    public void openDisbursmentlayout() {
        final View dialogView = View.inflate(this, R.layout.layout_disbursment_details, null);
        final Dialog dialog = new Dialog(this, R.style.MyAlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);

        FloatingActionButton floatingActionButton2 = dialog.findViewById(R.id.floatingActionButton2);
        listDisbursment = dialog.findViewById(R.id.list);
        presenter.setRecyclerViewDisbursementData();

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                new RevealClass().revealShow(dialogView, false, dialog, imageView2);
            }
        });

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onShow(DialogInterface dialogInterface) {
                new RevealClass().revealShow(dialogView, true, null, imageView2);
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    new RevealClass().revealShow(dialogView, false, dialog, imageView2);
                    return true;
                }
                return false;
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        dialog.getWindow().setLayout(width, height);
        dialog.show();
    }

    @Override
    public void setRecyclerViewDisbursementData() {
        pgReceiptDisDataList = presenter.getPgReceiptDisList(PgActivity.pgCodeSelected);
        aAdapterDis = new PgReceiptDisAdapter(this, pgReceiptDisDataList);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listDisbursment.setLayoutManager(verticalLayoutmanager);
        listDisbursment.setAdapter(aAdapterDis);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        String newDay = dayOfMonth+"";
        String newMonth = (monthOfYear+1)+"";
        if((monthOfYear+1)<10){
            newMonth="0"+newMonth;
        }
        if(dayOfMonth<10){
            newDay = "0"+dayOfMonth;
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
            Toast.makeText(PgReceiptActivity.this, "कृपया मान्य तिथि चुनें", Toast.LENGTH_LONG).show();
        } else {
            textView71.setText(newDate);
            textView71.setHint("");
        }
    }

    private boolean validation() {
        boolean result = false;
        if (headModelSelected.getHeadname().equals("मद का नाम चुने")) {
            presenter.blankHead();
        } else if (textView71.getHint().toString().equals("दिनांक दर्ज करें")) {
            presenter.blankDate();
        } else if (etEnterAmount.getText().toString().equals("")) {
            presenter.blankamount();
        } else if (etEnterAmount.getText().toString().equals("0")) {
            presenter.blankamount();
        }
        else if (selectedPaymentMode.equals("भुगतान का माध्यम")){
            presenter.blankpaymentmode();
        }
        else {
            result = true;
        }
        return result;
    }

    @OnClick({R.id.imageView16, R.id.button4, R.id.button})
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
                    if(unit_type.equals("इकाई को चुने")){
                        unit_type="Select unit";
                    }
                    if (AppConstant.editpgreceiptrecord) {
                        presenter.saveEditedData(budget_code, head_name, date, amount, remark, pgCode,
                                username, userid, isexported, qty, unit_type, selectedPaymentMode, BMID, pgReceiptSelectedItem);
                    }
                    else {
                        presenter.saveData(budget_code, head_name, date, amount, remark, pgCode, username,
                                userid, isexported,qty,unit_type, selectedPaymentMode,BMID);
                    }
                }
                break;
            case R.id.button:
                presenter.openDisburmentLayout();
                break;
        }
    }
}
