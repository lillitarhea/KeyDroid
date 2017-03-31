package com.example.android.keydroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PlayMusic extends AppCompatActivity {
Button button;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playmusic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id=getIntent().getIntExtra("user_id",0);

            }

    public void goNext(View view)
    {
        final Context context = this;
        Intent intent = new Intent(context, samrating.class);
        intent.putExtra("user_id",id);
        startActivity(intent);

    }
        }



