package com.example.demoalarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnSetAlarm;
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //================Casting==================
        setContentView(R.layout.activity_main);
        btnSetAlarm = findViewById(R.id.btnAlarm);
        //================Casting==================

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----------------Setup-----------------
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);
                //-----------------Setup-----------------


                //Create a new PendingIntent and add it to the AlarmManager -Step 1
                Intent intent = new Intent(MainActivity.this, AlarmReceiverActivity.class);
                int reqCode = 12345;
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                // Get AlarmManager instance -Step 2
                am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);

                // Set the alarm -Step 3
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });

    }
}
