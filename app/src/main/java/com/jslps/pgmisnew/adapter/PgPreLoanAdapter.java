package com.jslps.pgmisnew.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.objects.AlertAction;
import com.jslps.pgmisnew.BankWithdrawCashDeposit;
import com.jslps.pgmisnew.Loan;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.jslps.pgmisnew.presenter.PgPaymentPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sonu on 4/4/2018.
 */

public class PgPreLoanAdapter extends RecyclerView.Adapter<PgPreLoanAdapter.MyViewHolder> {

    private List<PgmisLoantbl> list;
    private Context context;
    PgmisLoantbl item;
    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView72)
        TextView textView72;
        @BindView(R.id.textView73)
        TextView textView73;
        @BindView(R.id.textView74)
        TextView textView74;
        @BindView(R.id.textView75)
        TextView textView75;
        @BindView(R.id.textView76)
        TextView textView76;
        @BindView(R.id.textView77)
        TextView textView77;
        @BindView(R.id.textView78)
        TextView textView78;
        @BindView(R.id.textView79)
        TextView textView79;
        @BindView(R.id.imageView18)
        ImageView imgDelete;
        @BindView(R.id.imageView17)
        ImageView imgEdit;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public PgPreLoanAdapter(Context context, List<PgmisLoantbl> itemList) {
        this.context = context;
        this.list = itemList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_pg_payment_activity, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PgmisLoantbl item = list.get(position);
        holder.textView76.setText(item.getItemname());
        holder.textView77.setText(item.getRate());
        holder.textView78.setText(item.getQuantity()+"("+item.getUnit()+")");
        double amount = Double.parseDouble(item.getQuantity()) * Double.parseDouble(item.getRate());
        holder.textView79.setText(amount+""+"("+item.getPaymentdate()+")");

           //condition to make edit and delete visible
        if(item.getIsexported().equals("0")){
            holder.imgEdit.setVisibility(View.GONE);
            holder.imgDelete.setVisibility(View.GONE);
        }else{
            //holder.imgEdit.setVisibility(View.VISIBLE);
            holder.imgDelete.setVisibility(View.GONE);
        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.irozon.alertview.AlertView alert = new com.irozon.alertview.AlertView("Delete", "Are you sure, you want to delete this record", AlertStyle.DIALOG);
                alert.addAction(new AlertAction("Delete", AlertActionStyle.DEFAULT, action -> {
                    //item.delete();
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, list.size());
                }));
                alert.show((Loan)context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
