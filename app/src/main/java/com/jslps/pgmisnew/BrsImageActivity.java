package com.jslps.pgmisnew;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jslps.pgmisnew.adapter.BrsReportAdapter;
import com.jslps.pgmisnew.database.BrsReportModel;
import com.jslps.pgmisnew.database.Itempurchasedbypgtbl;
import com.jslps.pgmisnew.database.PgCapitalSavetbl;
import com.jslps.pgmisnew.database.PgMemShipFeeSavetbl;
import com.jslps.pgmisnew.database.PgPaymentTranstbl;
import com.jslps.pgmisnew.database.PgReceiptDisData;
import com.jslps.pgmisnew.database.PgReceiptTranstbl;
import com.jslps.pgmisnew.database.Pgmisbrstranstbl;
import com.jslps.pgmisnew.database.ReceiptAmountSumModel;
import com.jslps.pgmisnew.interactor.BrsRepotInteractor;
import com.jslps.pgmisnew.presenter.BrsReportPresenter;
import com.jslps.pgmisnew.util.AppConstant;
import com.jslps.pgmisnew.view.BrsReport;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrsImageActivity extends AppCompatActivity{
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @BindView(R.id.closeBtn)
    ImageView closeBtn;
    @BindView(R.id.scanImage)
    ImageView scanImage;
    String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brs_image_full_screen);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        init();
    }
    private void init() {
        Intent intent = getIntent();
        imageName = intent.getStringExtra("imageName");
        String ImagePath = AppConstant.imagePath+"/"+imageName;
        Bitmap bitmap = BitmapFactory.decodeFile(ImagePath);
        scanImage.setImageBitmap(bitmap);
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

//    closeBtn.
        @OnClick({R.id.closeBtn})
        public void onViewClicked(View view) {
            finish();
        }





}
