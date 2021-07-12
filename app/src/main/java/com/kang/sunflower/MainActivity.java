package com.kang.sunflower;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BuildConfig.isRelease) {
            Toast.makeText(this, "111111", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "222222", Toast.LENGTH_SHORT).show();
        }
    }
}