package com.example.androidlab1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 实验三 2：创建自定义布局的 AlertDialog。
 * 通过 AlertDialog.Builder.setView() 把 dialog_login.xml 添加到对话框。
 */
public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btn_show_dialog).setOnClickListener(v -> showLoginDialog());
    }

    private void showLoginDialog() {
        View content = getLayoutInflater().inflate(R.layout.dialog_login, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(content)
                .create();
        // 去掉 AlertDialog 默认给自定义视图加的留白，让橙色标题栏铺满对话框
        dialog.setView(content, 0, 0, 0, 0);
        dialog.show();

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.bg_dialog);
            window.setLayout(dp(300), ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        EditText etUsername = content.findViewById(R.id.et_username);
        EditText etPassword = content.findViewById(R.id.et_password);

        content.findViewById(R.id.btn_cancel).setOnClickListener(v -> dialog.dismiss());
        content.findViewById(R.id.btn_sign_in).setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            if (username.isEmpty()) {
                Toast.makeText(this, R.string.dialog_sign_in_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            String password = etPassword.getText().toString();
            Toast.makeText(this, "欢迎 " + username + "（密码长度 " + password.length() + "）",
                    Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
