package com.zhou.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.banner.indicator.DotView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Banner extends FrameLayout {
    private List<String> imageUrls = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private BannerDTO bannerDTO;
    private BannerViewPager bannerViewPager;
    private TextView bannerTitleTV;
    private LinearLayout bannerIndicatorLL;
    private List<DotView> dotViews = new ArrayList<>();
    private BannerAdapter adapter;
    private boolean isAuto = false;
    private int count;//图片数量
    private WeakHandler weakHandler = new WeakHandler();

    public Banner(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        bannerViewPager = view.findViewById(R.id.banner_viewpager);
        bannerTitleTV = view.findViewById(R.id.banner_title);
        bannerTitleTV.setGravity(Gravity.LEFT);
        bannerIndicatorLL = view.findViewById(R.id.banner_indicator_layout);
        bannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE || i == ViewPager.SCROLL_STATE_DRAGGING) {//头
                    int currentItem = 0;
                    if (bannerViewPager.getCurrentItem() == 0) {
                        currentItem = imageUrls.size() - 2;
                        bannerViewPager.setCurrentItem(currentItem, false);
                    } else if (bannerViewPager.getCurrentItem() == imageUrls.size() - 1) {//尾
                        currentItem = 1;
                        bannerViewPager.setCurrentItem(currentItem, false);
                    }
                }

            }
        });
    }

    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_banner, null, true);
            ImageView imageView = view.findViewById(R.id.imageView);
            if (imageLoaderInterface != null) {
                imageLoaderInterface.displayImage(container.getContext(), imageUrls.get(position), imageView);
            }
            container.addView(view);
            return view;
        }
    }

    private ImageLoaderInterface imageLoaderInterface;

    public Banner setImageLoader(ImageLoaderInterface imageLoader) {
        this.imageLoaderInterface = imageLoader;
        return this;
    }


    public Banner start() {
        if (adapter != null) {
            Log.d(TAG, "start: 重复调用了");
        }
        adapter = new BannerAdapter();
        Log.d(TAG, "start: " + imageUrls.size());
        bannerViewPager.setAdapter(adapter);
        bannerTitleTV.setText(titles.get(0));
        startAutoPlay();
        return this;
    }

    public void startAutoPlay() {
        if (isAuto) {
            Log.d(TAG, "startAutoPlay: 已经在自动轮播了");
            return;
        }
        isAuto = true;
        Log.d(TAG, "startAutoPlay: 开始自动轮播");
        weakHandler.postDelayed(autoPlayRunnable, 4000);
    }

    public void stopAutoPlay() {
        weakHandler.removeCallbacks(autoPlayRunnable);
    }

    private static final String TAG = "Banner";
    private Runnable autoPlayRunnable = new Runnable() {
        @Override
        public void run() {
            if (isAuto && count > 1) {
//                Log.d(TAG, "run: "+bannerViewPager.getCurrentItem());
                bannerViewPager.setCurrentItem(bannerViewPager.getCurrentItem() + 1);
                bannerTitleTV.setText(titles.get(bannerViewPager.getCurrentItem()));
                bannerViewPager.postDelayed(autoPlayRunnable, 4000);
            }
        }
    };

    @Deprecated
    public void setData(List<String> urls) {
        if (urls == null || urls.size() == 0)
            try {
                throw new Exception("图片列表不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        imageUrls.clear();
        count = urls.size();
        Log.d(TAG, "setData:................................... " + count);
        imageUrls.add(urls.get(urls.size() - 1));
        imageUrls.addAll(urls);
        imageUrls.add(urls.get(0));

    }

    @Deprecated
    public Banner setData(String[] urls) {
        if (urls == null || urls.length == 0)
            try {
                throw new Exception("图片列表不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        imageUrls.clear();
        count = urls.length;
        imageUrls.add(urls[urls.length - 1]);
        for (String url : urls) {
            imageUrls.add(url);
        }
        imageUrls.add(urls[0]);
        return this;
    }

    public Banner setData(BannerDTO bannerDTO) {
        if (bannerDTO != null && bannerDTO.check()) {
            this.bannerDTO = bannerDTO;
            imageUrls.clear();
            count = bannerDTO.images.size();
            setData(bannerDTO.images, imageUrls);
            setData(bannerDTO.titles, titles);

//            bannerIndicatorLL.removeAllViews();
//            for (int i = 0; i < imageUrls.size(); i++) {
//                DotView dotView = new DotView(getContext());
//                if (i == 0) {
//                    dotView.setColor(R.color.colorWhite);
//                } else dotView.setColor(R.color.titleBackground);
//                dotViews.add(dotView);
//                bannerIndicatorLL.addView(dotView);
//            }
        } else {
            throw new NullPointerException("BannerDTO");
        }
        return this;
    }

    private void setData(List<String> sou, List<String> tar) {
        tar.add(sou.get(sou.size() - 1));
        for (String s : sou) {
            tar.add(s);
        }
        tar.add(sou.get(0));
    }
}
