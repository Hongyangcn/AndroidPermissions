/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grace.permission.general;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.grace.permission.R;
import com.grace.permission.interaction.OnRequestPermission;


/**
 * Created by hongyang on 17-4-25.
 */

 public  final class GraceDialog {

    private  android.support.v7.app.AlertDialog.Builder mBuilder ;
    private OnRequestPermission mSettingService;

    public GraceDialog(@NonNull Context context) {
        mBuilder = new android.support.v7.app.AlertDialog.Builder(context);
        mBuilder.setCancelable(false);
    }

    public void  PromptMaterial(@NonNull String message, OnRequestPermission settingService){
        mBuilder.setTitle(R.string.permission_title_permission_rationale)
                .setMessage(message)
                .setPositiveButton(R.string.permission_again, mClickListener)
                .setNegativeButton(R.string.permission_cancel, null).show();
        this.mSettingService = settingService;
    }

    public  void NoticeMaterial(@NonNull  String message, OnRequestPermission settingService){
        mBuilder.setTitle(R.string.permission_title_permission_failed)
                .setMessage(message)
                .setPositiveButton(R.string.permission_setting, mClickListener).show();
        this.mSettingService = settingService;
    }


    public  void DeclineMaterial( String message){
        mBuilder.setTitle(R.string.permission_title_permission_failed)
                .setMessage(message)
                .setPositiveButton(R.string.permission_konw, null).show();
    }

    @NonNull
    public GraceDialog setTitle(@NonNull String title) {
        mBuilder.setTitle(title);
        return this;
    }

    @NonNull
    public GraceDialog setTitle(@StringRes int title) {
        mBuilder.setTitle(title);
        return this;
    }

    @NonNull
    public GraceDialog setMessage(@NonNull String message) {
        mBuilder.setMessage(message);
        return this;
    }

    @NonNull
    public GraceDialog setMessage(@StringRes int message) {
        mBuilder.setMessage(message);
        return this;
    }

    @NonNull
    public GraceDialog setNegativeButton(@NonNull String text, @Nullable DialogInterface.OnClickListener
            negativeListener) {
        mBuilder.setNegativeButton(text, negativeListener);
        return this;
    }

    @NonNull
    public GraceDialog setNegativeButton(@StringRes int text, @Nullable DialogInterface.OnClickListener
            negativeListener) {
        mBuilder.setNegativeButton(text, negativeListener);
        return this;
    }

    public void show() {
        mBuilder.show();
    }

    /**
     * The dialog's btn click listener.
     */
    private DialogInterface.OnClickListener mClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if (mSettingService!=null){
                        mSettingService.execute();
                    }
                    break;
            }
        }
    };
}
