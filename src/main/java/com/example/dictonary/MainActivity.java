package com.example.dictonary;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Sqliteclass sqliteclass;
    EditText word;
    Adapter adapter;
    List<Datalist>datalists;
    RecyclerView recyclerView;
    Cursor cursor;
    SearchView searchView;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sqliteclass=new Sqliteclass(MainActivity.this);
        searchView=findViewById(R.id.word);
        recyclerView=findViewById(R.id.recyclearview);
        bottomNavigationView=findViewById(R.id.bottomnav);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Window window = getWindow();
            if (systemBars.top > 50) { // উদাহরণস্বরূপ একটি শর্ত
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue_sky)); // Status Bar রঙ
                window.setNavigationBarColor(Color.WHITE); // Navigation Bar রঙ

            } else {
                window.setStatusBarColor(Color.RED); // Default রঙ
                window.setNavigationBarColor(Color.BLACK); // Default রঙ
            }



            return insets;
        });


        datalists = new ArrayList<>();

        adapter=new Adapter(datalists,MainActivity.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cursor = sqliteclass.searchdata(query);
                datalists.clear(); // পুরোনো ডেটা ক্লিয়ার করুন
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String word = cursor.getString(1);
                        String meaning = cursor.getString(2);
                        String pertsofspeech = cursor.getString(3);
                        String example = cursor.getString(4);
                        datalists.add(new Datalist(id, word, meaning, pertsofspeech, example));
                    }
                }

                adapter.notifyDataSetChanged(); // অ্যাডাপ্টারকে জানিয়ে দিন
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    datalists.clear(); // ডেটা খালি করুন
                    adapter.notifyDataSetChanged(); // অ্যাডাপ্টার আপডেট করুন
                    return true;
                }

                cursor = sqliteclass.searchdata(newText);

                datalists.clear(); // পুরোনো ডেটা ক্লিয়ার করুন
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String word = cursor.getString(1);
                        String meaning = cursor.getString(2);
                        String pertsofspeech = cursor.getString(3);
                        String example = cursor.getString(4);
                        datalists.add(new Datalist(id, word, meaning, pertsofspeech, example));
                    }
                }

                adapter.notifyDataSetChanged(); // অ্যাডাপ্টারকে জানিয়ে দিন
                return true;
            }
        });



     bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {

             if (item.getItemId()==R.id.text){
                 Intent intent=new Intent(MainActivity.this,MainActivity.class);
                 startActivity(intent);
             }else if (item.getItemId()==R.id.speaker){
                 Intent intent=new Intent(MainActivity.this,Activity_translate.class);
                 startActivity(intent);
             }

             return true;
         }
     });






    }
}