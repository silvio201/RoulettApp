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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int [] imagesSlider = new int[]{R.mipmap.clubs, R.mipmap.diamonds, R.mipmap.hearts, R.mipmap.spades};
    int balance = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialSliders();
        resetImages();
        TextView balanceview = findViewById(R.id.txtBalance);
        balanceview.setText("" +balance);
    }

    private void resetImages(){
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);

        image1.setImageResource(R.mipmap.card);
        image2.setImageResource(R.mipmap.card);
        image3.setImageResource(R.mipmap.card);
    }

    private void loadImages(){
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);
        for(int i = 0; i < 3; i++){
            int generated = (int)(Math.random() * 3);
            switch(i){
                case 0: image1.setImageResource(imagesSlider[generated]);
                        image1.setTag(imagesSlider[generated]);
                        break;
                case 1: image2.setImageResource(imagesSlider[generated]);
                        image2.setTag(imagesSlider[generated]);
                        break;
                case 2: image3.setImageResource(imagesSlider[generated]);
                        image3.setTag(imagesSlider[generated]);
                        break;
            }
        }
    }

    private void initialSliders() {
        ViewPager slider1=(ViewPager) findViewById(R.id.slider1);
        ViewPager slider2=(ViewPager) findViewById(R.id.slider2);
        ViewPager slider3=(ViewPager) findViewById(R.id.slider3);

        ImageAdapter adapterView1 = new ImageAdapter(this,imagesSlider);
        ImageAdapter adapterView2 = new ImageAdapter(this,imagesSlider);
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

        if(imagesSlider[slider1.getCurrentItem()] == (int) image1.getTag()){
            multi++;
        }

        if(imagesSlider[slider2.getCurrentItem()]  == (int) image2.getTag()){
            multi++;
        }

        if(imagesSlider[slider3.getCurrentItem()]  == (int) image3.getTag()){
            multi++;
        }

        if(multi == 0) return -1;

        return multi;
    }

    public void GuessClicked(View view) {
        loadImages();
        TextView txtguess = findViewById(R.id.txtGuess);
        int guess = Integer.parseInt(txtguess.getText().toString());

        if (balance - guess < 0){
            Toast t = Toast.makeText(this, "Not enough Balance", Toast.LENGTH_SHORT);
            t.show();
        }else {
            int multi = getMulit();

            if(multi == 1){
                Toast t = Toast.makeText(this, "No win, no Lose!", Toast.LENGTH_SHORT);
                t.show();
            }else if(multi == -1){
                Toast t = Toast.makeText(this, "You lost " +guess+"!", Toast.LENGTH_SHORT);
                t.show();
                balance = balance - guess;
                TextView txtbalance = findViewById(R.id.txtBalance);
                txtbalance.setText("" + balance);
            }else if(multi == 2){
                Toast t = Toast.makeText(this, "You won " +guess+"!", Toast.LENGTH_SHORT);
                t.show();
                balance = balance + guess;
                TextView txtbalance = findViewById(R.id.txtBalance);
                txtbalance.setText("" + balance);
            }else if(multi == 3){
                Toast t = Toast.makeText(this, "You won " +guess*2+"!", Toast.LENGTH_SHORT);
                t.show();
                balance = balance + (guess * 2);
                TextView txtbalance = findViewById(R.id.txtBalance);
                txtbalance.setText("" + balance);
            }

        }
    }
}
