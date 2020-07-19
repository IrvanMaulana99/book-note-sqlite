package com.uas.booknote_irvanmaulana_18111167;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    //Deklarasi
    EditText title_input, author_input, pages_input;
    Button update_button, delete_button;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //Menampilkan
        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //Panggil method get dan set data
        getAndSetIntentData();

        //Atur judul di Action Bar
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        //Tombol Button Update , saat di klik
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ambil Data
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                //Update Data
                myDB.updateData(id, title, author, pages);
            }
        });
        //button Hapus saat di klik
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //panggil method konfirmasi
                confirmDialog();
            }
        });

    }
    //Method Get & Set data
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){

            //Ambil Data dari Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Atur Data Intent
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
            Log.d("irvan", title+" "+author+" "+pages);
        }else{
            //Pemberitahuan Data Kosong oleh Toast, durasi toast sebentar
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
        }
    }
    //method konfirmasi
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Dialog
        builder.setTitle("Hapus " + title + " ?");
        builder.setMessage("Apakah Yakin Ingin Menghapus " + title + " ?");
        //Button Ya, di dialog
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                //Delete satu Baris
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}