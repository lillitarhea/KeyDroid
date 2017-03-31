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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samrating);
        id=getIntent().getIntExtra("user_id",0);
    }


    public void pleasure1(View view)
    {
       pleasure="1";

    }

    public void arousal1(View view)
    {
        arousal="1";

    }






    public void Next(View view)
    {
        final Context context = this;
        Intent intent = new Intent(context, com.example.android.keydroid.Keyboard.class);

        Bundle extras = new Bundle();
        extras.putString("PLEASURE",pleasure);
        extras.putString("AROUSAL",arousal);
        extras.putInt("USER_ID",id);
        intent.putExtras(extras);
        startActivity(intent);

    }


}
