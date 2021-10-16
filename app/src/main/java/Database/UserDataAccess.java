package Database;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import model.UserModel;

public class UserDataAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private String[] cls ={SqliteDB.ID , SqliteDB.NAME , SqliteDB.MOBILE};


    public UserDataAccess(Activity context) {
        openHelper=new SqliteDB(context);
    }

    public void openDB(){

        database=openHelper.getWritableDatabase();

    }

    public void closeDB(){

        database.close();
    }

    public boolean addNewUser(UserModel model){
        try {

            String name = model.getName();
            String mobile =model.getMobile();
            String addQuery ="insert into " + SqliteDB.table_user + " (" + SqliteDB.NAME + " , " + SqliteDB.MOBILE + " , " + ")" +
                    "values (" + "'" + name + "'" + " , " + "'" + mobile + "'" + " , ";

            database.execSQL(addQuery);

            return true;

        }catch (Exception e){

            return false;
        }
    }
    public List<UserModel> getallusers(){

        List<UserModel> models =new ArrayList<>();

        String getallquery = "select * from " + SqliteDB.table_user;

        Cursor cursor = database.rawQuery(getallquery, null);

        if (cursor.moveToFirst()){
            do {

                int id = cursor.getInt(cursor.getColumnIndex(SqliteDB.ID));
                String name = cursor.getString(cursor.getColumnIndex(SqliteDB.NAME));
                String mobile = cursor.getString(cursor.getColumnIndex(SqliteDB.MOBILE));

                models.add(new UserModel(id , name , mobile));

            }while (cursor.moveToNext());

        }

        return models;
    }
}
