package com.uas.booknote_irvanmaulana_18111167;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AwalActivity extends AppCompatActivity {
    //Deklarasi
    Button tombol_masuk, tombol_tentang;
    TextView SyaratKetentuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        //Menampilkan
        tombol_masuk = findViewById(R.id.tombol_masuk);
        tombol_tentang = findViewById(R.id.tombol_tentang);
        SyaratKetentuan = findViewById(R.id.SyaratKetentuan);
        //Saat Tombol ditekan
        tombol_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            }
        });
        tombol_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TentangActivity.class));
                finish();
            }
        });
        SyaratKetentuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SyaratKetentuanActivity.class));
                finish();
            }
        });

    }
}