package com.example.android.keydroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatabaseAdapter dbhelper;
    EditText userName,Age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=(EditText)findViewById(R.id.editUserName);
        Age=(EditText)findViewById(R.id.editAge);
        dbhelper=new DatabaseAdapter(this.getApplicationContext());
    }

    public void insertUser(View view) {

        final Context context = this;

        String user=userName.getText().toString();
        String age=Age.getText().toString();
        long id=DatabaseAdapter.insertData(user,age);
        if (id<0)
        {
            Message.message(this,"Unsuccessful");
        }

        else
        {
            Message.message(this,"Successful");

        }

        Intent intent = new Intent(context, PlayMusic.class);
        startActivity(intent);

            }

        }



