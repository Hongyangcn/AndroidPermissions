package com.grace.permission.interaction;

import android.content.Context;
import android.support.annotation.NonNull;

import com.grace.permission.Permission;

/**
 * Created by hongyang on 17-4-26.
 */

public interface OnRationaleListener {

    void  onRation(@NonNull Context context, Permission permission,String... permissions);

    void  onDecline(@NonNull Context context,String... permissions);

    void  onForeverDecline(@NonNull Context context,String... permissions);

}
