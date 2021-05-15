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
import com.jslps.pgmisnew.ChequeLoanActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.presenter.ChequeLoanPresenter;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChequeLoanAdapter extends RecyclerView.Adapter<ChequeLoanAdapter.MyViewHolder> {

    private List<PgmisChequeLoantbl> list;
    private Context context;
    private ChequeLoanPresenter presenter;

    class MyViewHolder extends RecyclerView.ViewHolder {

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

    public ChequeLoanAdapter(Context context, List<PgmisChequeLoantbl> itemList, ChequeLoanPresenter presenter) {
        this.context = context;
        this.list = itemList;
        this.presenter = presenter;
    }

    @NotNull
    @Override
    public ChequeLoanAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_chequeloan, parent, false);

        return new ChequeLoanAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull ChequeLoanAdapter.MyViewHolder holder, int position) {
        PgmisChequeLoantbl item = list.get(position);
        holder.textView76.setText(item.getChequedate());
        holder.textView77.setText(item.getAmount());
        holder.textView78.setText(item.getPaymentmode());
        holder.textView79.setText(item.getRemark());

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void alert(String title, String message, PgmisChequeLoantbl item){
        com.irozon.alertview.AlertView alert = new com.irozon.alertview.AlertView(title, message, AlertStyle.DIALOG);
        alert.addAction(new AlertAction("Delete", AlertActionStyle.DEFAULT, action -> {
            item.delete();
            presenter.setRecyclerView();
        }));
        alert.show((ChequeLoanActivity)context);
    }
}

