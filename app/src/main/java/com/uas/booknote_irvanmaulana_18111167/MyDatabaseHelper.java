package com.uas.booknote_irvanmaulana_18111167;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {
    //Deskripsi Database
    //Hak Akses bersifat Private
    private Context context;
    //Nama Database
    private static final String DATABASE_NAME = "BookNote.db";
    //Versi Database
    private static final int DATABASE_VERSION = 1;
    //Nama Tabel
    private static final String TABLE_NAME = "my_library";
    //kolom id
    private static final String COLUMN_ID = "_id";
    //kolom judul
    private static final String COLUMN_TITLE = "book_title";
    //kolom penulis / penerbit
    private static final String COLUMN_AUTHOR = "book_author";
    //kolom jumlah halaman
    private static final String COLUMN_PAGES = "book_pages";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //perintah query sql buat tabel dan kolom
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";
        //Menjalankan Query
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //drop data dari tabel
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Method Tambah Buku
    void addBook(String title, String author, int pages){
        //Hak tulis data ( akses )
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //Simpan konten data pada kolom
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        long result = db.insert(TABLE_NAME,null, cv);
        //pemberitahuan Toast saat gagal dan berhasil tambah data
        if(result == -1){
            //saat gagal, durasi toast sebentar
            Toast.makeText(context, "Gagal Menambahkan Data", Toast.LENGTH_SHORT).show();
        }else {
            //saat berhasil, durasi toast sebentar
            Toast.makeText(context, "Data Telah Ditambahkan", Toast.LENGTH_SHORT).show();
        }
    }
    //Baca Data
    Cursor readAllData(){
        //Ambil dari Kolom
        String query = "SELECT * FROM " + TABLE_NAME;
        //Hak Baca data (akses)
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //Method Update data di list
    void updateData(String row_id, String title, String author, String pages){
        //Baca & tulis ( akses )
        SQLiteDatabase db = this.getWritableDatabase();
        //isi
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        //hasil
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        //Toast saat gagal dan berhasil update data
        if(result == -1){
            //saat gagal, durasi sebentar
            Toast.makeText(context, "Gagal Update Data", Toast.LENGTH_SHORT).show();
        }else {
            //saat berhasil, durasi sebentar
            Toast.makeText(context, "Data Berhasil Di Update", Toast.LENGTH_SHORT).show();
        }
    }
    //Method Hapus (Hanya satu baris)
    void deleteOneRow(String row_id){
        //hak tulis (akses)
        SQLiteDatabase db = this.getWritableDatabase();
        //hasil
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        //Pemberitahuan Toast, saat gagal dan Berhasil hapus 1 data
        if(result == -1){
            //saat gagal hapus 1 data, durasi toast sebentar
            Toast.makeText(context, "Gagal Saat Menghapus Data", Toast.LENGTH_SHORT).show();
        }else{
            //saat berhasil hapus 1 data, durasi toast sebentar
            Toast.makeText(context, "Data Telah Dihapus", Toast.LENGTH_SHORT).show();
        }
    }
    //Method Hapus semua data
    void deleteAllData(){
        //Hak tulis ( akses )
        SQLiteDatabase db = this.getWritableDatabase();
        //query tujuan , tabel
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
