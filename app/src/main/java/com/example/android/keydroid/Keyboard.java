package com.example.android.keydroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class Keyboard extends AppCompatActivity implements View.OnTouchListener {
    DatabaseAdapter dbhelper;
    public Button mb1, mb2, mb3, mb4, mb5, mb6, mb7, mb8, mb9, mb0;
    TextView txtView;
    String display;
    long press;
    long release;
    long keyhold;
    long duration_sum = 0, latency, latency_sum = 0;


    Vector p = new Vector<Long>();
    Vector r = new Vector<Long>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        dbhelper = new DatabaseAdapter(this.getApplicationContext());

        mb1 = (Button) findViewById(R.id.one);
        mb2 = (Button) findViewById(R.id.two);
        mb3 = (Button) findViewById(R.id.three);
        mb4 = (Button) findViewById(R.id.four);
        mb5 = (Button) findViewById(R.id.five);
        mb6 = (Button) findViewById(R.id.six);
        mb7 = (Button) findViewById(R.id.seven);
        mb8 = (Button) findViewById(R.id.eight);
        mb9 = (Button) findViewById(R.id.nine);
        mb0 = (Button) findViewById(R.id.zero);
        txtView = (TextView) findViewById(R.id.textbox);


        mb1.setOnTouchListener(this);

        mb2.setOnTouchListener(this);
        mb3.setOnTouchListener(this);
        mb4.setOnTouchListener(this);
        mb5.setOnTouchListener(this);
        mb6.setOnTouchListener(this);
        mb7.setOnTouchListener(this);
        mb8.setOnTouchListener(this);
        mb9.setOnTouchListener(this);
        mb0.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Button b = (Button) v;
            String display = b.getText().toString();
            txtView.append(display);
            press = System.currentTimeMillis();
            if (!r.isEmpty()) {
                long prev_release = Long.valueOf(r.lastElement().toString());
                latency = press - prev_release;
            }
            p.addElement(press);
            //System.out.println("The keypress time is " + press);


        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            release = System.currentTimeMillis();
            keyhold = release - press;

            r.addElement(release);
            duration_sum = duration_sum + keyhold;
            latency_sum = latency_sum + latency;


        }

        System.out.println("Time duration: " + String.format("%.3f", duration_sum / 1000.0));
        System.out.println("Time latency: " + String.format("%.3f", latency_sum / 1000.0));

        //Toast.makeText(this,sum/1000.0+"",Toast.LENGTH_LONG).show();//store in db
        return true;


    }

    public void NextSample(View view) {
        final Context context = this;

        Bundle extras = getIntent().getExtras();
        String pleasure = extras.getString("PLEASURE");
        String arousal = extras.getString("AROUSAL");
        long user_id = extras.getLong("USER_ID");
        String name = extras.getString("user_name");
        String duration = Long.toString(duration_sum);
        String latency = Long.toString(latency_sum);
        Users u = DatabaseAdapter.insertSamples(user_id,latency, duration, pleasure, arousal);
        //Toast.makeText(this, "I'm here", Toast.LENGTH_SHORT).show();
        if (u == null) {
            Message.message(this, "Unsuccessful");
        } else {
            Toast.makeText(this, getFilesDir()+"", Toast.LENGTH_LONG).show();


            //String filename = "myfile.txt";
            String string = name + "," + u.getId() + "," + u.getDuration() + "," + u.getLatency() + "," + u.getValence() + "," + u.getArousal();
            FileOutputStream outputStream;

            /*try {

                outputStream = openFileOutput(filename, Context.MODE_APPEND);

                outputStream.write(string.getBytes());
                Toast.makeText(this, "123256789", Toast.LENGTH_LONG).show();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();

            }*/

            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File (root.getAbsolutePath() + "/lil");
            dir.mkdirs();
            File file = new File(dir, "Samples6.csv");
            try {
                FileOutputStream f = new FileOutputStream(file,true);
                PrintWriter pw = new PrintWriter(f);
                pw.println(string);
                pw.flush();

                pw.close();
                f.close();
                Toast.makeText(this, file.toString(), Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(context, PlayMusic.class);
            intent.putExtra("user_id",user_id);
            intent.putExtra("user_name",name);
            startActivity(intent);

        }


    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}




