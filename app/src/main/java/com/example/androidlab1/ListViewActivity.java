package com.example.androidlab1;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实验三 1：使用 SimpleAdapter 实现 ListView。
 * 点击列表项后用 Toast 显示选中内容，并发送一条通知（图标为应用图标，标题为列表项内容）。
 */
public class ListViewActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "lab3_list_item";
    private static final int REQUEST_NOTIFICATION = 100;

    private final String[] names = {
            "Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"
    };

    private final int[] images = {
            R.drawable.animal_lion,
            R.drawable.animal_tiger,
            R.drawable.animal_monkey,
            R.drawable.animal_dog,
            R.drawable.animal_cat,
            R.drawable.animal_elephant
    };

    private View selectedRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        createNotificationChannel();
        requestNotificationPermission();

        ListView listView = findViewById(R.id.list_animals);

        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", names[i]);
            item.put("image", images[i]);
            data.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item_animal_list,
                new String[]{"name", "image"},
                new int[]{R.id.tv_animal_name, R.id.iv_animal});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            highlightRow(view);
            String name = names[position];
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            showNotification(name, position + 1);
        });
    }

    private void highlightRow(View row) {
        if (selectedRow != null) {
            selectedRow.setBackgroundResource(R.drawable.list_item_bg);
            ((TextView) selectedRow.findViewById(R.id.tv_animal_name))
                    .setTextColor(Color.parseColor("#212121"));
        }
        row.setBackgroundResource(R.drawable.list_item_bg_selected);
        ((TextView) row.findViewById(R.id.tv_animal_name)).setTextColor(Color.WHITE);
        selectedRow = row;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    getString(R.string.notify_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(getString(R.string.notify_channel_desc));
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION);
        }
    }

    private void showNotification(String name, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(name)
                .setContentText("你选中了列表项 " + name + "，来自实验三 ListView 示例")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(id, builder.build());
    }
}
