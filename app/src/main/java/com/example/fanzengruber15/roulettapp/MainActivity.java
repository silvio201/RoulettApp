package com.example.fanzengruber15.roulettapp;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
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
        loadImages();
        TextView balanceview = findViewById(R.id.txtBalance);
        balanceview.setText("" +balance);
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



        ImageAdapter adapterView1 = new ImageAdapter(this, new int[]{R.mipmap.clubs, R.mipmap.diamonds, R.mipmap.hearts, R.mipmap.spades});
        ImageAdapter adapterView2 = new ImageAdapter(this, new int[]{R.mipmap.clubs, R.mipmap.diamonds, R.mipmap.hearts, R.mipmap.spades});
        ImageAdapter adapterView3 = new ImageAdapter(this, new int[]{R.mipmap.clubs, R.mipmap.diamonds, R.mipmap.hearts, R.mipmap.spades});

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

        if(slider1.getCurrentItem() == (int) image1.getTag()){
            multi++;
        }else{
            multi--;
        }

        if(slider2.getCurrentItem() == (int) image2.getTag()){
            multi++;
        }else{
            multi--;
        }

        if(slider3.getCurrentItem() == (int) image3.getTag()){
            multi++;
        }else{
            multi--;
        }

        if(multi == -3) multi=-1;

        return multi;
    }

    public void GuessClicked(View view) {
        loadImages();
        TextView txtguess = findViewById(R.id.txtGuess);
        int guess = Integer.parseInt(txtguess.getText().toString());
        if (balance - guess < 0){
            Toast.makeText(this, "Not enough Balance", Toast.LENGTH_SHORT);
        }else {
            int oldguess = guess;
            guess = guess * getMulit();
            balance = balance + guess;
            TextView txtbalance = findViewById(R.id.txtBalance);
            txtbalance.setText("" + balance);
            if(guess == oldguess){
                Toast.makeText(this, "No win, no Lose!", Toast.LENGTH_SHORT);
            }else if(guess < oldguess){
                Toast.makeText(this, "You lost " +oldguess+"!", Toast.LENGTH_SHORT);
            }else if(guess > oldguess){
                Toast.makeText(this, "You won " +oldguess+"!", Toast.LENGTH_SHORT);
            }
        }
    }
}
