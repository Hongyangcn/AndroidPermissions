package com.grace.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.grace.permission.general.GraceAuthorization;
import com.grace.permission.general.GraceReflex;
import com.grace.permission.interaction.OnRationaleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongyang on 17-4-24.
 */

  class DefaultPermission  implements Permission {

    private Object object;
    private String[] permissions;
    private int requestCode;

    private OnRationaleListener onRationaleListener;

    public DefaultPermission(@NonNull Object object) {
        this.object = object;
    }

    @Override
    public Permission permissions(@NonNull String... permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public Permission OnRationaleListener(@NonNull  OnRationaleListener onRationaleListener) {
        this.onRationaleListener = onRationaleListener;
        return this;
    }

    @Override
    public Permission requestCode(@NonNull int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    @Override
    public void request() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            declarePermissions();
        } else {
            permissions = getDeniedPermissions(object, permissions);
            if (permissions.length > 0) {
                if (GraceAuthorization.shouldShowRationalePermissions(object, permissions)) {
                    onRationaleListener.onRation(GraceAuthorization.getContext(object),this,permissions);
                } else {
                    requestPermissions(object, requestCode, permissions);
                }
            } else {
                GraceReflex.transmit(object,requestCode);
            }
        }
    }

    @Override
    public void reset() {
         if (object==null)return;
         if (permissions==null||permissions.length==0)return;
         requestPermissions(object, requestCode, permissions);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static void requestPermissions(@NonNull Object o, @NonNull int requestCode, @NonNull String... permissions) {
        if (o instanceof Activity)
            ActivityCompat.requestPermissions(((Activity) o), permissions, requestCode);
        else if (o instanceof android.support.v4.app.Fragment)
            ((android.support.v4.app.Fragment) o).requestPermissions(permissions, requestCode);
        else if (o instanceof android.app.Fragment) {
            ((android.app.Fragment) o).requestPermissions(permissions, requestCode);
        }
    }

    private static String[] getDeniedPermissions(@NonNull Object o, @NonNull String... permissions) {
        Context context = GraceAuthorization.getContext(o);
        List<String> deniedList = new ArrayList<>(1);
        for (String permission : permissions)
            if (!GraceAuthorization.hasPermission23(context, permission))
                deniedList.add(permission);
        return deniedList.toArray(new String[deniedList.size()]);
    }

    private void declarePermissions() {
        Context context = GraceAuthorization.getContext(object);
        List<String> has = GraceAuthorization.hasPermission(context, permissions);
        if (has != null && has.size() == 0) {
            GraceReflex.transmit(object,requestCode);
        }
        if (has != null && has.size() > 0) {
            onRationaleListener.onDecline(context,(String[])has.toArray());
        }
    }
}

