package com.jslps.pgmisnew.interactor;

import com.jslps.pgmisnew.database.Pgmemtbl;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.Collections;
import java.util.List;

public class MDAInteractornew {

    public interface mdaInteractornew {
        void getPgMemListnew(List<Pgmemtbl> list);
    }

    public void getPgMemListnew(final MDAInteractornew.mdaInteractornew listener, String pgCode) {
        List<Pgmemtbl> list = Select.from(Pgmemtbl.class)
                .where(Condition.prop("Pgcode").eq(pgCode))
                .list();
        Collections.reverse(list);
        listener.getPgMemListnew(list);
    }
}
