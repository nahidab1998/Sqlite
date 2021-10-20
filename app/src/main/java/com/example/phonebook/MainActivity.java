package com.example.phonebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonebook.Database.SqliteDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import com.example.phonebook.adapter.HomeAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    HomeAdapter homeAdapter;
    RecyclerView recyclerView;
    //    List<UserModel> models;
    ArrayList<Contact> arrayList;
    FloatingActionButton fab;
    Dialog dialog;
    ArrayList<String> arrayName;
    ArrayList<String> arrayPhone;
    MenuItem menuItem;
    SQLiteDatabase db;
    SqliteDB sqliteDB;
    SearchView searchView;
    Toolbar toolbar;
    ImageButton save, cancel;
    EditText editTextname, editTextphone;
    EditText inputLayout_Name;
    ImageButton imageButtondelet ,imageButtonedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        dialog = new Dialog(this);
        initviews();
        openDialog();
        editTextname = dialog.findViewById(R.id.add_edit_name);
        editTextphone = dialog.findViewById(R.id.add_edit_number);

        imageButtondelet=findViewById(R.id.delete_btn);
        imageButtonedit=findViewById(R.id.edit_btn);

//        imageButtondelet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Toast.makeText(MainActivity.this, "Deleted the course", Toast.LENGTH_SHORT).show();
//            }
//        });
//        imageButtonedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        save = dialog.findViewById(R.id.checkedd);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextname.getText().toString();
                String phone = editTextphone.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {

//                    save.setEnabled(false);
                    Toast.makeText(MainActivity.this, "تمامی فیلدها باید پر شود", Toast.LENGTH_LONG).show();

                } else {
                    SqliteDB dataBase = new SqliteDB(MainActivity.this);
                    dataBase.add_Contact(name, phone);
                    initviews();
                    Toast.makeText(MainActivity.this, name + " با موفقیت ثبت شد ", Toast.LENGTH_LONG).show();
                    editTextname.setText("");
                    editTextphone.setText("");
                }
                dialog.dismiss();
            }
        });

        cancel = dialog.findViewById(R.id.close);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean pos = sqliteDB.delet_item_cursor(5);
//                Toast.makeText(MainActivity.this, " faild in delete data", Toast.LENGTH_LONG).show();
                editTextname.setText("");
                editTextphone.setText("");
                dialog.dismiss();
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

//    private static final String[] Pernissions={
//            Manifest.permission.CAMERA
//    };
//    private static final int REQUEST_PERMISSIONS= 34 ;
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.N);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        menuItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private void initviews() {
        sqliteDB = new SqliteDB(MainActivity.this);
        arrayName = new ArrayList<>();
        arrayPhone = new ArrayList<>();
        get_all_data();
        recyclerView = findViewById(R.id.rcy_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter(sqliteDB.peaple(), R.drawable.user_pro, MainActivity.this, this, sqliteDB);
        recyclerView.setAdapter(homeAdapter);

    }

    public void openDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
//        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
//        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        inputLayout_Name = dialog.findViewById(R.id.add_edit_name);

    }

    private void get_all_data() {

        Cursor cursor = sqliteDB.show_all_data();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No date", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                arrayName.add(cursor.getString(1));
                arrayPhone.add(cursor.getString(2));
            }
        }
    }

    String deletedMovie = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            arrayList = new ArrayList<>();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    arrayList.remove(position);

                    Snackbar.make(recyclerView, deletedMovie, Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onClick(View v) {
                                    arrayName.add(position, deletedMovie);
                                    homeAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;

                case ItemTouchHelper.RIGHT:

                    break;
            }
        }


    };
}