package com.jslps.pgmisnew.adapter;

import android.content.Context;
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
import com.jslps.pgmisnew.PgpaymentActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.presenter.PgBankWithdrawPresenter;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BankwithdrawcashAdapter extends RecyclerView.Adapter<BankwithdrawcashAdapter.MyViewHolder> {

    private List<PgBankwithdrawcashdeposit> list;
    private Context context;
    private PgBankWithdrawPresenter presenter;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView76)
        TextView textView76;
        @BindView(R.id.textView77)
        TextView textView77;
        @BindView(R.id.textView78)
        TextView textView78;
        @BindView(R.id.imageView18)
        ImageView imgDelete;
        @BindView(R.id.imageView17)
        ImageView imgEdit;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public BankwithdrawcashAdapter(Context context, List<PgBankwithdrawcashdeposit> itemList, PgBankWithdrawPresenter presenter) {
        this.context = context;
        this.list = itemList;
        this.presenter = presenter;
    }

    @NotNull
    @Override
    public BankwithdrawcashAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_pg_bankwithdrawcash, parent, false);

        return new BankwithdrawcashAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull BankwithdrawcashAdapter.MyViewHolder holder, int position) {
        PgBankwithdrawcashdeposit item = list.get(position);

        holder.textView76.setText(item.getDate());
        holder.textView77.setText(item.getHeadname());
        holder.textView78.setText(item.getAmount());

        //condition to make edit and delete visible
        if(item.getIsexported().equals("0")){
          //  holder.imgEdit.setVisibility(View.VISIBLE);
            holder.imgDelete.setVisibility(View.VISIBLE);
        }else{
          //  holder.imgEdit.setVisibility(View.GONE);
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
                presenter.editRecord(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void alert(String title, String message, PgBankwithdrawcashdeposit item){
        com.irozon.alertview.AlertView alert = new com.irozon.alertview.AlertView(title, message, AlertStyle.DIALOG);
        alert.addAction(new AlertAction("Delete", AlertActionStyle.DEFAULT, action -> {
            item.delete();
            presenter.setRecyclerView();
        }));
        alert.show((BankWithdrawCashDeposit)context);
    }
}
