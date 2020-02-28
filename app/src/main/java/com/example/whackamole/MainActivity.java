package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private Drawable catImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler timeHandler;
    public updateGame update;
    public int count;
    public int point;
    public TextView countView;
    public TextView pointView;
    private boolean on;
    private int imageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        countView = findViewById(R.id.countView);
        pointView = findViewById(R.id.pointView);
        moleImage = getDrawable(R.drawable.mole);
        catImage = getDrawable(R.drawable.creepy_cat);
        imageViews = new ImageView[16];
        timeHandler = new Handler();
        rand = new Random();
        on = false;
        count = 100;
        point = 0;
        imageNum = 0;
        update = new updateGame();
        moleLocation = rand.nextInt(16);
        for(int i=0; i<16; i++){
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if (i == moleLocation) imageViews[i].setImageDrawable(null);
            grid.addView(imageViews[i]);
        }
    }

    public void imagePressed(View v){
        Intent i = new Intent(this, imageActivity.class);
        i.putExtra("IMAGE",imageNum);
        startActivityForResult(i, 1);
    }

    public void helpPressed(View v){
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageNum = data.getIntExtra("IMAGE", 0);
    }

    public void startPressed(View v) {
        if (on) {
            on = false;
            timeHandler.removeCallbacks(update);
        } else {
            on = true;
            timeHandler.postDelayed(update, 1000);
        }
    }

    public void molePress(View v){
        if(((ImageView)v).getDrawable() == moleImage && count>0){
            point++;
            pointView.setText(point+"");
            imageViews[moleLocation].setImageDrawable(null);
        }else if(((ImageView)v).getDrawable() == catImage && count>0){
            point++;
            pointView.setText(point+"");
            imageViews[moleLocation].setImageDrawable(null);
        }
    }

    private class updateGame implements Runnable{
        public void run(){
            if(count>0){
                imageViews[moleLocation].setImageDrawable(null);
                moleLocation = rand.nextInt(16);
                if(imageNum == 0){
                    imageViews[moleLocation].setImageDrawable(moleImage);
                }else{
                    imageViews[moleLocation].setImageDrawable(catImage);
                }
                count--;
                countView.setText(count+"");
                timeHandler.postDelayed(update, 1000);
            }
        }
    }
}
