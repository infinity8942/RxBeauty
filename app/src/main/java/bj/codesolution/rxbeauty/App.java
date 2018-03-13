package bj.codesolution.rxbeauty;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class App extends Application {

    private static App mInstance;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults()
                .instanceField("android.view.inputmethod.InputMethodManager", "sInstance")
                .instanceField("android.view.inputmethod.InputMethodManager", "mLastSrvView")
                .instanceField("com.android.internal.policy.PhoneWindow$DecorView", "mContext")
                .instanceField("android.support.v7.widget.SearchView$SearchAutoComplete", "mContext")
                .build();

        refWatcher = LeakCanary.refWatcher(this)
                .listenerServiceClass(DisplayLeakService.class)
                .excludedRefs(excludedRefs)
                .buildAndInstall();
    }

    public static App getInstance() {
        return mInstance;
    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }
}