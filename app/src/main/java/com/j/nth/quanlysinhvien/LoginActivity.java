package com.j.nth.quanlysinhvien;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText edt_pass,edt_user;
    TextView txt_create,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (TextView) findViewById(R.id.btn_login);
        edt_user = (EditText) findViewById(R.id.usrusr);
        edt_pass = (EditText) findViewById(R.id.pswrdd);
        txt_create = (TextView) findViewById(R.id.txt_create);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        btn_login.setTypeface(custom_font1);
        txt_create.setTypeface(custom_font);
        edt_user.setTypeface(custom_font);
        edt_pass.setTypeface(custom_font);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
        txt_create.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent_signUp = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent_signUp);
            }
        });
    }
}
