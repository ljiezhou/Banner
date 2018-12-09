package com.zhou.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Banner extends FrameLayout {
    private List<String> imageUrls = new ArrayList<>();

    private BannerViewPager bannerViewPager;
    private BannerAdapter adapter;

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
        bannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE || i == ViewPager.SCROLL_STATE_DRAGGING) {
                    if (bannerViewPager.getCurrentItem() == 0) {
                        bannerViewPager.setCurrentItem(imageUrls.size() - 2, false);
                    } else if (bannerViewPager.getCurrentItem() == imageUrls.size() - 1) {
                        bannerViewPager.setCurrentItem(1, false);
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

    public void setData(List<String> urls) {
        if (urls == null || urls.size() == 0)
            try {
                throw new Exception("图片列表不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        imageUrls.add(urls.get(urls.size() - 1));
        imageUrls.addAll(urls);
        imageUrls.add(urls.get(0));

    }

    public Banner start() {
        adapter = new BannerAdapter();
        bannerViewPager.setAdapter(adapter);
        return this;
    }

    public Banner setData(String[] urls) {
        if (urls == null || urls.length == 0)
            try {
                throw new Exception("图片列表不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        imageUrls.add(urls[urls.length - 1]);
        for (String url : urls) {
            imageUrls.add(url);
        }
        imageUrls.add(urls[0]);
        return this;
    }
}
