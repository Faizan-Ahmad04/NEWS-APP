package com.example.allnewshub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        appName = findViewById(R.id.appNametTtle);
        Animation myAppNameTextAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.app_name_animation);

        appName.startAnimation(myAppNameTextAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myHome = new Intent(MainActivity.this,NewsCategoriesActivity.class);
                startActivity(myHome);

                finish();
            }
        },4000);
    }
}