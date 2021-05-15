package com.jslps.pgmisnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.database.PhCollectingInputnewtbl;
import com.jslps.pgmisnew.presenter.PhCollectingInputPresenter;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PhCollectingInputAdapter extends RecyclerView.Adapter<PhCollectingInputAdapter.MyViewHolder> {

    private List<Pgmemtbl> list;
    private List<Pgmemtbl> newItemList;
    private PhCollectingInputPresenter presenter;
    public String click_result="iteam_loan";
    private ArrayList<PhCollectingInputnewtbl> myItems = new ArrayList<>();
    Context context;

    class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView farmername,fatherhusbandshg,shg,fathername,husbandname,
                designation,primaryactivity,fishery,hva,livestock,ntfp,
                memfee,sharecapital;

        View viewlayout;
        ConstraintLayout constraintLayout,constraintLayout1;
        ImageView dropDown,edit,delete;
        Spinner select_input_type;
        CheckBox textView7;

        MyViewHolder(View view) {
            super(view);
            textView7 = view.findViewById(R.id.textView7);
            select_input_type=view.findViewById(R.id.textView9);
            fatherhusbandshg = view.findViewById(R.id.textView12);
            shg = view.findViewById(R.id.textView15);
            fathername = view.findViewById(R.id.textView18);
            husbandname = view.findViewById(R.id.textView21);
            designation = view.findViewById(R.id.textView27);
            primaryactivity = view.findViewById(R.id.textView30);
            fishery = view.findViewById(R.id.textView33);
            hva = view.findViewById(R.id.textView36);
            livestock = view.findViewById(R.id.textView39);
            ntfp = view.findViewById(R.id.textView42);
            memfee = view.findViewById(R.id.textView45);
            sharecapital = view.findViewById(R.id.textView48);
            constraintLayout = view.findViewById(R.id.secondLayout);
            constraintLayout1 = view.findViewById(R.id.firstLayout);
            viewlayout = view.findViewById(R.id.view);
            dropDown = view.findViewById(R.id.imageView7);
            edit = view.findViewById(R.id.imageView10);
            delete = view.findViewById(R.id.imageView6);
        }
    }

    public PhCollectingInputAdapter(PhCollectingInputPresenter presenter, List<Pgmemtbl> itemList) {
        this.presenter = presenter;
        this.list = itemList;
        this.newItemList = new ArrayList<>();
        this.newItemList.addAll(list);
    }

    @NotNull
    @Override
    public PhCollectingInputAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_phcollectinginput, parent, false);

        return new PhCollectingInputAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull PhCollectingInputAdapter.MyViewHolder holder, int position) {

        /*  final Pgmemtbl objIncome = list.get(position);
        //if true, your checkbox will be selected, else unselected
        holder.textView7.setChecked(objIncome.);
        holder.select_input_type.setClickable(objIncome.isSelected());*/

        presenter.setViewAdapternew(
                holder.constraintLayout1,
                holder.edit,
                holder.delete,
                holder.dropDown,
                holder.textView7,
                holder.fatherhusbandshg,
                holder.shg,
                holder.fathername,
                holder.husbandname,
                holder.designation,
                holder.primaryactivity,
                holder.fishery,
                holder.hva,
                holder.livestock,
                holder.ntfp,
                holder.viewlayout,
                holder.constraintLayout,
                holder.select_input_type,
                position);

        holder.select_input_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) {
                    presenter.spinerclicklisten(position,"FPC");
                }else if(position == 2) {
                    presenter.spinerclicklisten(position,"Other");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*holder.textView7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                *//*objIncome.setSelected(isChecked);*//*
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(newItemList);
        } else {
            for (Pgmemtbl dd : newItemList) {
                if(dd.getMembername()!=null && dd.getGrpname()!=null){
                    if (dd.getMembername().toLowerCase(Locale.getDefault())
                            .contains(charText)||dd.getGrpname().toLowerCase(Locale.getDefault())
                            .contains(charText)) {
                        list.add(dd);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}

