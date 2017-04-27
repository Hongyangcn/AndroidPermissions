package com.grace.permission.general;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongyang on 17-4-24.
 */

 public  final class GraceAuthorization {

    public static Context getContext(Object o) {
        if (o instanceof Activity)
            return (Activity) o;
        else if (o instanceof Fragment)
            return ((Fragment) o).getActivity();
        else if (o instanceof android.app.Fragment)
            return ((android.app.Fragment) o).getActivity();
        throw new IllegalArgumentException("The " + o.getClass().getName() + " is not support.");
    }


    public static boolean  hasPermission23(@NonNull Context context, @NonNull String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;

        for (String permission : permissions) {
            boolean hasPermission = (ContextCompat.checkSelfPermission(context, permission) == PackageManager
                    .PERMISSION_GRANTED);
            if (!hasPermission) return false;
        }
        return true;
    }


    public  static List<String> hasPermission(@NonNull Context context,String... permissions){
            List<String> arryList = new ArrayList<>();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            final int permissionCount = permissions.length;
            for (int i = 0; i < permissionCount; i++) {
                if (packageManager.checkPermission(
                        permissions[i], packageName)!=PackageManager.PERMISSION_GRANTED){
                    arryList.add(permissions[i]);
                }
            }
            return arryList;
    };

   public  static boolean shouldShowRationalePermissions(@NonNull Object o, String... permissions) {
        if (Build.VERSION.SDK_INT < 23) return false;
        boolean rationale = false;
        for (String permission : permissions) {
            if (o instanceof Activity) {
                rationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) o, permission);
            } else if (o instanceof android.support.v4.app.Fragment) {
                rationale = ((android.support.v4.app.Fragment) o).shouldShowRequestPermissionRationale(permission);
            } else if (o instanceof android.app.Fragment) {
                rationale = ((android.app.Fragment) o).shouldShowRequestPermissionRationale(permission);
            }
            if (rationale) return true;
        }
        return false;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param activity          {@link Activity}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull Activity activity, @NonNull List<String>
            deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRationalePermissions(activity, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment          {@link android.support.v4.app.Fragment}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull android.support.v4.app.Fragment fragment, @NonNull
            List<String>
            deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRationalePermissions(fragment, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment          {@link android.app.Fragment}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull android.app.Fragment fragment, @NonNull List<String>
            deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRationalePermissions(fragment, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    public  static void PromptLanguage( @NonNull String... permissions){
        for (String permission : permissions) {
            Log.e("permission",permission);
        }
    }

    public static void NullPointerException(Object o){
        if (o==null){
            throw new NullPointerException();
        }
    }

    public static void ExceptionInInitializerError(){
        throw new ExceptionInInitializerError();
    }


    public static boolean hasAlwaysDeniedPermission(@NonNull Object object,List<String> deniedList){
        GraceAuthorization.NullPointerException(object);
        if (object instanceof Activity)
            return GraceAuthorization.hasAlwaysDeniedPermission((Activity) object,deniedList);
        if (object instanceof android.support.v4.app.Fragment)
            return GraceAuthorization.hasAlwaysDeniedPermission((android.support.v4.app.Fragment) object,deniedList);
        if (object instanceof android.app.Fragment)
            return GraceAuthorization.hasAlwaysDeniedPermission((android.app.Fragment)object,deniedList);
        GraceAuthorization.ExceptionInInitializerError();
        return false;
    }


    public static  List<String> isSuccess(@NonNull int[] grantResults, @NonNull String... permissions){
        List<String> deniedList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                deniedList.add(permissions[i]);
            }
        }
        return deniedList;
    }
}
