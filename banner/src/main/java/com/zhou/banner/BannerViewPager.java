package com.zhou.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by 周利杰 on 2018/11/5.
 */
public class BannerViewPager extends ViewPager {

    public BannerViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        //下面遍历所有child的高度
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) //采用最大的view的高度。
                height = h;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
