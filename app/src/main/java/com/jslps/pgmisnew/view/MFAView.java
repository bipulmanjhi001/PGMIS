package com.jslps.pgmisnew.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.database.Pgmemtbl;
import java.util.List;

public interface MFAView {
    void setPgMemList(List<Pgmemtbl> list);

    void setZoomIn();

    void setPgName();

    void setViewAdapter(ConstraintLayout firstLayout, TextView farmer,
                        TextView total, TextView paid, TextView remaining,
                        TextInputEditText enterAmount, View viewLayout,
                        int adapterPosition, CheckBox checkBox,
                        TextView pos, String selectedPay, TextView payment_date,
                        RadioGroup radioGroup, ImageView call_calender);

    void setRecyclerView();

    void addTextChangeListner(ConstraintLayout firstLayout, TextView farmer, TextView total,
                              TextView paid, TextView remaining,
                              TextInputEditText enterAmount, View viewLayout,
                              int adapterPosition, CheckBox checkBox,TextView pos,TextView payment_date,
                              RadioGroup radioGroup,
                              String selectedPay);

    void datepaymentclicklisten(int adapterposition, String date, String paymentmode);
}
