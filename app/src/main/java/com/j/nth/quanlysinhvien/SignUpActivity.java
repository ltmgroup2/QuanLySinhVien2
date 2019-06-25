package com.j.nth.quanlysinhvien;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText edt_user,edt_pass,edt_pass_again;
    TextView txt_exist,txt_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        txt_exist = (TextView) findViewById(R.id.lin);
        edt_pass_again = (EditText) findViewById(R.id.edt_pass_again);
        edt_pass = (EditText) findViewById(R.id.edt_pass);
        edt_user = (EditText) findViewById(R.id.edt_user);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        edt_user.setTypeface(custom_font);
        edt_pass.setTypeface(custom_font1);
        edt_pass_again.setTypeface(custom_font);
        txt_exist.setTypeface(custom_font);
        txt_signup.setTypeface(custom_font);
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(it);
                Toast.makeText(SignUpActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
        txt_exist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
    }
}
