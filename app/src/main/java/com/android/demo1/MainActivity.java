package com.android.demo1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    ProgressBar prbLogin;
    RadioButton rdNam, rdNu;
    //fragment
    //activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewRef();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        int v = intent.getIntExtra("key", -986);
        if (v  != -986){
            //toast ra
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account("duy", "hn", 22);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("account", account);
                startActivity(intent);
                /*
                    thực hành chuyển dữ liệu giữa các activity
                    - kiểu dl nguyên thủy
                    - kiểu object bằng cả 2 cách
                    - Activity 2, sẽ có thêm 1 nút back, khi ấn vào back sẽ quay lại Activity1. và gửi key: abc value: 88
                    sau đó Toast lên Activity1
                 */
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private void viewRef() {
        btnLogin = findViewById(R.id.btnLogin);
        prbLogin = findViewById(R.id.prbLogin);
        rdNam = findViewById(R.id.rdNam);
        rdNu = findViewById(R.id.rdNu);
    }
}