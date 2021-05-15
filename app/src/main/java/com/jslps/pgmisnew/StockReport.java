package com.jslps.pgmisnew;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.interactor.StockReportModel;
import com.orm.query.Condition;
import com.orm.query.Select;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StockReport extends AppCompatActivity {

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.listview_list)
    ListView loadstock;
    String pgname;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList;
    List<Itempurchasedbypgtbl> itempurchasedbypgtblList_new;
    List<String> itemcodeString;
    ViewStockAdapter adapter;
    ArrayList<StockReportModel> stockReportModels;
    double new_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_report);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        pgname = PgActivity.pgNameSelected;
        textView23.setText(pgname);
        loadstock = (ListView) findViewById(R.id.listview_list);
        loadstock.setDivider(null);
        stockReportModels=new ArrayList<>();
        itempurchasedbypgtblList = Select.from(Itempurchasedbypgtbl.class)
                .where(Condition.prop("pgcode").eq(PgActivity.pgCodeSelected))
                .list();

        itemcodeString = new ArrayList<>();
        itempurchasedbypgtblList_new = new ArrayList<>();

        //adding quantity of same item
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemcodeString.clear();
                itempurchasedbypgtblList_new.clear();
                stockReportModels.clear();
                ObjectAnimator.ofFloat(floatingActionButton, "rotation", 0f, 360f).setDuration(800).start();
                ViewStock();
            }
        });
        ViewStock();
    }

    private void ViewStock(){
        for (int i = 0; i < itempurchasedbypgtblList.size(); i++) {
            String itemcode = itempurchasedbypgtblList.get(i).getItemcode();
            if (!itemcodeString.contains(itemcode)) {
                Itempurchasedbypgtbl itempurchasedbypgtbl = new Itempurchasedbypgtbl();
                itempurchasedbypgtbl.setItemname(itempurchasedbypgtblList.get(i).getItemname());
                itempurchasedbypgtbl.setItemcode(itempurchasedbypgtblList.get(i).getItemcode());
                itempurchasedbypgtbl.setPgcode(itempurchasedbypgtblList.get(i).getPgcode());
                itempurchasedbypgtbl.setQuantity(itempurchasedbypgtblList.get(i).getQuantity());
                itemcodeString.add(itempurchasedbypgtblList.get(i).getItemcode());
                itempurchasedbypgtblList_new.add(itempurchasedbypgtbl);

            } else {
                for (int j = 0; j < itempurchasedbypgtblList_new.size(); j++) {
                    String itemcode_new = itempurchasedbypgtblList_new.get(j).getItemcode();
                    if (itemcode_new.equals(itemcode)) {
                        String quantity_new = itempurchasedbypgtblList_new.get(j).getQuantity();
                        String quantity = itempurchasedbypgtblList.get(i).getQuantity();
                        String unit = itempurchasedbypgtblList.get(i).getUnit();
                        if (unit.equals("Kg")) {
                            quantity = Double.parseDouble(quantity) + "";
                        }
                         new_quantity = Double.parseDouble(quantity_new) + Double.parseDouble(quantity);

                        Itempurchasedbypgtbl model_new = new Itempurchasedbypgtbl();
                        model_new.setItemname(itempurchasedbypgtblList.get(i).getItemname());
                        model_new.setItemcode(itempurchasedbypgtblList.get(i).getItemcode());
                        model_new.setQuantity(new_quantity + "");
                        model_new.setUnit("gram");
                        itempurchasedbypgtblList_new.set(j, model_new);
                        //changing j to size of new list to stop for loop second
                        j = itempurchasedbypgtblList_new.size();
                    }
                }
            }
        }
        for (int j = 0; j < itempurchasedbypgtblList_new.size(); j++) {
            StockReportModel stockReportModel = new StockReportModel();
            stockReportModel.setItemcode(itempurchasedbypgtblList_new.get(j).getItemcode());
            stockReportModel.setItemname(itempurchasedbypgtblList_new.get(j).getItemname());
            stockReportModel.setQuantity(itempurchasedbypgtblList_new.get(j).getQuantity());
            stockReportModels.add(stockReportModel);
        }
        try {
            adapter = new ViewStockAdapter(stockReportModels, getApplicationContext());
            loadstock.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public class ViewStockAdapter extends BaseAdapter {
        private Context mContext;
        List<StockReportModel> mylist= new ArrayList<>();
        public ViewStockAdapter(List<StockReportModel> itemArray, Context mContext) {
            super();
            this.mContext = mContext;
            mylist = itemArray;
        }
        @Override
        public int getCount() {
            return mylist.size();
        }
        @Override
        public String getItem(int position) {
            return mylist.get(position).toString();
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder {
            private TextView id,name,designation;
            private TextView count,mobile;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ViewHolder view;
            LayoutInflater inflator = null;
            if (convertView == null) {
                view = new ViewHolder();
                try {
                    inflator = (StockReport.this).getLayoutInflater();
                    convertView = inflator.inflate(R.layout.view_stock_list, null);

                    view.id = (TextView) convertView.findViewById(R.id.stock_ids);
                    view.name = (TextView) convertView.findViewById(R.id.stock_name);
                    view.count=(TextView)convertView.findViewById(R.id.stock_count);

                    view.designation = (TextView) convertView.findViewById(R.id.stock_designation);
                    view.mobile=(TextView)convertView.findViewById(R.id.stock_mobile);

                    convertView.setTag(view);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                view = (ViewHolder) convertView.getTag();
            }
            try {
                view.id.setTag(position);
                view.id.setText("IteamCode : "+mylist.get(position).getItemcode());
                view.name.setText("Name : "+mylist.get(position).getItemname());
                view.count.setText("Quantity : "+mylist.get(position).getQuantity());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }
}