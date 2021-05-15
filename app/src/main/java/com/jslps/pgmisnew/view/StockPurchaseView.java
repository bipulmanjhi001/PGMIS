package com.jslps.pgmisnew.view;

import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;

public interface StockPurchaseView {

    void editRecord(Itempurchasedbypgtbl item);

    void setRecyclerView();

    void dataSaved();
}
