package bj.codesolution.rxbeauty;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        Fresco.initialize(getInstance(), ImagePipelineConfig.newBuilder(getInstance())
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setDownsampleEnabled(true)
                .build());
    }

    public static App getInstance() {
        return mInstance;
    }
}