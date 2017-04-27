package com.grace.permission.interaction;

import android.support.annotation.NonNull;

/**
 * Created by hongyang on 17-4-26.
 */

public interface OnPermissionListener {

    void  onPermission(@NonNull int requestCode);

}
