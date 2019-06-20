package com.j.nth.quanlysinhvien;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private GridView gridView;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tab_layout);
        appBarLayout = findViewById(R.id.appbar_layout);
        viewPager = findViewById(R.id.view_pager);
        gridView = findViewById(R.id.grid_view);
        btnAdd = findViewById(R.id.btn_add_dialog);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new StudentFragment(),"Danh Sách Sinh Viên");
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);

        // registerForContextMenu(gridView);

        // Xứ lý sụ kiện click float button
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // code xử lý
//            }
//        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail:
                // Chi tiết sinh viên
                break;
            case R.id.edit:
                // Sửa sinh viên
                break;
            case R.id.delete:
                // Xóa sinh viên
                break;
        }
        return true;
    }
}
