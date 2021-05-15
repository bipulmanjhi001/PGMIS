package com.jslps.pgmisnew;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.jslps.pgmisnew.adapter.PgItemPurcahseAdapter;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.Pgmisstockmsttbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.util.GetCurrentDate;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;
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

public class StockPurchase extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener {

    @BindView(R.id.spinner2)
    SearchableSpinner spinnerItem;
    @BindView(R.id.spinner4)
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

    Calendar c;
    private int mYear, mMonth, mDay;
    List<Pgmisstockmsttbl> pgmisstockmsttblList;
    List<TblMstPgPaymentReceipthead> pgPaymentHeadModelList;
    TblMstPgPaymentReceipthead headModelSelected;
    Pgmisstockmsttbl itemModelSelected;
    String unitSelected, paymentmode;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList;

    public ArrayAdapter<TblMstPgPaymentReceipthead> headSpinAdapter;
    public ArrayAdapter<Pgmisstockmsttbl> itemSpinAdapter;
    @BindView(R.id.textView23)
    TextView pgName;
    PgItemPurcahseAdapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_purchase);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        spinnerItem.setOnItemSelectedListener(this);
        spinnerUnit.setOnItemSelectedListener(this);
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
        TblMstPgPaymentReceipthead datainit = new TblMstPgPaymentReceipthead("0", "0", "मद का नाम चुने", "", "", "");
        pgPaymentHeadModelList.add(datainit);
        pgmisstockmsttblList = Pgmisstockmsttbl.listAll(Pgmisstockmsttbl.class);

        setSpinners();
        setRecyclerView();
    }

    private void setRecyclerView() {
        itempurchasedbypgtblList = Select.from(Itempurchasedbypgtbl.class)
                .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                .list();
        Collections.reverse(itempurchasedbypgtblList);

        aAdapter = new PgItemPurcahseAdapter(this, itempurchasedbypgtblList);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
    }


    private void setSpinners() {
        Collections.reverse(pgPaymentHeadModelList);
        headSpinAdapter = new ArrayAdapter<TblMstPgPaymentReceipthead>(this, android.R.layout.simple_spinner_dropdown_item, pgPaymentHeadModelList) {

        };
        spinnnerHead.setAdapter(headSpinAdapter);

        itemSpinAdapter = new ArrayAdapter<Pgmisstockmsttbl>(this, android.R.layout.simple_spinner_dropdown_item, pgmisstockmsttblList) {

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
                    Itempurchasedbypgtbl data = new Itempurchasedbypgtbl(UUID.randomUUID().toString(),
                            itemModelSelected.getItemcode(),
                            itemModelSelected.getItemname(),
                            unitSelected,
                            etRate.getText().toString(),
                            etQuantity.getText().toString(),
                            headModelSelected.getHeadname(),
                            headModelSelected.getBudgetcode(),
                            PgActivity.pgCodeSelected,
                            new GetCurrentDate().getDate(),
                            "0",
                            paymentmode,
                            textView71.getText().toString());

                    data.save();
                    setRecyclerView();

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
                // First item will be gray
                if (position == 0) {
                    ((TextView) view).setText("आइटम का नाम चुनें");
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }

                itemModelSelected = (Pgmisstockmsttbl) adapterView.getSelectedItem();
                break;

            case R.id.spinner4:

                unitSelected = adapterView.getSelectedItem().toString();
                if(unitSelected.equals("किलो")){
                    unitSelected="Kilo";
                } else if(unitSelected.equals("ग्राम")){
                unitSelected="Gram";
                 }

                break;

            case R.id.spinnermodeofpayment:
                paymentmode = adapterView.getSelectedItem().toString();
                if(paymentmode.equals("नकद")){
                    paymentmode="Cash";
                } else if(paymentmode.equals("बैंक")){
                    paymentmode="Bank";
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
        } else if (itemModelSelected.getItemcode().equals("0")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया आइटम का नाम चुनें")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        } else if (unitSelected.equals("इकाई को चुने")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया इकाई को चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        } else if (etRate.getText().toString().equals("")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया दर चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        } else if (etQuantity.getText().toString().equals("")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया मात्रा का माध्यम चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        } else if (paymentmode.equals("भुगतान का माध्यम")) {
            new StyleableToast
                    .Builder(this)
                    .text("कृपया भुगतान का माध्यम चुने")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }else if (textView71.getText().toString().equals("")) {
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
        String newDate = newMonth + "/" + newDay + "/" + year;
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
            Toast.makeText(StockPurchase.this, "कृपया मान्य तिथि चुने", Toast.LENGTH_LONG).show();
        } else {
            textView71.setText(newDate);
            textView71.setHint("");       }
    }
}
