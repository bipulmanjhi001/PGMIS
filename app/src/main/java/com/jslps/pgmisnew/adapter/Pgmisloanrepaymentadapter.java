package com.jslps.pgmisnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.Pgmisloanrepaymenttabl;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sonu on 4/4/2018.
 */

public class Pgmisloanrepaymentadapter extends RecyclerView.Adapter<Pgmisloanrepaymentadapter.MyViewHolder> {

    private List<Pgmisloanrepaymenttabl> list;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView119)
        TextView textView119;
        @BindView(R.id.textView120)
        TextView textView120;
        @BindView(R.id.textView121)
        TextView textView121;
        @BindView(R.id.textView122)
        TextView textView122;
        @BindView(R.id.Payment_type)
        TextView Payment_type;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public Pgmisloanrepaymentadapter(Context context, List<Pgmisloanrepaymenttabl> itemList) {
        this.context = context;
        this.list = itemList;

    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_loan_repayment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        Pgmisloanrepaymenttabl item = list.get(position);
        holder.textView121.setText(item.getEntrydate());
        holder.textView122.setText(item.getAmount());
        holder.Payment_type.setText(item.getPaymentdate()+"("+item.getSelectedPaymentMode()+")");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
