package com.zhou.banner;

import java.util.ArrayList;
import java.util.List;

public class BannerDTO {
    public List<String> images;
    public List<String> titles;

    public BannerDTO() {
        images = new ArrayList<>();
        titles = new ArrayList<>();
    }

    public void setImages(String[] images) {
        for (String image : images) {
            this.images.add(image);
        }
    }

    public void setImages(List<String> images) {
        this.images.addAll(images);
    }

    public void setTitles(String[] titles) {
        for (String title : titles) {
            this.titles.add(title);
        }
    }

    public void setTitles(List<String> titles) {
        this.titles.addAll(titles);
    }

    public boolean check() {
        if (images.size() == 0 || titles.size() == 0) {
            try {
                throw new Exception("图片和标题不能为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (images.size() != titles.size()) {
            try {
                throw new Exception("图片数量和标题数量不一致");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;

    }
}
