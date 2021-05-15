package com.jslps.pgmisnew.presenter;

import android.media.Image;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jslps.pgmisnew.database.PgMeetingtbl;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.database.tblPgMeetingCaders;
import com.jslps.pgmisnew.interactor.MeetingInteractor;
import com.jslps.pgmisnew.interactor.SHAInteractor;
import com.jslps.pgmisnew.view.MeetingView;
import com.jslps.pgmisnew.view.SHAView;

import java.util.List;

public class MeetingPresenter implements MeetingInteractor.meetingInteractor {

    private MeetingView meetingView;
    private MeetingInteractor meetingInteractor;

    public MeetingPresenter(MeetingView meetingView, MeetingInteractor meetingInteractor) {
        this.meetingView = meetingView;
        this.meetingInteractor = meetingInteractor;
    }

    public void pgName(){
        meetingView.setPgName();
    }

    public void openCalender(){
        meetingView.setOpenCalender();
    }

    public void adapter(ImageView edit, ImageView delete, TextView date, TextView no, TextView cadre, ConstraintLayout layout1, ConstraintLayout layout2, int position){
        meetingView.setAdapter(edit,delete,date,no,cadre,layout1,layout2,position);
    }

    public void validation(String date,String no,boolean AKM,boolean APS, boolean AMM,boolean MBK,boolean AW,
                           boolean acheckBox1,boolean acheckBox2,boolean acheckBox3,boolean acheckBox4,
                           boolean acheckBox5,boolean acheckBox6,boolean acheckBox7,boolean acheckBox8,
                           boolean acheckBox9,boolean acheckBox10){
        meetingInteractor.Validation(date,no,AKM,APS,AMM,MBK,AW,acheckBox1,acheckBox2,acheckBox3,acheckBox4,acheckBox5,acheckBox6,acheckBox7,acheckBox8,acheckBox9,acheckBox10,this);
    }

    @Override
    public void success() {
        meetingView.saveData();
    }

    @Override
    public void dateNo() {
        meetingView.dateNoEmpty();
    }

    @Override
    public void getMeetingData(List<PgMeetingtbl> list) {
        meetingView.meetingData(list);
    }

    @Override
    public void getMeetingData2(List<tblPgMeetingCaders> list2) {
        meetingView.meetingData2(list2);
    }

    public void callRecyclerView(){
        meetingView.setRecyclerView();
    }

    public void getMeetingData(String pgCode){
        meetingInteractor.getMeetingData(pgCode,this);
    }

    public void getMeetingData2(String pgCode){
        meetingInteractor.getMeetingData2(pgCode,this);
    }
}
