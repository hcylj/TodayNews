package todaynews.itheima.com.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_insert)
    Button btnInsert;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.btn_insert_sql)
    Button btnInsertSql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_insert, R.id.btn_delete, R.id.btn_query, R.id.btn_insert_sql})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_insert_sql:
                insertTransaction();
                break;
        }
    }

    private void insertTransaction() {
        SQLiteDatabase db = null;
        try {
            DBHelper dbHelper = new DBHelper(this);
            long startTime = System.currentTimeMillis();
             db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            for (int i = 0; i < 1000; i++) {
               // if (i == 10) {
                //    int j = 1 / 0;
              //  }
                ContentValues contentValues = new ContentValues();
                contentValues.put("username", "aaa" + i);
                db.insert("t_test", null, contentValues);
            }
        db.setTransactionSuccessful();

            long endTime = System.currentTimeMillis();
            long dis = endTime-startTime;
            Log.i("","Transaction dis ="+dis);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
    }

    private void query() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("t_test", new String[]{"username"},
                null, null, null, null, null);
    while(cursor.moveToNext()){
            String username = cursor.getString(0);
            Log.i("","username = "+username);
        }
        cursor.close();
        db.close();


    }

    private void delete() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("t_test",null,null);

        db.close();


    }

    private void insert() {
    DBHelper dbHelper = new DBHelper(this);
        long startTime = System.currentTimeMillis();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for(int i = 0;i<1000;i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("username","aaa"+i);
            db.insert("t_test",null,contentValues);
        }

db.close();
        long endTime = System.currentTimeMillis();
        long dis = endTime-startTime;
        Log.i("","dis ="+dis);
    }
}
