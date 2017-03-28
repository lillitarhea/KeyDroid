package com.example.android.keydroid;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Lill_Rhea on 29/03/2017.
 */
public class Message {

    public static void message(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
