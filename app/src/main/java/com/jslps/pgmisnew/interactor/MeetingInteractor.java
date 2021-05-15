package com.jslps.pgmisnew.interactor;

import android.widget.CheckBox;
import android.widget.TextView;

import com.jslps.pgmisnew.database.PgMeetingtbl;
import com.jslps.pgmisnew.database.Pgtbl;
import com.jslps.pgmisnew.database.ShgModel;
import com.jslps.pgmisnew.database.Shgmemnonpg;
import com.jslps.pgmisnew.database.Shgtbl;
import com.jslps.pgmisnew.database.tblPgMeetingCaders;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingInteractor {

    public interface meetingInteractor {
        void success();
        void dateNo();
        void getMeetingData(List<PgMeetingtbl> list);
        void getMeetingData2(List<tblPgMeetingCaders> list2);
    }

    public void Validation(String date,String no,boolean AKM,boolean APS,
                           boolean AMM,boolean MBK,boolean AW,boolean acheckBox1,
                           boolean acheckBox2,boolean acheckBox3, boolean acheckBox4,
                           boolean acheckBox5,boolean acheckBox6,boolean acheckBox7,
                           boolean acheckBox8,boolean acheckBox9,boolean acheckBox10,final  meetingInteractor listner){

        if(date.equals("")||no.equals("")){
            listner.dateNo();
        }else{
            listner.success();
        }
    }

    public void getMeetingData(String pgcode, final  meetingInteractor listner){
        List<PgMeetingtbl> list = Select.from(PgMeetingtbl.class)
                .where(Condition.prop("Pgcode").eq(pgcode))
                .list();
           //Reverse Meeting data
            Collections.reverse(list);
            listner.getMeetingData(list);
    }

    public void getMeetingData2(String pgcode, final  meetingInteractor listner){
        List<tblPgMeetingCaders> list2 = Select.from(tblPgMeetingCaders.class)
                .where(Condition.prop("Pgcode").eq(pgcode))
                .list();
        //Reverse tblPgMeetingCaders data
        Collections.reverse(list2);
        listner.getMeetingData2(list2);
    }
}
