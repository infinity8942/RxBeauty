package bj.codesolution.rxbeauty.util.Image;

import android.content.Context;

/**
 * Created by Rylynn on 2016/5/19 0019.
 */
public class ImageLoaderUtil {

    private static ImageLoaderUtil mInstance;
    private BaseImageLoaderProvider mProvider;

    private ImageLoaderUtil() {
        mProvider = new FrescoImageLoaderProvider();
    }

    public static ImageLoaderUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }


    public void loadImage(Context ctx, ImageLoader loader) {
        mProvider.loadImage(ctx, loader);
    }
}