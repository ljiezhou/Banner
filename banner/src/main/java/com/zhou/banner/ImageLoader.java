package com.zhou.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class ImageLoader implements ImageLoaderInterface<ImageView> {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

    }

    /**
     * 提供一个默认的实现
     *
     * @param context
     * @return
     */
    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
