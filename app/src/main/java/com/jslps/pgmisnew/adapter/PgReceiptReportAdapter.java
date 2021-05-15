package com.jslps.pgmisnew.adapter;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.PaymentReceiptReportModel;
import com.jslps.pgmisnew.database.ReceiptReportModel;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sonu on 4/4/2018.
 */

public class PgReceiptReportAdapter extends RecyclerView.Adapter<PgReceiptReportAdapter.MyViewHolder> {

    private List<ReceiptReportModel> list;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.head)
        TextView head;
        @BindView(R.id.receivedAnt)
        TextView receivedAmt;
        @BindView(R.id.paymentAmt)
        TextView paymentAmt;
        @BindView(R.id.mode)
        TextView mode;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public PgReceiptReportAdapter(Context context, List<ReceiptReportModel> itemList) {
        this.context = context;
        this.list = itemList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_pg_receipt_report_activity, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        ReceiptReportModel item = list.get(position);

      holder.date.setText(item.getDate());
      holder.head.setText(item.getHeadname());
      holder.receivedAmt.setText(item.getReceivedamount());
      holder.paymentAmt.setText(item.getPaymentamount());
      holder.mode.setText(item.getPaymentmode());

//        holder.imgEdit.setVisibility(View.GONE);
//        holder.imgDelete.setVisibility(View.GONE);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
