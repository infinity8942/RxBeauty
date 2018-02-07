package bj.codesolution.rxbeauty.util.Image;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Fresco
 * Created by Rylynn on 2016/5/19 0019.
 */
public class FrescoImageLoaderProvider extends BaseImageLoaderProvider {
    @Override
    public void loadImage(Context ctx, ImageLoader loader) {

        if (!TextUtils.isEmpty(loader.getUrl())) {
            loader.getImageView().setImageURI(Uri.parse(loader.getUrl()));
        } else if (loader.getPlaceHolder() != 0) {
            loader.getImageView().setImageURI(Uri.parse("res://bj.codesolution.rxbeauty/" + loader.getPlaceHolder()));
        }
    }
}