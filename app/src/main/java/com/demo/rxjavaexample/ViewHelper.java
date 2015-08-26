package com.demo.rxjavaexample;

import android.content.Context;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by wish8 on 8/26/15.
 */
public class ViewHelper {
    public static void showError(Context context, Throwable throwable) {
        new MaterialDialog.Builder(context)
                .title(R.string.system_report_title)
                .content(throwable.getLocalizedMessage())
                .negativeText(R.string.continue_title)
                .show();
    }
}
