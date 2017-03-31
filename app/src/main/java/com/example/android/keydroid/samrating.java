package com.example.android.keydroid;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class samrating extends AppCompatActivity {
    String pleasure;
    String arousal;
    int id;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samrating);
        Intent intent = getIntent();
        id = intent.getIntExtra("user_id",-2);
        name = intent.getStringExtra("user_name");
    }


    public void pleasure1(View view)
    {
       pleasure="1";

    }
    public void pleasure2(View view)
    {
        pleasure="2";

    }
    public void pleasure3(View view)
    {
        pleasure="3";

    }
    public void pleasure4(View view)
    {
        pleasure="4";

    }
    public void pleasure5(View view)
    {
        pleasure="5";

    }

    public void arousal1(View view)
    {
        arousal="1";

    }

    public void arousal2(View view)
    {
        arousal="2";

    }

    public void arousal3(View view)
    {
        arousal="3";

    }

    public void arousal4(View view)
    {
        arousal="4";

    }

    public void arousal5(View view)
    {
        arousal="5";

    }






    public void Next(View view)
    {
        final Context context = this;
        Intent intent = new Intent(context, com.example.android.keydroid.Keyboard.class);

        Bundle extras = new Bundle();
        extras.putString("PLEASURE",pleasure);
        extras.putString("AROUSAL",arousal);
        extras.putInt("USER_ID", id);
        extras.putString("user_name", name);
        intent.putExtras(extras);
        startActivity(intent);

    }


}
