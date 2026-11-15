package com.example.androidlab1;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实验三 4：创建上下文操作模式（ActionMode）的上下文菜单。
 * 长按列表项进入 ActionMode，标题显示已选数量，支持全选与删除。
 */
public class ActionModeActivity extends AppCompatActivity {

    private final List<String> items = new ArrayList<>(
            Arrays.asList("One", "Two", "Three", "Four", "Five"));

    private final List<Map<String, Object>> data = new ArrayList<>();

    private SimpleAdapter adapter;
    private ListView listView;
    private View toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);

        toolbar = findViewById(R.id.toolbar_action_mode);
        listView = findViewById(R.id.list_actions);
        for (String name : items) {
            Map<String, Object> row = new HashMap<>();
            row.put("name", name);
            row.put("icon", R.drawable.ic_android);
            data.add(row);
        }
        adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item_action_row,
                new String[]{"name", "icon"},
                new int[]{R.id.tv_action_name, R.id.iv_action_icon});
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(getString(R.string.action_selected_count, listView.getCheckedItemCount()));
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.action_mode, menu);
                mode.setTitle(getString(R.string.action_selected_count, 0));
                toolbar.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_select_all) {
                    for (int i = 0; i < adapter.getCount(); i++) {
                        listView.setItemChecked(i, true);
                    }
                    return true;
                }
                if (id == R.id.action_delete) {
                    SparseBooleanArray checked = listView.getCheckedItemPositions();
                    for (int i = checked.size() - 1; i >= 0; i--) {
                        if (checked.valueAt(i)) {
                            data.remove(checked.keyAt(i));
                        }
                    }
                    listView.clearChoices();
                    adapter.notifyDataSetChanged();
                    mode.setTitle(getString(R.string.action_selected_count, 0));
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                toolbar.setVisibility(View.VISIBLE);
            }
        });
    }
}
