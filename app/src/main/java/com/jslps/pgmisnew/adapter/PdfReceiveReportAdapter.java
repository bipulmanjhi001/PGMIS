package com.jslps.pgmisnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jslps.pgmisnew.GeneratePdfReceiptReportActivity;
import com.jslps.pgmisnew.PgActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.Blocktbl;
import com.jslps.pgmisnew.database.Clustertbl;
import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.Pgtbl;
import com.jslps.pgmisnew.database.ReceiptReportModel;
import com.jslps.pgmisnew.database.Villagetbl;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PdfReceiveReportAdapter extends RecyclerView.Adapter<PdfReceiveReportAdapter.MyViewHolder> {
    private List<ReceiptReportModel> list;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView85)
        TextView textView85;
        @BindView(R.id.textView87)
        TextView textView87;
        @BindView(R.id.textView88)
        TextView textView88;
        @BindView(R.id.textView89)
        TextView textView89;
        @BindView(R.id.textView90)
        TextView textView90;
        @BindView(R.id.textView91)
        TextView textView91;
        @BindView(R.id.textView92)
        TextView textView92;
        @BindView(R.id.constraintLayout7)
        ConstraintLayout constraintLayout7;
//        @BindView(R.id.textView72)
//        TextView textView72;
//        @BindView(R.id.textView73)
//        TextView textView73;
//        @BindView(R.id.textView74)
//        TextView textView74;
//        @BindView(R.id.textView75)
//        TextView textView75;
//        @BindView(R.id.textView76)
//        TextView textView76;
//        @BindView(R.id.textView77)
//        TextView textView77;
//        @BindView(R.id.textView78)
//        TextView textView78;
//        @BindView(R.id.textView79)
//        TextView textView79;
//        @BindView(R.id.constraintLayout8)
//        ConstraintLayout constraintLayout8;
        @BindView(R.id.textView93)
        TextView textView93;
        @BindView(R.id.textView94)
        TextView textView94;
        @BindView(R.id.textView95)
        TextView textView95;
        @BindView(R.id.textView96)
        TextView textView96;
        @BindView(R.id.textView97)
        TextView textView97;
        @BindView(R.id.textView98)
        TextView textView98;
        @BindView(R.id.lastlayout)
        ConstraintLayout lastlayout;

        @BindView(R.id.dateText)
        TextView dateText;
        @BindView(R.id.headText)
        TextView headText;
        @BindView(R.id.receivedAntText)
        TextView receivedAntText;
        @BindView(R.id.paymentAmtText)
        TextView paymentAmtText;
        @BindView(R.id.paymentModeTxt)
        TextView paymentModeTxt;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public PdfReceiveReportAdapter(Context context, List<ReceiptReportModel> itemList) {
        this.context = context;
        this.list = itemList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pdf_template_receipt_report, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        ReceiptReportModel item = list.get(position);

        if(position==0){
            //================ print date =================
            holder.constraintLayout7.setVisibility(View.VISIBLE);
            if(GeneratePdfReceiptReportActivity.from.equals("Select Date")){
                holder.textView92.setText("Upto: "+GeneratePdfReceiptReportActivity.to);
            }else if(GeneratePdfReceiptReportActivity.to.equals("Select Date")){
                holder.textView92.setText("From: "+GeneratePdfReceiptReportActivity.from);
            }else{
                holder.textView92.setText("From: "+GeneratePdfReceiptReportActivity.from+" To: "+GeneratePdfReceiptReportActivity.to);
            }

            //================= print PG details ===========
            List<Pgtbl> list = Select.from(Pgtbl.class)
                    .where(Condition.prop("Pgcode").eq(PgActivity.pgCodeSelected))
                    .list();
            if(list.size()>0){

                List<Villagetbl> village = Select.from(Villagetbl.class)
                        .where(Condition.prop("Villagecode").eq(list.get(0).getVillagecode()))
                        .list();

                if(village.size()>0){
                    holder.textView90.setText("Village: "+village.get(0).getVillagename());
                    List<Clustertbl> cluster = Select.from(Clustertbl.class)
                            .where(Condition.prop("Clustercode").eq(village.get(0).getClustercode()))
                            .list();

                    if(cluster.size()>0){
                        holder.textView89.setText("Panchayat:"+cluster.get(0).getClustername());
                        List<Blocktbl> block = Select.from(Blocktbl.class)
                                .where(Condition.prop("Blockcode").eq(cluster.get(0).getBlockcode()))
                                .list();


                        if(block.size()>0){
                            holder.textView88.setText("Block:"+block.get(0).getBlockname());
                            String dis = block.get(0).getDistrictcode();
                            List<Logintbl> district = Select.from(Logintbl.class)
                                    .where(Condition.prop("Districtcode").eq(block.get(0).getDistrictcode()))
                                    .list();
                            if(district.size()>0){
                                holder.textView87.setText("District:"+district.get(0).getDistrictname());
                            }

                        }
                    }
                }
            }

        }else{
            holder.constraintLayout7.setVisibility(View.GONE);
        }

        if(position==list.size()-1){
            holder.lastlayout.setVisibility(View.VISIBLE);
        }else{
            holder.lastlayout.setVisibility(View.GONE);
        }

//        holder.textView76.setText(item.getHeadname());
//        holder.textView77.setText(item.getReceivedamount());
//        holder.textView78.setText(item.getPaymentamount());
//        holder.textView79.setText(item.getPaymentmode());

        holder.textView85.setText(PgActivity.pgNameSelected);
        holder.dateText.setText(item.getDate());
        holder.headText.setText(item.getHeadname());
        holder.receivedAntText.setText(item.getReceivedamount());
        holder.paymentAmtText.setText(item.getPaymentamount());
        holder.paymentModeTxt.setText(item.getPaymentmode());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
