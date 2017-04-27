package example.grace.permission;

import android.app.AlertDialog;
import android.content.Context;
import android.view.KeyEvent;

/**
 * Created by hongyang on 17-4-27.
 */

public class Dialog extends AlertDialog {

    protected Dialog(Context context) {
        super(context);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


}
