package com.zhou.banner.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BannerIndicatorView extends ViewGroup {
    public BannerIndicatorView(Context context) {
        this(context, null, 0);
    }

    public BannerIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private List<DotView> dotViews = new ArrayList<>();

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        for (int i = 0; i < 5; i++) {
            dotViews.add(new DotView(context));
            this.addView(dotViews.get(i));
        }
    }

    private int width;
    private int height;
    private static final String TAG = "BannerIndicatorView";

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = height;

        for (DotView dotView : dotViews) {
            measureChild(dotView, width, height);
        }
        setMeasuredDimension(width * 5, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View dotView = getChildAt(i);
            dotView.layout(i * width, 0, (i + 1) * width, height);
        }
    }
}
