package Database;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDB extends SQLiteOpenHelper {

    public static final String DB_name="my_sqlite";
    public static final int DB_version=1;


    public static final String table_user="my_user";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String MOBILE="mobile";
    public static final String PICTURE="picture";

    public SqliteDB(Activity context) {
        super(context,DB_name,null,DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE IF NOT EXISTS "+ table_user +" ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                NAME + " TEXT NOT NULL , " +
                MOBILE + " TEXT )";
        //ADD IMG

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS " + table_user;
        db.execSQL(query);

        onCreate(db);

    }
}
