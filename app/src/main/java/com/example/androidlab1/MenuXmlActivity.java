package com.example.androidlab1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * 实验三 3：使用 XML 定义菜单。
 * 字体大小：小 10sp / 中 16sp / 大 20sp；普通菜单项弹出 Toast；字体颜色：红色 / 黑色。
 */
public class MenuXmlActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        tvTest = findViewById(R.id.tv_menu_test);
        tvTest.getPaint().setUnderlineText(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_font_small) {
            tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            item.setChecked(true);
            return true;
        }
        if (id == R.id.menu_font_normal) {
            tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            item.setChecked(true);
            return true;
        }
        if (id == R.id.menu_font_large) {
            tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            item.setChecked(true);
            return true;
        }
        if (id == R.id.menu_normal) {
            Toast.makeText(this, R.string.menu_normal_toast, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menu_color_red) {
            tvTest.setTextColor(Color.RED);
            item.setChecked(true);
            return true;
        }
        if (id == R.id.menu_color_black) {
            tvTest.setTextColor(Color.BLACK);
            item.setChecked(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
