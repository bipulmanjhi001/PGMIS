package com.jslps.pgmisnew.interactor;

import android.database.SQLException;

import com.jslps.pgmisnew.PgActivity;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class StockPurchaseInterface {

    public interface Stockpurchaseinterface {
        void dataSaved();
    }

    public List<Itempurchasedbypgtbl> getStockPurchaseItem(StockPurchaseInterface.Stockpurchaseinterface listner, String PGCode) {

        return Select.from(Itempurchasedbypgtbl.class)
                .where(Condition.prop("pgcode").eq(PGCode))
                .list();
    }

    public String[] getUserDetails(StockPurchaseInterface.Stockpurchaseinterface listner) {
        List<Logintbl> users = Logintbl.listAll(Logintbl.class);

        String username = users.get(0).getUsername();
        String userid = users.get(0).getUserid();

        return new String[]{username, userid};
    }

    public void saveData(StockPurchaseInterface.Stockpurchaseinterface listner,String uuid, String itemcode, String itemname, String unit, String rate, String quantity, String budgetname, String budgetcode, String pgcode, String entrydate, String isexported, String paymentmode, String selecteddate, String BMID) {
        try {
            Itempurchasedbypgtbl data = new Itempurchasedbypgtbl(uuid, itemcode, itemname, unit, rate, quantity, budgetname, budgetcode, pgcode, entrydate, isexported, paymentmode, selecteddate, BMID);
            data.save();
            listner.dataSaved();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
