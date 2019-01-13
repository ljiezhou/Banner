package com.zhou.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.zhou.banner.R;

public class DotView extends View {
    private Paint paint;

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    public DotView(Context context) {
        this(context, null, 0);
    }

    public DotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void setColor(int color) {
        if (paint != null) paint.setColor(ContextCompat.getColor(getContext(), color));
    }

    private int height;
    private int width;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        height = MeasureSpec.getSize(heightMeasureSpec);
        width = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = height / 5;
        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }
}
