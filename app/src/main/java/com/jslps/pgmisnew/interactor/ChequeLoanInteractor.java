package com.jslps.pgmisnew.interactor;

import android.database.SQLException;
import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.Collections;
import java.util.List;

public class ChequeLoanInteractor {

    public interface Chequeloaninteractor {
        void getHeadList(List<TblMstPgPaymentReceipthead> list);
        void dataSaved();
    }

    public List<PgmisChequeLoantbl> getPgmischequeloan(ChequeLoanInteractor.Chequeloaninteractor listner, String PGCode) {
        return Select.from(PgmisChequeLoantbl.class)
                .where(Condition.prop("pgcode").eq(PGCode))
                .list();
    }

    public void getHeadList(ChequeLoanInteractor.Chequeloaninteractor listner){
        List<TblMstPgPaymentReceipthead> list = Select.from(TblMstPgPaymentReceipthead.class)
                .whereOr(Condition.prop("showin").eq("P"))
                .whereOr(Condition.prop("budgettype").eq("Loan"))
                .list();

        TblMstPgPaymentReceipthead model = new TblMstPgPaymentReceipthead("0","0","0","","मद का नाम चुने","","");
        list.add(model);
        Collections.reverse(list);
        listner.getHeadList(list);
    }

    public String[] getUserDetails(ChequeLoanInteractor.Chequeloaninteractor listner) {
        List<Logintbl> users = Logintbl.listAll(Logintbl.class);
        String username = users.get(0).getUsername();
        String userid = users.get(0).getUserid();

        return new String[]{username, userid};
    }

    public void saveData(ChequeLoanInteractor.Chequeloaninteractor listner,String uuid,String pgcode,String grpcode,String grpmemcode,String isexported,String entrydate,String entryby,String appliedforloan,String chequedate,String amount,String remark,String paymentmode) {
        try {
            PgmisChequeLoantbl data = new PgmisChequeLoantbl(uuid, pgcode, grpcode, grpmemcode, isexported, entrydate, entryby, appliedforloan, chequedate, amount, remark, paymentmode);
            data.save();
            listner.dataSaved();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
