package com.jslps.pgmisnew.presenter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.interactor.MDAInteractornew;
import com.jslps.pgmisnew.view.MDAViewnew;

import java.util.List;

public class MDAPresenternew implements MDAInteractornew.mdaInteractornew {

    private MDAViewnew mdaViewnew;
    private MDAInteractornew mdaInteractornew;

    public MDAPresenternew(MDAViewnew mdaViewnew, MDAInteractornew mdaInteractornew) {
        this.mdaViewnew = mdaViewnew;
        this.mdaInteractornew = mdaInteractornew;
    }

    public void getPgMemnew(String pgCode){
        mdaInteractornew.getPgMemListnew(this,pgCode);
    }

    @Override
    public void getPgMemListnew(List<Pgmemtbl> list) {
        mdaViewnew.setPgMemListnew(list);
    }

    public void setZoomInnew(){
        mdaViewnew.setZoomInnew();
    }

    public void setRecyclerViewnew(){
        mdaViewnew.setRecyclerViewnew();
    }

    public void setViewAdapternew(ConstraintLayout firstLayout,
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
                                  int adapterPosition) {

        mdaViewnew.setViewAdapternew(
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
                memfee,
                sharecapital,
                viewLayout,
                layout,
                iteam_click,
                cheque_click,
                click_result,
                adapterPosition);
    }

    public void moveToNextActivitynew(){
        mdaViewnew.moveToNextnew();
    }

    public void setPgnamenew(){
        mdaViewnew.setPgNamenew();
    }

    public void setPgItemNonew(){
        mdaViewnew.setPgItemsnew();
    }

    public void searchnew(){
        mdaViewnew.searchnew();
    }

}

