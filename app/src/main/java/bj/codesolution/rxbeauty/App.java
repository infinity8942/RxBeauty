package bj.codesolution.rxbeauty;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
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

        refWatcher = LeakCanary.install(getInstance());

        Fresco.initialize(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    public static App getInstance() {
        return mInstance;
    }
}