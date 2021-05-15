package com.jslps.pgmisnew.view;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jslps.pgmisnew.database.PgMeetingtbl;
import com.jslps.pgmisnew.database.tblPgMeetingCaders;

import java.util.List;

public interface MeetingView {
    void setPgName();

    void setOpenCalender();

    void setAdapter(ImageView edit, ImageView delete, TextView date, TextView no, TextView cadre, ConstraintLayout layout1, ConstraintLayout layout2, int position);

    void dateNoEmpty();

    void saveData();

    void setRecyclerView();

    void meetingData(List<PgMeetingtbl> list);

    void meetingData2(List<tblPgMeetingCaders> list2);

}
