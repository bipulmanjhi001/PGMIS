package com.jslps.pgmisnew.interactor;

import android.database.SQLException;

import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.PgBankwithdrawcashdeposit;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class PgBankWithdrawInteractor {

    public interface Pgbankwithdrawinteractor {
        void dataSaved();
    }

    public List<PgBankwithdrawcashdeposit> getPgBankwithdrawcashdeposit(PgBankWithdrawInteractor.Pgbankwithdrawinteractor listner, String PGCode) {

        return Select.from(PgBankwithdrawcashdeposit.class)
                .where(Condition.prop("PG_Code").eq(PGCode))
                .list();
    }
    public String[] getUserDetails(PgBankWithdrawInteractor.Pgbankwithdrawinteractor listner) {
        List<Logintbl> users = Logintbl.listAll(Logintbl.class);

        String username = users.get(0).getUsername();
        String userid = users.get(0).getUserid();

        return new String[]{username, userid};
    }

    public void saveData(PgBankWithdrawInteractor.Pgbankwithdrawinteractor listner,String amount,String uuid,String PGCode,String username,String userid,String isexported,String ID,String head_name,String date) {
        try {
            PgBankwithdrawcashdeposit data = new PgBankwithdrawcashdeposit(amount, uuid, PGCode, username, userid, isexported, ID, head_name, date);

            data.save();
            listner.dataSaved();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
