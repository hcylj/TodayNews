package todaynews.itheima.com.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrmliteMainActivity extends AppCompatActivity {

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
        Savepoint savepoint = null;
        AndroidDatabaseConnection connection = null;
        try {
        OrmliteDBHelper ormliteDBHelper = new OrmliteDBHelper(this);
        SQLiteDatabase db = ormliteDBHelper.getWritableDatabase();
        connection = new AndroidDatabaseConnection(db,true);
        Dao<User,Integer> dao = ormliteDBHelper.getDao(User.class);
             savepoint = connection.setSavePoint("start");
            connection.setAutoCommit(false);
            for(int i = 0;i<100;i++){
                if(i==10){
                    int j = 1/0;
                }
                User user = new User();
                user.setUsername("aaa"+i);

                try {
                    dao.create(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connection.commit(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }




    }

    private void query() {
        try {
        OrmliteDBHelper ormliteDBHelper = new OrmliteDBHelper(this);
        Dao<User,Integer> dao = ormliteDBHelper.getDao(User.class);
            List<User> users = dao.queryForAll();
            for(int i =0;i<users.size();i++){
                User user = users.get(i);
                Log.i("","user.getUsername()="+user.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void delete() {
        OrmliteDBHelper ormliteDBHelper = new OrmliteDBHelper(this);
        Dao<User,Integer> dao = ormliteDBHelper.getDao(User.class);
       for(int i = 0;i<100;i++){
           User user = new User();
           user.setUsername("aaa"+i);


           try {
               DeleteBuilder<User,Integer> builder = dao.deleteBuilder();
               dao.deleteBuilder().where().eq("username",user.getUsername());
                builder.delete();
           } catch (SQLException e) {
               e.printStackTrace();
           }

       }

    }

    private void insert() {
        OrmliteDBHelper ormliteDBHelper = new OrmliteDBHelper(this);
        Dao<User,Integer> dao = ormliteDBHelper.getDao(User.class);
    for(int i = 0;i<100;i++){
        User user = new User();
        user.setUsername("aaa"+i);
        try {
            dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    }

}
