package com.jslps.pgmisnew.presenter;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.interactor.PhCollectingInputInteractor;
import com.jslps.pgmisnew.view.PhCollectingInputView;
import java.util.List;

public class PhCollectingInputPresenter implements PhCollectingInputInteractor.PhcollectingInputInteractor,PhCollectingInputInteractor.PhCollectingInputView {

    private PhCollectingInputInteractor phCollectingInputInteractor;
    private PhCollectingInputView phCollectingInputView;

    public PhCollectingInputPresenter(PhCollectingInputView phCollectingInputView, PhCollectingInputInteractor phCollectingInputInteractor) {
        this.phCollectingInputView = phCollectingInputView;
        this.phCollectingInputInteractor = phCollectingInputInteractor;
    }

    public void getPgMemnew(String pgCode) {
        phCollectingInputInteractor.getPgMemListnew(this, pgCode);
    }

    @Override
    public void getPgMemListnew(List<Pgmemtbl> list) {
        phCollectingInputView.setPgMemListnew(list);
    }

    public void setZoomInnew() {
        phCollectingInputView.setZoomInnew();
    }

    public void setRecyclerViewnew() {
        phCollectingInputView.setRecyclerViewnew();
    }

    public void setViewAdapternew(ConstraintLayout firstLayout,
                                  ImageView edit,
                                  ImageView delete,
                                  ImageView dropDown,
                                  CheckBox farmername,
                                  TextView fatherhusbandshg,
                                  TextView shg,
                                  TextView fathername,
                                  TextView husbandname,
                                  TextView designation,
                                  TextView primaryactivity,
                                  TextView fishery,
                                  TextView hva,
                                  TextView livestock,
                                  TextView ntfp,
                                  View viewLayout,
                                  ConstraintLayout layout,
                                  Spinner select_input_type,
                                  int adapterPosition) {

        phCollectingInputView.setViewAdapternew(
                firstLayout,
                edit,
                delete,
                dropDown,
                farmername,
                fatherhusbandshg,
                shg,
                fathername,
                husbandname,
                designation,
                primaryactivity,
                fishery,
                hva,
                livestock,
                ntfp,
                viewLayout,
                layout,
                select_input_type,
                adapterPosition);
    }

    public void moveToNextActivitynew() {
        phCollectingInputView.moveToNextnew();
    }

    public void setPgnamenew() {
        phCollectingInputView.setPgNamenew();
    }

    public void setPgItemNonew() {
        phCollectingInputView.setPgItemsnew();
    }

    public void searchnew() {
        phCollectingInputView.searchnew();
    }

    public void openCalender() {
        phCollectingInputView.setOpenCalender();
    }

    public void blankDate() {
        phCollectingInputView.blankDate();
    }

    public String[] getUserDetails() {
        return phCollectingInputInteractor.getUserDetails( this);
    }

    @Override
    public void dataSaved() {
        phCollectingInputInteractor.saveData(this);

    }

    public void spinerclicklisten(int adapterposition,String type) {
        phCollectingInputView.spinerclicklisten(adapterposition,type);
    }

    public void clearForm() {
        phCollectingInputView.clearForm();
    }
}
