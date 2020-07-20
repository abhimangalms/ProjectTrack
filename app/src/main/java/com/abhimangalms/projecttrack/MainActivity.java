package com.abhimangalms.projecttrack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mAddProjectFab;
    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> project_name, project_id, project_created;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        mAddProjectFab = findViewById(R.id.addProjectFloatingActionButton);

        mAddProjectFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 1);

            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        project_name = new ArrayList<>();
        project_id = new ArrayList<>();
        project_created = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this, project_name, project_id, project_created);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        customAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            recreate();
        }

    }

    private void storeDataInArrays() {

        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No project available to show", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                project_id.add(cursor.getString(2));
                project_name.add(cursor.getString(1));
                project_created.add(cursor.getString(3));

            }
        }
    }
}