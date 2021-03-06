package com.jslps.pgmisnew.interactor;

import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.PgPaymentHeadModel;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.TblMstPgPaymentReceipthead;
import com.jslps.pgmisnew.util.SeedDataPgPaymentHead;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.Collection;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PgReceiptInteractor {


    public interface PgreceiptInteractor {

        void getHeadList(List<TblMstPgPaymentReceipthead> list);
        void dataSaved();
        void dataEdited();
    }

    public void getHeadList(PgreceiptInteractor listner){
        List<TblMstPgPaymentReceipthead> list = Select.from(TblMstPgPaymentReceipthead.class)
                .whereOr(Condition.prop("showin").eq("R"))
                .where(Condition.prop("budgettype").eq("Other"))
                .list();
        TblMstPgPaymentReceipthead model = new TblMstPgPaymentReceipthead("0","0","0","","मद का नाम चुने","","");
        list.add(model);
        Collections.reverse(list);
        listner.getHeadList(list);
    }

    public String[] getUserDetails(PgreceiptInteractor listner) {
        List<Logintbl> users = Logintbl.listAll(Logintbl.class);

        String username = users.get(0).getUsername();
        String userid = users.get(0).getUserid();

        return new String[]{username,userid};
    }

    public List<PgReceiptTranstbl> getPgReceiptTransList(PgreceiptInteractor listner, String pgCode) {

        return Select.from(PgReceiptTranstbl.class)
                .where(Condition.prop("pgcode").eq(pgCode))
                .list();
    }

    public List<PgReceiptDisData> getPgReceiptDisList(PgreceiptInteractor listner, String pgCode) {

        return Select.from(PgReceiptDisData.class)
                .where(Condition.prop("pgcode").eq(pgCode))
                .list();
    }

    public void saveData(PgreceiptInteractor listner,String budget_code,
                         String head_name, String date,
                         String amount, String remark, String pgCode,
                         String username, String userid, String isexported,String qty,String unit_type,String paymentmode,String BMID) {
        PgReceiptTranstbl data = new PgReceiptTranstbl(UUID.randomUUID().toString(),budget_code,head_name,date,
                amount,remark,pgCode,username,userid,isexported,qty,unit_type,paymentmode,BMID);
        data.save();
        listner.dataSaved();
    }

    public void saveEditedData(PgreceiptInteractor listner,String budget_code, String head_name,
                               String date, String amount,
                               String remark, String pgCode, String username,
                               String userid, String isexported,String qty,String unit_type,String paymentmode,String BMID,PgReceiptTranstbl pgReceiptTranstbl) {

        pgReceiptTranstbl.setBudgetcode(budget_code);
        pgReceiptTranstbl.setHeadname(head_name);
        pgReceiptTranstbl.setDate(date);
        pgReceiptTranstbl.setAmount(amount);
        pgReceiptTranstbl.setRemark(remark);
        pgReceiptTranstbl.setQuantity(qty);
        pgReceiptTranstbl.setUnittype(unit_type);
        pgReceiptTranstbl.setPaymentmode(paymentmode);
        pgReceiptTranstbl.setBMID(BMID);
        pgReceiptTranstbl.save();
        listner.dataEdited();
    }

}
