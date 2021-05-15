package com.jslps.pgmisnew;

import android.content.pm.ActivityInfo;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class BatchLoanVIew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_loan_v_iew);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

    }
}
