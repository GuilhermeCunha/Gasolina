package com.example.savegas.Launcher;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import com.example.savegas.R;

public class Splash extends AppCompatActivity {
    private static final String TAG = "Splash";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMain();
            }
        },2000);
    }

    private void mostrarMain(){
        Intent intent = new Intent(Splash.this,Menu.class);
        startActivity(intent);
        finish();
    }
}
