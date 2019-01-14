package com.example.fanzengruber15.roulettapp;

import android.content.Context;
import android.graphics.Path;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Build;
import android.os.VibrationEffect;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Scroller;

public class MainActivity extends AppCompatActivity {
    int [] imagesSlider = new int[]{R.mipmap.clubs, R.mipmap.diamonds, R.mipmap.hearts, R.mipmap.spades};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrate();
        initialSliders();
    }

    private void initialSliders() {
        ViewPager slider1=(ViewPager) findViewById(R.id.slider1);
        ViewPager slider2=(ViewPager) findViewById(R.id.slider2);
        ViewPager slider3=(ViewPager) findViewById(R.id.slider3);



        ImageAdapter adapterView1 = new ImageAdapter(this, imagesSlider);
        ImageAdapter adapterView2 = new ImageAdapter(this, imagesSlider);
        ImageAdapter adapterView3 = new ImageAdapter(this, imagesSlider);

        slider1.setAdapter(adapterView1);
        slider2.setAdapter(adapterView2);
        slider3.setAdapter(adapterView3);


    }

    public void vibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    public int getMulit(){
        int multi=0;

        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);


        ViewPager slider1=(ViewPager) findViewById(R.id.slider1);
        ViewPager slider2=(ViewPager) findViewById(R.id.slider2);
        ViewPager slider3=(ViewPager) findViewById(R.id.slider3);

        if(slider1.getCurrentItem() == image1.getTag()){
            multi++;
        }else{
            multi--;
        }

        if(slider2.getCurrentItem() == image2.getTag()){
            multi++;
        }else{
            multi--;
        }

        if(slider3.getCurrentItem() == image3.getTag()){
            multi++;
        }else{
            multi--;
        }

        if(multi == -3) multi=-1;

        return multi;
    }

    public void GuessClicked(View view) {

    }
}
