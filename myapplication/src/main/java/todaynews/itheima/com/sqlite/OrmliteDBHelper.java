package todaynews.itheima.com.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Lou on 2018/8/8.
 */

public class OrmliteDBHelper extends OrmLiteSqliteOpenHelper {
    public OrmliteDBHelper(Context context) {
        super(context, "demo1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao getDao(Class clazz){
        try {
            Dao dao = super.getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
