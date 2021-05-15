package com.jslps.pgmisnew.presenter;

import android.content.Context;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.interactor.StockPurchaseInterface;
import com.jslps.pgmisnew.view.StockPurchaseView;
import java.util.List;

public class StockPurchasePresenter implements StockPurchaseInterface.Stockpurchaseinterface {

    private StockPurchaseInterface stockPurchaseInterface;
    private StockPurchaseView stockPurchaseView;
    private Context context;

    public StockPurchasePresenter (Context context) {
        this.context=context;
    }

    public StockPurchasePresenter(StockPurchaseInterface stockPurchaseInterface, StockPurchaseView stockPurchaseView) {
        this.stockPurchaseInterface = stockPurchaseInterface;
        this.stockPurchaseView = stockPurchaseView;
    }

    public String[] getUserDetails() {
        return stockPurchaseInterface.getUserDetails(this);
    }

    public List<Itempurchasedbypgtbl> getStockPurchaseItem(String pgCodeSelected) {
        return  stockPurchaseInterface.getStockPurchaseItem(this,pgCodeSelected);
    }

    public void saveData(String uuid, String itemcode, String itemname, String unit, String rate, String quantity, String budgetname, String budgetcode, String pgcode, String entrydate, String isexported, String paymentmode, String selecteddate, String BMID) {

        stockPurchaseInterface.saveData(this, uuid, itemcode, itemname, unit, rate, quantity, budgetname, budgetcode, pgcode, entrydate, isexported, paymentmode, selecteddate, BMID);
    }

    public void editRecord(Itempurchasedbypgtbl item) {
        stockPurchaseView.editRecord(item);
    }
    public void setRecyclerView() {
        stockPurchaseView.setRecyclerView();
    }

    @Override
    public void dataSaved() {
      stockPurchaseView.dataSaved();
    }
}
