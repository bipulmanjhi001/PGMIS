package com.jslps.pgmisnew;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jslps.pgmisnew.adapter.PgPreLoanAdapter;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.PgmisBatchLoantbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.interactor.PgPaymentInteractor;
import com.jslps.pgmisnew.presenter.PgPaymentPresenter;
import com.jslps.pgmisnew.util.GetCurrentDate;
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

public class Loan extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener{

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
    @BindView(R.id.spinner4)
    Spinner spinnerUnit;
    @BindView(R.id.et_rate)
    TextInputEditText etRate;
    @BindView(R.id.textInputLayout6)
    TextInputLayout textInputLayout6;
    @BindView(R.id.et_quantity)
    TextInputEditText etQuantity;
    @BindView(R.id.textInputLayout7)
    TextInputLayout textInputLayout7;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;
    @BindView(R.id.button4)
    ImageView button4;
    @BindView(R.id.textView72)
    TextView textView72;
    @BindView(R.id.textView73)
    TextView textView73;
    @BindView(R.id.textView74)
    TextView textView74;
    @BindView(R.id.textView75)
    TextView textView75;
    @BindView(R.id.textView76)
    TextView textView76;
    @BindView(R.id.textView77)
    TextView textView77;
    @BindView(R.id.textView78)
    TextView textView78;
    @BindView(R.id.textView79)
    TextView textView79;
    @BindView(R.id.constraintLayout4)
    ConstraintLayout constraintLayout4;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.parentContainer)
    ConstraintLayout parentContainer;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.loanbatchbutton)
    Button loanbatchbutton;
    @BindView(R.id.textView101)
    TextView textView101;
    @BindView(R.id.textView102)
    TextView textView102;
    @BindView(R.id.textView104)
    TextView textView104;
    @BindView(R.id.payment_date)
    TextView payment_date;
    @BindView(R.id.call_calender)
    ImageView call_calender;
    PgPaymentPresenter presenter;

    List<Itempurchasedbypgtbl> itempurchasedbypgtblList;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList_new;
    List<String> itemcodeString;
    public ArrayAdapter<Itempurchasedbypgtbl> itemSpinAdapter;

    String pgname, pgcode, grpcode, grpmemcode, memname,newDate,username,userid,demo;
    Itempurchasedbypgtbl itempurchasedbypgtblSelected;
    String unitSelected,unitSelected1,selectedDate,select_payment,bankqty,cashqty,getdefineunit,unitSelected2;
    PgPreLoanAdapter aAdapter;
    ArrayList<String> arrayList;

    List<PgmisLoantbl> pgmisLoantblList;
    private static double thresholdloanamount = 30000;
    double quantityFinal;
    List<PgmisBatchLoantbl> pgmisBatchLoantblList;
    double loantakenbymem=0;
    double remainingloanamount=0;
    String getfinal,itemuuid,date,bmid;
    ArrayAdapter<String> arrayAdapter;
    double quantitygivenasloan = 0;
    double quantitythis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        pgname = PgActivity.pgNameSelected;
        Intent intent = getIntent();
        pgcode = intent.getStringExtra("pgcode");
        grpcode = intent.getStringExtra("grpcode");
        grpmemcode = intent.getStringExtra("grpmemcode");
        memname = intent.getStringExtra("membername");
        getLoanDetailsMember();
        presenter = new PgPaymentPresenter(Loan.this);
        textView24.setText(memname);
        textView23.setText(pgname);

        call_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Loan.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        spinner2.setOnItemSelectedListener(this);
        spinnerUnit.setOnItemSelectedListener(this);

        itempurchasedbypgtblList = Select.from(Itempurchasedbypgtbl.class)
                .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                .list();

        itemcodeString = new ArrayList<>();
        itempurchasedbypgtblList_new = new ArrayList<>();

        //adding quantity of same item
        for (int i = 0; i < itempurchasedbypgtblList.size(); i++) {
            String itemcode = itempurchasedbypgtblList.get(i).getItemcode();
            if (!itemcodeString.contains(itemcode)) {
                Itempurchasedbypgtbl itempurchasedbypgtbl = new Itempurchasedbypgtbl();
                itempurchasedbypgtbl.setItemname(itempurchasedbypgtblList.get(i).getItemname());
                itempurchasedbypgtbl.setItemcode(itempurchasedbypgtblList.get(i).getItemcode());
                itempurchasedbypgtbl.setBMID(itempurchasedbypgtblList.get(i).getBMID());
                itempurchasedbypgtbl.setBudgetcode(itempurchasedbypgtblList.get(i).getBudgetcode());
                itempurchasedbypgtbl.setBudgetname(itempurchasedbypgtblList.get(i).getBudgetname());
                itempurchasedbypgtbl.setPgcode(itempurchasedbypgtblList.get(i).getPgcode());
                itempurchasedbypgtbl.setEntrydate(itempurchasedbypgtblList.get(i).getEntrydate());
                itempurchasedbypgtbl.setRate(itempurchasedbypgtblList.get(i).getRate());

                if (itempurchasedbypgtblList.get(i).getUnit().equals("Kg")) {
                    double quantity = Double.parseDouble(itempurchasedbypgtblList.get(i).getQuantity()) * 1000; //changing to gram
                    itempurchasedbypgtbl.setQuantity(quantity + "");

                } else {
                    itempurchasedbypgtbl.setQuantity(itempurchasedbypgtblList.get(i).getQuantity());
                }

                itempurchasedbypgtbl.setUnit("gram");
                itempurchasedbypgtbl.setUuid(itempurchasedbypgtblList.get(i).getUuid());
                itemcodeString.add(itempurchasedbypgtblList.get(i).getItemcode());
                itempurchasedbypgtblList_new.add(itempurchasedbypgtbl);
            } else {
                for (int j = 0; j < itempurchasedbypgtblList_new.size(); j++) {
                    String itemcode_new = itempurchasedbypgtblList_new.get(j).getItemcode();
                    if (itemcode_new.equals(itemcode)) {
                        String quantity_new = itempurchasedbypgtblList_new.get(j).getQuantity();
                        String quantity = itempurchasedbypgtblList.get(i).getQuantity();
                        String unit = itempurchasedbypgtblList.get(i).getUnit();
                        if (unit.equals("Kg")) {
                            quantity = Double.parseDouble(quantity) * 1000 + "";
                        }
                        double new_quantity = Double.parseDouble(quantity_new) + Double.parseDouble(quantity);
                        Itempurchasedbypgtbl model_new = new Itempurchasedbypgtbl();
                        model_new.setItemname(itempurchasedbypgtblList.get(i).getItemname());
                        model_new.setItemcode(itempurchasedbypgtblList.get(i).getItemcode());
                        model_new.setQuantity(new_quantity + "");
                        model_new.setUnit("gram");
                        itempurchasedbypgtblList_new.set(j, model_new);
                        //changing j to size of new list to stop for loop second
                        j = itempurchasedbypgtblList_new.size();
                    }
                  }
                }
             }

        Itempurchasedbypgtbl model1 = new Itempurchasedbypgtbl();
        model1.setItemname("आइटम का नाम चुनें");
        itempurchasedbypgtblList_new.add(model1);
        Collections.reverse(itempurchasedbypgtblList_new);
        setSpinners();
        setRecyclerView();
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(Loan.this,android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(arrayAdapter);
    }

    private void getLoanDetailsMember() {
        loantakenbymem=0;
        textView101.setText(thresholdloanamount+"");
        pgmisBatchLoantblList = Select.from(PgmisBatchLoantbl.class)
                .where(Condition.prop("pgcode").eq(pgcode))
                .where(Condition.prop("grpcode").eq(grpcode))
                .where(Condition.prop("grpmemcode").eq(grpmemcode))
                .list();

        for(int i=0;i<pgmisBatchLoantblList.size();i++){
            double amount = Double.parseDouble(pgmisBatchLoantblList.get(i).getAmount());
            loantakenbymem =loantakenbymem+amount;
        }
        textView102.setText("-"+loantakenbymem);
        remainingloanamount = thresholdloanamount - loantakenbymem;
        textView104.setText(""+remainingloanamount);
    }

    private void setRecyclerView() {
        pgmisLoantblList = Select.from(PgmisLoantbl.class)
                .where(Condition.prop("pgcode").eq(pgcode))
                .where(Condition.prop("grpcode").eq(grpcode))
                .where(Condition.prop("grpmemcode").eq(grpmemcode))
                .where(Condition.prop("appliedforloan").eq("0"))
                .list();

        Collections.reverse(pgmisLoantblList);
        aAdapter = new PgPreLoanAdapter(this, pgmisLoantblList);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
        List<Logintbl> users = Logintbl.listAll(Logintbl.class);

        username = users.get(0).getUsername();
        userid = users.get(0).getUserid();
    }

    private void setSpinners() {
        itemSpinAdapter = new ArrayAdapter<Itempurchasedbypgtbl>(this, android.R.layout.simple_spinner_dropdown_item, itempurchasedbypgtblList_new) {};
        spinner2.setAdapter(itemSpinAdapter);
    }

    @OnClick(R.id.button4)
    public void onViewClicked() {
        if (validation()) {
            PgmisLoantbl data = new PgmisLoantbl(UUID.randomUUID().toString(),
                    pgcode,
                    grpcode,
                    grpmemcode,
                    itempurchasedbypgtblSelected.getItemcode(),
                    itempurchasedbypgtblSelected.getItemname(),
                    etRate.getText().toString(),
                    unitSelected,
                    "0",
                    new GetCurrentDate().getDate(),
                    "",
                    "0",
                    etQuantity.getText().toString(),
                    newDate,
                    itempurchasedbypgtblSelected.getBMID());

            data.save();
            clearForm();
            setRecyclerView();
            new StyleableToast
                    .Builder(this)
                    .text("डेटा सफलतापूर्वक सहेजा गया")
                    .iconStart(R.drawable.right)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner2:
                if (position == 0) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(this, R.color.colorGrayHint));
                }
                itempurchasedbypgtblSelected = (Itempurchasedbypgtbl) adapterView.getSelectedItem();
                getdefineunit =adapterView.getSelectedItem().toString();
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
                }
                else if (getdefineunit.equals("अन्य वस्तु")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Kg";
                    unitSelected2 = "Gram";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayList.add(unitSelected2);
                    arrayAdapter.notifyDataSetChanged();
                }
                else if (getdefineunit.equals("पौधा")) {
                    arrayList.clear();
                    demo="इकाई को चुने";
                    unitSelected1 = "Number";
                    arrayList.add(demo);
                    arrayList.add(unitSelected1);
                    arrayAdapter.notifyDataSetChanged();
                }
                else if (getdefineunit.equals("कीटनाशक(तरल)")) {
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
            case R.id.spinner4:
                unitSelected = arrayAdapter.getItem(position);
                if (!itempurchasedbypgtblSelected.getItemname().equals("आइटम का नाम चुनें")) {
                    String todisplayquantity;
                    List<PgmisLoantbl> list = Select.from(PgmisLoantbl.class)
                            .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                            .where(Condition.prop("itemcode").eq(itempurchasedbypgtblSelected.getItemcode()))
                            .list();
                    for (int i = 0; i < list.size(); i++) {
                        String unitthis = list.get(i).getUnit();
                        double quantitythis = 0;
                        if (unitthis.equals("Kg")) {
                            quantitythis = Double.parseDouble(list.get(i).getQuantity())*1000;
                        }
                        if(unitthis.equals("Number")) {
                            quantitythis = Double.parseDouble(list.get(i).getQuantity());
                        }
                        if(unitthis.equals("Gram")){
                            quantitythis = Double.parseDouble(list.get(i).getQuantity());
                        }
                        if(unitthis.equals("Liter")){
                            quantitythis = Double.parseDouble(list.get(i).getQuantity())*1000;
                        }
                        if(unitthis.equals("ML")){
                            quantitythis = Double.parseDouble(list.get(i).getQuantity());
                        }
                            quantitygivenasloan = quantitygivenasloan + quantitythis;
                       }

                    quantityFinal = Double.parseDouble(itempurchasedbypgtblSelected.getQuantity()) - quantitygivenasloan;

                    if ((quantityFinal / 1000) < 1) {
                        todisplayquantity = quantityFinal + " gram";
                        quantitygivenasloan=0;
                    }else {
                        todisplayquantity = quantityFinal / 1000 + " Kg";
                        quantitygivenasloan=0;
                    }
                    if(unitSelected.equals("Gram")) {
                        todisplayquantity = quantityFinal + " Gram";
                        quantitygivenasloan=0;
                    }
                    if(unitSelected.equals("Number")) {
                        todisplayquantity = quantityFinal + " Number";
                        quantitygivenasloan=0;
                    }
                    if(unitSelected.equals("ML")) {
                        todisplayquantity = quantityFinal + " ML";
                        quantitygivenasloan=0;
                    }
                    if(unitSelected.equals("Liter")){
                        todisplayquantity = quantityFinal / 1000 + " Liter";
                        quantitygivenasloan=0;
                    }
                    textInputLayout7.setHint("मात्रा दर्ज करें (उपलब्ध मात्रा:" + todisplayquantity + ")");
                }
                else {
                    textInputLayout7.setHint("मात्रा दर्ज करें");
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean validation() {
        boolean result = false;
        if (itempurchasedbypgtblSelected.getItemname().equals("आइटम का नाम चुनें")) {
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
                    .text("कृपया इकाई चुनें")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (etRate.getText().toString().equals("")) {
            new StyleableToast
                    .Builder(this)
                    .text("दर खाली नहीं हो सकती")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (payment_date.getHint().toString().equals("ऋण तिथि का चयन करें")) {
            new StyleableToast
                    .Builder(this)
                    .text("ऋण तिथि का चयन करें")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (etQuantity.getText().toString().equals("") || etQuantity.getText().toString().equals("0")) {
            new StyleableToast
                    .Builder(this)
                    .text("मात्रा खाली नहीं हो सकती")
                    .iconStart(R.drawable.wrong_icon_white)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
        else if (!etQuantity.getText().toString().equals("")  || !etQuantity.getText().toString().equals("0")) {
            double quantity = Double.parseDouble(etQuantity.getText().toString());
            if (unitSelected.equals("Kg")) {
                quantity = quantity * 1000;
            }
            if (unitSelected.equals("Liter")) {
                quantity = quantity * 1000;
            }
            if (quantity > quantityFinal) {
                new StyleableToast
                        .Builder(this)
                        .text("उपलब्ध स्टॉक की तुलना में मात्रा अधिक है")
                        .iconStart(R.drawable.wrong_icon_white)
                        .textColor(Color.WHITE)
                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
            } else {
                //checking for threshold loan amount
                double amount = Double.parseDouble(etRate.getText().toString()) * Double.parseDouble(etQuantity.getText().toString());
                double amountsum=0;
                for (int i = 0; i < pgmisLoantblList.size(); i++) {
                    double ratethis = Double.parseDouble(pgmisLoantblList.get(i).getRate());
                    double quantitythis = Double.parseDouble(pgmisLoantblList.get(i).getQuantity());
                    double amountthis = ratethis * quantitythis;
                    amountsum = amountsum + amountthis;
                }
                if (amount > (remainingloanamount-amountsum)) {
                    new StyleableToast
                            .Builder(this)
                            .text("ऋण राशि से अधिक नहीं हो सकती है " + (remainingloanamount-amountsum))
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    public void clearForm() {
        setSpinners();
        spinnerUnit.setSelection(0);
        etRate.setText("");
        etQuantity.setText("");
        // payment_date.setText("भुगतान तिथि का चयन करें");
    }

    @OnClick(R.id.loanbatchbutton)
    public void onViewClicked1() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Loan.this);
        alertDialog.setTitle("भुगतान मोड का चयन करें");
        String[] items = new String[]{"ऋण", "नकद", "बैंक"};
        int checkedItem = -1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        select_payment = "Loan";
                        break;
                    case 1:
                        select_payment = "Cash";
                        break;
                    case 2:
                        select_payment = "Bank";
                        break;
                }
            }
        });

        alertDialog.setPositiveButton("हां।", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    if (select_payment.equals("Loan")) {
                        try {
                            double amount = 0;
                            if (pgmisLoantblList.size() > 0) {
                                for (int i = 0; i < pgmisLoantblList.size(); i++) {
                                    double rate = Double.parseDouble(pgmisLoantblList.get(i).getRate());
                                    double quantity = Double.parseDouble(pgmisLoantblList.get(i).getQuantity());
                                    date = pgmisLoantblList.get(i).getPaymentdate();
                                    itemuuid=pgmisLoantblList.get(i).getUuid();
                                    double amountthis = rate * quantity;
                                    amount = amount + amountthis;
                                    pgmisLoantblList.get(i).setAppliedforloan("1");
                                    pgmisLoantblList.get(i).save();
                                }

                                PgmisBatchLoantbl data = new PgmisBatchLoantbl(
                                        UUID.randomUUID().toString(),itemuuid,
                                        pgcode, grpcode, grpmemcode,
                                        date,
                                        "0", amount + "",
                                        "Loan");

                                data.save();
                                payment_date.setText("ऋण तिथि का चयन करें");

                                new StyleableToast
                                        .Builder(Loan.this)
                                        .text("सफलतापूर्वक ऋण के लिए आवेदन किया")
                                        .iconStart(R.drawable.right)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();

                                getLoanDetailsMember();
                                setRecyclerView();
                                alertDialog.setCancelable(true);

                            } else {
                                new StyleableToast
                                        .Builder(Loan.this)
                                        .text("कृपया पहले आइटम जोड़ें")
                                        .iconStart(R.drawable.wrong_icon_white)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();

                                alertDialog.setCancelable(true);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (select_payment.equals("Bank")) {
                        try {
                            double amount = 0;
                            if (pgmisLoantblList.size() > 0) {
                                for (int i = 0; i < pgmisLoantblList.size(); i++) {
                                    double rate = Double.parseDouble(pgmisLoantblList.get(i).getRate());
                                    bankqty = pgmisLoantblList.get(i).getQuantity();
                                    date = pgmisLoantblList.get(i).getPaymentdate();
                                    itemuuid=pgmisLoantblList.get(i).getUuid();
                                    double quantity = Double.parseDouble(pgmisLoantblList.get(i).getQuantity());
                                    double amountthis = rate * quantity;
                                    amount = amount + amountthis;

                                    pgmisLoantblList.get(i).setAppliedforloan("1");
                                    pgmisLoantblList.get(i).save();
                                }

                                PgReceiptTranstbl data = new PgReceiptTranstbl(
                                        itemuuid,
                                        grpmemcode, "सामग्री वितरण",
                                        date,
                                        amount + "",
                                        "", pgcode,
                                        userid, grpcode,
                                        "0", bankqty,
                                        "Select unit", "Bank", bmid);

                                data.save();
                                payment_date.setText("ऋण तिथि का चयन करें");
                                new StyleableToast
                                        .Builder(Loan.this)
                                        .text("सफलतापूर्वक सहेजें")
                                        .iconStart(R.drawable.right)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();

                                getLoanDetailsMember();
                                setRecyclerView();
                                alertDialog.setCancelable(true);

                            } else {
                                new StyleableToast
                                        .Builder(Loan.this)
                                        .text("कृपया पहले आइटम जोड़ें")
                                        .iconStart(R.drawable.wrong_icon_white)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();

                                alertDialog.setCancelable(true);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (select_payment.equals("Cash")) {
                        try {
                            double amount = 0;
                            if (pgmisLoantblList.size() > 0) {
                                for (int i = 0; i < pgmisLoantblList.size(); i++) {
                                    double rate = Double.parseDouble(pgmisLoantblList.get(i).getRate());
                                    bankqty = pgmisLoantblList.get(i).getQuantity();
                                    date = pgmisLoantblList.get(i).getPaymentdate();
                                    itemuuid=pgmisLoantblList.get(i).getUuid();
                                    bmid=pgmisLoantblList.get(i).getBMID();
                                    double quantity = Double.parseDouble(pgmisLoantblList.get(i).getQuantity());
                                    double amountthis = rate * quantity;
                                    amount = amount + amountthis;

                                    pgmisLoantblList.get(i).setAppliedforloan("1");
                                    pgmisLoantblList.get(i).save();
                                }

                                PgReceiptTranstbl data = new PgReceiptTranstbl(
                                        itemuuid,
                                        grpmemcode, "सामग्री वितरण",
                                        date,
                                        amount + "",
                                        "", pgcode,
                                        userid, grpcode,
                                        "0", bankqty,
                                        "Select unit", "Cash", bmid);

                                data.save();
                                payment_date.setText("ऋण तिथि का चयन करें");

                                new StyleableToast
                                        .Builder(Loan.this)
                                        .text("सफलतापूर्वक सहेजें")
                                        .iconStart(R.drawable.right)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();

                                getLoanDetailsMember();
                                setRecyclerView();
                                alertDialog.setCancelable(true);

                            } else {
                                new StyleableToast
                                        .Builder(Loan.this)
                                        .text("कृपया पहले आइटम जोड़ें")
                                        .iconStart(R.drawable.wrong_icon_white)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();

                                alertDialog.setCancelable(true);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        alertDialog.setNeutralButton("खारिज", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.setCancelable(true);
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
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
            Toast.makeText(Loan.this, "कृपया मान्य तिथि चुनें", Toast.LENGTH_LONG).show();
        } else {
            payment_date.setText(newDate);
            payment_date.setHint("");
        }
    }
}
