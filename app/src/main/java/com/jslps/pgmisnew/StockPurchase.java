package com.jslps.pgmisnew;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.adapter.PgItemPurcahseAdapter;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.Pgmemshipfeesavetbl;
import com.jslps.pgmisnew.database.Pgmisstockmsttbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.interactor.PgBankWithdrawInteractor;
import com.jslps.pgmisnew.interactor.StockPurchaseInterface;
import com.jslps.pgmisnew.presenter.PgBankWithdrawPresenter;
import com.jslps.pgmisnew.presenter.StockPurchasePresenter;
import com.jslps.pgmisnew.util.GetCurrentDate;
import com.jslps.pgmisnew.view.StockPurchaseView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

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

public class StockPurchase extends AppCompatActivity implements StockPurchaseView,AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener {

    @BindView(R.id.spinner2)
    SearchableSpinner spinnerItem;
    @BindView(R.id.spinner_purchase)
    Spinner spinnerUnit;
    @BindView(R.id.et_rate)
    TextInputEditText etRate;
    @BindView(R.id.et_quantity)
    TextInputEditText etQuantity;
    @BindView(R.id.button4)
    Button btnSave;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.spinner5)
    SearchableSpinner spinnnerHead;
    @BindView(R.id.spinnermodeofpayment)
    Spinner spinnermodeofpayment;
    @BindView(R.id.textView71)
    TextView textView71;
    @BindView(R.id.imageView16)
    ImageView imageView16;
    DatePickerDialog mDatePicker;
    ArrayList<String> arrayList;

    Calendar c;
    private int mYear, mMonth, mDay;
    ArrayAdapter<String> arrayAdapter;
    List<Pgmisstockmsttbl> pgmisstockmsttblList;
    List<TblMstPgPaymentReceipthead> pgPaymentHeadModelList;
    TblMstPgPaymentReceipthead headModelSelected;
    Pgmisstockmsttbl itemModelSelected;
    String unitSelected,unitSelected1,paymentmode,newDate,demo,
            getdefineunit,unitSelected2,unitSelected3;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList;

    public ArrayAdapter<TblMstPgPaymentReceipthead> headSpinAdapter;
    public ArrayAdapter<Pgmisstockmsttbl> itemSpinAdapter;
    @BindView(R.id.textView23)
    TextView pgName;
    StockPurchasePresenter presenter;
    PgItemPurcahseAdapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_purchase);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        presenter = new StockPurchasePresenter(new StockPurchaseInterface(), this);
        spinnerItem.setOnItemSelectedListener(this);
        spinnnerHead.setOnItemSelectedListener(this);
        spinnermodeofpayment.setOnItemSelectedListener(this);
        pgName.setText(PgActivity.pgNameSelected);
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mDatePicker=new DatePickerDialog();
        mDatePicker.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));

        pgPaymentHeadModelList = Select.from(TblMstPgPaymentReceipthead.class)
                .where(Condition.prop("budgettype").eq("Loan"))
                .where(Condition.prop("showin").eq("P"))
                .list();

        TblMstPgPaymentReceipthead datainit = new TblMstPgPaymentReceipthead("0", "0", "0", "", "मद का नाम चुने", "","");
        pgPaymentHeadModelList.add(datainit);

        pgmisstockmsttblList = Pgmisstockmsttbl.listAll(Pgmisstockmsttbl.class);
        setSpinners();
        presenter.setRecyclerView();

        //  setRecyclerView();
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(arrayAdapter);
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    unitSelected = arrayAdapter.getItem(position);
                    Log.d("unitselected", unitSelected);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
           }
           @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void editRecord(Itempurchasedbypgtbl item) {

    }

    @Override
    public void setRecyclerView() {
        itempurchasedbypgtblList = presenter.getStockPurchaseItem(PgActivity.pgCodeSelected);
        Collections.reverse(itempurchasedbypgtblList);
        aAdapter = new PgItemPurcahseAdapter(this, itempurchasedbypgtblList,presenter);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);

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
    }

    private void setSpinners() {
        Collections.reverse(pgPaymentHeadModelList);
        headSpinAdapter = new ArrayAdapter<TblMstPgPaymentReceipthead>(this,
                android.R.layout.simple_spinner_dropdown_item, pgPaymentHeadModelList) {
        };
        spinnnerHead.setAdapter(headSpinAdapter);

        itemSpinAdapter = new ArrayAdapter<Pgmisstockmsttbl>(this,
                android.R.layout.simple_spinner_dropdown_item, pgmisstockmsttblList) {
        };
        spinnerItem.setAdapter(itemSpinAdapter);
    }

    @OnClick({R.id.imageView16, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView16:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        StockPurchase.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
                dpd.show(getFragmentManager(), "Datepickerdialog");

                break;

            case R.id.button4:
                if(validation()) {
                    presenter.saveData(UUID.randomUUID().toString(),
                            itemModelSelected.getItemcode(),
                            itemModelSelected.getItemname(),
                            unitSelected,
                            etRate.getText().toString(),
                            etQuantity.getText().toString(),
                            headModelSelected.getHeadname(),
                            headModelSelected.getBudgetid(),
                            PgActivity.pgCodeSelected,
                            new GetCurrentDate().getDate(),
                            "0",
                            paymentmode,
                            newDate,
                            headModelSelected.getBMID());

                    new StyleableToast
                            .Builder(this)
                            .text("डेटा सफलतापूर्वक सहेजा गया")
                            .iconStart(R.drawable.right)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();

                    clearForm();
                }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner5:
                // First item will be gray
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                headModelSelected = (TblMstPgPaymentReceipthead) adapterView.getSelectedItem();
                break;
            case R.id.spinner2:
                itemModelSelected = (Pgmisstockmsttbl) adapterView.getSelectedItem();
                getdefineunit = adapterView.getSelectedItem().toString();
                if (getdefineunit.equals("आइटम का नाम चुनें")) {
                    arrayList.clear();
                    unitSelected1 = "इकाई को चुने";
                    arrayList.add(unitSelected1);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("बीज")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("उर्वरक")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("कीटनाशक")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("बकरी")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Number";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("चूजे")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Number";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("सूअर")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Number";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("फीड")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("दवाइयां")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("मछली बीज")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("मछली दाना")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("अन्य वस्तु")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("पौधा")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Number";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayAdapter.notifyDataSetChanged();

                } else if (getdefineunit.equals("कीटनाशक(तरल)")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Liter";
                    unitSelected2 = "ML";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                }
                else if (getdefineunit.equals("उर्वरक(तरल)")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Liter";
                    unitSelected2 = "ML";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();

                }
                else if (getdefineunit.equals("फीड(तरल)")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Liter";
                    unitSelected2 = "ML";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();
                }
                else if (getdefineunit.equals("दवाइयां(तरल)")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Liter";
                    unitSelected2 = "ML";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();
                }
                else if (getdefineunit.equals("अन्य वस्तु(तरल)")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Liter";
                    unitSelected2 = "ML";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();
                }
                break;

            case R.id.spinnermodeofpayment:
                paymentmode = adapterView.getSelectedItem().toString();
                if (paymentmode.equals("इकाई को चुने")) {
                    paymentmode = "";
                    etRate.setHint("दर");
                    etRate.setFocusableInTouchMode(true);
                    etRate.setFocusable(true);
                }
                if (paymentmode.equals("नकद")) {
                    if(etRate.getText().equals("0")) {
                        paymentmode = "Cash";
                        etRate.setFocusableInTouchMode(true);
                        etRate.setFocusable(true);
                        etRate.setText("");
                    }else {
                        paymentmode = "Cash";
                        etRate.setFocusableInTouchMode(true);
                        etRate.setFocusable(true);
                    }
                }
                if (paymentmode.equals("बैंक")) {
                    if(etRate.getText().equals("0")) {
                        paymentmode = "Bank";
                        etRate.setFocusableInTouchMode(true);
                        etRate.setFocusable(true);
                        etRate.setText("");
                    }else {
                        paymentmode = "Bank";
                        etRate.setFocusableInTouchMode(true);
                        etRate.setFocusable(true);
                    }
                }
                if(paymentmode.equals("भण्डार")){
                    paymentmode = "Stock";
                    etRate.setFocusableInTouchMode(true);
                    etRate.setFocusable(false);
                    etRate.setText("0");
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean validation() {
        boolean result = false;
        if (headModelSelected.getBudgetcode().equals("0")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया मद का नाम चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (itemModelSelected.getItemcode().equals("0")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया आइटम का नाम चुनें")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (unitSelected.equals("इकाई को चुने")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया इकाई को चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (etRate.getText().toString().equals("")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया दर चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (etQuantity.getText().toString().equals("")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया मात्रा का माध्यम चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (paymentmode.equals("भुगतान का माध्यम")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया भुगतान का माध्यम चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (textView71.getText().toString().equals("भुगतान तिथि का चयन करें")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया मान्य भुगतान तिथि का चयन करें")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else {
            result = true;
        }
        return result;
    }

    public void clearForm() {
        //setSpinners();
        spinnnerHead.setSelection(0);
        spinnerItem.setSelection(0);
        spinnerUnit.setSelection(0);
        spinnermodeofpayment.setSelection(0);
        etRate.setText("");
        etQuantity.setText("");
        textView71.setText("भुगतान तिथि का चयन करें");
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
        newDate = year + "/" + newMonth + "/" + newDay;
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
            Toast.makeText(StockPurchase.this, "कृपया मान्य तिथि चुने", Toast.LENGTH_LONG).show();
        } else {
            textView71.setText(newDate);
            textView71.setHint("");       }
    }
}
