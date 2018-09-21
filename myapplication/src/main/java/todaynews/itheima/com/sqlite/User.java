package todaynews.itheima.com.sqlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Lou on 2018/8/8.
 */

@DatabaseTable(tableName = "t_user")
public class User {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField()
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
