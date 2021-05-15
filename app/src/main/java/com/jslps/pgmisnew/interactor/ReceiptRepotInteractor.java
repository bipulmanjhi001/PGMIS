package com.jslps.pgmisnew.interactor;

import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PgCapitalSavetbl;
import com.jslps.pgmisnew.database.PgMemShipFeeSavetbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.PgmisLoantbl;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class ReceiptRepotInteractor {

    public interface paymentReceiptReport{

    }

    public List<PgReceiptDisData> getPgReceiptList(paymentReceiptReport listner, String pgCode) {

        return Select.from(PgReceiptDisData.class)
                .where(Condition.prop("pgcode").eq(pgCode))
                .list();
    }

    //===================get payment details (PgPaymentTranstbl) ======
    public List<PgPaymentTranstbl> getPgPaymentTransList(String fromDate,String toDate,String pgcode){

        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgPaymentTranstbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("date").gt(fromDate)).or(Condition.prop("date").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgPaymentTranstbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("date").lt(toDate)).or(Condition.prop("date").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgPaymentTranstbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("date").gt(fromDate),Condition.prop("date").lt(toDate)).or(Condition.prop("date").eq(fromDate)).or(Condition.prop("date").eq(toDate))
                    .list();
        }

    }

    //================== get data from PgReceiptTranList ============
    public List<PgReceiptTranstbl> getPgReceiptTranList(String fromDate, String toDate, String pgcode){

        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgReceiptTranstbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("date").gt(fromDate)).or(Condition.prop("date").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgReceiptTranstbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("date").lt(toDate)).or(Condition.prop("date").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgReceiptTranstbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("date").gt(fromDate),Condition.prop("date").lt(toDate)).or(Condition.prop("date").eq(fromDate)).or(Condition.prop("date").eq(toDate))
                    .list();
        }

    }

    //================== get data from Itempurchasedbypgtbl ============
    public List<Itempurchasedbypgtbl> getItempurchasedbypgList(String fromDate, String toDate, String pgcode){

        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(Itempurchasedbypgtbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("entrydate").gt(fromDate)).or(Condition.prop("entrydate").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(Itempurchasedbypgtbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("entrydate").lt(toDate)).or(Condition.prop("entrydate").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(Itempurchasedbypgtbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("entrydate").gt(fromDate),Condition.prop("entrydate").lt(toDate)).or(Condition.prop("entrydate").eq(fromDate)).or(Condition.prop("entrydate").eq(toDate))
                    .list();
        }

    }

    //================== get data from Pgmemtbl ===========
    public List<PgmisLoantbl> getPgmisLoanList(String fromDate, String toDate, String pgcode){

        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgmisLoantbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").gt(fromDate)).or(Condition.prop("payment_date").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgmisLoantbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").lt(toDate)).or(Condition.prop("payment_date").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgmisLoantbl.class)
                    .where(Condition.prop("pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").gt(fromDate),Condition.prop("payment_date").lt(toDate)).or(Condition.prop("payment_date").eq(fromDate)).or(Condition.prop("payment_date").eq(toDate))
                    .list();
        }

    }

    //================== get data from PgMemShipFeeSavetbl ===========
    public List<PgMemShipFeeSavetbl> getPgMemShipFeeSavetblList(String fromDate, String toDate, String pgcode){

        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgMemShipFeeSavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").gt(fromDate)).or(Condition.prop("payment_date").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgMemShipFeeSavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").lt(toDate)).or(Condition.prop("payment_date").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgMemShipFeeSavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").gt(fromDate),Condition.prop("payment_date").lt(toDate)).or(Condition.prop("payment_date").eq(fromDate)).or(Condition.prop("payment_date").eq(toDate))
                    .list();
        }

    }

    //================== get data from PgCapitalSavetbl ===========
    public List<PgCapitalSavetbl> getPgCapitalSavetblList(String fromDate, String toDate, String pgcode){

        if(!fromDate.equals("Select Date") && toDate.equals("Select Date")){
            //only from date present
            return Select.from(PgCapitalSavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").gt(fromDate)).or(Condition.prop("payment_date").eq(fromDate))
                    .list();
        }else if(fromDate.equals("Select Date") && !toDate.equals("Select Date")){
            //only to date present
            return Select.from(PgCapitalSavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").lt(toDate)).or(Condition.prop("payment_date").eq(toDate))
                    .list();
        }else{
            //both date prsent
            return Select.from(PgCapitalSavetbl.class)
                    .where(Condition.prop("Pgcode").eq(pgcode))
                    .where(Condition.prop("payment_date").gt(fromDate),Condition.prop("payment_date").lt(toDate)).or(Condition.prop("payment_date").eq(fromDate)).or(Condition.prop("payment_date").eq(toDate))
                    .list();
        }

    }




}
