package bj.codesolution.rxbeauty.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import bj.codesolution.rxbeauty.App;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class CommonUtil {
    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) App.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) App.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
