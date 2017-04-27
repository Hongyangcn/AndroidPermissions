package example.grace.permission;

import android.Manifest;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grace.permission.Grace;
import com.grace.permission.GracePermission;
import com.grace.permission.interaction.OnPermissionListener;

/**
 * Created by hongyang on 17-4-26.
 */

    public class Frament extends Fragment   {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setText("hello word");
        return tv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Grace.requestCamera(this);
        Grace.requestMicrophoneCameraStorage(this);
        GracePermission.with(this).permissions(Manifest.permission.CAMERA).requestCode(200).request();

        Grace.requestMicrophoneCameraStorage(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Grace.onRequestPermissionsResult(this,requestCode, permissions, grantResults);
    }

    public void onPermission(int requestCode) {
        switch (requestCode) {
            case  Grace.State.MICROPHONE_CAMERA_STORAGE:
                Log.e(Frament.class.getSimpleName(),"requestCode"+requestCode);
                break;
        }
    }
}
