package com.example.fanzengruber15.roulettapp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
    Context mainActivity_Context;
    private int[] sliderImageId;
    int position;

    public ImageAdapter(Context mContext, int [] images) {
        this.mainActivity_Context = mContext;
        this.sliderImageId = images;
    }

    @Override
    public int getCount() {
        if(position==sliderImageId.length-1){

        }
        return sliderImageId.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mainActivity_Context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView, 0);
        this.position=position;

        return imageView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((ImageView) o);
    }





}
