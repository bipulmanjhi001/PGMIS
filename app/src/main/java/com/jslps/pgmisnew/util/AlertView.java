package com.jslps.pgmisnew.util;


import androidx.appcompat.app.AppCompatActivity;

import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.objects.AlertAction;

public class AlertView {

    public AlertView(AppCompatActivity activity, String error_success, String message, String actionMsg){
        com.irozon.alertview.AlertView alert = new com.irozon.alertview.AlertView(error_success, message, AlertStyle.DIALOG);
        alert.addAction(new AlertAction(actionMsg, AlertActionStyle.DEFAULT, action -> {
        }));
        alert.show(activity);
    }
}
