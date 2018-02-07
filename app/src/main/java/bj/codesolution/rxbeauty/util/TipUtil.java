package bj.codesolution.rxbeauty.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Ryylnn on 2016/5/19 0019.
 */
public class TipUtil {
    private TipUtil() {
    }

    public static void showTipWithAction(View view, String tipText, String actionText, View.OnClickListener listener) {
        Snackbar.make(view, tipText, Snackbar.LENGTH_INDEFINITE).setAction(actionText, listener).show();
    }

    public static void showTipWithAction(View view, String tipText, String actionText, View.OnClickListener listener, int duration) {
        Snackbar.make(view, tipText, duration).setAction(actionText, listener).show();
    }

    public static void showSnackTip(View view, String tipText) {
        Snackbar.make(view, tipText, Snackbar.LENGTH_SHORT).show();
    }
}