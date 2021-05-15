package com.jslps.pgmisnew.view;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jslps.pgmisnew.database.Pgmemtbl;

import java.util.List;

public interface PhCollectingInputView {

    void setPgMemListnew(List<Pgmemtbl> list);

    void setZoomInnew();

    void setRecyclerViewnew();

    void moveToNextnew();

    void clearForm();

    void setPgNamenew();

    void setPgItemsnew();

    void searchnew();

    void setOpenCalender();

    void blankDate();

    void spinerclicklisten(int adapterposition,String type);

    void setViewAdapternew(ConstraintLayout firstLayout,
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
                           int adapterPosition);

}
