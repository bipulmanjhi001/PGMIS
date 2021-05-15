package com.jslps.pgmisnew.presenter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.interactor.SHAInteractor;
import com.jslps.pgmisnew.view.SHAView;

import java.util.List;

public class SHAPresenter implements SHAInteractor.shaInteractor {

    private SHAView shaView;
    private SHAInteractor shaInteractor;

    public SHAPresenter(SHAView shaView, SHAInteractor shaInteractor) {
        this.shaView = shaView;
        this.shaInteractor = shaInteractor;
    }

    public void getPgMem(String pgCode){
        shaInteractor.getPgMemList(this,pgCode);
    }

    public void setZoomIn(){
        shaView.setZoomIn();
    }

    @Override
    public void getPgMemList(List<Pgmemtbl> list) {
        shaView.setPgMemList(list);
    }

    public void setPgname(){
        shaView.setPgName();
    }

    public void setViewAdapter(ConstraintLayout firstLayout,
                               TextView farmer,
                               TextView total,
                               TextView paid, TextView remaining,
                               TextInputEditText enterAmount,
                               View viewLayout,
                               int adapterPosition,
                               CheckBox checkBox,
                               TextView pos,
                               TextView payment_date,
                               String payment_type,
                               RadioGroup radioGroup,
                               ImageView call_calender) {

             shaView.setViewAdapter(firstLayout,farmer,total,
                paid,remaining,enterAmount,
                viewLayout,adapterPosition,
                checkBox,pos,payment_date,payment_type,radioGroup,call_calender);
    }

    public void setRecyclerView(){
        shaView.setRecyclerView();
    }

    public void addTextChangeListner(ConstraintLayout firstLayout,
                                     TextView farmer, TextView total, TextView paid,
                                     TextView remaining, TextInputEditText enterAmount,
                                     View viewLayout, int adapterPosition,
                                     CheckBox checkBox,TextView pos,TextView payment_date,
                                     String payment_type) {

        shaView.addTextChangeListner(
                firstLayout,farmer,total,
                paid,remaining,enterAmount,
                viewLayout,adapterPosition,
                checkBox,pos,payment_date,payment_type);
    }

    public void datepaymentclicklisten(int adapterposition,String date,String paymentmode) {
        shaView.datepaymentclicklisten(adapterposition,date,paymentmode);
    }

}
