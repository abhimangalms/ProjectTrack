package com.abhimangalms.projecttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;

public class AddProjectActivity extends AppCompatActivity {

    EditText mEditTextProjectName, mEditTextProjectId;
    Button mSaveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        mEditTextProjectName = findViewById(R.id.editTextProjectName);
        mEditTextProjectId = findViewById(R.id.editTextProjectId);
        mSaveButton = findViewById(R.id.saveButton);
        //getting system time
        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddProjectActivity.this);
                myDB.addProject(mEditTextProjectName.getText().toString().trim(),
                        mEditTextProjectId.getText().toString().trim(), currentDateTimeString);

                /** To store the PROJECT ADDED TIME, we need to get the system time at the
                 * time when we add the project and store it into the local db.*/

                /**getting system time */
                // String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());



            }
        });
    }
}