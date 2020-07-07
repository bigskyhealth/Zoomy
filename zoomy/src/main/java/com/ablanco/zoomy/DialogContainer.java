package com.ablanco.zoomy;

import android.app.Dialog;
import android.view.Window;

/**
 * Created by Álvaro Blanco Cabrero on 01/05/2017.
 * Zoomy.
 */

public class DialogContainer implements TargetContainer {

    private Dialog mDialog;

    DialogContainer(Dialog dialog) {
        this.mDialog = dialog;
    }

    @Override
    public Window getWindow() {
        return mDialog.getWindow();
    }
}
