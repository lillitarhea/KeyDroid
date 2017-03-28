package com.example.android.keydroid;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class samrating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samrating);
    }

    public void Next(View view)
    {
        final Context context = this;
        Intent intent = new Intent(context, com.example.android.keydroid.Keyboard.class);
        startActivity(intent);

    }
}
