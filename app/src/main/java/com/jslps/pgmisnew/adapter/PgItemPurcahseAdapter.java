package com.jslps.pgmisnew.adapter;

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
import com.jslps.pgmisnew.PgpaymentActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.StockPurchase;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.presenter.PgPaymentPresenter;
import com.jslps.pgmisnew.presenter.StockPurchasePresenter;
import com.jslps.pgmisnew.view.StockPurchaseView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sonu on 4/4/2018.
 */

public class PgItemPurcahseAdapter extends RecyclerView.Adapter<PgItemPurcahseAdapter.MyViewHolder> {

     List<Itempurchasedbypgtbl> list;
     Context context;
     StockPurchasePresenter presenter;

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
        ImageView imageView17;
        @BindView(R.id.imageView18)
        ImageView imgDelete;
        @BindView(R.id.imageView17)
        ImageView imgEdit;
        @BindView(R.id.Payment_type)
          TextView Payment_type;
        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public PgItemPurcahseAdapter(Context context, List<Itempurchasedbypgtbl> itemList, StockPurchasePresenter presenter) {
        this.context = context;
        this.list = itemList;
        this.presenter=presenter;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_pg_payment_activity, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        Itempurchasedbypgtbl item = list.get(position);

        holder.imgDelete.setVisibility(View.GONE);
        holder.imgEdit.setVisibility(View.GONE);

        holder.textView76.setText(item.getBudgetname());
        holder.textView77.setText(item.getItemname());
        holder.textView78.setText(item.getQuantity()+"("+item.getUnit()+")");
        if(!item.getQuantity().equals("0")) {
            double amount = Double.parseDouble(item.getQuantity()) * Double.parseDouble(item.getRate());
            holder.textView79.setText(amount+""+"("+item.getPaymentmode()+")");
        }else {
            double amount = Double.parseDouble(item.getRate());
            holder.textView79.setText(amount + "" + "(" + item.getPaymentmode() + ")");
        }

        holder.Payment_type.setText(item.getPaymentmode());
        //condition to make edit and delete visible
        if(item.getIsexported().equals("0")){
            holder.imgEdit.setVisibility(View.GONE);
            holder.imgDelete.setVisibility(View.VISIBLE);
        }else{
            holder.imgEdit.setVisibility(View.GONE);
            holder.imgDelete.setVisibility(View.GONE);
        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                alert("Delete","Are you sure, you want to delete this record",item);
           }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   presenter.editRecord(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void alert(String title,String message,Itempurchasedbypgtbl item){
        com.irozon.alertview.AlertView alert = new com.irozon.alertview.AlertView(title, message, AlertStyle.DIALOG);
        alert.addAction(new AlertAction("Delete", AlertActionStyle.DEFAULT, action -> {
            item.delete();
             presenter.setRecyclerView();
        }));
        alert.show((StockPurchase)context);
    }

}
