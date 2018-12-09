package com.zhou.bannerdemo;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.zhou.banner.ImageLoader;

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        super.displayImage(context, path, imageView);
        GlideApp.with(context).load(path)
                .apply(new RequestOptions().placeholder(R.drawable.ic_default).error(R.drawable.ic_fail).dontAnimate().centerCrop())
                .dontAnimate().into(imageView);
    }
}
