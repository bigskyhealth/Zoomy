package com.ablanco.zoomy;

import android.app.Activity;
import android.view.Window;

/**
 * Created by Álvaro Blanco Cabrero on 01/05/2017.
 * Zoomy.
 */

public class ActivityContainer implements TargetContainer{

    private Activity mActivity;

    ActivityContainer(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public Window getWindow() {
        return mActivity.getWindow();
    }
}
