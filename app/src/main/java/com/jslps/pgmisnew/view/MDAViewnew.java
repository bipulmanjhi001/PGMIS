package com.jslps.pgmisnew.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jslps.pgmisnew.database.Pgmemtbl;
import java.util.List;

public interface MDAViewnew {

    void setPgMemListnew(List<Pgmemtbl> list);

    void setZoomInnew();

    void setRecyclerViewnew();

    void moveToNextnew();

    void setPgNamenew();

    void setPgItemsnew();

    void searchnew();

    void setViewAdapternew(ConstraintLayout firstLayout,
                           ImageView edit,
                           ImageView delete,
                           ImageView dropDown,
                           TextView farmername,
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
                           TextView memfee,
                           TextView sharecapital,
                           View viewLayout,
                           ConstraintLayout layout,
                           Button iteam_click,
                           Button cheque_click,
                           String click_result,
                           int adapterPosition);
}
