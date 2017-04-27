package com.grace.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;


import com.grace.permission.general.GraceAuthorization;
import com.grace.permission.general.GraceDescription;
import com.grace.permission.general.GraceDialog;
import com.grace.permission.general.GraceReflex;
import com.grace.permission.interaction.OnRationaleListener;
import com.grace.permission.interaction.OnRequestPermission;

import java.util.List;

/**
 * Created by hongyang on 17-4-24.
 */

public class GracePermission{

    private static  OnRationaleListener onRationaleListener;

    static {

        onRationaleListener =new  OnRationaleListener() {
            @Override
            public void onRation(final Context context, final Permission permission,String... permissions) {
                new GraceDialog(context).PromptMaterial(
                        GraceDescription.declineDescription(context,permissions),new  OnRequestPermission() {
                    @Override
                    public void execute() {
                        if (permission!=null){
                            permission.reset();
                        }
                     }
                });
            }

            @Override
            public void onDecline(final Context context,String... permissions) {
                new GraceDialog(context).DeclineMaterial(
                        GraceDescription.declineDescription(context,permissions));
            }

            @Override
            public void onForeverDecline(final Context context,String... permissions) {
                new GraceDialog(context).NoticeMaterial(
                        GraceDescription.declineDescription(context,permissions),new  OnRequestPermission() {
                    @Override
                    public void execute() {
                        Intent intent =  new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                        context.startActivity(intent);
                    }
                });
            }
        };

    }

    @NonNull
    public static Permission with(@NonNull Activity activity) {
        return new DefaultPermission(activity).OnRationaleListener(onRationaleListener);
    }

    public static @NonNull Permission with(@NonNull android.support.v4.app.Fragment fragment) {
        return new DefaultPermission(fragment).OnRationaleListener(onRationaleListener);
    }

    public static @NonNull Permission with(@NonNull android.app.Fragment fragment) {
        return new DefaultPermission(fragment).OnRationaleListener(onRationaleListener);
    }

    public static  void onRequestPermissionsResult(@NonNull Object object,@NonNull int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        List<String> deniedList =  GraceAuthorization.isSuccess(grantResults,permissions);

        if (deniedList.isEmpty()){
            GraceReflex.transmit(object,requestCode);
            return;
        }

        if (GraceAuthorization.hasAlwaysDeniedPermission(object,deniedList)){
            onRationaleListener.onForeverDecline(GraceAuthorization.getContext(object),deniedList.toArray(new String[deniedList.size()]));
        }

    }
}
