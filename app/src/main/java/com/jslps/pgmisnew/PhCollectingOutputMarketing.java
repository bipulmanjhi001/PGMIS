package com.jslps.pgmisnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.adapter.PhCollectingInputAdapter;
import com.jslps.pgmisnew.adapter.PhCollectingInputSpinnerModel;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.database.PhCollectingInputnewtbl;
import com.jslps.pgmisnew.database.PhCollectingInputtbl;
import com.jslps.pgmisnew.interactor.PhCollectingInputInteractor;
import com.jslps.pgmisnew.presenter.PhCollectingInputPresenter;
import com.jslps.pgmisnew.util.Activitycode;
import com.jslps.pgmisnew.util.GetCurrentDate;
import com.jslps.pgmisnew.view.PhCollectingInputView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhCollectingOutputMarketing extends AppCompatActivity implements PhCollectingInputView, PhCollectingInputInteractor.PhcollectingInputInteractor, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.saves)
    Button saves;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView70)
    TextView textView70;
    @BindView(R.id.imageView14)
    ImageView imageView14;
    @BindView(R.id.payment_date)
    TextView payment_date;
    @BindView(R.id.call_calender)
    ImageView call_calender;
    String Type;

    /*Defining objects*/
    PhCollectingInputPresenter presenternew;
    PhCollectingInputAdapter aAdapternew;

    /*Class Globals*/
    List<Pgmemtbl> pgmemtblList;
    private boolean mActive = false;
    String fromWhichActivity;
    List<PhCollectingInputtbl> phCollectingInputtbls;
    List<PhCollectingInputSpinnerModel> phCollectingInputSpinnerModels;
    ArrayList<PhCollectingInputtbl> phCollectingInputnewtbls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_collecting_output_marketing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        fromWhichActivity = intent.getStringExtra("fromactivity");
        /*initialiazation*/
        presenternew = new PhCollectingInputPresenter(this, new PhCollectingInputInteractor());
        /*calling presenter methods*/
        presenternew.getPgMemnew(PgActivity.pgCodeSelected);
        presenternew.setZoomInnew();
        presenternew.setRecyclerViewnew();
        presenternew.setPgnamenew();
        presenternew.setPgItemNonew();
        phCollectingInputnewtbls=new ArrayList<PhCollectingInputtbl>();

        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenternew.searchnew();
            }
        });

        call_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenternew.openCalender();
            }
        });

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                if (phCollectingInputtbls.size() > 0) {
                    for (int i = 0; i < phCollectingInputtbls.size(); i++) {
                        String type = phCollectingInputSpinnerModels.get(i).getType();
                        try {
                            if (!type.equals("")) {

                                PhCollectingInputnewtbl phCollectingInputnewtbl = new PhCollectingInputnewtbl(
                                        phCollectingInputtbls.get(i).getUuid(),
                                        phCollectingInputtbls.get(i).getPgcode(),
                                        phCollectingInputtbls.get(i).getGrpmemcode(),
                                        phCollectingInputtbls.get(i).getGrpcode(),
                                        "",
                                        payment_date.getText().toString(),
                                        phCollectingInputSpinnerModels.get(i).getType(),
                                        "",
                                        "",
                                        "0",
                                        new GetCurrentDate().getDate(),
                                        "", "", true);

                                phCollectingInputnewtbl.save();
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            new StyleableToast
                                    .Builder(PhCollectingOutputMarketing.this)
                                    .text("कृपया मोड का चयन करें")
                                    .iconStart(R.drawable.wrong_icon_white)
                                    .textColor(Color.WHITE)
                                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                    .show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setPgMemListnew(List<Pgmemtbl> list) {
        pgmemtblList = list;
    }

    @Override
    public void setZoomInnew() {
        Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        imageView2.startAnimation(zoom);
    }

    private void add(Pgmemtbl item, TextInputEditText date, int adapterPosition) {
        PhCollectingInputtbl model = new PhCollectingInputtbl();
        model.setUuid(UUID.randomUUID().toString());
        model.setPgcode(PgActivity.pgCodeSelected);
        model.setGrpmemcode(item.getGrpmemcode());
        model.setGrpcode(item.getGrpcode());
        model.setAdapterposition(adapterPosition + "");
        model.setSelecteddate(payment_date.getText().toString());
        phCollectingInputtbls.set(adapterPosition, model);
        System.out.print("");
    }

    @Override
    public void setRecyclerViewnew() {
        aAdapternew = new PhCollectingInputAdapter(presenternew, pgmemtblList);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapternew);

        phCollectingInputtbls = new ArrayList<>();
        phCollectingInputSpinnerModels = new ArrayList<>();
        if (pgmemtblList.size() > 0) {
            for (int i = 0; i < pgmemtblList.size(); i++) {
                PhCollectingInputtbl model = new PhCollectingInputtbl();
                model.setAdapterposition("");
                phCollectingInputtbls.add(model);

                PhCollectingInputSpinnerModel model1 = new PhCollectingInputSpinnerModel();
                model1.setAdapterposition("");
                phCollectingInputSpinnerModels.add(model1);
            }
        }
    }

    @Override
    public void moveToNextnew() {

    }

    @Override
    public void clearForm() {
        payment_date.setText("");
        payment_date.setHint("भुगतान की तिथि");
    }

    @Override
    public void setPgNamenew() {
        textView23.setText(PgActivity.pgNameSelected);
    }

    @Override
    public void setPgItemsnew() {
        if (pgmemtblList != null) {
            textView70.setText("Members: " + pgmemtblList.size());
        }
    }

    @Override
    public void searchnew() {
        openDilog();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    @Override
    public void setOpenCalender() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                PhCollectingOutputMarketing.this,
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
    public void spinerclicklisten(int adapterposition, String type) {
        String types = null;
        if (phCollectingInputSpinnerModels.size() > 0) {
            types = phCollectingInputSpinnerModels.get(adapterposition).getType();
        }
        PhCollectingInputSpinnerModel model = new PhCollectingInputSpinnerModel();
        model.setAdapterposition(adapterposition + "");
        if (type != null) {
            model.setType(types);
        } else {
            if (type != null) {
                model.setType(types);
            }
        }
        phCollectingInputSpinnerModels.set(adapterposition, model);
        System.out.print("");
    }

    @Override
    public void setViewAdapternew(ConstraintLayout firstLayout, ImageView edit,
                                  ImageView delete, ImageView dropDown,
                                  CheckBox farmername, TextView fatherhusbandshg,
                                  TextView shg, TextView fathername, TextView husbandname,
                                  TextView designation, TextView primaryactivity,
                                  TextView fishery, TextView hva, TextView livestock,
                                  TextView ntfp, View viewLayout, ConstraintLayout layout,
                                  Spinner select_input_type, int adapterPosition) {

        Pgmemtbl item = pgmemtblList.get(adapterPosition);
        edit.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);
        if (adapterPosition == 0) {
            viewLayout.setVisibility(View.VISIBLE);
        } else {
            viewLayout.setVisibility(View.GONE);
        }
        if (layout.getVisibility() == View.VISIBLE) {
            layout.setVisibility(View.GONE);
            dropDown.setRotation(0);
        }
        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActive) {
                    Animation slideup = AnimationUtils.loadAnimation(PhCollectingOutputMarketing.this, R.anim.slide_up);
                    layout.startAnimation(slideup);
                    dropDown.setRotation(0);
                    slideup.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            layout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mActive = false;
                } else {
                    Animation slidedown = AnimationUtils.loadAnimation(PhCollectingOutputMarketing.this, R.anim.slide_down);
                    layout.startAnimation(slidedown);
                    layout.setVisibility(View.VISIBLE);
                    dropDown.setRotation(180);
                    mActive = true;
                }
            }
        });
        //setting values
        farmername.setText(item.getMembername());
        fatherhusbandshg.setText(item.getFatherhusbandnameshg());
        shg.setText(item.getGrpname());
        fathername.setText(item.getFathername());
        husbandname.setText(item.getHusbandname());
        designation.setText(new Activitycode(this).designation(Integer.parseInt(item.getDesignation())));
        primaryactivity.setText(new Activitycode(this).primaryActivity(Integer.parseInt(item.getPrimaryactivity())));

        if (item.getFishery().equals("1")) {
            fishery.setText("Yes");
        } else {
            fishery.setText("No");
        }
        if (item.getHva().equals("1")) {
            hva.setText("Yes");
        } else {
            hva.setText("No");
        }
        if (item.getLivestock().equals("1")) {
            livestock.setText("Yes");
        } else {
            livestock.setText("No");
        }
        if (item.getNtfp().equals("1")) {
            ntfp.setText("Yes");
        } else {
            ntfp.setText("No");
        }
    }

    private void openDilog() {
        final View dialogView = View.inflate(this, R.layout.view_search, null);
        final Dialog dialog = new Dialog(this, R.style.MyAlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);

        TextInputEditText etSearch = dialog.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = etSearch.getText().toString().toLowerCase(Locale.getDefault());
                aAdapternew.filter(text);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        dialog.getWindow().setLayout(width, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
        dialog.setCancelable(true);
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
            Toast.makeText(PhCollectingOutputMarketing.this, "कृपया मान्य तिथि चुनें", Toast.LENGTH_LONG).show();
        } else {
            payment_date.setText(newDate);
            payment_date.setHint("");
        }
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

        presenternew.setRecyclerViewnew();
        presenternew.clearForm();
    }
}
