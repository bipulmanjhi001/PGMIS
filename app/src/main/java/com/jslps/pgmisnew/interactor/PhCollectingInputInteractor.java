package com.jslps.pgmisnew.interactor;

import android.database.SQLException;
import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.Pgmemtbl;
import com.jslps.pgmisnew.database.PhCollectingInputtbl;
import com.orm.query.Condition;
import com.orm.query.Select;
import java.util.Collections;
import java.util.List;

public class PhCollectingInputInteractor  {

    public interface PhCollectingInputView {
        void getPgMemListnew(List<Pgmemtbl> list);
    }

    public interface PhcollectingInputInteractor {
        void dataSaved();
    }

    public void getPgMemListnew(final PhCollectingInputInteractor.PhCollectingInputView listener, String pgCode) {
        List<Pgmemtbl> list = Select.from(Pgmemtbl.class)
                .where(Condition.prop("Pgcode").eq(pgCode))
                .list();
        Collections.reverse(list);
        listener.getPgMemListnew(list);
    }

    public String[] getUserDetails(PhCollectingInputInteractor.PhcollectingInputInteractor listner) {
        List<Logintbl> users = Logintbl.listAll(Logintbl.class);
        String username = users.get(0).getUsername();
        String userid = users.get(0).getUserid();
        return new String[]{username, userid};
    }

    public void saveData(PhCollectingInputInteractor.PhcollectingInputInteractor listner) {
        try {
            PhCollectingInputtbl data = new PhCollectingInputtbl();
            data.save();
            listner.dataSaved();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

