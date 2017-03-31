package com.example.android.keydroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Toast.makeText(this,"id is "+id,Toast.LENGTH_LONG).show();
        if (id<0)
        {
            Message.message(this,"Unsuccessful");
        }

        else
        {
            Message.message(this,"Successful");

        }

        Intent intent = new Intent(context, PlayMusic.class);
        intent.putExtra("user_id",id);
        startActivity(intent);

            }

        }



