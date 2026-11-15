package com.example.androidlab1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int clickCount = 0;
    private TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello = findViewById(R.id.tv_hello);
        Button btnClick = findViewById(R.id.btn_click);

        btnClick.setOnClickListener(v -> {
            clickCount++;
            tvHello.setText(getString(R.string.clicked_times, clickCount));
            Toast.makeText(this, getString(R.string.toast_prefix, clickCount), Toast.LENGTH_SHORT).show();
        });
    }
}
