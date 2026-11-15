package com.example.androidlab1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_linear).setOnClickListener(v -> open(LinearLayoutActivity.class));
        findViewById(R.id.btn_table).setOnClickListener(v -> open(TableLayoutActivity.class));
        findViewById(R.id.btn_constraint1).setOnClickListener(v -> open(Constraint1Activity.class));
        findViewById(R.id.btn_constraint2).setOnClickListener(v -> open(Constraint2Activity.class));
        findViewById(R.id.btn_compose).setOnClickListener(v -> open(ComposeActivity.class));
        findViewById(R.id.btn_listview).setOnClickListener(v -> open(ListViewActivity.class));
        findViewById(R.id.btn_dialog).setOnClickListener(v -> open(CustomDialogActivity.class));
        findViewById(R.id.btn_xml_menu).setOnClickListener(v -> open(MenuXmlActivity.class));
        findViewById(R.id.btn_action_mode).setOnClickListener(v -> open(ActionModeActivity.class));
        findViewById(R.id.btn_recycler).setOnClickListener(v -> open(RecyclerViewActivity.class));
    }

    private void open(Class<?> activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}
