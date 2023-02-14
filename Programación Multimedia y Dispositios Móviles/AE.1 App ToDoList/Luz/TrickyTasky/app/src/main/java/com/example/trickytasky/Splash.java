package com.example.trickytasky;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity implements Animation.AnimationListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        MediaPlayer mediaPlayer = MediaPlayer.create(Splash.this, R.raw.clicker);
        mediaPlayer.start();


        TextView cargando = findViewById(R.id.tituloSplash);
        Animation animacion = AnimationUtils.loadAnimation(this,R.anim.splash);
        cargando.startAnimation(animacion);
        animacion.setAnimationListener(this);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        Intent intent = new Intent(Splash.this, Login.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


}
