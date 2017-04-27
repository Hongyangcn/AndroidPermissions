package com.grace.permission;

import android.support.annotation.NonNull;

import com.grace.permission.interaction.OnRationaleListener;

/**
 * Created by hongyang on 17-4-24.
 */

public  interface Permission {

    Permission  permissions(@NonNull  String... permissions);

    Permission  OnRationaleListener(@NonNull OnRationaleListener onRationaleListener);

    Permission  requestCode(@NonNull  int requestCode);

    void   request();

    void   reset();

}
