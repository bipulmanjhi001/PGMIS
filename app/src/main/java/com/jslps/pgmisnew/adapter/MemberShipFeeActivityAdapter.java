package com.jslps.pgmisnew.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.PgpaymentActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.presenter.MFAPresenter;
import org.jetbrains.annotations.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by sonu on 4/4/2018.
 */
public class MemberShipFeeActivityAdapter extends RecyclerView.Adapter<MemberShipFeeActivityAdapter.MyViewHolder> {

    private List<Pgmemtbl> list;
    private MFAPresenter presenter;
    String selectedPay;
    Context contex;
    int mYear,mMonth,mDay;

    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView farmer,total,paid,remaining,textView62,payment_date;
        ConstraintLayout firstLayout;
        TextInputEditText enterAmount;
        CheckBox checkBox;
        RadioGroup radioGroup;
        View viewlayout;
        ImageView call_calender;
        private RadioButton cash,bank;

        MyViewHolder(View view) {
            super(view);

            farmer = view.findViewById(R.id.textView7);
            total = view.findViewById(R.id.textView57);
            paid = view.findViewById(R.id.textView58);
            remaining = view.findViewById(R.id.textView61);
            firstLayout = view.findViewById(R.id.firstLayout);
            viewlayout = view.findViewById(R.id.view);
            enterAmount = view.findViewById(R.id.et_member_fee);
            checkBox = view.findViewById(R.id.checkBox);
            textView62 = view.findViewById(R.id.textView62);
            payment_date = view.findViewById(R.id.payment_date);
            radioGroup = view.findViewById(R.id.radiogroup);
            call_calender = view.findViewById(R.id.call_calender);

            cash = view.findViewById(R.id.cash);
            bank = view.findViewById(R.id.bank);

                presenter.addTextChangeListner(firstLayout,
                        farmer, total, paid, remaining, enterAmount,
                        viewlayout, getAdapterPosition(), checkBox, textView62, payment_date,
                        radioGroup,
                        selectedPay);
        }
    }

    public MemberShipFeeActivityAdapter(Context contex,MFAPresenter presenter, List<Pgmemtbl> itemList) {
        this.presenter = presenter;
        this.list = itemList;
        this.contex = contex;
    }

    @NotNull
    @Override
    public MemberShipFeeActivityAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_membershipfee, parent, false);

        return new MemberShipFeeActivityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MemberShipFeeActivityAdapter.MyViewHolder holder, int position) {
        int positionn = position;
        holder.call_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                //    DatePickerDialog mDatePicker=new DatePickerDialog();
                android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(contex,R.style.DialogTheme,
                        new android.app.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                String newDay = dayOfMonth + "";
                                String newMonth = (monthOfYear + 1) + "";
                                if ((monthOfYear + 1) < 10) {
                                    newMonth = "0" + newMonth;
                                }
                                if (dayOfMonth < 10) {
                                    newDay = "0" + dayOfMonth;
                                }
                                String newDate = year + "/" + newMonth + "/" + newDay;
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
                                    Toast.makeText(contex, "कृपया मान्य तिथि चुनें", Toast.LENGTH_LONG).show();
                                } else {
                                    holder.payment_date.setText(newDate);
                                    presenter.datepaymentclicklisten(positionn,newDate,null);
                                    holder.payment_date.setHint("");
                                }
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        holder.cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.datepaymentclicklisten(positionn,null,"cash");
            }
        });

        holder.bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.datepaymentclicklisten(positionn,null,"bank");
            }
        });

        presenter.setViewAdapter(
               holder.firstLayout,
                holder.farmer,
                holder.total,
                holder.paid,
                holder.remaining,
                holder.enterAmount,
                holder.viewlayout,
                position,
                holder.checkBox,
                holder.textView62,
                selectedPay,
                holder.payment_date,
                holder.radioGroup,
                holder.call_calender);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
