package com.jslps.pgmisnew.presenter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.interactor.MFAInteractor;
import com.jslps.pgmisnew.view.MFAView;

import java.util.List;

public class MFAPresenter implements MFAInteractor.mfaInteractor {
    private MFAView mfaView;
    private MFAInteractor mfaInteractor;

    public MFAPresenter(MFAView mfaView, MFAInteractor mfaInteractor) {
        this.mfaView = mfaView;
        this.mfaInteractor = mfaInteractor;
    }

    public void getPgMem(String pgCode){
        mfaInteractor.getPgMemList(this,pgCode);
    }

    public void setZoomIn(){
        mfaView.setZoomIn();
    }

    @Override
    public void getPgMemList(List<Pgmemtbl> list) {
        mfaView.setPgMemList(list);
    }

    public void setPgname(){
        mfaView.setPgName();
    }

    public void setViewAdapter(ConstraintLayout firstLayout, TextView farmer, TextView total,
                               TextView paid, TextView remaining, TextInputEditText enterAmount,
                               View viewLayout, int adapterPosition, CheckBox checkBox,
                               TextView pos, String selectedPay, TextView payment_date, RadioGroup radioGroup,
                               ImageView call_calender) {
        mfaView.setViewAdapter(firstLayout,farmer,total,paid,remaining,enterAmount,viewLayout,
                adapterPosition,checkBox,pos,selectedPay,payment_date,radioGroup,call_calender);
    }

    public void setRecyclerView(){
        mfaView.setRecyclerView();
    }

    public void addTextChangeListner(ConstraintLayout firstLayout, TextView farmer,
                                     TextView total, TextView paid, TextView remaining,
                                     TextInputEditText enterAmount, View viewLayout,
                                     int adapterPosition, CheckBox checkBox,TextView pos,TextView payment_date,
                                     RadioGroup radioGroup,
                                     String selectedPay){

        mfaView.addTextChangeListner(firstLayout,farmer,total,paid,remaining,enterAmount,viewLayout,
                adapterPosition,checkBox,pos,payment_date,radioGroup,
                selectedPay);
    }

    public void datepaymentclicklisten(int adapterposition,String date,String paymentmode) {
        mfaView.datepaymentclicklisten(adapterposition,date,paymentmode);
    }
}
