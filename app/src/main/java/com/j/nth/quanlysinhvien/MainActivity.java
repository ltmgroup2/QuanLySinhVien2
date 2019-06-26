package com.j.nth.quanlysinhvien;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j.nth.quanlysinhvien.adapter.adapterSV;
import com.j.nth.quanlysinhvien.classes.MyDatabaseHelper;
import com.j.nth.quanlysinhvien.classes.SinhVien;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private FloatingActionButton btnAdd;
    private SearchView searchViewExample;
    private GridView gridView;

    private com.j.nth.quanlysinhvien.adapter.adapterSV adapterSV;
    private ArrayList<SinhVien> sinhVienArrayList;

    private TextView txtTitle;
    MyDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        btnAdd = findViewById(R.id.btn_add_dialog);
        searchViewExample = findViewById(R.id.search_view_example);
        txtTitle = findViewById(R.id.txt_title);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);

        Navigation();


        EditText searchEditText =  searchViewExample.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setHintTextColor(Color.WHITE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code xử lý
                openDialogAdd("Thêm");
            }
        });
        gridView = findViewById(R.id.grid_view);

        db = new MyDatabaseHelper(this);
        //DropTable();
        CreateTable();

        sinhVienArrayList = new ArrayList<>();
        adapterSV = new adapterSV(this, R.layout.layout_item_list_sv, sinhVienArrayList);
        gridView.setAdapter(adapterSV);
        registerForContextMenu(gridView);

        setEventSearchView();

        LoadData();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sinhVien = sinhVienArrayList.get(position);
                return false;
            }
        });

    }
    private void Navigation() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // set item as selected to persist highlight
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.android:
                        Toast.makeText(MainActivity.this, "https://www.fpt-software.com/", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rateUs:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:02838110521"));
                        startActivity(intent);
                        break;
                    case R.id.signOut:
                        Intent intent2 = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent2);
                        finish();
                        break;

                }
                // close drawer when item is tapped
                drawerLayout.closeDrawers();

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here

                return true;
            }
        });
    }

    private void setEventSearchView() {
        searchViewExample.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTitle.setVisibility(View.GONE);
            }
        });
        searchViewExample.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                txtTitle.setVisibility(View.VISIBLE);

                return false;
            }
        });
        searchViewExample.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSV.getFilter().filter(newText);
                return false;
            }
        });
    }

    TextView txtTitleDialog;
    EditText txtMaSV,txtTenSV,txtLop,txtNamSinh,txtChuyenNganh;
    ImageView avatar;
    Button btnChonHinh,btnDongY,btnHuy;

    private void openDialogAdd(final String action) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_student);
        txtTitleDialog = dialog.findViewById(R.id.txt_title_dialog);
        txtMaSV = dialog.findViewById(R.id.txtMaSV);
        txtTenSV = dialog.findViewById(R.id.txtTenSV);
        txtLop = dialog.findViewById(R.id.txtLop);
        txtNamSinh = dialog.findViewById(R.id.txtNamSinh);
        //txtChuyenNganh = dialog.findViewById(R.id.txtChuyenNganh);
        avatar = dialog.findViewById(R.id.imageViewHinhSV);
        btnChonHinh = dialog.findViewById(R.id.btnLayHinhAnh);
        btnDongY = dialog.findViewById(R.id.btnDialogDongY);
        btnHuy = dialog.findViewById(R.id.btnDialogHuy);

        if (action.equals("Thêm")) {
            txtTitleDialog.setText("Thêm Sinh Viên");
        } else {
            txtMaSV.setText(sinhVien.getMaSV());
            txtTenSV.setText(sinhVien.getTenSV());
            txtLop.setText(sinhVien.getLopSV());
            txtNamSinh.setText(sinhVien.getNamSinh());
            txtChuyenNganh.setText(sinhVien.getChuyenNganh());
            avatar.setImageBitmap(BitmapFactory.decodeByteArray(sinhVien.getHinhAnh(),0,sinhVien.getHinhAnh().length));
            txtMaSV.setEnabled(false);
            txtTitleDialog.setText("Sửa Sinh Viên");
        }

        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera,1111);
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMaSV.getText().toString().equals("")||txtTenSV.getText().toString().equals("")||txtLop.getText().toString().equals("")||txtNamSinh.getText().toString().equals("")||txtChuyenNganh.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    if (checkNamSinh()) {
                        Toast.makeText(MainActivity.this, "Năm sinh không đúng!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        String arr[] = txtNamSinh.getText().toString().split("\\/");
                        Calendar calendar = Calendar.getInstance();
                        int nam = calendar.get(Calendar.YEAR);
                        int nam2 = Integer.parseInt(arr[2]);
                        if((nam - nam2) < 18) {
                            Toast.makeText(MainActivity.this,"Không đủ tuổi",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"Đã xảy ra lỗi trong quá trình xử lý",Toast.LENGTH_SHORT).show();
                }
                SinhVien sinhVien = new SinhVien(txtMaSV.getText().toString(),
                        txtTenSV.getText().toString(),
                        txtLop.getText().toString(),
                        txtNamSinh.getText().toString(),
                        txtChuyenNganh.getText().toString(),
                        ImageView_To_Byte(avatar)
                        );
                if (action.equals("Thêm")) {
                    if (checkSinhVien()) {
                        Toast.makeText(MainActivity.this, "Sinh Viên Đã Tồn Tại!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        db.insertSinhVien(sinhVien);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                    db.updateSinhVien(sinhVien);
                }
                LoadData();

                dialog.dismiss();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private boolean checkSinhVien() {
        for (SinhVien sv : sinhVienArrayList) {
            if (sv.getMaSV().equals(txtMaSV.getText().toString()))
                return true;
        }

        return false;
    }
    private boolean checkNamSinh(){
        String ns = txtNamSinh.getText().toString();
        String date = ns.substring(0,2);
        String month = ns.substring(3,5);
        Log.d("ns",date+"||"+month);
        if (ns.length() != 10) return true;
        if (Integer.parseInt(date) > 31) {
            return true;
        } else {
            if (Integer.parseInt(date) > 30 && (Integer.parseInt(month) == 4 || Integer.parseInt(month) == 6 || Integer.parseInt(month) == 9 || Integer.parseInt(month) == 11)) {
                return true;
            }
            if (Integer.parseInt(date) > 29 && Integer.parseInt(month) == 2) {
                return true;
            }
        }
        if (Integer.parseInt(month) > 12) return true;
        return false;
    }
    private void LoadData() {
        Cursor sv  = db.GetData("select * from SINHVIEN");

        sinhVienArrayList.clear();
        while (sv.moveToNext()) {

            sinhVienArrayList.add(new SinhVien(
                    sv.getString(0),
                    sv.getString(1),
                    sv.getString(2),
                    sv.getString(3),
                    sv.getString(4),
                    sv.getBlob(5)
            ));
        }
        adapterSV.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1111 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            avatar.setImageBitmap(photo);
        }
    }
    public byte[] ImageView_To_Byte(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    SinhVien sinhVien;
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                // Sửa sinh viên
                openDialogAdd("Sửa");
                break;
            case R.id.delete:
                // Xóa sinh viên
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Xóa Sinh Viên");
                builder.setMessage("Bạn có chắc muốn xóa không?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM SINHVIEN WHERE MASV = '"+sinhVien.getMaSV()+"'";
                        Log.d("AAA",sinhVien.getMaSV());
                        db.query(sql);
                        LoadData();
                        Toast.makeText(MainActivity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
                break;
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void CreateTable() {
        String sql="CREATE TABLE IF NOT EXISTS SINHVIEN(MASV VARCHAR(200) PRIMARY KEY," +
                "TENSV VARCHAR(200)," +
                "LOPSV VARCHAR(200)," +
                "NAMSINH VARCHAR(200)," +
                "CN VARCHAR(200)," +
                "HINHANH BLOB)";
        db.query(sql);
    }
    private void DropTable() {
        String sql2= "DROP TABLE IF EXISTS SINHVIEN";
        db.query(sql2);
    }

}
