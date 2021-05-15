package com.jslps.monitor.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jslps.monitor.DPRListActivity;
import com.jslps.monitor.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageUploadService extends Service implements VolleyString.VolleyListner {
    int count =0;

    Notification.Builder notificationBuilder;
    Notification notification;
    NotificationManager notificationManager;
    Integer notificationID;

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
        notificationID = DPRListActivity.imagetblList.size();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

       //Set notification information:
        ; notificationBuilder = new Notification.Builder(getApplicationContext());
         notificationBuilder.setOngoing(true)
                .setContentTitle("Image Uploading: "+"(0/"+notificationID+")")
                .setContentText("Dpr Monitor")
                .setSmallIcon(R.drawable.irriagation_icon)
                .setProgress(notificationID, 0, false);



       //Send the notification:
        notification = notificationBuilder.build();
        notificationManager.notify(notificationID, notification);

        //image upload function
        imageupload();
    }

    private void imageupload() {
        Bitmap bm = BitmapFactory.decodeFile(AppConstant.imagePath+"/"+DPRListActivity.imagetblList.get(count).getImagename());
        System.out.println("");
        if(bm!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            File source = new File(AppConstant.imagePath+"/"+DPRListActivity.imagetblList.get(count).getImagename());
            long length = source.length();
            length = length / 1024;
            if(length>3000){
                bm.compress(Bitmap.CompressFormat.JPEG, 5, byteArrayOutputStream);
            }else if(length>2000){
                bm.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
            }else if(length>1000){
                bm.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
            }else if(length>700){
                bm.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            }else{
                bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            }

            byte[] byteArray = byteArrayOutputStream .toByteArray();
            String encodedbase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            RequestQueue mRequestQueue;
            StringRequest mStringRequest;
            mRequestQueue = Volley.newRequestQueue(this);
            mStringRequest = new VolleyString(AppConstant.domain+"/"+AppConstant.DPRforDIC_Image, "", this).postString(encodedbase64,DPRListActivity.imagetblList.get(count).getImagename(),DPRListActivity.imagetblList.get(count).getDprno());
            mRequestQueue.add(mStringRequest);
        }
    }

    @Override
    public void onResponseSuccess(String tableIndentifier, String result) {
        if(result.equals("OK")){
            DPRListActivity.imagetblList.get(count).setIsexported("1");
            DPRListActivity.imagetblList.get(count).save();
            count++;
            if(count<DPRListActivity.imagetblList.size()){
                notifyNotification("progress");
                imageupload();
            }else{
                AppConstant.imageuploading=0;
                notifyNotification("complete");
                Toast.makeText(this,"All images Succesfully Uploaded",Toast.LENGTH_LONG).show();
                stopSelf();
            }
        }else{
            count++;
            if(count<DPRListActivity.imagetblList.size()){
                notifyNotification("progress");
                imageupload();
            }else{
                AppConstant.imageuploading=0;
                notifyNotification("complete");
                Toast.makeText(this,"All images Succesfully Uploaded",Toast.LENGTH_LONG).show();
                stopSelf();
            }
//            AppConstant.imageuploading=0;
//            notifyNotification("error");
//            Toast.makeText(this,"Something went wrong ,Try Again later",Toast.LENGTH_LONG).show();
//            stopSelf();
        }
    }

    private void notifyNotification(String message) {
        //Update notification information:
        if(message.equals("progress")){
            notificationBuilder.setProgress(notificationID, count, false);
            notificationBuilder.setContentTitle("Image Uploading: "+"("+count+"/"+notificationID+")");

        }else if(message.equals("complete")){
            notificationBuilder.setProgress(notificationID, count, false);
            notificationBuilder.setContentTitle("Image Upload Completed: "+"("+count+"/"+notificationID+")");
            notificationBuilder.setOngoing(false);
        }else{
            notificationBuilder.setProgress(notificationID, count, false);
            notificationBuilder.setContentTitle("Image Upload Failed Try Again Later ");
            notificationBuilder.setOngoing(false);
        }

        //Send the notification:
        notification = notificationBuilder.build();
        notificationManager.notify(notificationID, notification);
    }

    @Override
    public void onResponseFailure(String tableIdentifier) {
        AppConstant.imageuploading=0;
        notifyNotification("error");
        Toast.makeText(this,"Image Upload Failed due to Server Error, Try again later",Toast.LENGTH_LONG).show();
        stopSelf();
    }
}
