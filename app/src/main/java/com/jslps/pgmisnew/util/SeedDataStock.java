package com.jslps.pgmisnew.util;

import com.jslps.pgmisnew.database.PgPaymentHeadModel;
import com.jslps.pgmisnew.database.Pgmisstockmsttbl;
import java.util.ArrayList;
import java.util.List;

public class SeedDataStock {
    private static final String[] item_code = new String[] {
    };

    private static final String[] item_name = new String[] {
    };

    private static final String[] item_unit = new String[] {
    };
   /* public static List<Pgmisstockmsttbl> getListData(){
        List<Pgmisstockmsttbl> list = new ArrayList<>();
        for (int i = 0; i < item_code.length; i++)
        {
            Pgmisstockmsttbl model = new Pgmisstockmsttbl(item_code[i],item_name[i],item_unit[i]);
            // Binds all strings into an array
            list.add(model);
        }
        return list;
    }*/
}
