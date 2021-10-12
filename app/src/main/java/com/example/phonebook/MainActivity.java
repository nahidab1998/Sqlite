package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

//    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mtoolbar=findViewById(R.id.main_toolbar);
//        setSupportActionBar(mtoolbar);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        return true;
//
//    }
}