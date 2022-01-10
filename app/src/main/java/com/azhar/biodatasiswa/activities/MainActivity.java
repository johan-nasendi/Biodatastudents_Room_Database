package com.azhar.biodatasiswa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.biodatasiswa.R;
import com.azhar.biodatasiswa.adapter.KelasAdapter;
import com.azhar.biodatasiswa.database.SiswaDatabase;
import com.azhar.biodatasiswa.model.KelasModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @BindView(R.id.rvKelas)
    RecyclerView rvKelas;

    private SiswaDatabase siswaDatabase;
    private List<KelasModel> kelasModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Membuat object database
        siswaDatabase = SiswaDatabase.createDatabase(this);

        // Membuat membuat object List
        kelasModelList = new ArrayList<>();

        bottomNavigationView = findViewById(R.id.button_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_about:
                        startActivity(new Intent(getApplicationContext()
                                ,AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_info:
                        startActivity(new Intent(getApplicationContext()
                                ,InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_setting:
                        startActivity(new Intent(getApplicationContext()
                                ,SettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        ExtendedFloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahKelasActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Mengosongkan List agar dipastikan list dapat disi dengan data yg paling baru
        kelasModelList.clear();

        // Mengambil data dari Sqlite
        getData();

        // Mensetting dan proses menampilkan data ke RecyclerView
        rvKelas.setLayoutManager(new GridLayoutManager(this, 2));
        rvKelas.setAdapter(new KelasAdapter(this, kelasModelList));
    }

    private void getData() {

        // Operasi mengambil data dari database Sqlite
        kelasModelList = siswaDatabase.kelasDao().select();
    }

}
