package com.example.englishforkids;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHander extends SQLiteOpenHelper {
    //Khai báo biến
    private Context dbContext;
    private SQLiteDatabase db;
    private static final String dbPath = "/data/data/com.example.englishforkids/databases/EnglishForKids.db ";
    private static final String dbName = "EnglishForKids.db";
    private static final int dbVersion = 1;

    //Phưowng thức khởi dựng
    public DatabaseHander(Context context) {
        super(context, dbName, null, dbVersion);
        // TODO Auto-generated constructor stub
        this.dbContext = context;
    }

    //Mở kết nối csdl
    public void openDB() {
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    //Ngắt kết nối
    public void closeDB() {
        db.close();
    }

    //Phương thức sao chép
    public void CopyDB() throws IOException {

        InputStream myInput = dbContext.getAssets().open("EnglishForKids.db");
        String outFileName = dbPath;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    //Phương thức 5: Copy CSDL sang đường dẫn SD Card
// Nếu CSDL SQLite đã có thì thôi
// Nếu CSDL chưa có thì copy sang
    public void copyDB2SDCard() {
        try {
            File file = new File(dbPath);
            if (file.exists()) {
                this.close();
            } else {
                try {
                    this.getReadableDatabase();
                    InputStream in = dbContext.getAssets().open(dbName);
                    OutputStream out = new FileOutputStream(dbPath);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception eio) {
            eio.printStackTrace();
        }

    }

    //Phương thức kiểm tra mã trùng
    public int GetCount(String sql) {

        //Bước 1
        openDB();
        //Bước 2
        Cursor cur = db.rawQuery(sql, null);
        int count = cur.getCount();
        //Bước 3
        closeDB();
        return count;
    }

    // Phương thức 7: Thực thi câu lệnh Insert, Update hay Delete
    public void ExecuteSQL(String sql) {
        //Bước 1: kết nối CSDL
        openDB();
        //Bước 2: Thực thi
        db.execSQL(sql);
        //Bước 3: Ngắt kết nối
        closeDB();
    }

    // Phương thức 8: Trả về một Cursor
    public Cursor getCursor(String sql) {
        openDB();
        return db.rawQuery(sql, null);
    }

    // Phương thức onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    }

    // Phương thức onUpgrade
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
