package com.example.asshoanthien.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {
    private TextView mTvSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_);
        initView();

        Intent intent = getIntent();
        int a = intent.getIntExtra("mark",0);
        int i = intent.getIntExtra("bien",0);

        Log.e("diemden","" + a);

        mTvSoLuong.setText("Bạn đã làm đúng " + a + " câu hỏi");

    }

    private void initView() {
        mTvSoLuong = findViewById(R.id.tvSoLuong);
    }
}
