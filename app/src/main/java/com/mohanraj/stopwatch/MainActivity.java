package com.mohanraj.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {



    private int second =0;

    private boolean running;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState != null){
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }

        runtimer();

    }

    private void runtimer() {

        final TextView timer = findViewById(R.id.timer_text_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = second / 3600;
                int minute = (second % 3600) / 60;
                int seconds = second % 60;

                String time = String.format(Locale.getDefault(), "%d : %02d : %02d ",hours,minute,seconds);

                timer.setText(time);

                if(running){

                    second++;

                }
                handler.postDelayed(this, 1000);

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasrunning){
            running =true;
        }

    }

    public void startbutton(View view) {
        running = true;
    }

    public void holdbutton(View view) {
        running = false;
    }

    public void resetbutton(View view) {
        running = false;
        second = 0;
    }
}