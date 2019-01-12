package com.elgohry.ibrahimhany.gittest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ImageView splashImage;
    TextView splashText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashImage = findViewById(R.id.splashImage);
        splashText=findViewById(R.id.splashText);
        Animation imagefadeIn = new AlphaAnimation(0, 1);
        imagefadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        imagefadeIn.setDuration(3000);
        Animation textFadeIn = new AlphaAnimation(0, 1);
        textFadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        textFadeIn.setDuration(3000);
        splashImage.setAnimation(imagefadeIn);
        splashText.setAnimation(textFadeIn);
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {

        Intent intent=new Intent(Splash.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
},5000);
    }
}
