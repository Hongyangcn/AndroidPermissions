package com.grace.permission.general;

import android.content.Context;

import com.grace.permission.Grace;
import com.grace.permission.R;

import java.util.HashMap;

/**
 * Created by hongyang on 17-4-26.
 */

public final  class GraceDescription {

    private  static   HashMap<String,String> hashMap;
    private  static   String DECLINE_START;
    private  static   String DECLINE_END;
    private  static   String PERMISSIOIN;
    private  static   String FOREVER_START;
    private  static   String FOREVER_END;


    private  static void initDescription (Object o) {
        Context c = GraceAuthorization.getContext(o);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            hashMap.put(Grace.Permissions.CALENDAR, c.getString(R.string.android_permission_READ_CALENDAR));
            hashMap.put(Grace.Permissions.CAMERA, c.getString(R.string.android_permission_CAMERA));
            hashMap.put(Grace.Permissions.CONTACTS, c.getString(R.string.android_permission_READ_CONTACTS));
            hashMap.put(Grace.Permissions.LOCATION, c.getString(R.string.android_permission_ACCESS_FINE_LOCATION));
            hashMap.put(Grace.Permissions.MICROPHONE, c.getString(R.string.android_permission_RECORD_AUDIO));
            hashMap.put(Grace.Permissions.PHONE, c.getString(R.string.android_permission_CALL_PHONE));
            hashMap.put(Grace.Permissions.SMS, c.getString(R.string.android_permission_SEND_SMS));
            hashMap.put(Grace.Permissions.STORAGE, c.getString(R.string.android_permission_WRITE_EXTERNAL_STORAGE));
            DECLINE_START=c.getString(R.string.decline_start);
            DECLINE_END  =c.getString(R.string.decline_end);
            PERMISSIOIN =c.getString(R.string.permission);
            FOREVER_START=c.getString(R.string.forever_start);
            FOREVER_START=c.getString(R.string.forever_start);
            FOREVER_END  =c.getString(R.string.forever_end);
        }
    }

    public  static String  declineDescription(Object o,String... permissions){
        initDescription(o);
        StringBuffer buffer = new StringBuffer(DECLINE_START);
        buffer.append(edit(permissions)).append(DECLINE_END);
        return buffer.toString();
    }

    public  static String  foreverDescription(Object o,String... permissions){
        initDescription(o);
        StringBuffer buffer = new StringBuffer(FOREVER_START);
        buffer.append(edit(permissions)).append(FOREVER_END);
        return buffer.toString();
    }

    private static String  edit(String...permissions){
        StringBuffer buffer = new StringBuffer();
        boolean status=false;
        for (String permission:permissions){
            if (hashMap.get(permission)!=null){
                buffer.append(hashMap.get(permission)).append(PERMISSIOIN).append(",");
                status=true;
            }
        }
        if (status){
            buffer.deleteCharAt(buffer.length()-1);
        }
        return buffer.toString();
    }

}
