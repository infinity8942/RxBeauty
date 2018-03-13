package bj.codesolution.rxbeauty.model.entity;

public class Size {
    private int mWidth;
    private int mHeight;

    public Size(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    @Override
    public String toString() {
        return "Size{" +
                "mWidth=" + mWidth +
                ", mHeight=" + mHeight +
                '}';
    }
}