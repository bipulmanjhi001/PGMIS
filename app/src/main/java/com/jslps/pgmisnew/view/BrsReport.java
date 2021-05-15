package com.jslps.pgmisnew.view;

import com.jslps.pgmisnew.database.BrsReportModel;

import java.util.List;

public interface BrsReport {
    void setPgName();

    void openCalender(String from);

    void selectAtLeastOneCalender();

    void getReport();

    void clearDates();

    void showImage(String imageName);


}
