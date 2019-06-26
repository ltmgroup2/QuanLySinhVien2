package com.j.nth.quanlysinhvien;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j.nth.quanlysinhvien.classes.MyDatabaseHelper;
import com.j.nth.quanlysinhvien.classes.TaiKhoan;

public class SignUpActivity extends AppCompatActivity {
    EditText edt_user,edt_pass,edt_pass_again;
    TextView txt_exist,txt_signup;
    MyDatabaseHelper db;
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
                String user         = edt_user.getText().toString();
                String pass         = edt_pass.getText().toString();
                String passAgain    = edt_pass_again.getText().toString();
                //kiểm tra các ô có rỗng hay không
                if(user.equals("")||pass.equals("")||passAgain.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                //kiểm tra mật khẩu và nhập lại mật khẩu có giống nhau k
                if(!pass.equals(passAgain))
                {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                //kiểm tra xem tài khoản đã tồn tại chưa
                db = new MyDatabaseHelper(SignUpActivity.this);
                if(db.check_exist_user(user))
                {
                    Toast.makeText(SignUpActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    edt_user.setText("");
                    return;
                }
                //nếu tất cả điều kiện thỏa mãn
                TaiKhoan taiKhoan = new TaiKhoan(null,user,pass);
                long ID_ACCOUNT = db.insertAccount(taiKhoan);
                taiKhoan.setID_ACCOUNT(String.valueOf(ID_ACCOUNT));
                Log.d("AAA",ID_ACCOUNT+"");
                Intent it = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(it);
                Toast.makeText(SignUpActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                finish();
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
