package com.grace.permission;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by hongyang on 17-4-25.
 */

public  class Grace {


    public interface   State{

        /**
         * camera state
         */
       int  CAMERA  =0x01;

        /**
         * file  state
         */
       int  STORAGE  =0x02;

        /**
         * gps  state
         */
       int  LOCATION   =0x03;

        /**
         * SMS  state
         */
       int  SMS =0x04;

        /**
         * PHONE  state
         */
       int  PHONE =0x05;

        /**
         * MICROPHONE  state
         */
       int  MICROPHONE =0x06;

        /**
         * CONTACTS  state
         */
       int  CONTACTS =0x07;

        /**
         * CALENDAR  state
         */
       int  CALENDAR =0x08;

        /**
         * CAMERA_STORAGE  state
         */

       int  CAMERA_STORAGE=0x09;

        /**
         * STORAGE_LOCATION  state
         */
       int  STORAGE_LOCATION  =0x10;

        /**
         * PHONE_SMS state
         */
        int PHONE_SMS=0x11;

        /**
         * MICROPHONE_CAMERA  state
         */
        int MICROPHONE_CAMERA=0x12;

        /**
         * MICROPHONE_CAMERA_STORAGE  state
         */
        int MICROPHONE_CAMERA_STORAGE=0x13;
    }

     public interface   Permissions{

        /**
         * Used for permissions that are associated with accessing camera or capturing images/video from the device.
         * Constant Value: "android.permission-group.CAMERA"
         */
        String CAMERA = Manifest.permission.CAMERA;

        /**
         * Used for runtime permissions related to the shared external storage.
         * Constant Value: "android.permission-group.STORAGE"
         */
        String STORAGE  = Manifest.permission.WRITE_EXTERNAL_STORAGE;

         /**
          * Used for runtime permissions related to user's SMS messages.
          * Constant Value: "android.permission-group.SMS"
          */
        String SMS   = Manifest.permission.SEND_SMS;

         /**
          *Used for permissions that are associated telephony features.
           Constant Value: "android.permission-group.PHONE"
          */
        String PHONE   = Manifest.permission.CALL_PHONE;

         /**
          * Used for permissions that are associated with accessing microphone audio from the device.
          * Note that phone calls also capture audio but are in a separate (more visible) permission group.
          *Constant Value: "android.permission-group.MICROPHONE"
          */
        String MICROPHONE   = Manifest.permission.RECORD_AUDIO;

         /**
          * Used for permissions that allow accessing the device location.
          * Constant Value: "android.permission-group.LOCATION"
          */
        String LOCATION   = Manifest.permission.ACCESS_FINE_LOCATION;

         /**
          * Used for runtime permissions related to contacts and profiles on this device.
          * Constant Value: "android.permission-group.CONTACTS"
          */
        String CONTACTS   = Manifest.permission.READ_CONTACTS;

         /**
          *Used for runtime permissions related to user's calendar.
          *Constant Value: "android.permission-group.CALENDAR"
          */
        String CALENDAR   = Manifest.permission.READ_CALENDAR;

         /**
          * LOCATION  &&  STORAGE
          */
        String[] STORAGE_LOCATION={STORAGE,LOCATION};

         /**
          *CAMERA  &&   STORAGE
          */
        String[] CAMERA_STORAGE={CAMERA,STORAGE};

         /**
          * PHONE && SMS
          */
        String[] PHONE_SMS={PHONE,SMS};

         /**
          * MICROPHONE && CAMERA
          */
        String[] MICROPHONE_CAMERA={MICROPHONE,CAMERA};

         /**
          * MICROPHONE &&CAMERA && STORAGE
          */
        String[] MICROPHONE_CAMERA_STORAGE={MICROPHONE,CAMERA,STORAGE};


    }


    /**
     * request Camera Permission in android
     * @param
     */
    public static void requestCamera(@NonNull Object o){
        requestOracePermission(o,State.CAMERA,Permissions.CAMERA);
    }


    /**
     * request Storage Permission in android
     * @param o
     */
    public static void requestStorage(@NonNull Object o){
        requestOracePermission(o,State.STORAGE,Permissions.STORAGE);
    }

    /**
     * request Location Permission in android
     * @param o
     */
    public static void requestLocation(@NonNull Object o){
        requestOracePermission(o,State.LOCATION,Permissions.LOCATION);
    }

    /**
     * request Phone Permission in android
     * @param o
     */
    public static void requestPhone(@NonNull Object o){
        requestOracePermission(o,State.PHONE,Permissions.PHONE);

    }

    /**
     * request SMS Permission in android
     * @param o
     */
    public static void requestSMS(@NonNull Object o){
        requestOracePermission(o,State.SMS,Permissions.SMS);
    }

    /**
     * request Microphone Permission in android
     * @param o
     */
    public static void requestMicrophone(@NonNull Object o){
        requestOracePermission(o,State.MICROPHONE,Permissions.MICROPHONE);
    }

    /**
     * request Contacts Permission in android
     * @param
     */
    public static void requestContacts (@NonNull Object o){
        requestOracePermission(o,State.CONTACTS,Permissions.CONTACTS);
    }

    /**
     * request Calendar Permission in android
     * @param o
     */
    public static void requestCalendar(@NonNull Object o){
        requestOracePermission(o,State.CALENDAR,Permissions.CALENDAR);
    }

    /**
     * request Camera && Storage Permission in android
     * @param o
     */
    public static void requestCameraStorage(@NonNull Object o){
        requestOracePermission(o,State.CAMERA_STORAGE,Permissions.CAMERA_STORAGE);
    }

    /**
     * request Storage && Location Permission in android
     * @param o
     */
    public static void requestStorageLocation(@NonNull Object o){
        requestOracePermission(o,State.STORAGE_LOCATION,Permissions.STORAGE_LOCATION);
    }

    /**
     * request  Phone && SMS Permission in android
     * @param o
     */
    public static void requestPhoneSMS(@NonNull Object o){
        requestOracePermission(o,State.PHONE_SMS,Permissions.PHONE_SMS);
    }

    /**
     * request Microphone && Camera  Permission in android
     * @param o
     */
    public static void requestMicrophoneCamera(@NonNull Object o){
        requestOracePermission(o,State.MICROPHONE_CAMERA,Permissions.MICROPHONE_CAMERA);
    }

    /**
     * request Microphone && Camera && Storage Permission in android
     * @param o
     */
    public static void requestMicrophoneCameraStorage(@NonNull Object o){
        requestOracePermission(o,State.MICROPHONE_CAMERA_STORAGE,Permissions.MICROPHONE_CAMERA_STORAGE);
    }

    /**
     *
     * @param object
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static  void onRequestPermissionsResult(@NonNull Object object,@NonNull int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        GracePermission.onRequestPermissionsResult(object,requestCode, permissions, grantResults);
    }

    private static void requestOracePermission(@NonNull Object o,int requestCode,String... permissions){
        if (o==null){
                throw new NullPointerException(" request Can not be equal to empty");
        }
        if (o instanceof Activity){
            GracePermission.with((Activity) o).requestCode(requestCode)
                    .permissions(permissions).request();
        }else if (o instanceof android.app.Fragment){
            GracePermission.with((Fragment) o).requestCode(requestCode)
                    .permissions(permissions).request();
        }else if (o instanceof android.support.v4.app.Fragment){
            GracePermission.with((android.support.v4.app.Fragment) o).requestCode(requestCode)
                    .permissions(permissions).request();
        }else{
                throw new ExceptionInInitializerError("request Must be a specified type { Activity or android.app.Fragment or  android.support.v4.app.Fragment }");
        }

    }

}
