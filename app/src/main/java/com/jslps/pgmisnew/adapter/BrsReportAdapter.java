package com.jslps.pgmisnew.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.BrsReportModel;
import com.jslps.pgmisnew.database.ReceiptReportModel;
import com.jslps.pgmisnew.presenter.BrsReportPresenter;
import com.jslps.pgmisnew.view.BrsReport;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BrsReportAdapter extends RecyclerView.Adapter<BrsReportAdapter.MyViewHolder> {


    private List<BrsReportModel> list;
    private Context context;
    BrsReportPresenter presenter;


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.head)
        TextView head;
        @BindView(R.id.receivedAnt)
        TextView receivedAmt;
        @BindView(R.id.paymentAmt)
        TextView paymentAmt;
        @BindView(R.id.cashBookImgBtn)
        ImageView cashBookImgBtn;
        @BindView(R.id.passbookImgBtn)
        ImageView passbookImgBtn;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public BrsReportAdapter(Context context, List<BrsReportModel> itemList,BrsReportPresenter presenter) {
        this.context = context;
        this.list = itemList;
        this.presenter = presenter;
    }
//    public BrsReportAdapter(Context context, List<BrsReportModel> itemList, BrsReportPresenter presenter) {
//        this.context = context;
//        this.list = itemList;
//        this.presenter = presenter;
//    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_brs_report, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        BrsReportModel item = list.get(position);

        holder.date.setText(item.getYear());
        holder.head.setText(item.getMonth());
        holder.receivedAmt.setText(item.getBalanceCashbook());
        holder.paymentAmt.setText(item.getBalancePassbook());
        //----------- below is for image view ----------
        //show cashbook image
        holder.cashBookImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = item.getImageCashbook();
                presenter.showImage(imageName);
            }
        });
        //show passbook image
        holder.passbookImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = item.getImagePassbook();
                presenter.showImage(imageName);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
