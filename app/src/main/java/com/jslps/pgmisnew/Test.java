package com.jslps.pgmisnew;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
