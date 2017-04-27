package com.grace.permission.general;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hongyang on 17-4-25.
 */

public final class GraceReflex {

    public static void transmit(@NonNull Object object, @NonNull int requestCode){
        try {
            Class[] argsClass = new Class[]{};
            Method method=object.getClass().getMethod("onPermission",new Class[] {int.class});
            method.invoke(object,requestCode);
        } catch (NoSuchMethodException e) {
            throw new NullPointerException("Please implement the interface : OnPermissionListener or Custom method onPermission(int requestCode)");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
