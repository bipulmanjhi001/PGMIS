package com.jslps.pgmisnew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jslps.pgmisnew.LoanRepaymentFormActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.PgmisBatchLoantbl;
import com.jslps.pgmisnew.database.PgmisLoanRepaymentModel;
import com.jslps.pgmisnew.database.Pgmisloanrepaymenttabl;
import com.orm.query.Condition;
import com.orm.query.Select;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sonu on 4/4/2018.
 */

public class Pgmisbatchloanadapter extends RecyclerView.Adapter<Pgmisbatchloanadapter.MyViewHolder> {

    private List<PgmisLoanRepaymentModel> list;
    private Context context;
    List<Pgmisloanrepaymenttabl> pgmisloanrepaymenttablList;
    double remainingamount;

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView105)
        TextView textView105;
        @BindView(R.id.textView106)
        TextView textView106;
        @BindView(R.id.textView107)
        TextView textView107;
        @BindView(R.id.textView108)
        TextView textView108;
        @BindView(R.id.textView109)
        TextView textView109;
        @BindView(R.id.textView110)
        TextView textView110;
        @BindView(R.id.textView111)
        TextView textView111;
        @BindView(R.id.outerlayout)
        ConstraintLayout outerlayout;
        @BindView(R.id.textdate)
        TextView textdate;
        @BindView(R.id.textView222)
         TextView textView222;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public Pgmisbatchloanadapter(Context context, List<PgmisLoanRepaymentModel> itemList) {
        this.context = context;
        this.list = itemList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_batch_loan_taken, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        PgmisLoanRepaymentModel item = list.get(position);
        holder.textView105.setText(position + 1 + ".");
        holder.textView110.setText(item.getEntrydate());
        holder.textView111.setText(item.getAmount() + "(Rs)");
        holder.textdate.setText(item.getPgcode());

        pgmisloanrepaymenttablList = Select.from(Pgmisloanrepaymenttabl.class)
                .where(Condition.prop("loanid").eq(item.getLoanid()))
                .list();

        double totalpaidamountasloan=0;
        if(pgmisloanrepaymenttablList.size()>0){
            for(int i=0;i<pgmisloanrepaymenttablList.size();i++){
                totalpaidamountasloan = totalpaidamountasloan + Double.parseDouble(pgmisloanrepaymenttablList.get(i).getAmount());
            }
        }
        remainingamount = Double.parseDouble(item.getAmount()) -totalpaidamountasloan;
        holder.textView222.setText("शेष राशि: "+remainingamount+"(Rs)");
        holder.outerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoanRepaymentFormActivity.class);
                intent.putExtra("loanid",item.getLoanid());
                intent.putExtra("loanamount",item.getAmount());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
