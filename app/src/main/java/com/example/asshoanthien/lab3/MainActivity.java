package com.example.asshoanthien.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sport(View view) {
        Intent intent=new Intent(MainActivity.this,Sport_Activity.class);
        startActivity(intent);
    }

    public void maths(View view) {
        Intent intent=new Intent(MainActivity.this,Maths_Activity.class);
        startActivity(intent);
    }


}
