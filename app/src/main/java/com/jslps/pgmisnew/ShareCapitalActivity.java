package com.jslps.pgmisnew;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.adapter.ShareCapitalActivityAdapter;
import com.jslps.pgmisnew.database.MembershipFeeModel;
import com.jslps.pgmisnew.database.Membershipfeedatepaymodemodel;
import com.jslps.pgmisnew.database.Pgcapitalsavetbl;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.interactor.SHAInteractor;
import com.jslps.pgmisnew.presenter.SHAPresenter;
import com.jslps.pgmisnew.util.AppConstant;
import com.jslps.pgmisnew.util.GetCurrentDate;
import com.jslps.pgmisnew.view.SHAView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareCapitalActivity extends AppCompatActivity implements SHAView {

    @BindView(R.id.imageView2)
    ImageView headerImg;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.textView23)
    TextView tvPgName;
    @BindView(R.id.save)
    Button btnSave;
    @BindView(R.id.parentContainer)
    ConstraintLayout parentContainer;

    /*Defining objects*/
    SHAPresenter presenter;
    ShareCapitalActivityAdapter aAdapter;

    /*Class Globals*/
    List<Pgmemtbl> pgmemtblList;
    List<MembershipFeeModel> membershipFeeModelList;
    List<Membershipfeedatepaymodemodel> membershipfeedatepaymodemodelList;
    boolean setEditTextByCode = false;
    String amount,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_capital);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();

    }

    private void init() {
        /*initialiazation*/
        presenter = new SHAPresenter(this, new SHAInteractor());
        /*calling presenter methods*/
        presenter.getPgMem(PgActivity.pgCodeSelected);
        presenter.setZoomIn();
        presenter.setPgname();
        presenter.setRecyclerView();

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        date = year + "/" + (month + 1) + "/" + day;
    }

    @Override
    public void setPgMemList(List<Pgmemtbl> list) {
        pgmemtblList = list;
    }

    @Override
    public void setZoomIn() {
        Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        headerImg.startAnimation(zoom);
    }

    @Override
    public void setPgName() {
        tvPgName.setText(PgActivity.pgNameSelected);
    }

    @Override
    public void setViewAdapter(ConstraintLayout firstLayout, TextView farmer,
                               TextView total, TextView paid, TextView remaining,
                               TextInputEditText enterAmount, View viewLayout,
                               int adapterPosition, CheckBox checkBox,
                               TextView pos, TextView payment_date, String payment_mode, RadioGroup radioGroup,
                               ImageView call_calender) {

        pos.setText(adapterPosition + "");
        Pgmemtbl item = pgmemtblList.get(Integer.parseInt(pos.getText().toString()));
        if (adapterPosition == 0) {
            viewLayout.setVisibility(View.VISIBLE);
        } else {
            viewLayout.setVisibility(View.GONE);
        }
        farmer.setText(String.format("%s(%s)", item.getMembername(), item.getGrpname()));
        total.setText(AppConstant.SHARECAPITAL);
        String paidS;
        if (item.getSharecapital() != null) {
            paidS = item.getSharecapital();
            if (item.getSharecapital().equals("") || item.getSharecapital().equals("null")) {
                paidS = "0";
            }
            paid.setText(paidS);
        } else {
            paidS = "0";
            paid.setText(paidS);
        }
        float remainingAmount = Float.parseFloat(AppConstant.SHARECAPITAL) - Float.parseFloat(paidS);
        remaining.setText(String.format("%s", remainingAmount));
        if (remainingAmount == 0) {
            firstLayout.setBackgroundResource(R.drawable.item_border_light_green);
            //hide
            radioGroup.setVisibility(View.GONE);
            call_calender.setVisibility(View.GONE);
            payment_date.setVisibility(View.GONE);
        }
        else {
            firstLayout.setBackgroundResource(R.drawable.item_border_view_pg_activity);
            radioGroup.setVisibility(View.VISIBLE);
            call_calender.setVisibility(View.VISIBLE);
            payment_date.setVisibility(View.VISIBLE);
        }
        if (membershipFeeModelList.size() > 0) {
            String amount = membershipFeeModelList.get(adapterPosition).getAmount();
            if (amount != null) {
                setEditTextByCode = true;
                enterAmount.setText(membershipFeeModelList.get(adapterPosition).getAmount());
                } else {
                    setEditTextByCode = true;
                    enterAmount.setText("");
            }
        }
        if(membershipfeedatepaymodemodelList.size()>0){
            String date = membershipfeedatepaymodemodelList.get(adapterPosition).getDate();
            String pmode = membershipfeedatepaymodemodelList.get(adapterPosition).getPmode();
            if(date!=null){
                payment_date.setText(date);
            }else{
                payment_date.setText("भुगतान तिथि का चयन करें");
            }
            if(pmode!=null){
                if(pmode.equals("cash")||pmode.equals("bank")){
                    if(pmode.equals("cash")){
                        radioGroup.check(R.id.cash);
                    }else{
                        radioGroup.check(R.id.bank);
                     }
                }
            }else{
                radioGroup.clearCheck();
            }
        }
    }

    private void timeDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setEditTextByCode = false;
            }
        }, 500);
    }

    @Override
    public void addTextChangeListner(ConstraintLayout firstLayout,
                                     TextView farmer, TextView total,
                                     TextView paid, TextView remaining,
                                     TextInputEditText enterAmount, View viewLayout,
                                     int adapterPosition, CheckBox checkBox,
                                     TextView pos,TextView payment_date,String payment_mode) {

           enterAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String dateselected = null;
                String pmodeselected = null;

                if(membershipfeedatepaymodemodelList.size()>0 &&!setEditTextByCode) {
                    dateselected = membershipfeedatepaymodemodelList.get(Integer.parseInt(pos.getText().toString())).getDate();
                    pmodeselected = membershipfeedatepaymodemodelList.get(Integer.parseInt(pos.getText().toString())).getPmode();
                }
                if(dateselected!=null && pmodeselected!=null) {
                    if (!enterAmount.getText().equals("") && !setEditTextByCode) {
                            Pgmemtbl item = pgmemtblList.get(Integer.parseInt(pos.getText().toString()));
                            String paidS;
                            if (item.getSharecapital() != null) {
                                paidS = item.getSharecapital();
                                if (item.getSharecapital().equals("") || item.getSharecapital().equals("null")) {
                                    paidS = "0";
                                }
                            } else {
                                paidS = "0";
                            }
                            String finalPaidS = paidS;
                            float remainingAmount = Float.parseFloat(AppConstant.SHARECAPITAL) - Float.parseFloat(paidS);
                            String ss = enterAmount.getText().toString();
                            String sb = payment_date.getText().toString();
                            Log.d("sb", sb);
                            if (ss.equals("")) {
                                ss = "0";
                            }
                            if (Float.parseFloat(ss) > remainingAmount) {
                                new StyleableToast
                                        .Builder(ShareCapitalActivity.this)
                                        .text(getString(R.string.cant_be_greater))
                                        .iconStart(R.drawable.wrong_icon_white)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();
                                setEditTextByCode = true;
                                enterAmount.setText("");
                                payment_date.setText("");
                                timeDelay();

                                if (membershipFeeModelList.size() > 0) {
                                        add(item, enterAmount, Integer.parseInt(pos.getText().toString()),
                                                finalPaidS, payment_mode, payment_date);
                             }
                            } else {
                                if (membershipFeeModelList.size() > 0) {
                                        add(item, enterAmount, Integer.parseInt(pos.getText().toString()),
                                                finalPaidS, payment_mode, payment_date);
                                  }
                            }
                        } else {
                            setEditTextByCode = false;
                        }
                    }
                else {
                    if (!setEditTextByCode) {
                        new StyleableToast
                                .Builder(ShareCapitalActivity.this)
                                .text("कृपया भुगतान का तरीका और भुगतान मोड का चयन पहले")
                                .iconStart(R.drawable.wrong_icon_white)
                                .textColor(Color.WHITE)
                                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                .show();

                        setEditTextByCode = true;
                        enterAmount.setText("");
                        timeDelay();
                    } else {
                        setEditTextByCode = false;
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void datepaymentclicklisten(int adapterposition, String date, String paymentmode) {
        String dateset = null,pmodeset = null;
        if(membershipfeedatepaymodemodelList.size()>0) {
            dateset = membershipfeedatepaymodemodelList.get(adapterposition).getDate();
            pmodeset = membershipfeedatepaymodemodelList.get(adapterposition).getPmode();
        }
        Membershipfeedatepaymodemodel model = new Membershipfeedatepaymodemodel();
        model.setAdapterposition(adapterposition+"");

        if(date!=null){
            model.setDate(date);
        }else{
            if(dateset!=null){
                model.setDate(dateset);
            }
        }
        if(paymentmode!=null){
            model.setPmode(paymentmode);
        }else{
            if(pmodeset!=null){
                model.setPmode(pmodeset);
            }
        }
        membershipfeedatepaymodemodelList.set(adapterposition,model);
        System.out.print("");
    }

    private void add(Pgmemtbl item, TextInputEditText enterAmount, int adapterPosition,
                     String finalPaidS,String paymentmode,TextView paymentdate) {

        MembershipFeeModel model = new MembershipFeeModel();
        model.setPgcode(PgActivity.pgCodeSelected);
        model.setPgmemcode(item.getGrpmemcode());
        model.setGrpcode(item.getGrpcode());
        model.setAdapterposition(adapterPosition + "");
        model.setAmount(enterAmount.getText().toString());
        String amt = enterAmount.getText().toString();

        if (amt.equals("")) {
            amt = "0";
        }

        float calculatedAmount = Float.parseFloat(amt) + Float.parseFloat(finalPaidS);
        model.setUpdateamount(calculatedAmount + "");
        membershipFeeModelList.set(adapterPosition, model);
        System.out.print("");
    }

    @Override
    public void setRecyclerView() {
        aAdapter = new ShareCapitalActivityAdapter(ShareCapitalActivity.this,presenter, pgmemtblList);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
        membershipFeeModelList = new ArrayList<>();
        membershipfeedatepaymodemodelList = new ArrayList<>();
        if (pgmemtblList.size() > 0) {
            for (int i = 0; i < pgmemtblList.size(); i++) {
                MembershipFeeModel model = new MembershipFeeModel();
                model.setAdapterposition("");
                membershipFeeModelList.add(model);

                Membershipfeedatepaymodemodel model1 = new Membershipfeedatepaymodemodel();
                model1.setAdapterposition("");
                membershipfeedatepaymodemodelList.add(model1);
            }
        }
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        recylerList.setLayoutAnimation(animation);
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        int count = 0;
        if (membershipFeeModelList.size() > 0) {
            for (int i = 0; i < membershipFeeModelList.size(); i++) {
                String pgCode = membershipFeeModelList.get(i).getPgcode();
                String grpMemCode = membershipFeeModelList.get(i).getPgmemcode();
                String grpCode = membershipFeeModelList.get(i).getGrpcode();
                String calculatedAmount = membershipFeeModelList.get(i).getUpdateamount();

                amount = membershipFeeModelList.get(i).getAmount();
                if (amount == null||amount.equals("")) {
                    count++;
                }
                if (amount != null) {
                        if (!amount.equals("")) {
                            List<Pgmemtbl> list = Select.from(Pgmemtbl.class)
                                    .where(Condition.prop("Pgcode").eq(pgCode))
                                    .where(Condition.prop("Grpmemcode").eq(grpMemCode))
                                    .where(Condition.prop("Grpcode").eq(grpCode))
                                    .list();

                            String membername = list.get(0).getMembername();
                            list.get(0).setSharecapital(calculatedAmount);
                            list.get(0).setIsupdated("1");
                            list.get(0).save();

                            String pgmode = membershipfeedatepaymodemodelList.get(i).getPmode();
                            try {
                                if (!pgmode.equals("")) {
                                    Pgcapitalsavetbl pgCapitalSavetbl = new Pgcapitalsavetbl(
                                            membershipFeeModelList.get(i).getPgcode(),
                                            membershipFeeModelList.get(i).getPgmemcode(),
                                            membershipFeeModelList.get(i).getGrpcode(),
                                            membername,
                                            amount + "",
                                            "0",
                                            UUID.randomUUID().toString(),
                                            new GetCurrentDate().getDate(),
                                            membershipfeedatepaymodemodelList.get(i).getDate(),
                                            membershipfeedatepaymodemodelList.get(i).getPmode());

                                    pgCapitalSavetbl.save();
                                }
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                                new StyleableToast
                                        .Builder(ShareCapitalActivity.this)
                                        .text("कृपया भुगतान मोड का चयन करें")
                                        .iconStart(R.drawable.wrong_icon_white)
                                        .textColor(Color.WHITE)
                                        .backgroundColor(getResources().getColor(R.color.colorPrimary))
                                        .show();
                            }
                        }
                    }
                }
            if (count == membershipFeeModelList.size()) {
                    new StyleableToast
                            .Builder(ShareCapitalActivity.this)
                            .text(getString(R.string.at_least_one_amount))
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                } else {
                    presenter.getPgMem(PgActivity.pgCodeSelected);
                    presenter.setRecyclerView();
                    new StyleableToast
                            .Builder(ShareCapitalActivity.this)
                            .text(getString(R.string.saved))
                            .iconStart(R.drawable.right)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
            }
    }
}
