package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash extends AppCompatActivity implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        TextView cargando = findViewById(R.id.splash);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.anim_splash);//carga la animacion
        cargando.startAnimation(animacion);//inicia la animacion
        animacion.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Intent intent = new Intent(splash.this, Login.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(splash.this, Login.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}