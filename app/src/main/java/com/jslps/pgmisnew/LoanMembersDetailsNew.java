package com.jslps.pgmisnew;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.adapter.MemberDetailsActivityAdapter;
import com.jslps.pgmisnew.adapter.MemberDetailsActivityAdapternew;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.interactor.MDAInteractor;
import com.jslps.pgmisnew.interactor.MDAInteractornew;
import com.jslps.pgmisnew.presenter.MDAPresenter;
import com.jslps.pgmisnew.presenter.MDAPresenternew;
import com.jslps.pgmisnew.util.Activitycode;
import com.jslps.pgmisnew.view.MDAView;
import com.jslps.pgmisnew.view.MDAViewnew;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanMembersDetailsNew extends AppCompatActivity  implements MDAViewnew {

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

    /*Defining objects*/
    MDAPresenternew presenternew;
    MemberDetailsActivityAdapternew aAdapternew;

    /*Class Globals*/
    List<Pgmemtbl> pgmemtblList;
    String primaryCodeSelected, primaryNameSelected, designationSelected, genderNameSelected, castNameSelected;
    int designationCodeSelected, genderCodeSelected, castCodeSelected;
    List<String> primary;
    List<String> primaryName;
    private boolean mActive = false;
    String fromWhichActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_members_details_new);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        fromWhichActivity = intent.getStringExtra("fromactivity");
        /*initialiazation*/
        presenternew = new MDAPresenternew(this, new MDAInteractornew());
        /*calling presenter methods*/
        presenternew.getPgMemnew(PgActivity.pgCodeSelected);
        presenternew.setZoomInnew();
        presenternew.setRecyclerViewnew();
        presenternew.setPgnamenew();
        presenternew.setPgItemNonew();
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenternew.searchnew();
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

    @Override
    public void setRecyclerViewnew() {
        aAdapternew = new MemberDetailsActivityAdapternew(presenternew, pgmemtblList);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapternew);
    }

    @Override
    public void moveToNextnew() {

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
    public void setViewAdapternew(ConstraintLayout firstLayout, ImageView edit, ImageView delete, ImageView dropDown, TextView farmername, TextView fatherhusbandshg, TextView shg, TextView fathername, TextView husbandname, TextView designation, TextView primaryactivity, TextView fishery, TextView hva, TextView livestock, TextView ntfp, TextView memfee, TextView sharecapital, View viewLayout, ConstraintLayout layout, Button iteam_click, Button cheque_click, String click_result, int adapterPosition) {
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
                    Animation slideup = AnimationUtils.loadAnimation(LoanMembersDetailsNew.this, R.anim.slide_up);
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
                    Animation slidedown = AnimationUtils.loadAnimation(LoanMembersDetailsNew.this, R.anim.slide_down);
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
        memfee.setText(item.getMembershipfee());
        sharecapital.setText(item.getSharecapital());
        iteam_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanMembersDetailsNew.this, Loan.class);
                intent.putExtra("pgcode", item.getPgcode());
                intent.putExtra("grpcode", item.getGrpcode());
                intent.putExtra("grpmemcode", item.getGrpmemcode());
                intent.putExtra("membername", item.getMembername());
                startActivity(intent);
            }
        });

        cheque_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanMembersDetailsNew.this, ChequeLoanActivity.class);
                intent.putExtra("pgcode", item.getPgcode());
                intent.putExtra("grpcode", item.getGrpcode());
                intent.putExtra("grpmemcode", item.getGrpmemcode());
                intent.putExtra("membername", item.getMembername());
                startActivity(intent);
            }
        });
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
}