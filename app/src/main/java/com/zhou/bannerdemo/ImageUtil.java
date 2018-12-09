package com.zhou.bannerdemo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

/**
 * Created by zhou on 2018/4/1.
 */

public class ImageUtil {

    public static void loadImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context).load(url)
                .apply(new RequestOptions().placeholder(R.drawable.ic_default).error(R.drawable.ic_fail).dontAnimate().centerCrop())
                .dontAnimate().into(imageView);
    }
}
