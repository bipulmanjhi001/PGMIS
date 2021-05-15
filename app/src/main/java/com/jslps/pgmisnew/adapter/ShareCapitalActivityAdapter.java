package com.jslps.pgmisnew.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.google.android.material.textfield.TextInputEditText;
import com.jslps.pgmisnew.PgpaymentActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.ShareCapitalActivity;
import com.jslps.pgmisnew.StockPurchase;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.presenter.MFAPresenter;
import com.jslps.pgmisnew.presenter.SHAPresenter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
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

public class ShareCapitalActivityAdapter extends RecyclerView.Adapter<ShareCapitalActivityAdapter.MyViewHolder>{

    private List<Pgmemtbl> list;
    private SHAPresenter presenter;
    String selectedPay="cash";
    Context contex;
    int mYear,mMonth,mDay;

    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView farmer,total,paid,remaining,textView62,payment_date;
        ConstraintLayout firstLayout;
        TextInputEditText enterAmount;
        ImageView call_calender;
        CheckBox checkBox;
        View viewlayout;
        RadioGroup radioGroup;
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
            call_calender = view.findViewById(R.id.call_calender);
            payment_date = view.findViewById(R.id.payment_date);
            radioGroup = view.findViewById(R.id.radiogroup);
            cash = view.findViewById(R.id.cash);
            bank = view.findViewById(R.id.bank);
            enterAmount.setHint("");

                presenter.addTextChangeListner(firstLayout,
                        farmer, total, paid, remaining, enterAmount, viewlayout,
                        getAdapterPosition(), checkBox, textView62, payment_date,
                        selectedPay);
        }
    }

    public ShareCapitalActivityAdapter(Context contex, SHAPresenter presenter, List<Pgmemtbl> itemList) {
        this.presenter = presenter;
        this.list = itemList;
        this.contex =contex;
    }

    @SuppressLint("NewApi")
    @NotNull
    @Override
    public ShareCapitalActivityAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sharecapital, parent, false);
        return new ShareCapitalActivityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShareCapitalActivityAdapter.MyViewHolder holder, int position) {
        int positionn = position;
        holder.call_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

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
                holder.payment_date,
                selectedPay,
                holder.radioGroup,
                holder.call_calender);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
