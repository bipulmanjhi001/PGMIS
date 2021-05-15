package com.jslps.pgmisnew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.objects.AlertAction;
import com.jslps.pgmisnew.adapter.MeetingAdapter;
import com.jslps.pgmisnew.database.CadreTypeMaster;
import com.jslps.pgmisnew.database.PgMeetingtbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.database.ReceiptReportModel;
import com.jslps.pgmisnew.database.tblPgMeetingCaders;
import com.jslps.pgmisnew.interactor.MeetingInteractor;
import com.jslps.pgmisnew.presenter.MeetingPresenter;
import com.jslps.pgmisnew.view.MeetingView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orm.query.Condition;
import com.orm.query.Select;
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

public class MeetingDetailsOfPg extends AppCompatActivity implements MeetingView, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.imageView2)
    ImageView headerImg;
    @BindView(R.id.textView23)
    TextView tvPgname;
    @BindView(R.id.textView65)
    TextView tvDate;
    @BindView(R.id.imageView11)
    ImageView imgCalender;
    @BindView(R.id.et_no_of_mem)
    TextInputEditText etNoOfMem;
    @BindView(R.id.checkBox3)
    CheckBox chAKM;
    @BindView(R.id.checkBox4)
    CheckBox chAPS;
    @BindView(R.id.checkBox5)
    CheckBox chAMM;
    @BindView(R.id.checkBox6)
    CheckBox chMBK;
    @BindView(R.id.checkBox7)
    CheckBox chaw;
    @BindView(R.id.acheckBox1)
    CheckBox acheckBox1;
    @BindView(R.id.acheckBox2)
    CheckBox acheckBox2;
    @BindView(R.id.acheckBox3)
    CheckBox acheckBox3;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textInputLayout3)
    TextInputLayout textInputLayout3;
    @BindView(R.id.textView66)
    TextView textView66;
    @BindView(R.id.parentContainer)
    ConstraintLayout parentContainer;
    @BindView(R.id.button4)
    Button btnSave;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    /*Defining objects*/
    MeetingAdapter aAdapter;
    MeetingPresenter presenter;
    /*Class Globals*/
    List<PgMeetingtbl> pgMeetingtblList;
    List<tblPgMeetingCaders> tblPgMeetingCader;

    @BindView(R.id.PurchaseSaleperson1)
    CheckBox PurchaseSaleperson1;
    @BindView(R.id.PurchaseSaleperson2)
    CheckBox PurchaseSaleperson2;
    @BindView(R.id.Planperson1)
    CheckBox Planperson1;
    @BindView(R.id.Planperson2)
    CheckBox Planperson2;
    @BindView(R.id.Socialperson1)
    CheckBox Socialperson1;
    @BindView(R.id.Socialperson2)
    CheckBox Socialperson2;
    @BindView(R.id.checkBoxnew)
    CheckBox checkBoxnew;
    String getunitthis,getunitthiscaders;
    StringBuilder builder;
    String meetingId;

    String unitthis,unitthis1,unitthis2,unitthis3,unitthis4,unitthis5,
            unitthis6,unitthis7,unitthis8,unitthis9,unitthis10,unitthis11,unitthis12,
            unitthis13,unitthis14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details_of_pg);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();
    }

    private void init() {
        /*initialiazation*/
        presenter = new MeetingPresenter(this, new MeetingInteractor());
        pgMeetingtblList  =new ArrayList<>();
        //callling presenter method
        presenter.pgName();
        presenter.getMeetingData(PgActivity.pgCodeSelected);
        presenter.getMeetingData2(PgActivity.pgCodeSelected);
        presenter.callRecyclerView();

        List<CadreTypeMaster> list = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("AKM"))
                .list();
        for (int i = 0; i < list.size(); i++) {
            unitthis = list.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list2 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("APS"))
                .list();
        for (int i = 0; i < list2.size(); i++) {
            unitthis1 = list2.get(i).getCadretypeid();
            Log.d("unitthis1",unitthis1);
        }

        List<CadreTypeMaster> list3 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("AMM"))
                .list();
        for (int i = 0; i < list3.size(); i++) {
            unitthis2 = list3.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list4 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("PG Book keeper"))
                .list();
        for (int i = 0; i < list4.size(); i++) {
            unitthis3 = list4.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list5 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("AW"))
                .list();
        for (int i = 0; i < list5.size(); i++) {
            unitthis4 = list5.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list6 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("Secretary"))
                .list();
        for (int i = 0; i < list6.size(); i++) {
            unitthis5 = list6.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list7 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("President"))
                .list();
        for (int i = 0; i < list7.size(); i++) {
            unitthis6 = list7.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list8 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("Treasurer"))
                .list();
        for (int i = 0; i < list8.size(); i++) {
            unitthis7 = list8.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list9 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("Sales and Purchase-1"))
                .list();
        for (int i = 0; i < list9.size(); i++) {
            unitthis8 = list9.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list10 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("Sales and Purchase-2"))
                .list();
        for (int i = 0; i < list10.size(); i++) {
            unitthis9 = list10.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list11 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("Planning committee-1"))
                .list();
        for (int i = 0; i < list11.size(); i++) {
            unitthis10 = list11.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list12 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("Planning committee-2"))
                .list();
        for (int i = 0; i < list12.size(); i++) {
            unitthis11 = list12.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list13 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("social and enivironment committee-1"))
                .list();
        for (int i = 0; i < list13.size(); i++) {
            unitthis12 = list13.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list14 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("social and enivironment committee-2"))
                .list();
        for (int i = 0; i < list14.size(); i++) {
            unitthis13 = list14.get(i).getCadretypeid();
        }

        List<CadreTypeMaster> list15 = Select.from(CadreTypeMaster.class)
                .where(Condition.prop("Cadredesignation").eq("AVM"))
                .list();
        for (int i = 0; i < list15.size(); i++) {
            unitthis14 = list15.get(i).getCadretypeid();
        }
    }

    @Override
    public void setPgName() {
        tvPgname.setText(PgActivity.pgNameSelected);
    }

    @Override
    public void setOpenCalender() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MeetingDetailsOfPg.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void setAdapter(ImageView edit, ImageView delete, TextView date, TextView no, TextView cadre, ConstraintLayout layout1, ConstraintLayout layout2,int position) {
        PgMeetingtbl item = pgMeetingtblList.get(position);
        //currently not using edit button
        edit.setVisibility(View.GONE);
        date.setText(item.getMeetingdate());
        no.setText(item.getNoofpeople());
        cadre.setText(item.getCadres());
        String meetingid=item.getMeetingid();
        //delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert(MeetingDetailsOfPg.this.getString(R.string.delete_sure),"",item.getId(),meetingid);
            }
        });
    }

    @Override
    public void dateNoEmpty() {
        new StyleableToast
                .Builder(this)
                .text(getString(R.string.date_cant_be_empty))
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    @Override
    public void saveData() {
        meetingId = UUID.randomUUID().toString();
        StringBuilder builder = new StringBuilder();
        if (chAKM.isChecked()) {
            builder.append(unitthis);
            builder.append(",");
        }else {
            unitthis="";
        }
        if (chAPS.isChecked()) {
           builder.append(unitthis1);
            builder.append(",");
        }else {
            unitthis1="";
        }
        if (chAMM.isChecked()) {
            builder.append(unitthis2);
            builder.append(",");
        }else {
            unitthis2="";
        }
        if (chMBK.isChecked()) {
            builder.append(unitthis3);
            builder.append(",");
        }else {
            unitthis3="";
        }
        if(chaw.isChecked()){
            builder.append(unitthis4);
            builder.append(",");
        }else {
            unitthis4="";
        }
        if (acheckBox1.isChecked()) {
            builder.append(unitthis5);
            builder.append(",");
        }else {
            unitthis5="";
        }
        if (acheckBox2.isChecked()) {
            builder.append(unitthis6);
            builder.append(",");
        }else {
            unitthis6="";
        }
        if(acheckBox3.isChecked()){
            builder.append(unitthis7);
            builder.append(",");
        }else {
            unitthis7="";
        }
        if (PurchaseSaleperson1.isChecked()) {
            builder.append(unitthis8);
            builder.append(",");
        }else {
            unitthis8="";
        }
        if (PurchaseSaleperson2.isChecked()) {
            builder.append(unitthis9);
            builder.append(",");
        }else {
            unitthis9="";
        }
        if (Planperson1.isChecked()) {
            builder.append(unitthis10);
            builder.append(",");
        }else {
            unitthis10="";
        }
        if(Planperson2.isChecked()){
            builder.append(unitthis11);
            builder.append(",");
        }else {
            unitthis11="";
        }
        if (Socialperson1.isChecked()) {
            builder.append(unitthis12);
            builder.append(",");
        }else {
            unitthis12="";
        }
        if (Socialperson2.isChecked()) {
            builder.append(unitthis13);
            builder.append(",");
        }else {
            unitthis13="";
        }
        if (checkBoxnew.isChecked()) {
            builder.append(unitthis14);
            builder.append(",");
        }else {
            unitthis14="";
        }
        try {
           builder.setLength(builder.length() - 1);
       }catch (StringIndexOutOfBoundsException e){
           e.printStackTrace();
           new StyleableToast
                   .Builder(this)
                   .text("सदस्य प्रकारों का चयन करेंं")
                   .iconStart(R.drawable.wrong_icon_white)
                   .textColor(Color.WHITE)
                   .backgroundColor(getResources().getColor(R.color.colorPrimary))
                   .show();
       }

     if(!tvDate.getText().toString().equals("बैठक की तारीख दर्ज करें")) {
         if( builder.toString().length() > 1) {
             String strMain=builder.toString();
             String[] arrSplit = strMain.split(",");
             for (int j = 0; j < arrSplit.length; j++) {
                 String caders = arrSplit[j].toString();
                 List<CadreTypeMaster> list = Select.from(CadreTypeMaster.class)
                         .where(Condition.prop("Cadretypeid").eq(caders))
                         .list();
                 for (int i = 0; i < list.size(); i++) {
                     getunitthis = list.get(i).getCadredesignationHin();
                           getunitthiscaders = getunitthiscaders + "," + getunitthis;
                           getunitthiscaders=getunitthiscaders.replaceAll("null,","");
                 }
               }
                 PgMeetingtbl data = new PgMeetingtbl(meetingId, tvDate.getText().toString(),
                         etNoOfMem.getText().toString(),
                         getunitthiscaders,
                         PgActivity.pgCodeSelected, "0");

                 data.save();

             tblPgMeetingCaders data2 = new tblPgMeetingCaders(meetingId,builder.toString(),PgActivity.pgCodeSelected,"0");

             data2.save();

             new StyleableToast
                     .Builder(this)
                     .text(getString(R.string.meeting_saved_successfully))
                     .iconStart(R.drawable.right)
                     .textColor(Color.WHITE)
                     .backgroundColor(getResources().getColor(R.color.colorPrimary))
                     .show();

             clearForm();
             presenter.callRecyclerView();

         }else {
             new StyleableToast
                     .Builder(this)
                     .text("सदस्य प्रकारों का चयन करेंं")
                     .iconStart(R.drawable.wrong_icon_white)
                     .textColor(Color.WHITE)
                     .backgroundColor(getResources().getColor(R.color.colorPrimary))
                     .show();
         }
     }else {
         new StyleableToast
                 .Builder(this)
                 .text("बैठक की तारीख दर्ज करें")
                 .iconStart(R.drawable.wrong_icon_white)
                 .textColor(Color.WHITE)
                 .backgroundColor(getResources().getColor(R.color.colorPrimary))
                 .show();
     }
    }

    private void clearForm() {
        tvDate.setText("बैठक की तारीख दर्ज करें");
        etNoOfMem.setText("");
        chMBK.setChecked(false);
        chAKM.setChecked(false);
        chAMM.setChecked(false);
        chAPS.setChecked(false);
        chaw.setChecked(false);
        acheckBox1.setChecked(false);
        acheckBox2.setChecked(false);
        acheckBox3.setChecked(false);
        PurchaseSaleperson1.setChecked(false);
        PurchaseSaleperson2.setChecked(false);
        Planperson1.setChecked(false);
        Planperson2.setChecked(false);
        Socialperson1.setChecked(false);
        Socialperson2.setChecked(false);
        checkBoxnew.setChecked(false);
        Intent intent = new Intent(MeetingDetailsOfPg.this, MeetingDetailsOfPg.class);
        startActivity(intent);
        finish();
     }

    @Override
    public void setRecyclerView() {
        presenter.getMeetingData(PgActivity.pgCodeSelected);
        aAdapter = new MeetingAdapter(presenter, pgMeetingtblList);
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
    }

    @Override
    public void meetingData(List<PgMeetingtbl> list) {
        pgMeetingtblList = list;
    }

    @Override
    public void meetingData2(List<tblPgMeetingCaders> list2) {
        tblPgMeetingCader=list2;
    }

    @OnClick(R.id.imageView11)
    public void onViewClicked() {
        presenter.openCalender();
    }

    @OnClick(R.id.textView65)
    public void onViewClicked1() {
        presenter.openCalender();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
       String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
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
            Toast.makeText(MeetingDetailsOfPg.this,"कृपया मान्य तिथि चुनें",Toast.LENGTH_LONG).show();
        }else{
            tvDate.setText(date);
        }
    }

    @OnClick(R.id.button4)
    public void onViewClickedsave() {
        presenter.validation(tvDate.getText().toString(), etNoOfMem.getText().toString(),
                chAKM.isChecked(), chAPS.isChecked(), chAMM.isChecked(), chMBK.isChecked(),
                chaw.isChecked(), acheckBox1.isChecked(), acheckBox2.isChecked(),acheckBox3.isChecked(),
                PurchaseSaleperson1.isChecked(), PurchaseSaleperson2.isChecked(), Planperson1.isChecked(),
                Planperson2.isChecked(), Socialperson1.isChecked(), Socialperson2.isChecked(),checkBoxnew.isChecked());

    }

    private void alert(String heading,String message,long id,String meetingid){
        com.irozon.alertview.AlertView alert = new com.irozon.alertview.AlertView(heading, message, AlertStyle.DIALOG);
        alert.addAction(new AlertAction(getString(R.string.yes), AlertActionStyle.DEFAULT, action -> {
            //delete logic here
            PgMeetingtbl data = PgMeetingtbl.findById(PgMeetingtbl.class, id);
            data.delete();

            tblPgMeetingCaders.deleteAll(tblPgMeetingCaders.class, "Meetingid = ?", meetingid);

            new StyleableToast
                    .Builder(MeetingDetailsOfPg.this)
                    .text("सफलतापूर्वक हटा दिया गया")
                    .iconStart(R.drawable.right)
                    .textColor(Color.WHITE)
                    .backgroundColor(getResources().getColor(R.color.colorPrimary))
                    .show();
            //refreshing
            presenter.callRecyclerView();
        }));
        alert.addAction(new AlertAction(getString(R.string.no), AlertActionStyle.DEFAULT, action -> {

        }));
        alert.show(MeetingDetailsOfPg.this);
    }
}
