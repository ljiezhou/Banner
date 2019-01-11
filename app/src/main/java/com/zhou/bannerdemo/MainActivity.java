package com.zhou.bannerdemo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhou.banner.Banner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * 图片地址  来自鸿洋大神的wanandroid网站
     */
    private String[] urls = {"http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
            "http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png",
            "http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png",
            "http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
            "http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg",
            "http://www.wanandroid.com/blogimgs/90cf8c40-9489-4f9d-8936-02c9ebae31f0.png",
            "http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png"};
    private String[] titles = {"一起来做个APP吧",
            "看看别人的面经，搞定面试",
            "兄弟，要不要挑个项目学习下？",
            "我们新增了一个常用导航Tab",
            "公众号文章列表强势上线",
            "JSON工具",
            "微信文章合集"};
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = findViewById(R.id.banner);
        banner.setData(urls)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
}
