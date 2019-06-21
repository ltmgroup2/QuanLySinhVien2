package com.j.nth.quanlysinhvien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(BackgroundActivity.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_exit_left,R.anim.anim_enter_left);
                }
            }
        });
        thread.start();

    }
    protected void onPause() {
        super.onPause();
        finish();
    }
}
