package com.jslps.pgmisnew.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;

import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jslps.pgmisnew.PgActivity;
import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.presenter.PgActivityPresenter;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageUploadService extends Service implements VolleyString.VolleyListner {

    int count =0;
//    PgActivityPresenter presenter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //image upload function
        imageupload();
    }

    private void imageupload() {
        if (PgActivity.pgmisbrsimgtbls.size()>0) {
           // count++;
            Bitmap bm = BitmapFactory.decodeFile(AppConstant.imagePath + "/" + PgActivity.pgmisbrsimgtbls.get(count).getFileName());
            System.out.println("");
            if (bm != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                File source = new File(AppConstant.imagePath + "/" + PgActivity.pgmisbrsimgtbls.get(count).getFileName());
                long length = source.length();
                length = length / 1024;
                if (length > 3000) {
                    bm.compress(Bitmap.CompressFormat.JPEG, 5, byteArrayOutputStream);
                } else if (length > 2000) {
                    bm.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
                } else if (length > 1000) {
                    bm.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
                } else if (length > 700) {
                    bm.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                } else {
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encodedbase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                RequestQueue mRequestQueue;
                StringRequest mStringRequest;
                mRequestQueue = Volley.newRequestQueue(this);
                mStringRequest = new VolleyString(AppConstant.domain + "/" + AppConstant.Upload_Image, AppConstant.UploadBRSImage, this).postPgmisBrsImage(encodedbase64, PgActivity.pgmisbrsimgtbls.get(count).getFileName());
                mRequestQueue.add(mStringRequest);
            }
        }
    }

    @Override
    public void onResponseSuccess(String tableIndentifier, String result) {
        if(tableIndentifier.equals(AppConstant.UploadBRSImage)){
            try {
                JSONObject jsonObject = new JSONObject(result);
                String data = jsonObject.optString("Data");
                if(data.equals("1")){
                    for(int i=0;i<PgActivity.pgmisbrsimgtbls.size();i++){
                        PgActivity.pgmisbrsimgtbls.get(i).setIsexported("1");
                        PgActivity.pgmisbrsimgtbls.get(i).save();
                    }
                    new StyleableToast
                            .Builder(this)
                            .text("Successfully Uploaded")
                            .iconStart(R.drawable.right)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();

                } if (!data.equals("1")){
                    new StyleableToast
                            .Builder(this)
                            .text("Upload failed for pgmisbrsimgtbls")
                            .iconStart(R.drawable.wrong_icon_white)
                            .textColor(Color.WHITE)
                            .backgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
          }
        }

    @Override
    public void onResponseFailure(String tableIdentifier) {
        new StyleableToast
                .Builder(this)
                .text("server error,Please check internet Connection")
                .iconStart(R.drawable.wrong_icon_white)
                .textColor(Color.WHITE)
                .backgroundColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
}
