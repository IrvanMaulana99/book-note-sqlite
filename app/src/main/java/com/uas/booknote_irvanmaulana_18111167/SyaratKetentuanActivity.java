package com.uas.booknote_irvanmaulana_18111167;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SyaratKetentuanActivity extends AppCompatActivity {
    //Deklarasi
    Button tombol_kembali2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat_ketentuan);
        //Menampilkan
        tombol_kembali2 = findViewById(R.id.tombol_kembali2);
        //Ketika Tombol ditekan
        tombol_kembali2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AwalActivity.class));
                finish();
            }
        });
    }
}