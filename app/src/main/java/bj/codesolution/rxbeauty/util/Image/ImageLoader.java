package bj.codesolution.rxbeauty.util.Image;

import android.widget.ImageView;

/**
 * Created by Rylynn on 2016/5/19 0019.
 */
public class ImageLoader {

    private String url;
    private int placeHolder;
    private ImageView mImageView;

    private ImageLoader(Builder builder) {
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.mImageView = builder.mImageView;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public static class Builder {
        private String url;
        private int placeHolder;
        private ImageView mImageView;

        public Builder() {
            this.url = "";
            this.placeHolder = 0;
            this.mImageView = null;
        }

        public Builder url(String url) {
            if (null != url) {
                this.url = url;
            }
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder mImageView(ImageView mImageView) {
            this.mImageView = mImageView;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }

    }
}